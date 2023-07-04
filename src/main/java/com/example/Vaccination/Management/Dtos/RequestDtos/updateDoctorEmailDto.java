package com.example.Vaccination.Management.Dtos.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateDoctorEmailDto {
    private String email;
    private int age;
}
