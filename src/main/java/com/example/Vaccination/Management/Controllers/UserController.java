package com.example.Vaccination.Management.Controllers;

import com.example.Vaccination.Management.Dtos.RequestDtos.updateEmailDto;
import com.example.Vaccination.Management.Exceptions.EmailAlreadyExistsException;
import com.example.Vaccination.Management.Exceptions.EmailIdIsEmptyException;
import com.example.Vaccination.Management.Models.User;
import com.example.Vaccination.Management.Repository.UserRepository;
import com.example.Vaccination.Management.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/add")
    public String addUser(@RequestBody User user){

        try {
            String response = userService.addToUser(user);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @GetMapping("/getVaccinationDate")
    public Date getVaccinationDate(@RequestParam("userId") int userId){
        return userService.getVaccinationDate(userId);
    }

    @PutMapping("/updateEmail")
    public String updateEmailId(@RequestBody updateEmailDto updateEmail){
        return userService.updateEmail(updateEmail);
    }

    @GetMapping("/getByEmail/{email}")
    public User getByEmailId(@PathVariable("email") String emailId){
        return userService.getUserByEmailId(emailId);
    }
}
