package dev.heewon.shopping.smartstoreinsight.services.shopping;

import dev.heewon.shopping.smartstoreinsight.config.NaverShoppingConfig;
import dev.heewon.shopping.smartstoreinsight.domain.shopping.Shopping;
import dev.heewon.shopping.smartstoreinsight.domain.shopping.ShoppingRepository;
import dev.heewon.shopping.smartstoreinsight.dto.category.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NaverShoppingServiceImpl implements NaverShoppingService {
    private final NaverShoppingConfig naverShoppingConfig;
    private final ShoppingRepository shoppingRepository;
    private final RestTemplate restTemplate;

    @Override
    public void getData() {
        List<CategoryDto.Category> categoryList = CategoryDto.Category.makeCategoryList();
        categoryList.forEach(this::crawlData);
    }

    private void crawlData(CategoryDto.Category category) {
        String apiUrl = naverShoppingConfig.getUrl();
        String categoryId = category.getParam().get(0);
        ResponseEntity<String> html = restTemplate.getForEntity(apiUrl + "/search/category?catId=" + categoryId, String.class);
        String count = parseHTML(html.getBody()).replace(",", "");
        Shopping shopping = convert(category.getName(), Long.parseLong(count));
        shoppingRepository.save(shopping);
    }

    private String parseHTML(String body) {
        Document document = Jsoup.parse(body);
        Elements select = document.select("#__next > div > div.container > div > div > div > div.seller_filter_area > ul > li:nth-child(1) > button > span");
        return select.text();
    }

    private Shopping convert(String categoryName, Long count) {
        Shopping shopping = new Shopping();
        shopping.setCategoryName(categoryName);
        shopping.setCount(count);
        shopping.setDate(LocalDate.now());
        return shopping;
    }


}
