package com.solidsoft.telephone.dto;


import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ContactDTO extends ContactRequestDTO {

    private Integer id;
}
