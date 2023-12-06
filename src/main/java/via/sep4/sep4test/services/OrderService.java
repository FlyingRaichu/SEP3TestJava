package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Order;
import via.sep4.protobuf.OrderItem;
import via.sep4.protobuf.OrderServiceGrpc;
import via.sep4.sep4test.database.domain.DomainOrder;
import via.sep4.sep4test.database.domain.DomainOrderItem;
import via.sep4.sep4test.database.repository.OrderItemRepository;
import via.sep4.sep4test.database.repository.OrderRepository;
import via.sep4.sep4test.mappers.OrderItemMapper;
import via.sep4.sep4test.mappers.OrderMapper;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class OrderService
        extends OrderServiceGrpc.OrderServiceImplBase {
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;

    public OrderService(OrderItemRepository orderItemRepository,
                        OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void getAllOrders(Empty request,
                             StreamObserver<Order> responseObserver) {
        List<DomainOrder> domainOrders = orderRepository.findAll();

        for (DomainOrder domainOrder : domainOrders) {
            List<DomainOrderItem> orderItems = orderItemRepository.findDomainOrderItemsByOrder(
                    domainOrder);
            List<OrderItem> orderItemProtoList = orderItemMapper.toProtoList(
                    orderItems);

            Order protoOrder = orderMapper.toProto(domainOrder);
            protoOrder = protoOrder.toBuilder().addAllItems(orderItemProtoList)
                    .build();
            responseObserver.onNext(protoOrder);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void addOrder(Order request,
                         StreamObserver<Empty> responseObserver) {
        DomainOrder domainOrder = orderMapper.toEntity(request);
        List<DomainOrderItem> orderItems = new ArrayList<>();
        for (OrderItem item : request.getItemsList()
        ) {
            DomainOrderItem domainOrderItem = orderItemMapper.toEntity(item);
            domainOrderItem.setOrder(orderRepository.getById(request.getId()));
            orderItems.add(domainOrderItem);
        }

        domainOrder.setOrderItems(orderItems);
        orderRepository.save(domainOrder);
        orderItemRepository.saveAll(domainOrder.getOrderItems());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateOrder(Order request,
                            StreamObserver<Empty> responseObserver) {
        Int32Value id = Int32Value.of(request.getId());
        DomainOrder orderToDelete = orderRepository.findById(id.getValue())
                .orElseThrow(RuntimeException::new);
        DomainOrder order = orderMapper.toEntity(request);
        order.setId(orderToDelete.getId());

        orderRepository.delete(orderToDelete);
        orderRepository.save(order);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteOrder(Order request,
                            StreamObserver<Empty> responseObserver) {
        DomainOrder domainOrder = orderMapper.toEntity(request);

        orderItemRepository.deleteAll(domainOrder.getOrderItems());
        orderRepository.delete(domainOrder);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void addItemToOrder(OrderItem request,
                               StreamObserver<Empty> responseObserver) {
        DomainOrderItem itemToSave = orderItemMapper.toEntity(request);
        itemToSave.setOrder(orderRepository.findById(request.getOrderId()).orElseThrow());
        itemToSave.setId(orderItemRepository.findAll().size()+1);
        orderItemRepository.save(itemToSave);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateItemInOrder(OrderItem request,
                                  StreamObserver<Empty> responseObserver) {
        Int32Value id = Int32Value.of(request.getId());
        DomainOrderItem itemToSave = orderItemMapper.toEntity(request);
        itemToSave.setOrder(orderRepository.findById(request.getOrderId()).orElseThrow());
        DomainOrderItem itemToDelete = orderItemRepository.findById(id.getValue()).orElseThrow();

        orderItemRepository.delete(itemToDelete);
        orderItemRepository.save(itemToSave);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteItemFromOrder(OrderItem request,
                                    StreamObserver<Empty> responseObserver) {
        DomainOrderItem domainOrderItem = orderItemMapper.toEntity(request);

        orderItemRepository.delete(domainOrderItem);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
