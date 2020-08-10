package dev.heewon.shopping.smartstoreinsight.dto.naver;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class NaverClickDto {
    @Getter
    @Setter
    @ToString
    public static class CreateRequest {
        private LocalDate targetDate;
    }

    @Getter
    @Setter
    @ToString
    public static class Category {
        private String name;
        private List<String> param;

        public Category(String name, String param) {
            this.name = name;
            this.param = Collections.singletonList(param);
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ClickValue {
        private LocalDate period;
        private Long ratio;
    }

    @Getter
    @Setter
    @ToString
    public static class Result {
        private String title;
        private List<String> category;
        private List<ClickValue> data;
    }

    @Getter
    @Setter
    @ToString
    public static class DataLabRequest {
        private String startDate;
        private String endDate;
        private String timeUnit;
        private List<Category> category;
        private String gender;
        private String device;
        private List<String> ages;
    }

    @Getter
    @Setter
    @ToString
    public static class DataLabResponse {
        private LocalDate startDate;
        private LocalDate endDate;
        private String timeUnit;
        private List<Result> results;
    }


}