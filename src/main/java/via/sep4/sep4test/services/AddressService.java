package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Address;
import via.sep4.protobuf.AddressResponse;
import via.sep4.protobuf.AddressServiceGrpc;
import via.sep4.sep4test.database.domain.DomainAddress;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.repository.AddressRepository;
import via.sep4.sep4test.mappers.AddressMapper;

@GrpcService
public class AddressService extends AddressServiceGrpc.AddressServiceImplBase {
    private AddressRepository addressRepository;
    private final AddressMapper mapper = AddressMapper.INSTANCE;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

//    @Override
//    public void getAddress(Int32Value request, StreamObserver<Address> responseObserver) {
//        //todo needs to update when requirement comes
//        DomainAddress domainAddress = addressRepository.findById(request.getValue()).orElseThrow(RuntimeException::new);
//
//        Address protoAddress = mapper.toProto(domainAddress);
//
//        responseObserver.onNext(protoAddress);
//        responseObserver.onCompleted();
//    }

    @Override
    public void addAddress(Address address, StreamObserver<AddressResponse> responseObserver){
        DomainAddress domainAddress = mapper.toEntity(address);
        AddressResponse.Builder response = AddressResponse.newBuilder();

        //todo Q, how to better compare addresses? Bc having and id removes redundancy when it becomes a foreign key, but there's potential for duplicate values
        if(addressRepository.findById(address.getId()).isEmpty()){
            addressRepository.save(domainAddress);
            responseObserver.onNext(response.setResponse("Address added").build());
        }else {
            responseObserver.onNext(response.setResponse("Address already exists").build());
        }
        responseObserver.onCompleted();
    }

//    @Override
//    public void updateAddress(Address address, StreamObserver<AddressResponse> responseObserver){
//        //todo needs to update when requirement comes
//        DomainAddress domainAddress = mapper.toEntity(address);
//
//        addressRepository.deleteById(domainAddress.getId());
//        addressRepository.save(domainAddress);
//
//        responseObserver.onNext(AddressResponse.newBuilder().build());
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void deleteAddress(Address address, StreamObserver<AddressResponse> responseObserver){
//        //todo needs to update when requirement comes
//        DomainAddress domainAddress = mapper.toEntity(address);
//
//        addressRepository.delete(domainAddress);
//
//        responseObserver.onNext(AddressResponse.newBuilder().build());
//        responseObserver.onCompleted();
//    }
}
