package dev.heewon.shopping.smartstoreinsight.dto.click;

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
}
