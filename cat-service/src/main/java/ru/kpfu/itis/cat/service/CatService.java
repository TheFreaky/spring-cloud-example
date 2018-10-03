package ru.kpfu.itis.cat.service;

import ru.kpfu.itis.cat.dto.CatDto;

import java.util.Optional;


public interface CatService {
    Optional<CatDto> getCat();
}
