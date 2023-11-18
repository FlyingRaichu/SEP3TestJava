package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.*;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.domain.DomainOrder;
import via.sep4.sep4test.database.repository.*;
import via.sep4.sep4test.mappers.OrderMapper;

import java.util.List;

@GrpcService
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase {

    private OrderRepository orderRepository;
    private final OrderMapper mapper = OrderMapper.INSTANCE;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void getOrder(Int32Value request, StreamObserver<Order> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }

    @Override
    public void getOrdersByUser(Empty request, StreamObserver<Order> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }

    @Override
    public void getAllOrders(Empty request, StreamObserver<Order> responseObserver) {
        List<DomainOrder> domain = orderRepository.findAll();

        for (DomainOrder domainOrder : domain){
            Order proto = mapper.toProto(domainOrder);
            responseObserver.onNext(proto);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void addOrder(Order request, StreamObserver<OrderResponse> responseObserver) {
        DomainOrder domainOrder = mapper.toEntity(request);
        OrderResponse.Builder response = OrderResponse.newBuilder();

        if(orderRepository.findById(request.getId()).isEmpty()){
            orderRepository.save(domainOrder);
            responseObserver.onNext(response.setResponse("order added").build());
        }else{
            responseObserver.onNext(response.setResponse("Order already exists").build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void updateOrder(Order request, StreamObserver<Empty> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }

    @Override
    public void deleteOrder(Order request, StreamObserver<Empty> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }
}
