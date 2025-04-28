package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.AdModel;

import java.util.List;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {

    /**
     * Синглтон экземпляр AdMapper.
     */
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    /**
     * Преобразует объект AdModel в DTO Ad.
     *
     * @param adModel объект AdModel для преобразования
     * @return преобразованный DTO Ad
     */
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "image")
    Ad toDto(AdModel adModel);

    /**
     * Преобразует DTO Ad в объект AdModel.
     *
     * @param ad DTO Ad для преобразования
     * @return преобразованный объект AdModel
     */
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "image", source = "image")
    AdModel toModel(Ad ad);

    /**
     * Преобразует объект CreateOrUpdateAd в объект AdModel.
     *
     * @param createOrUpdateAd объект CreateOrUpdateAd для преобразования
     * @return преобразованный объект AdModel
     */
    AdModel toModel(CreateOrUpdateAd createOrUpdateAd);

    /**
     * Преобразует объект ExtendedAd в DTO Ad.
     *
     * @param extendedAd объект ExtendedAd для преобразования
     * @return преобразованный DTO Ad
     */
    Ad toDto(ExtendedAd extendedAd);

    /**
     * Преобразует список объектов AdModel в список DTO Ad.
     *
     * @param adModels список объектов AdModel для преобразования
     * @return список преобразованных DTO Ad
     */
    List<Ad> toDtoList(List<AdModel> adModels);

    /**
     * Преобразует список DTO Ad в список объектов AdModel.
     *
     * @param ads список DTO Ad для преобразования
     * @return список преобразованных объектов AdModel
     */
    List<AdModel> toModelList(List<Ad> ads);


}
