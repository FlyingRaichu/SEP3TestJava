package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.sep4test.database.domain.DomainUser;

@Repository
public interface UserRepository extends JpaRepository<DomainUser, Integer> {
}
