package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Item;
import via.sep4.protobuf.ItemServiceGrpc;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.repository.ItemRepository;
import via.sep4.sep4test.mappers.ItemMapper;

import java.util.List;

@GrpcService public class ItemService
    extends ItemServiceGrpc.ItemServiceImplBase
{
  private ItemRepository itemRepository;
  private final ItemMapper mapper = ItemMapper.INSTANCE;


  public ItemService(ItemRepository itemRepository){
    this.itemRepository = itemRepository;
  }

  @Override public void getItem(Int32Value request,
      StreamObserver<Item> responseObserver) {
      DomainItem domainItem = itemRepository.findById(request.getValue()).orElseThrow(RuntimeException::new);

      Item protoItem = mapper.toProto(domainItem);

    responseObserver.onNext(protoItem);
    responseObserver.onCompleted();
  }

  @Override public void getAllItems(Empty request,
      StreamObserver<Item> responseObserver) {
    List<DomainItem> domainItems = itemRepository.findAll();

    for (DomainItem domainItem : domainItems){
      Item protoItem = mapper.toProto(domainItem);
      responseObserver.onNext(protoItem);
    }

    responseObserver.onCompleted();
  }

  @Override public void addItem(Item request,
      StreamObserver<Empty> responseObserver) {
    DomainItem domainItem = mapper.toEntity(request);

    itemRepository.save(domainItem);

    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }

  @Override public void updateItem(Item request,
      StreamObserver<Empty> responseObserver) {
    DomainItem domainItem = mapper.toEntity(request);

    itemRepository.deleteById(domainItem.getId());
    itemRepository.save(domainItem);

    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }

  @Override public void deleteItem(Item request,
      StreamObserver<Empty> responseObserver) {
    DomainItem domainItem = mapper.toEntity(request);

    itemRepository.delete(domainItem);

    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }
}
