package dev.heewon.shopping.smartstoreinsight.services.click;

import dev.heewon.shopping.smartstoreinsight.domain.clicks.Click;
import dev.heewon.shopping.smartstoreinsight.domain.clicks.ClickRepository;
import dev.heewon.shopping.smartstoreinsight.dto.click.ClickDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClickServiceImpl implements ClickService {

    private final ClickRepository clickRepository;

    public List<ClickDto.ClickInfo> getData(LocalDate date) {
        List<Click> clickList = clickRepository.findByDate(date);

        return clickList.stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    private ClickDto.ClickInfo convertTo(Click click) {
        return ClickDto.ClickInfo.builder()
                .id(click.getId())
                .categoryName(click.getCategoryName())
                .date(click.getDate())
                .value(click.getValue())
                .build();
    }
}
