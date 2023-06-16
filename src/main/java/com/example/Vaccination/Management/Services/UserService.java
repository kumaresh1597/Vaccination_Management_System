package com.example.Vaccination.Management.Services;

import com.example.Vaccination.Management.Dtos.RequestDtos.updateEmailDto;
import com.example.Vaccination.Management.Exceptions.EmailAlreadyExistsException;
import com.example.Vaccination.Management.Exceptions.EmailIdIsEmptyException;
import com.example.Vaccination.Management.Models.Dose;
import com.example.Vaccination.Management.Models.User;
import com.example.Vaccination.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String addToUser(User user) throws EmailIdIsEmptyException,EmailAlreadyExistsException{
        if(user.getEmail() == null){
            throw new EmailIdIsEmptyException("Email is Mandatory");
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExistsException("User already registered with this email");
        }
        userRepository.save(user);
     return "User successfully registered with userId : "+ user.getUserId();
    }

    public Date getVaccinationDate(int userId) {
        User user = userRepository.findById(userId).get();
        Dose dose = user.getDose();
        return dose.getVaccinationDate();
    }

    public String updateEmail(updateEmailDto updateEmail) {
        int userId = updateEmail.getUserId();
        User user = userRepository.findById(userId).get();

        user.setEmail(updateEmail.getNewEmail());

        userRepository.save(user);

        return "Email updated successfully with new Email "+user.getEmail();
    }

    public User getUserByEmailId(String emailId) {
        User user = userRepository.findByEmail(emailId);
        return user;
    }
}
