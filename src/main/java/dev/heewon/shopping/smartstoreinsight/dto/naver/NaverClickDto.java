package dev.heewon.shopping.smartstoreinsight.dto.naver;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;

public class NaverClickDto {

    @Getter
    @Setter
    @ToString
    public static class Category {
        private String name;
        private ArrayList<String> param;

    }

    @Getter
    @Setter
    @ToString
    public static class DataLabRequest {
        private LocalDate startDate;
        private LocalDate endDate;
        private String timeUnit;
        private ArrayList<Category> category;
        private String gender;
        private String device;
        private ArrayList<String> ages;
    }
}