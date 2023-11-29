package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.sep4test.database.domain.DomainItem;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<DomainItem, Integer>
{
    List<DomainItem> findAllByIdIn(List<Integer> itemIds);
}
