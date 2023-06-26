package com.example.Vaccination.Management.Controllers;

import com.example.Vaccination.Management.Dtos.RequestDtos.AppointmentDto;
import com.example.Vaccination.Management.Exceptions.DoctorNotFoundException;
import com.example.Vaccination.Management.Exceptions.UserNotFoundException;
import com.example.Vaccination.Management.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/bookAppointment")
    public ResponseEntity<String> bookAppointments(@RequestBody AppointmentDto appointmentDto){
        try {
           String response = appointmentService.bookAppointments(appointmentDto);
           return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
