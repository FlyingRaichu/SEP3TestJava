package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import jdk.jshell.spi.ExecutionControl;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Item;
import via.sep4.protobuf.Review;
import via.sep4.protobuf.ReviewServiceGrpc;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.domain.DomainReview;
import via.sep4.sep4test.database.domain.DomainUser;
import via.sep4.sep4test.database.repository.ReviewRepository;
import via.sep4.sep4test.database.repository.UserRepository;
import via.sep4.sep4test.mappers.ReviewMapper;

import java.util.List;

@GrpcService
public class ReviewService extends ReviewServiceGrpc.ReviewServiceImplBase {
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private final ReviewMapper mapper = ReviewMapper.INSTANCE;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void getReview(Int32Value request, StreamObserver<Review> responseObserver) {
        DomainReview domain = reviewRepository.findById(request.getValue()).orElseThrow(RuntimeException::new);

        Review proto = mapper.toProto(domain);

        responseObserver.onNext(proto);
        responseObserver.onCompleted();
    }

    @Override
    public void getReviewsByUser(StringValue request, StreamObserver<Review> responseObserver) {
        DomainUser domainUser = userRepository.findDomainUserByUsername(request.getValue());
        List<DomainReview> domainReviews = reviewRepository.findDomainReviewsByUserId(domainUser);

        for(DomainReview domainReview : domainReviews){
            Review proto = mapper.toProto(domainReview);
            responseObserver.onNext(proto);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getAllReviews(Empty request, StreamObserver<Review> responseObserver) {
       throw new RuntimeException("method is not implemented");
    }

    @Override
    public void addReview(Review request, StreamObserver<Empty> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }

    @Override
    public void updateReview(Review request, StreamObserver<Empty> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }

    @Override
    public void deleteReview(Review request, StreamObserver<Empty> responseObserver) {
        throw new RuntimeException("method is not implemented");
    }
}
