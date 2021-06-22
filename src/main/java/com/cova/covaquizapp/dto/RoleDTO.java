package com.cova.covaquizapp.dto;

import com.cova.covaquizapp.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {

    private ERole name;
}
