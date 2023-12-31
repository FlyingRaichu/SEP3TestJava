package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Item;
import via.sep4.protobuf.ItemServiceGrpc;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.domain.DomainItemTag;
import via.sep4.sep4test.database.domain.DomainReview;
import via.sep4.sep4test.database.repository.ItemRepository;
import via.sep4.sep4test.database.repository.ItemTagRepository;
import via.sep4.sep4test.database.repository.ReviewRepository;
import via.sep4.sep4test.database.repository.TagRepository;
import via.sep4.sep4test.mappers.ItemMapper;
import java.util.List;

@GrpcService
public class ItemService
        extends ItemServiceGrpc.ItemServiceImplBase {
    private ItemRepository itemRepository;
    private TagRepository tagRepository;
    private ItemTagRepository itemTagRepository;
    private ReviewRepository reviewRepository;
    private final ItemMapper mapper = ItemMapper.INSTANCE;


    public ItemService(ItemRepository itemRepository, TagRepository tagRepository, ItemTagRepository itemTagRepository, ReviewRepository reviewRepository) {
        this.itemRepository = itemRepository;
        this.tagRepository = tagRepository;
        this.itemTagRepository = itemTagRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void getItem(Int32Value request,
                        StreamObserver<Item> responseObserver) {
        DomainItem domainItem = itemRepository.findById(request.getValue()).orElseThrow(RuntimeException::new);

        Item protoItem = mapper.toProto(domainItem);

        responseObserver.onNext(protoItem);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllItems(Empty request,
                            StreamObserver<Item> responseObserver) {
        List<DomainItem> domainItems = itemRepository.findAll();

        for (DomainItem domainItem : domainItems) {
            List<DomainItemTag> itemTags = itemTagRepository.findDomainItemTagsByItemId(domainItem.getId());
            List<Integer> tagIds = itemTags.stream()
                    .map(DomainItemTag::getTagId)
                    .toList();
            Item protoItem = mapper.toProto(domainItem);
            protoItem = protoItem.toBuilder().addAllTags(tagIds).build();
            responseObserver.onNext(protoItem);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void addItem(Item request,
                        StreamObserver<Empty> responseObserver) {
        DomainItem domainItem = mapper.toEntity(request);

        for (int i : request.getTagsList()) {
            itemTagRepository.save(new DomainItemTag(request.getId(), i));
        }

        itemRepository.save(domainItem);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateItem(Item request,
                           StreamObserver<Empty> responseObserver) {
        Int32Value id = Int32Value.of(request.getId());
        DomainItem domainItem = mapper.toEntity(request);
        DomainItem itemToDelete = itemRepository.findById(id.getValue()).orElseThrow(RuntimeException::new);
        List<DomainItemTag> domainItemTag = itemTagRepository.findDomainItemTagsByItemId(request.getId());
        List<DomainReview> reviews = reviewRepository.findDomainReviewsByItem(itemToDelete);

        reviewRepository.deleteAll(reviews);


        itemRepository.delete(itemToDelete);
        itemTagRepository.deleteAll(domainItemTag);

        for (int i : request.getTagsList()) {
            itemTagRepository.save(new DomainItemTag(request.getId(), i));
        }

        itemRepository.save(domainItem);
        reviewRepository.saveAll(reviews);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteItem(Item request,
                           StreamObserver<Empty> responseObserver) {
        DomainItem domainItem = mapper.toEntity(request);

        itemRepository.delete(domainItem);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
