package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Order;
import via.sep4.protobuf.OrderItem;
import via.sep4.protobuf.OrderServiceGrpc;
import via.sep4.sep4test.database.repository.OrderItemRepository;
import via.sep4.sep4test.database.repository.OrderRepository;
import via.sep4.sep4test.mappers.OrderMapper;

@GrpcService
public class OrderService extends
        OrderServiceGrpc.OrderServiceImplBase {
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private final OrderMapper mapper = OrderMapper.INSTANCE;

    public OrderService(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void getAllOrders(Empty request, StreamObserver<Order> responseObserver) {
        super.getAllOrders(request, responseObserver);
    }

    @Override
    public void addOrder(Order request, StreamObserver<Empty> responseObserver) {
        super.addOrder(request, responseObserver);
    }

    @Override
    public void updateOrder(Order request, StreamObserver<Empty> responseObserver) {
        super.updateOrder(request, responseObserver);
    }

    @Override
    public void deleteOrder(Order request, StreamObserver<Empty> responseObserver) {
        super.deleteOrder(request, responseObserver);
    }

    @Override
    public void addItemToOrder(OrderItem request, StreamObserver<Empty> responseObserver) {
        super.addItemToOrder(request, responseObserver);
    }

    @Override
    public void updateItemInOrder(OrderItem request, StreamObserver<Empty> responseObserver) {
        super.updateItemInOrder(request, responseObserver);
    }

    @Override
    public void deleteItemFromOrder(OrderItem request, StreamObserver<Empty> responseObserver) {
        super.deleteItemFromOrder(request, responseObserver);
    }
}
