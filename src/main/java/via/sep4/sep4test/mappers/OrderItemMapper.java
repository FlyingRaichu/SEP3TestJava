package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.OrderItem;
import via.sep4.sep4test.database.domain.DomainOrderItem;

import java.util.List;

@Mapper(uses = {OrderMapper.class})
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItem toProto(DomainOrderItem domainOrderItem);

    DomainOrderItem toEntity(OrderItem orderItem);

    List<OrderItem> toProtoList(List<DomainOrderItem> domainOrderItems);
    List<DomainOrderItem> toEntityList(List<OrderItem> orderItems);
}
