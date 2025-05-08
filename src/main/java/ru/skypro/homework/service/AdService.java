package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;

public interface AdService {

    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image) throws IOException;

    ExtendedAd getAd(int id);

    void deleteAd(int id);

    Ad updateAd(int id, CreateOrUpdateAd createOrUpdateAd);

    Ads getAdsByUserId(int userId);

    String updateImage(long id, MultipartFile image) throws IOException;


    Ads getAdsByUserName(String username);
}
