package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.User;
import via.sep4.protobuf.UserServiceGrpc;
import via.sep4.sep4test.database.domain.DomainUser;
import via.sep4.sep4test.database.repository.UserRepository;
import via.sep4.sep4test.mappers.UserMapper;

import java.util.List;

@GrpcService public class UserService
    extends UserServiceGrpc.UserServiceImplBase
{
  private UserRepository userRepository;
  private final UserMapper mapper = UserMapper.INSTANCE;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override public void getUser(Int32Value request,
                                StreamObserver<User> responseObserver) {
    DomainUser domainUser = userRepository.findById(request.getValue()).orElseThrow(RuntimeException::new);

    User user = mapper.toProto(domainUser);

    responseObserver.onNext(user);
    responseObserver.onCompleted();
  }

  @Override public void usernameContains(StringValue request,
      StreamObserver<User> responseObserver) {
//    User user = USERS.stream()
//        .filter(findUser -> findUser.getUsername().contains(request.getValue()))
//        .findFirst()
//        .orElseThrow();
//
//
//    responseObserver.onNext(user);
//    responseObserver.onCompleted();
  }

  @Override public void getAllUsers(Empty request,
      StreamObserver<User> responseObserver) {
    List<DomainUser> domainUsers = userRepository.findAll();

    for (DomainUser domainUser : domainUsers){
      User user = mapper.toProto(domainUser);
      responseObserver.onNext(user);
    }

    responseObserver.onCompleted();
  }

  @Override
  public void addUser(User request, StreamObserver<Empty> responseObserver) {
    DomainUser domainUser = mapper.toEntity(request);

    userRepository.save(domainUser);

    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }
}
