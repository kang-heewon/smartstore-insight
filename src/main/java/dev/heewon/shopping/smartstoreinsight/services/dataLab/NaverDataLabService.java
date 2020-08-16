package dev.heewon.shopping.smartstoreinsight.services.dataLab;

import dev.heewon.shopping.smartstoreinsight.dto.dataLab.NaverClickDto;

public interface NaverDataLabService {
    void getData(NaverClickDto.CreateRequest request);

}
