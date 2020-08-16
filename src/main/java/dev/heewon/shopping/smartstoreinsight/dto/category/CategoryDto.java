package dev.heewon.shopping.smartstoreinsight.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CategoryDto {
    @Getter
    @Setter
    public static class Category {
        private String name;
        private List<String> param;

        public Category(String name, String param) {
            this.name = name;
            this.param = Collections.singletonList(param);
        }

        public static List<CategoryDto.Category> makeCategoryList() {
            return Arrays.asList(
                    new CategoryDto.Category("화장품/미용", "50000002"),
                    new CategoryDto.Category("디지털/가전", "50000003"),
                    new CategoryDto.Category("가구/인테리어", "50000004"),
                    new CategoryDto.Category("출산/육아", "50000005"),
                    new CategoryDto.Category("식품", "50000006"),
                    new CategoryDto.Category("스포츠/레저", "50000007"),
                    new CategoryDto.Category("생활/건강", "50000008"),
                    new CategoryDto.Category("여가/생활편의", "50000009"),
                    new CategoryDto.Category("면세점", "50000010")
            );
        }
    }


}
