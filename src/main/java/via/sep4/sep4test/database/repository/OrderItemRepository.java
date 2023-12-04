package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import via.sep4.sep4test.database.domain.DomainOrderItem;

public interface OrderItemRepository extends JpaRepository<DomainOrderItem, Integer>
{
}
