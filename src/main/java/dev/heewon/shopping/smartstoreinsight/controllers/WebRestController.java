package dev.heewon.shopping.smartstoreinsight.controllers;

import dev.heewon.shopping.smartstoreinsight.common.CommonResponse;
import dev.heewon.shopping.smartstoreinsight.dto.click.ClickDto;
import dev.heewon.shopping.smartstoreinsight.dto.dataLab.NaverClickDto;
import dev.heewon.shopping.smartstoreinsight.services.click.ClickService;
import dev.heewon.shopping.smartstoreinsight.services.dataLab.NaverDataLabService;
import dev.heewon.shopping.smartstoreinsight.services.shopping.NaverShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final NaverDataLabService naverDataLabService;
    private final NaverShoppingService naverShoppingService;
    private final ClickService clickService;

    @GetMapping("/click/{date}")
    public CommonResponse getClick(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<ClickDto.ClickInfo> response = clickService.getData(localDate);
        return CommonResponse.success(response);
    }

    @PostMapping("/crawl/click")
    public CommonResponse crawl(@RequestBody NaverClickDto.CreateRequest request) {
        naverDataLabService.getData(request);
        return CommonResponse.success("success");
    }

    @PostMapping("/crawl/shopping")
    public CommonResponse crawl() {
        naverShoppingService.getData();
        return CommonResponse.success("success");
    }


}