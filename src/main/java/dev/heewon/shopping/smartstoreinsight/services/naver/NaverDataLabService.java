package dev.heewon.shopping.smartstoreinsight.services.naver;

import dev.heewon.shopping.smartstoreinsight.dto.naver.NaverClickDto;

public interface NaverDataLabService {

    String getData(NaverClickDto.CreateRequest request);

}
