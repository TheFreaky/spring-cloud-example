package ru.kpfu.itis.cat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.cat.dto.CatDto;
import ru.kpfu.itis.cat.service.CatService;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final RestTemplate restTemplate;
    @Value("${cats.get.request.url}")
    private String catsGetRequestUrl;

    @Override
    public Optional<CatDto> getCat() {
        CatDto[] body = restTemplate.getForEntity(catsGetRequestUrl, CatDto[].class).getBody();
        if (body != null) {
            return Arrays.stream(body)
                    .findFirst();
        }
        return Optional.empty();
    }
}
