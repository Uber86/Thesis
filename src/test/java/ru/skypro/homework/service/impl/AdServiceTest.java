package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ImageProcessingException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UsersDetailsService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockAuthentication;
import static ru.skypro.homework.dto.Role.ADMIN;
import static ru.skypro.homework.dto.Role.USER;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest {

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdMapper adMapper;

    @Mock
    private UsersDetailsService usersDetailsService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AdServiceImpl adService;

    @Test
    public void getAllAds_ShouldReturnAllAds() {
        AdModel adModel1 = new AdModel();
        AdModel adModel2 = new AdModel();
        List<AdModel> adModels = List.of(adModel1, adModel2);

        Ad adDto1 = new Ad();
        Ad adDto2 = new Ad();
        List<Ad> adDtos = List.of(adDto1, adDto2);

        when(adRepository.findAll()).thenReturn(adModels);
        when(adMapper.toDtoList(adModels)).thenReturn(adDtos);

        Ads result = adService.getAllAds();

        assertEquals(2, result.getCount());
        assertEquals(adDtos, result.getResults());
        verify(adRepository, times(1)).findAll();
        verify(adMapper, times(1)).toDtoList(adModels);
    }

    @Test
    void addAd_ShouldSaveAndReturnAd() throws IOException {
        // Подготовка тестовых данных
        CreateOrUpdateAd properties = new CreateOrUpdateAd();
        properties.setTitle("Test Ad");
        properties.setPrice(1000);
        properties.setDescription("Test Description");

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "test.jpg",
                "image/jpeg",
                "test image content".getBytes()
        );

        UserModel currentUser = new UserModel();
        currentUser.setId(1L);
        currentUser.setEmail("user@example.com");
        currentUser.setRole(USER);

        AdModel adModel = new AdModel();
        adModel.setAuthor(currentUser);
        adModel.setTitle(properties.getTitle());
        adModel.setPrice(properties.getPrice());
        adModel.setDescription(properties.getDescription());

        Ad expectedAd = new Ad();
        expectedAd.setPk(1);
        expectedAd.setTitle(properties.getTitle());
        expectedAd.setPrice(properties.getPrice());

        // Настройка моков
        when(adMapper.toModel(properties)).thenReturn(adModel);

        // Правильная настройка security context
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user@example.com");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(currentUser));
        when(adRepository.save(any(AdModel.class))).thenReturn(adModel);
        when(adMapper.toDto(adModel)).thenReturn(expectedAd);

        // Вызов метода
        Ad result = adService.addAd(properties, image);

        // Проверки
        assertNotNull(result);
        assertEquals(expectedAd, result);
        verify(adRepository, times(1)).save(adModel);
        verify(adMapper, times(1)).toModel(properties);
        verify(adMapper, times(1)).toDto(adModel);
    }

    @Test
    void getAd_ShouldThrowAdNotFoundException_WhenAdNotFound() {
        int adId = 999;
        when(adRepository.findById((long) adId)).thenReturn(Optional.empty());

        assertThrows(AdNotFoundException.class, () -> adService.getAd(adId));
    }

    @Test
    void getAdsByUserId_ShouldReturnUserAds() {
        int userId = 1;
        UserModel user = new UserModel();
        user.setId((long) userId);

        AdModel adModel1 = new AdModel();
        adModel1.setPk(1L);
        AdModel adModel2 = new AdModel();
        adModel2.setPk(2L);
        List<AdModel> adModels = List.of(adModel1, adModel2);

        Ad adDto1 = new Ad();
        Ad adDto2 = new Ad();
        List<Ad> adDtos = List.of(adDto1, adDto2);

        when(adRepository.findByPk((int) userId)).thenReturn(adModels);
        when(adMapper.toDtoList(adModels)).thenReturn(adDtos);

        Ads result = adService.getAdsByUserId(userId);

        assertEquals(2, result.getCount());
        assertEquals(adDtos, result.getResults());
        verify(adRepository, times(1)).findByPk((int) userId);
    }

    @Test
    void updateImage_ShouldUpdateAndReturnImage() throws IOException {
        long adId = 1L;
        MockMultipartFile image = new MockMultipartFile(
                "image",
                "test.jpg",
                "image/jpeg",
                "test image content".getBytes()
        );

        AdModel adModel = new AdModel();
        adModel.setPk(adId);

        when(adRepository.findById(adId)).thenReturn(Optional.of(adModel));

        byte[] result = adService.updateImage(adId, image);

        assertNotNull(result);
        verify(adRepository, times(1)).save(adModel);
    }

    @Test
    void updateImage_ShouldThrowImageProcessingException_WhenIOException() throws IOException {
        long adId = 1L;
        MockMultipartFile image = mock(MockMultipartFile.class);
        when(image.getBytes()).thenThrow(new IOException("Test exception"));

        AdModel adModel = new AdModel();
        adModel.setPk(adId);

        when(adRepository.findById(adId)).thenReturn(Optional.of(adModel));

        assertThrows(ImageProcessingException.class, () -> adService.updateImage(adId, image));
    }

    @Test
    void getAdsByUserName_ShouldReturnUserAds() {
        String username = "user@example.com";
        UserModel user = new UserModel();
        user.setEmail(username);

        AdModel adModel1 = new AdModel();
        AdModel adModel2 = new AdModel();
        List<AdModel> adModels = List.of(adModel1, adModel2);

        Ad adDto1 = new Ad();
        Ad adDto2 = new Ad();
        List<Ad> adDtos = List.of(adDto1, adDto2);

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));
        when(adRepository.findAllByAuthor(user)).thenReturn(adModels);
        when(adMapper.toDtoList(adModels)).thenReturn(adDtos);

        Ads result = adService.getAdsByUserName(username);

        assertEquals(2, result.getCount());
        assertEquals(adDtos, result.getResults());
        verify(userRepository, times(1)).findByEmail(username);
    }

    @Test
    void getAdsByUserName_ShouldThrowUsernameNotFoundException_WhenUserNotFound() {
        String username = "nonexistent@example.com";
        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> adService.getAdsByUserName(username));
    }

    private void mockSecurityContext(String username) {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(username);
        UserDetails userDetails = mock(UserDetails.class);
        when(usersDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
    }
}