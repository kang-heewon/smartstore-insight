package dev.heewon.shopping.smartstoreinsight.services.naver;

import dev.heewon.shopping.smartstoreinsight.config.NaverDataLabConfig;
import dev.heewon.shopping.smartstoreinsight.dto.click.ClickDto;
import dev.heewon.shopping.smartstoreinsight.dto.naver.NaverClickDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class NaverDataLabServiceImpl implements NaverDataLabService {

    private final NaverDataLabConfig naverDataLabConfig;
    private final RestTemplate restTemplate;

    public String getData(ClickDto.CreateRequest request) {
        try {
            NaverClickDto.DataLabRequest requestBody = makeResponse(request);
            ResponseEntity<String> responseBody = this.crawlData(requestBody);
            return responseBody.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
            return "hello";
        }
    }

    private <T> ResponseEntity<String> crawlData(T request) {
        String clientId = naverDataLabConfig.getClientId();
        String clientSecret = naverDataLabConfig.getClientSecret();
        String apiUrl = naverDataLabConfig.getUrl();
        System.out.println(clientId + "|" + clientSecret);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.set("Content-Type", "application/json");
        System.out.println("hello\n" + request.toString() + "\n");
        HttpEntity<T> entity = new HttpEntity<T>(request, headers);
        return restTemplate.postForEntity(apiUrl, entity, String.class);
    }

    private NaverClickDto.DataLabRequest makeResponse(ClickDto.CreateRequest request) {
        NaverClickDto.DataLabRequest dataLabRequest = new NaverClickDto.DataLabRequest();
        dataLabRequest.setStartDate(request.getStartDate());
        dataLabRequest.setEndDate(request.getEndDate());
        dataLabRequest.setTimeUnit(request.getTimeUnit());
        dataLabRequest.setCategory(request.getCategory());
        return dataLabRequest;
    }
}
