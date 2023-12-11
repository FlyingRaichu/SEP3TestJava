package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import com.google.protobuf.Int32Value;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.DiscountCode;
import via.sep4.protobuf.DiscountCodeServiceGrpc;
import via.sep4.protobuf.Item;
import via.sep4.sep4test.database.domain.DomainDiscountCode;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.repository.DiscountCodeRepository;
import via.sep4.sep4test.mappers.DiscountCodeMapper;

import java.util.List;

@GrpcService
public class DiscountCodeService extends DiscountCodeServiceGrpc.DiscountCodeServiceImplBase{

  private final DiscountCodeRepository discountCodeRepository;
  private final DiscountCodeMapper mapper = DiscountCodeMapper.INSTANCE;

  public DiscountCodeService(DiscountCodeRepository discountCodeRepository) {
    this.discountCodeRepository = discountCodeRepository;
  }

  @Override public void getDiscountCode(Int32Value request, StreamObserver<DiscountCode> responseObserver) {
    DomainDiscountCode domainDiscountCode = discountCodeRepository.findById(
        request.getValue()).orElseThrow(RuntimeException::new);

    DiscountCode protoDiscountCode = mapper.toProto(domainDiscountCode);

    responseObserver.onNext(protoDiscountCode);
    responseObserver.onCompleted();
  }

  @Override public void getAllDiscountCodes(Empty request, StreamObserver<DiscountCode> responseObserver) {
    List<DomainDiscountCode> domainDiscountCodes = discountCodeRepository.findAll();

    for (DomainDiscountCode domainDiscountCode : domainDiscountCodes)
    {
      DiscountCode protoDiscountCode = mapper.toProto(domainDiscountCode);
      protoDiscountCode.toBuilder().build();
      responseObserver.onNext(protoDiscountCode);
    }

    responseObserver.onCompleted();
  }
  @Override
  public void addDiscountCode(DiscountCode request,
      StreamObserver<Empty> responseObserver) {
    DomainDiscountCode domainDiscountCode = mapper.toEntity(request);

    discountCodeRepository.save(domainDiscountCode);
    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }
  @Override
  public void deleteDiscountCode(DiscountCode request, StreamObserver<Empty> responseObserver) {
    DomainDiscountCode domainDiscountCode = mapper.toEntity(request);

    discountCodeRepository.delete(domainDiscountCode);

    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }


}
