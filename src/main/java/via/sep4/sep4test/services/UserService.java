package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.User;
import via.sep4.protobuf.UserServiceGrpc;

import java.util.List;

@GrpcService public class UserService
    extends UserServiceGrpc.UserServiceImplBase
{
  private static List<User> USERS = List.of(
      User.newBuilder().setUsername("GoshoGranatata")
          .setPassword("123123as").setEmail("GoshoGranatata@gmail.com")
          .build(),


      User.newBuilder().setUsername("MishoAvtomata")
          .setPassword("123123as").setEmail("Mishoavtomata@gmail.com")
          .build()

  );

  @Override public void getUser(StringValue request,
      StreamObserver<User> responseObserver) {
    User user = USERS.stream()
        .filter(findUser -> request.getValue().equals(findUser.getUsername())).findFirst()
        .orElseThrow();

    responseObserver.onNext(user);
    responseObserver.onCompleted();
  }

  @Override public void usernameContains(StringValue request,
      StreamObserver<User> responseObserver) {
    User user = USERS.stream()
        .filter(findUser -> findUser.getUsername().contains(request.getValue()))
        .findFirst()
        .orElseThrow();


    responseObserver.onNext(user);
    responseObserver.onCompleted();
  }

  @Override public void getAllUsers(Empty request,
      StreamObserver<User> responseObserver) {
    for (User user : USERS){
      responseObserver.onNext(user);
    }

    responseObserver.onCompleted();
  }
}
