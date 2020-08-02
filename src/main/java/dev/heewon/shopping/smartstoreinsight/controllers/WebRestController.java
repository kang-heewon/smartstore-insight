package dev.heewon.shopping.smartstoreinsight.controllers;

import dev.heewon.shopping.smartstoreinsight.dto.click.ClickDto;
import dev.heewon.shopping.smartstoreinsight.services.naver.NaverDataLabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final NaverDataLabService naverDataLabService;

    @PostMapping("/crawl")
    public String crawl(@RequestBody ClickDto.CreateRequest request) {
        return this.naverDataLabService.getData(request);
    }
}