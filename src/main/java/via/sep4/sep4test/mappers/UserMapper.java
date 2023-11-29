package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.User;
import via.sep4.sep4test.database.domain.DomainUser;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toProto(DomainUser domainUser);

    DomainUser toEntity(User user);
}
