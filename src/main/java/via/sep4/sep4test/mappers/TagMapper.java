package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Tag;
import via.sep4.sep4test.database.domain.DomainTag;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    Tag toProto(DomainTag domainTag);

    DomainTag toEntity(Tag tag);

    List<Tag> toProtoList(List<DomainTag> domainTags);

    List<DomainTag> toEntityList(List<Tag> tags);
}