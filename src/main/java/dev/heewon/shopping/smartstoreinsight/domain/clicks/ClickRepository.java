package dev.heewon.shopping.smartstoreinsight.domain.clicks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClickRepository extends JpaRepository<Click, Long> {
    Optional<Click> findByDate(LocalDate date);
}
