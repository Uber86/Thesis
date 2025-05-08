package ru.skypro.homework.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class AdControllerTest {

    @Mock
    private AdService adService;

    @InjectMocks
    private AdController adController;

    private MockMvc mockMvc;

    private final UserDetails userDetails = new User("user@mail.com", "password", Collections.emptyList());
    private final Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adController).build();
    }

    @Test
    public void getAllAds_ShouldReturnAds() throws Exception {
        Ads ads = new Ads();
        when(adService.getAllAds()).thenReturn(ads);

        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void getAd_ShouldReturnExtendedAd() throws Exception {
        ExtendedAd extendedAd = new ExtendedAd();
        when(adService.getAd(anyInt())).thenReturn(extendedAd);

        mockMvc.perform(get("/ads/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void deleteAd_ShouldReturnOk() throws Exception {
        mockMvc.perform(delete("/ads/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void addAd_ShouldReturnCreatedAd() throws Exception {
        Ad ad = new Ad();
        when(adService.addAd(any(), any())).thenReturn(ad);

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "test image".getBytes()
        );

        MockMultipartFile properties = new MockMultipartFile(
                "properties",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                "{\"title\":\"Test\",\"price\":100}".getBytes()
        );

        mockMvc.perform(multipart("/ads")
                        .file(image)
                        .file(properties))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void updateAd_ShouldReturnUpdatedAd() throws Exception {
        Ad ad = new Ad();
        when(adService.updateAd(anyInt(), any())).thenReturn(ad);

        mockMvc.perform(patch("/ads/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated\",\"price\":200}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void getAdsMe_ShouldReturnUserAds() throws Exception {
        Ads ads = new Ads();
        when(adService.getAdsByUserName(any())).thenReturn(ads);

        mockMvc.perform(get("/ads/me")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
}