package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.sep4test.database.domain.DomainFavorite;

@Repository
public interface FavoriteRepository extends JpaRepository<DomainFavorite, Integer> {
    DomainFavorite findByUserIdAndItemId(int userId, int itemId);
}
