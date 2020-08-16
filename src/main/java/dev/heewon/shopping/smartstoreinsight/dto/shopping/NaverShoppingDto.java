package dev.heewon.shopping.smartstoreinsight.dto.shopping;

import dev.heewon.shopping.smartstoreinsight.dto.category.CategoryDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class NaverShoppingDto {
    @Getter
    @Setter
    @ToString
    public static class ShoppingRequest {
        private CategoryDto.Category targetCategory;
    }

}