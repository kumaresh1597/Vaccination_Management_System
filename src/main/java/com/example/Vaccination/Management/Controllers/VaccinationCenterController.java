package com.example.Vaccination.Management.Controllers;

import com.example.Vaccination.Management.Enums.Gender;
import com.example.Vaccination.Management.Exceptions.AddressNotFoundException;
import com.example.Vaccination.Management.Models.Doctor;
import com.example.Vaccination.Management.Models.VaccinationCenter;
import com.example.Vaccination.Management.Services.VaccinationCenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCenters")
public class VaccinationCenterController {
    @Autowired
    private VaccinationCenterServices vaccinationCenterServices;

    @PostMapping("/add")
    public ResponseEntity<String> addCenter(@RequestBody VaccinationCenter center){
        try {
            String response = vaccinationCenterServices.addCenter(center);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AddressNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllDoctorsAtParticularCenter")
    public List<Doctor> getAllDoctorsAtCenter(@RequestParam("id") int centerId){
        try{
            List<Doctor> doctors = vaccinationCenterServices.getDoctorsOfThisCenter(centerId);
            return doctors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAllDoctorsAtParticularCenterByGender")
    public List<Doctor> getAllDoctorsAtCenterByGender(@RequestParam("id") int centerId, @RequestParam("gender") Gender gender){
        try{
            List<Doctor> doctors = vaccinationCenterServices.getDoctorsOfThisCenterByGender(centerId,gender);
            return doctors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAllDoctorsAtParticularCenterByGenderByAge")
    public List<Doctor> getAllDoctorsAtCenterByGenderByAge(@RequestParam("id") int centerId, @RequestParam("gender") Gender gender,@RequestParam("age") int age){
        try{
            List<Doctor> doctors = vaccinationCenterServices.getDoctorsOfThisCenterByGenderByAge(centerId,gender,age);
            return doctors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
