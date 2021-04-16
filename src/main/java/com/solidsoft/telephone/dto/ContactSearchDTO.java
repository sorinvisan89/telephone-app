package com.solidsoft.telephone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactSearchDTO {

    @NotEmpty(message = "Search Pattern is required")
    private String searchString;
}
