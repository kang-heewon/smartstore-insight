package dev.heewon.shopping.smartstoreinsight.services.dataLab;

import dev.heewon.shopping.smartstoreinsight.config.NaverDataLabConfig;
import dev.heewon.shopping.smartstoreinsight.domain.clicks.Click;
import dev.heewon.shopping.smartstoreinsight.domain.clicks.ClickRepository;
import dev.heewon.shopping.smartstoreinsight.dto.category.CategoryDto;
import dev.heewon.shopping.smartstoreinsight.dto.dataLab.NaverClickDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NaverDataLabServiceImpl implements NaverDataLabService {

    private final NaverDataLabConfig naverDataLabConfig;
    private final RestTemplate restTemplate;
    private final ClickRepository clickRepository;

    public void getData(NaverClickDto.CreateRequest request) {
        List<CategoryDto.Category> categoryList = CategoryDto.Category.makeCategoryList();
        categoryList.forEach(category -> {
            Optional<Click> click = clickRepository.findByDateAndCategoryName(request.getTargetDate(), category.getName());
            NaverClickDto.DataLabRequest requestBody = makeResponse(request, category);
            NaverClickDto.DataLabResponse dataLabResponse = crawlData(requestBody).getBody();
            List<NaverClickDto.Result> results = dataLabResponse.getResults();
            Click newClick = convert(click.orElse(new Click()), category, dataLabResponse.getStartDate(), results.get(2));
            clickRepository.save(newClick);
        });
    }

    private <T> ResponseEntity<NaverClickDto.DataLabResponse> crawlData(T request) {
        String clientId = naverDataLabConfig.getClientId();
        String clientSecret = naverDataLabConfig.getClientSecret();
        String apiUrl = naverDataLabConfig.getUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.set("Content-Type", "application/json");
        HttpEntity<T> entity = new HttpEntity<T>(request, headers);
        return restTemplate.postForEntity(apiUrl, entity, NaverClickDto.DataLabResponse.class);
    }

    private NaverClickDto.DataLabRequest makeResponse(NaverClickDto.CreateRequest request, CategoryDto.Category category) {
        NaverClickDto.DataLabRequest dataLabRequest = new NaverClickDto.DataLabRequest();
        dataLabRequest.setStartDate(request.getTargetDate().toString());
        dataLabRequest.setEndDate(request.getTargetDate().toString());
        dataLabRequest.setTimeUnit("date");
        dataLabRequest.setCategory(Arrays.asList(new CategoryDto.Category("패션의류", "50000000"), new CategoryDto.Category("패션잡화", "50000001"), category));
        dataLabRequest.setDevice("");
        dataLabRequest.setGender("");
        dataLabRequest.setAges(Collections.emptyList());
        return dataLabRequest;
    }

    private Click convert(Click click, CategoryDto.Category category, LocalDate localDate, NaverClickDto.Result result) {
        Long value = result.getData().get(0).getRatio();
        click.setDate(localDate);
        click.setCategoryName(category.getName());
        click.setValue(value);
        return click;
    }

}
