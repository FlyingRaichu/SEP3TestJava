package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.protobuf.Favorite;
import via.sep4.sep4test.database.domain.DomainFavorite;

import java.util.Map;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    DomainFavorite findByAllFields(int userId, int itemId);
}
