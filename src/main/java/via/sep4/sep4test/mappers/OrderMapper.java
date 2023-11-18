package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Order;
import via.sep4.sep4test.database.domain.DomainOrder;

@Mapper(uses = {OrderItemMapper.class, AddressMapper.class, FullNameMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toProto(DomainOrder domainOrder);

    DomainOrder toEntity(Order order);
}
