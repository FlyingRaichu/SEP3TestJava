package via.sep4.sep4test.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.domain.DomainReview;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<DomainReview, Integer> {
    List<DomainReview> findDomainReviewByItem(DomainItem domainItem);
    List<DomainReview> findDomainReviewsByItem(DomainItem id);
}
