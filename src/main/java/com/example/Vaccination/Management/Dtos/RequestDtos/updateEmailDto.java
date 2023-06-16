package com.example.Vaccination.Management.Dtos.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class updateEmailDto {
    private int userId;
    private String newEmail;
}
