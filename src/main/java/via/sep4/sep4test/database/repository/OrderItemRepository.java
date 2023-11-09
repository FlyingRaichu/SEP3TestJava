package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.sep4test.database.domain.DomainOrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<DomainOrderItem, Integer> {
}
