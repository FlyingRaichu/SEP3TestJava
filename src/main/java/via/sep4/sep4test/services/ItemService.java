package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Item;
import via.sep4.protobuf.ItemServiceGrpc;

import java.util.ArrayList;
import java.util.List;

@GrpcService public class ItemService
    extends ItemServiceGrpc.ItemServiceImplBase
{
  private List<Item> items = new ArrayList<>(List.of(
      Item.newBuilder().setId(1).setTitle("Red yarn").setDescription("Red yarn")
          .setPrice(10.10).build(),

      Item.newBuilder().setId(1).setTitle("Yellow yarn")
          .setDescription("Yellow yarn").setPrice(10.10).build(),

      Item.newBuilder().setId(1).setTitle("Green yarn")
          .setDescription("Green yarn").setPrice(10.10).build(),

      Item.newBuilder().setId(1).setTitle("Blue yarn")
          .setDescription("Blue yarn").setPrice(10.10).build()));

  @Override public void getItem(Int32Value request,
      StreamObserver<Item> responseObserver) {
    Item item = items.stream()
        .filter(findItem -> findItem.getId() == request.getValue()).findFirst()
        .orElseThrow();

    responseObserver.onNext(item);
    responseObserver.onCompleted();
  }

  @Override public void getAllItems(Empty request,
      StreamObserver<Item> responseObserver) {
    for (Item item : items) {
      responseObserver.onNext(item);
    }
    responseObserver.onCompleted();
  }

  @Override public void addItem(Item request,
      StreamObserver<Empty> responseObserver) {
    items.add(request);

    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }
}
