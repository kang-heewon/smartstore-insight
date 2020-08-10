package dev.heewon.shopping.smartstoreinsight.dto.click;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

public class ClickDto {
    @Getter
    @Setter
    @ToString
    public static class CreateRequest {
        private LocalDate targetDate;
    }

    @Getter
    @Builder
    @ToString
    public static class ClickInfo {
        private final Long id;
        private final LocalDate date;
        private final Long value;
        private final String categoryName;
    }
}
