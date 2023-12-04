package via.sep4.sep4test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Favorite;
import via.sep4.sep4test.database.domain.DomainFavorite;

@Mapper
public interface FavoriteMapper {
    FavoriteMapper INSTANCE = Mappers.getMapper(FavoriteMapper.class);

    Favorite toProto(DomainFavorite domainFavorite);
    DomainFavorite toEntity(Favorite favorite);
}
