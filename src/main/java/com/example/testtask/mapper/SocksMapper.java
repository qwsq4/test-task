package com.example.testtask.mapper;

import com.example.testtask.dto.SocksDTO;
import com.example.testtask.entity.Socks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SocksMapper {
    @Mapping(target = "id", ignore = true)
    Socks toEntity(SocksDTO dto);

    SocksDTO toDto(Socks entity);
}
