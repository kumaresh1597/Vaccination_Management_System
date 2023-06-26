package com.example.Vaccination.Management.Controllers;

import com.example.Vaccination.Management.Dtos.RequestDtos.associateDocterDto;
import com.example.Vaccination.Management.Exceptions.CenterNotFoundException;
import com.example.Vaccination.Management.Exceptions.DoctorNotFoundException;
import com.example.Vaccination.Management.Models.Doctor;
import com.example.Vaccination.Management.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doc){
        try{
          String response = doctorService.addDoctor(doc);
          return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/associateDoctor")
    public ResponseEntity<String> associateDoctorWithCenter(associateDocterDto input) {
        try {
            String response = doctorService.associateDoctor(input);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}