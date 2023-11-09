package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Review;
import via.sep4.sep4test.database.domain.DomainReview;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review toProto(DomainReview domainReview);

    DomainReview toEntity(Review review);
}
