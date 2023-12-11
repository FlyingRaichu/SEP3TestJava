package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.DiscountCode;
import via.sep4.sep4test.database.domain.DomainDiscountCode;

@Mapper
public interface DiscountCodeMapper {
  DiscountCodeMapper INSTANCE = Mappers.getMapper(DiscountCodeMapper.class);

  DiscountCode toProto(DomainDiscountCode domainDiscountCode);
  DomainDiscountCode toEntity(DiscountCode discountCode);
}