package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.FullName;
import via.sep4.protobuf.OrderItem;
import via.sep4.protobuf.OrderItemResponse;
import via.sep4.protobuf.OrderItemServiceGrpc;
import via.sep4.sep4test.database.domain.DomainFullName;
import via.sep4.sep4test.database.domain.DomainOrderItem;
import via.sep4.sep4test.database.repository.ItemRepository;
import via.sep4.sep4test.database.repository.OrderItemRepository;
import via.sep4.sep4test.mappers.OrderItemMapper;

import java.util.List;

@GrpcService
public class OrderItemService extends OrderItemServiceGrpc.OrderItemServiceImplBase {
    private OrderItemRepository orderItemRepository;
    private ItemRepository itemRepository;
    private final OrderItemMapper mapper = OrderItemMapper.INSTANCE;

    public OrderItemService(OrderItemRepository orderItemRepository, ItemRepository itemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void getOrderItem(Int32Value request, StreamObserver<OrderItem> responseObserver) {
        DomainOrderItem domain = orderItemRepository.findById(request.getValue()).orElseThrow(RuntimeException::new);

        OrderItem proto = mapper.toProto(domain);

        responseObserver.onNext(proto);
        responseObserver.onCompleted();
    }

    @Override
    public void addOrderItem(OrderItem request, StreamObserver<OrderItemResponse> responseObserver) {
        DomainOrderItem domain = mapper.toEntity(request);
        OrderItemResponse.Builder response = OrderItemResponse.newBuilder();

        if(orderItemRepository.findById(request.getItemId()).isEmpty()){
            orderItemRepository.save(domain);
            responseObserver.onNext(response.setResponse("Order Item added").build());
        }else {
            responseObserver.onNext(response.setResponse("Order Item with the id exists").build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getAllOrderItems(Empty request, StreamObserver<OrderItem> responseObserver) {
        List<DomainOrderItem> orderItems = orderItemRepository.findAll();

        for(DomainOrderItem domainOrderItem : orderItems){
            OrderItem protoOrderItem = mapper.toProto(domainOrderItem);
            responseObserver.onNext(protoOrderItem);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void updateOrderItem(OrderItem request, StreamObserver<Empty> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }

    @Override
    public void deleteOrderItem(OrderItem request, StreamObserver<Empty> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }
}
