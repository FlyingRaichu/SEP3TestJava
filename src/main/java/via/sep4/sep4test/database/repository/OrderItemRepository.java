package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import via.sep4.sep4test.database.domain.DomainOrder;
import via.sep4.sep4test.database.domain.DomainOrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<DomainOrderItem, Integer>
{
  List<DomainOrderItem> findDomainOrderItemsByOrder(DomainOrder order);
}
