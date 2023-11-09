package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Address;
import via.sep4.sep4test.database.domain.DomainAddress;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toProto(DomainAddress domainAddress);

    DomainAddress toEntity(Address address);

}
