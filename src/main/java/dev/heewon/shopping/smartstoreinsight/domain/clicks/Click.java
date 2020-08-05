package dev.heewon.shopping.smartstoreinsight.domain.clicks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
public class Click extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Long value;
    private String categoryName;

    public static Click of(LocalDate localDate, String categoryName, Long value) {
        Click click = new Click();
        click.date = localDate;
        click.value = value;
        click.categoryName = categoryName;
        return click;
    }
}
