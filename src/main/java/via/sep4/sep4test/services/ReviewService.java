package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Item;
import via.sep4.protobuf.Review;
import via.sep4.protobuf.ReviewServiceGrpc;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.domain.DomainReview;
import via.sep4.sep4test.database.repository.ItemRepository;
import via.sep4.sep4test.database.repository.ReviewRepository;
import via.sep4.sep4test.mappers.ReviewMapper;

import java.util.List;
import java.util.Optional;

@GrpcService
public class ReviewService extends ReviewServiceGrpc.ReviewServiceImplBase {
    private ReviewRepository reviewRepository;
    private ItemRepository itemRepository;
    private ReviewMapper mapper = ReviewMapper.INSTANCE;

    public ReviewService(ReviewRepository reviewRepository, ItemRepository itemRepository) {
        this.reviewRepository = reviewRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void getReview(Int32Value request, StreamObserver<Review> responseObserver) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void getAllReviews(Empty request, StreamObserver<Review> responseObserver) {
        List<DomainReview> domainReviews = reviewRepository.findAll();
        List<Review> reviews = mapper.toProtoList(domainReviews);

        reviews.forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void addReview(Review request, StreamObserver<Empty> responseObserver) {
        DomainReview domainReview = mapper.toEntity(request);

        reviewRepository.save(domainReview);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteReview(Review request, StreamObserver<Empty> responseObserver) {
        DomainReview domainReview = mapper.toEntity(request);

        reviewRepository.delete(domainReview);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllReviewsByItemId(Int32Value request, StreamObserver<Review> responseObserver) {
        Optional<DomainItem> item = itemRepository.findById(request.getValue());
        List<DomainReview> domainReviews = reviewRepository.findDomainReviewByItem(item.get());
        List<Review> reviews = mapper.toProtoList(domainReviews);

        reviews.forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
