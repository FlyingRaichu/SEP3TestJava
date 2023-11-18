package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.OrderItem;
import via.sep4.sep4test.database.domain.DomainOrderItem;

@Mapper(uses = {ItemMapper.class})
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItem toProto(DomainOrderItem domainOrderItem);

    DomainOrderItem toEntity(OrderItem orderItem);
}
