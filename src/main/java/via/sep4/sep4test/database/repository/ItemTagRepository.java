package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.sep4test.database.domain.DomainItemTag;

import java.util.List;

@Repository
public interface ItemTagRepository extends JpaRepository<DomainItemTag, Integer> {
    List<DomainItemTag> findDomainItemTagsByItemId(int id);
}
