package dev.heewon.shopping.smartstoreinsight.services.click;

import dev.heewon.shopping.smartstoreinsight.dto.click.ClickDto;

import java.time.LocalDate;
import java.util.List;

public interface ClickService {

    List<ClickDto.ClickInfo> getData(LocalDate date);

}
