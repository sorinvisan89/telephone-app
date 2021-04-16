package com.solidsoft.telephone.mapper;

import com.solidsoft.telephone.dto.ContactDTO;
import com.solidsoft.telephone.dto.ContactRequestDTO;
import com.solidsoft.telephone.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContactMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "number", source = "number"),
            @Mapping(target = "email", source = "email")
    })
    Contact toEntity(final ContactRequestDTO contactRequestDTO);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "number", source = "number"),
            @Mapping(target = "email", source = "email")
    })
    ContactDTO toDTO(final Contact contact);
}
