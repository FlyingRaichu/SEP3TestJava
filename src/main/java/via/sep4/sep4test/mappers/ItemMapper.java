package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Item;
import via.sep4.protobuf.Review;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.domain.DomainReview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(uses = {ReviewMapper.class})
public interface ItemMapper
{
  ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);


  // Method to convert from domain Item to protobuf Item
  Item toProto(DomainItem domainItem);
  default List<Review> domainReviewToReview(List<DomainReview> domain) {
    List<Review> protoReviews = new ArrayList<>();
    for(DomainReview dr : domain){
      protoReviews.add(Mappers.getMapper(ReviewMapper.class).toProto(dr));
    }
    return protoReviews;
  }

  // Method to convert from protobuf Item to domain Item
  DomainItem toEntity(Item item);
  default List<DomainReview> reviewToDomainReview(List<Review> entity) {
    List<DomainReview> domainReviews = new ArrayList<>();
    for(Review r : entity){
      domainReviews.add(Mappers.getMapper(ReviewMapper.class).toEntity(r));
    }
    return domainReviews;
  }
}
