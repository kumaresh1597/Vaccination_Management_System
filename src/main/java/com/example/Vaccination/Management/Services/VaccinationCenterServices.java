package com.example.Vaccination.Management.Services;

import com.example.Vaccination.Management.Exceptions.AddressNotFoundException;
import com.example.Vaccination.Management.Models.VaccinationCenter;
import com.example.Vaccination.Management.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterServices {
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public String addCenter(VaccinationCenter center) throws AddressNotFoundException {
         if(center.getAddress() == null){
             throw new AddressNotFoundException("Address is empty");
         }
         vaccinationCenterRepository.save(center);
         return "center added at location "+ center.getAddress();
    }
}
