package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Item;
import via.sep4.sep4test.database.domain.DomainItem;

@Mapper public interface ItemMapper
{
  ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

  // Method to convert from domain Item to protobuf Item
//  @Mapping(target = "tags", ignore = true)
  Item toProto(DomainItem domainItem);

  // Method to convert from protobuf Item to domain Item
  DomainItem toEntity(Item item);
}
