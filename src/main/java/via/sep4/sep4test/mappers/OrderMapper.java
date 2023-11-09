package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Order;
import via.sep4.sep4test.database.domain.DomainOrder;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toProto(DomainOrder domainOrder);

    DomainOrder toEntity(Order order);
}
