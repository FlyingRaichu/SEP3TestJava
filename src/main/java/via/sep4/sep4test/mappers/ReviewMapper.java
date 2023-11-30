package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Review;
import via.sep4.sep4test.database.domain.DomainReview;

import java.util.List;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review toProto(DomainReview domainReview);

    DomainReview toEntity(Review review);

    List<Review> toProtoList(List<DomainReview> domainReviews);

    List<DomainReview> toEntityList(List<Review> reviews);
}
