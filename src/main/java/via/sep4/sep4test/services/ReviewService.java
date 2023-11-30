package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.IntListRequest;
import via.sep4.protobuf.Review;
import via.sep4.protobuf.ReviewServiceGrpc;
import via.sep4.sep4test.database.domain.DomainReview;
import via.sep4.sep4test.database.repository.ReviewRepository;
import via.sep4.sep4test.mappers.ReviewMapper;

import java.util.List;

@GrpcService
public class ReviewService extends ReviewServiceGrpc.ReviewServiceImplBase {
    private ReviewRepository reviewRepository;
    private ReviewMapper mapper = ReviewMapper.INSTANCE;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void getReview(Int32Value request, StreamObserver<Review> responseObserver) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void getAllReviews(Empty request, StreamObserver<Review> responseObserver) {
        List<Review> reviews = mapper.toProtoList(reviewRepository.findAll());

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
    public void getAllWithId(IntListRequest request, StreamObserver<Review> responseObserver) {
        List<Integer> ids = request.getValuesList();
        List<Review> reviews = mapper.toProtoList(reviewRepository.findAllById(ids));

        reviews.forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
