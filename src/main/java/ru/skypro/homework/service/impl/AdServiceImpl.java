package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    @Override
    public Ads getAllAds() {
        List<AdModel> adModels = adRepository.findAll();
        List<Ad> adDtos = adMapper.toDtoList(adModels);
        Ads adsResponse = new Ads();
        adsResponse.setCount(adDtos.size());
        adsResponse.setResults(adDtos);

        return adsResponse;
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {
        AdModel adModel = adMapper.toModel(properties);
        return adMapper.toDto(adRepository.save(adModel));
    }

    @Override
    public ExtendedAd getAd(long id) {
        Optional<AdModel> optionalAd = adRepository.findById(id);

        if (optionalAd.isPresent()) {
            return adMapper.toDto(optionalAd.get());
        } else {
            throw new RuntimeException("Объявление с ID " + id + " не найдено");
        }
    }

    @Override
    public void deleteAd(long id) {
        adRepository.deleteById(id);

    }

    @Override
    public Ad updateAd(long id, CreateOrUpdateAd createOrUpdateAd) {
        AdModel existing = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        existing.setTitle(createOrUpdateAd.getTitle());
        existing.setDescription(createOrUpdateAd.getDescription());

        return adMapper.toDto(adRepository.save(existing));
    }

    @Override
    public Ads getAdsByUserId(int userId) {
        List<AdModel> ads = adRepository.findByPk(userId);
        List<Ad> adDtos = adMapper.toDtoList(ads);

        Ads adsResponse = new Ads();
        adsResponse.setCount(adDtos.size());
        adsResponse.setResults(adDtos);

        return adsResponse;
    }

    @Override
    public byte[] updateImage(long id, MultipartFile image) {
        AdModel existing = adRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Объявление не найдено"));

        try {
            existing.setImage(Arrays.toString(image.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при получении изображения", e);
        }

        adRepository.save(existing);

        return existing.getImage().getBytes();
    }

    @Override
    public Ads getAdsByUserName(String username) {
        // Получаем пользователя по имени (email или username)
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<Ad> adList = adRepository.findAllByUser(user);

        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);

        return ads;
    }

}
