package ru.skypro.homework.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.controller.AdController;

public class AdControllerTest {

    @InjectMocks
    private AdController adController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adController).build();
    }

    @Test
    public void testGetAllAds() throws Exception {
        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetAd() throws Exception {
        Integer adId = 1;

        mockMvc.perform(get("/ads/{id}", adId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAd() throws Exception {
        Integer adId = 1;

        mockMvc.perform(delete("/ads/{id}", adId))
                .andExpect(status().isOk());
    }
}


