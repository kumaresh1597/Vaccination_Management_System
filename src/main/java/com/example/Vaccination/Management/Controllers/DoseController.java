package com.example.Vaccination.Management.Controllers;

import com.example.Vaccination.Management.Models.Dose;
import com.example.Vaccination.Management.Services.DoseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseServices doseServices;
    @PostMapping("/giveDose1")
    public String giveDose(@RequestParam("doseId") String doseId, @RequestParam("userId") int userId){
        return doseServices.giveDose(doseId,userId);
    }
}
