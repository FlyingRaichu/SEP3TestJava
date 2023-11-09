package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.FullName;
import via.sep4.sep4test.database.domain.DomainFullName;

@Mapper
public interface FullNameMapper {
    FullNameMapper INSTANCE = Mappers.getMapper(FullNameMapper.class);

    FullName toProto(DomainFullName domainFullName);

    DomainFullName toEntity(FullName fullName);
}
