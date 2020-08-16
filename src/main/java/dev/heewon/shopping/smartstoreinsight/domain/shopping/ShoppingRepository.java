package dev.heewon.shopping.smartstoreinsight.domain.shopping;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
    List<Shopping> findByDate(LocalDate date);

}
