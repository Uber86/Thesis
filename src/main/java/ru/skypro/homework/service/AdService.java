package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.mapper.AdMapper;

import javax.persistence.criteria.CriteriaBuilder;

@Service
@RequiredArgsConstructor
public class AdService {
    /*
    private final AdRepository adRepository;
    private final AdMapper adMapper;

    public Ad createAd(Ad adDto) {
        AdModel adModel = adMapper.toModel(adDto);
        AdModel savedAd = adRepository.save(adModel);
        return adMapper.toDto(savedAd);
    }

    public Ad getAdById(Long id) {
        AdModel adModel = adRepository.findById(id).orElseThrow();
        return adMapper.toDto(adModel);
    }

     */
}
