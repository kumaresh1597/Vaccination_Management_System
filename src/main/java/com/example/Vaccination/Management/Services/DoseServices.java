package com.example.Vaccination.Management.Services;

import com.example.Vaccination.Management.Models.Dose;
import com.example.Vaccination.Management.Models.User;
import com.example.Vaccination.Management.Repository.DoseRepository;
import com.example.Vaccination.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseServices {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    UserRepository userRepository;
    public String giveDose(String doseId, int userId) {

        User user = userRepository.findById(userId).get();

        Dose dose1 = new Dose();
        dose1.setDoseId(doseId);
        dose1.setUser(user);

        user.setDose(dose1);

        userRepository.save(user);

        return "Dose successfully given to the user";
    }
}
