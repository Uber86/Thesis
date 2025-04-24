package ru.skypro.homework.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.model.AdModel;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {
    /*
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "imageAd")
    Ad toDto(AdModel adModel);

    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "imageAd", source = "image")
    AdModel toModel(Ad ad);

     */
}
