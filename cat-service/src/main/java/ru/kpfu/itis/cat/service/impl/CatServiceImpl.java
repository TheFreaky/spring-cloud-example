package ru.kpfu.itis.cat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.cat.dto.CatDto;
import ru.kpfu.itis.cat.service.CatService;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cats.get.request.url}")
    private String catsGetRequestUrl;

    @Override
    public Optional<CatDto> getCat() {
        CatDto[] body = restTemplate.getForEntity(catsGetRequestUrl, CatDto[].class).getBody();
        return Arrays.stream(body).findFirst();
    }
}
