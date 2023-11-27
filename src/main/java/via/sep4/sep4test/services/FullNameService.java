package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Address;
import via.sep4.protobuf.FullName;
import via.sep4.protobuf.FullNameResponse;
import via.sep4.protobuf.FullNameServiceGrpc;
import via.sep4.sep4test.database.domain.DomainAddress;
import via.sep4.sep4test.database.domain.DomainFullName;
import via.sep4.sep4test.database.repository.AddressRepository;
import via.sep4.sep4test.database.repository.FullNameRepository;
import via.sep4.sep4test.mappers.AddressMapper;
import via.sep4.sep4test.mappers.FullNameMapper;

import java.util.List;

@GrpcService
public class FullNameService extends FullNameServiceGrpc.FullNameServiceImplBase {
    private FullNameRepository fullNameRepository;
    private final FullNameMapper mapper = FullNameMapper.INSTANCE;

    public FullNameService(FullNameRepository fullNameRepository) {
        this.fullNameRepository = fullNameRepository;
    }

//    @Override
//    public void getFullName(Int32Value request, StreamObserver<FullName> responseObserver) {
//        //todo needs to update when requirement comes
//        DomainFullName domain = fullNameRepository.findById(request.getValue()).orElseThrow(RuntimeException::new);
//
//        FullName proto = mapper.toProto(domain);
//
//        responseObserver.onNext(proto);
//        responseObserver.onCompleted();
//    }

    @Override
    public void addFullName(FullName fullName, StreamObserver<FullNameResponse> responseObserver){
        DomainFullName domain = mapper.toEntity(fullName);
        FullNameResponse.Builder response = FullNameResponse.newBuilder();

        if(fullNameRepository.findById(fullName.getId()).isEmpty()){
            fullNameRepository.save(domain);
            responseObserver.onNext(response.setResponse("Full Name added").build());
        }else{
            responseObserver.onNext(response.setResponse("Name with the id already exists").build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getAllFullNames(Empty request, StreamObserver<FullName> responseObserver) {
        List<DomainFullName> domainFullNames = fullNameRepository.findAll();

        for(DomainFullName domainFullName : domainFullNames){
            FullName protoFullName = mapper.toProto(domainFullName);
            responseObserver.onNext(protoFullName);
        }

        responseObserver.onCompleted();
    }
//    @Override
//    public void updateFullName(FullName fullName, StreamObserver<FullNameResponse> responseObserver){
//        //todo needs to update when requirement comes
//        DomainFullName domain = mapper.toEntity(fullName);
//
//
//        fullNameRepository.deleteById(domain.getId());
//        fullNameRepository.save(domain);
//
//        responseObserver.onNext(Empty.newBuilder().build());
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void deleteFullName(FullName fullName, StreamObserver<Empty> responseObserver){
//        //todo needs to update when requirement comes
//        DomainFullName domain = mapper.toEntity(fullName);
//
//        fullNameRepository.delete(domain);
//
//        responseObserver.onNext(Empty.newBuilder().build());
//        responseObserver.onCompleted();
//    }


}
