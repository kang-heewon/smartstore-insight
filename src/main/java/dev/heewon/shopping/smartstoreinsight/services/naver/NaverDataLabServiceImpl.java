package dev.heewon.shopping.smartstoreinsight.services.naver;

import dev.heewon.shopping.smartstoreinsight.config.NaverDataLabConfig;
import dev.heewon.shopping.smartstoreinsight.domain.clicks.Click;
import dev.heewon.shopping.smartstoreinsight.domain.clicks.ClickRepository;
import dev.heewon.shopping.smartstoreinsight.dto.naver.NaverClickDto;
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

    public String getData(NaverClickDto.CreateRequest request) {
        List<NaverClickDto.Category> categoryList = makeCategoryList();
        categoryList.forEach(category -> {
            Optional<Click> click = clickRepository.findByDateAndCategoryName(request.getTargetDate(), category.getName());
            NaverClickDto.DataLabRequest requestBody = makeResponse(request, category);
            NaverClickDto.DataLabResponse dataLabResponse = crawlData(requestBody).getBody();
            List<NaverClickDto.Result> results = dataLabResponse.getResults();
            Click newClick = convert(click.orElse(new Click()), category, dataLabResponse.getStartDate(), results.get(2));
            clickRepository.save(newClick);
        });
        return "hello";
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

    private NaverClickDto.DataLabRequest makeResponse(NaverClickDto.CreateRequest request, NaverClickDto.Category category) {
        NaverClickDto.DataLabRequest dataLabRequest = new NaverClickDto.DataLabRequest();
        dataLabRequest.setStartDate(request.getTargetDate().toString());
        dataLabRequest.setEndDate(request.getTargetDate().toString());
        dataLabRequest.setTimeUnit("date");
        dataLabRequest.setCategory(Arrays.asList(new NaverClickDto.Category("패션의류", "50000000"), new NaverClickDto.Category("패션잡화", "50000001"), category));
        dataLabRequest.setDevice("");
        dataLabRequest.setGender("");
        dataLabRequest.setAges(Collections.emptyList());
        return dataLabRequest;
    }

    private Click convert(Click click, NaverClickDto.Category category, LocalDate localDate, NaverClickDto.Result result) {
        Long value = result.getData().get(0).getRatio();
        click.setDate(localDate);
        click.setCategoryName(category.getName());
        click.setValue(value);
        return click;
    }

    private List<NaverClickDto.Category> makeCategoryList() {
        return Arrays.asList(
                new NaverClickDto.Category("화장품/미용", "50000002"),
                new NaverClickDto.Category("디지털/가전", "50000003"),
                new NaverClickDto.Category("가구/인테리어", "50000004"),
                new NaverClickDto.Category("출산/육아", "50000005"),
                new NaverClickDto.Category("식품", "50000006"),
                new NaverClickDto.Category("스포츠/레저", "50000007"),
                new NaverClickDto.Category("생활/건강", "50000008"),
                new NaverClickDto.Category("여가/생활편의", "50000009"),
                new NaverClickDto.Category("면세점", "50000010")
        );
    }
}
