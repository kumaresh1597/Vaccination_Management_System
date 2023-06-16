package com.example.Vaccination.Management.Controllers;

import com.example.Vaccination.Management.Models.Doctor;
import com.example.Vaccination.Management.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addDoctor(@RequestBody Doctor doc){
        try{
          String response = doctorService.addDoctor(doc);
          return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
