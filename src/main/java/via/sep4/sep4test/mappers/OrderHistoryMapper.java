package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.OrderHistory;
import via.sep4.sep4test.database.domain.DomainOrderHistory;

@Mapper
public interface OrderHistoryMapper {
    OrderHistoryMapper INSTANCE = Mappers.getMapper(OrderHistoryMapper.class);

    OrderHistory toProto(DomainOrderHistory domainOrderHistory);

    DomainOrderHistory toEntity(OrderHistory orderHistory);
}
