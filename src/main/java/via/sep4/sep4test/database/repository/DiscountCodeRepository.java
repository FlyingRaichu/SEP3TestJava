package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.sep4test.database.domain.DomainDiscountCode;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DomainDiscountCode, Integer> {
  // Add custom query methods as needed

}

