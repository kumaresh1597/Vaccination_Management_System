package com.example.Vaccination.Management.Controllers;

import com.example.Vaccination.Management.Exceptions.AddressNotFoundException;
import com.example.Vaccination.Management.Models.VaccinationCenter;
import com.example.Vaccination.Management.Services.VaccinationCenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
