package com.up42.mapper;

import com.up42.model.Feature;
import com.up42.model.Properties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;

@Mapper
public interface PropertiesMapper extends Converter<Properties, Feature> {

    PropertiesMapper INSTANCE = Mappers.getMapper(PropertiesMapper.class);

    @Mapping(target = "beginViewingDate", source = "acquisition.beginViewingDate")
    @Mapping(target = "endViewingDate", source = "acquisition.endViewingDate")
    @Mapping(target = "missionName", source = "acquisition.missionName")
    Feature convert(Properties properties);

    default Instant map(Long value) {
        return Instant.ofEpochMilli(value);
    }
}
