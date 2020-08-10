package dev.heewon.shopping.smartstoreinsight.domain.clicks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClickRepository extends JpaRepository<Click, Long> {
    List<Click> findByDate(LocalDate date);

    @Nullable
    Optional<Click> findByDateAndCategoryName(LocalDate date, String categoryName);

}
