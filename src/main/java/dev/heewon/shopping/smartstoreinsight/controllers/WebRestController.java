package dev.heewon.shopping.smartstoreinsight.controllers;

import dev.heewon.shopping.smartstoreinsight.common.CommonResponse;
import dev.heewon.shopping.smartstoreinsight.dto.click.ClickDto;
import dev.heewon.shopping.smartstoreinsight.dto.naver.NaverClickDto;
import dev.heewon.shopping.smartstoreinsight.services.click.ClickService;
import dev.heewon.shopping.smartstoreinsight.services.naver.NaverDataLabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final NaverDataLabService naverDataLabService;
    private final ClickService clickService;

    @GetMapping("/click/{date}")
    public CommonResponse getClick(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<ClickDto.ClickInfo> response = clickService.getData(localDate);
        return CommonResponse.success(response);
    }

    @PostMapping("/crawl")
    public String crawl(@RequestBody NaverClickDto.CreateRequest request) {
        return naverDataLabService.getData(request);
    }
}