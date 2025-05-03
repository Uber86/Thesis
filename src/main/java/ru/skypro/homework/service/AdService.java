package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdService {

    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image);

    ExtendedAd getAd(long id);

    void deleteAd(long id);

    Ad updateAd(long id, CreateOrUpdateAd createOrUpdateAd);

    Ads getAdsByUserId(int userId);

    byte[] updateImage(long id, MultipartFile image);



}
