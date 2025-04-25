package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.service.mapper.AdMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest {

    private final AdMapper mapper = Mappers.getMapper(AdMapper.class);

    @Test
    void testToDto() {
        UserModel user = new UserModel();
        user.setId(123L);

        AdModel adModel = new AdModel();
        adModel.setPk(1L);
        adModel.setAuthor(user);
        adModel.setImage("/path/to/image.jpg");
        adModel.setPrice(1000);
        adModel.setTitle("Test Ad");

        Ad adDto = mapper.toDto(adModel);

        assertEquals(1, adDto.getPk());
        assertEquals(123, adDto.getAuthor());
        assertEquals("/path/to/image.jpg", adDto.getImage());
    }

    @Test
    void testToModel() {
        Ad adDto = new Ad();
        adDto.setPk(123);
        adDto.setAuthor(1);
        adDto.setTitle("Test Ad");
        adDto.setPrice(1000);
        adDto.setImage("/path/to/image.jpg");
        adDto.setTitle("Test Ad");

        AdModel adModel = mapper.toModel(adDto);

        assertNotNull(adModel);
        assertEquals(123, adModel.getPk());

        assertNotNull(adModel.getAuthor());
        assertEquals(1, adModel.getAuthor().getId());

        assertEquals("Test Ad", adModel.getTitle());
        assertEquals(1000, adModel.getPrice());
        assertEquals("/path/to/image.jpg", adModel.getImage());
    }


}
