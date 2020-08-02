package dev.heewon.shopping.smartstoreinsight.dto.click;

import dev.heewon.shopping.smartstoreinsight.dto.naver.NaverClickDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClickDto {
    @Getter
    @Setter
    @ToString
    public static class CreateRequest {
        private LocalDate startDate;
        private LocalDate endDate;
        private String timeUnit;
        private ArrayList<NaverClickDto.Category> category;
    }
}
