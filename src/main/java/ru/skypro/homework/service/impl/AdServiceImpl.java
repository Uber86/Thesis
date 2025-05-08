package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ImageProcessingException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UsersDetailsService;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ru.skypro.homework.dto.Role.ADMIN;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final UsersDetailsService usersDetailsService;

    @Override
    @Transactional
    public Ads getAllAds() {
        List<AdModel> adModels = adRepository.findAll();
        List<Ad> adDtos = adMapper.toDtoList(adModels);
        Ads adsResponse = new Ads();
        adsResponse.setCount(adDtos.size());
        adsResponse.setResults(adDtos);

        return adsResponse;
    }

    @Override
    @Transactional
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {
        UserModel user = getCurrentUser();
        AdModel adModel = adMapper.toModel(properties);
        adModel.setAuthor(user);
        return adMapper.toDto(adRepository.save(adModel));

//        if (image == null || image.isEmpty()) {
//            throw new IllegalArgumentException("Image file must not be null or empty");
//        }
//
//        AdModel adModel = adMapper.toModel(properties);
//
//        // Преобразование изображения в Base64
//        try {
//            byte[] bytes = image.getBytes();
//            String base64Image = Base64.encodeBase64String(bytes);
//            adModel.setImage(base64Image); // Установка Base64 строки
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to convert image to Base64", e);
//        }
//
//        return adMapper.toDto(adRepository.save(adModel));
    }

    @Override
    @Transactional
    public ExtendedAd getAd(int id) {
        AdModel ad = adRepository.findById((long) id)
                .orElseThrow(() -> new AdNotFoundException(id));

        return adMapper.toDto(ad);
    }

    @Override
    @Transactional
    public void deleteAd(int id) {
        UserModel userModel = userRepository.findById(getCurrentUserId()).
                orElseThrow(() -> new UserNotFoundException("User not init"));
        AdModel adModel = adRepository.findById((long) id)
                .orElseThrow(() -> new AdNotFoundException(id));
        if ((userModel.getId()
                .equals(adModel
                        .getAuthor()
                        .getId())) || userModel
                .getRole() == ADMIN) {
            adRepository.delete(adModel);
        }
//        AdModel existing = adRepository.findById((long) id)
//                .orElseThrow(() -> new AdNotFoundException(id));
//
//        checkUserPermission(existing);
//
//        adRepository.deleteById((long) id);
    }

    @Override
    @Transactional
    public Ad updateAd(int id, CreateOrUpdateAd createOrUpdateAd) {
        UserModel userModel = userRepository.findById(getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundException("User not init"));
        AdModel adModel = adRepository.findById((long)id)
                .orElseThrow(() -> new AdNotFoundException(id));
        if ((userModel.getId()
                .equals(adModel
                        .getAuthor()
                        .getId())) || userModel
                .getRole() == ADMIN) {
            adModel.setTitle(createOrUpdateAd.getTitle());
            adModel.setPrice(createOrUpdateAd.getPrice());
            adModel.setDescription(createOrUpdateAd.getDescription());
            adRepository.save(adModel);
        }
        return adMapper.toDto(adModel);

//        AdModel existing = adRepository.findById((long) id)
//                .orElseThrow(() -> new AdNotFoundException(id));
//
//        checkUserPermission(existing);
//
//        existing.setTitle(createOrUpdateAd.getTitle());
//        existing.setDescription(createOrUpdateAd.getDescription());
//
//        return adMapper.toDto(adRepository.save(existing));
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
        AdModel existing = adRepository.findById( id)
                .orElseThrow(() -> new AdNotFoundException(id));

        try {
            existing.setImage(Arrays.toString(image.getBytes()));
        } catch (IOException e) {
            throw new ImageProcessingException("Error while receiving image", e);
        }

        adRepository.save(existing);

        return existing.getImage().getBytes();
    }

    @Override
    public Ads getAdsByUserName(String username) {

        UserModel user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<AdModel> adModels = adRepository.findAllByAuthor(user);

        List<Ad> adList = adMapper.toDtoList(adModels);

        // Создаем объект Ads и заполняем его данными
        Ads ads = new Ads();
        ads.setCount(adList.size());
        ads.setResults(adList);

        return ads;
    }

    private UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName(); // Предполагается, что имя пользователя - это email

        return userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + currentUserEmail));
    }

    private void checkUserPermission(AdModel ad) {
        UserModel currentUser = getCurrentUser();

        if (!ad.getAuthor().getEmail().equals(currentUser.getEmail())) {
            throw new SecurityException("You do not have permission to modify this ad.");
        }
    }
    private UserModel getUserModelByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User '" + username + "' not founded");
        }
        return user;
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UserDetails userDetails = usersDetailsService.loadUserByUsername(username);
            UserModel user = userRepository.findByUsername(userDetails.getUsername());
            if (user == null){
                throw new UserNotFoundException("User not found");
            }
            return user.getId();
        }
        throw new UserNotFoundException("No authenticated user found");
    }



}
