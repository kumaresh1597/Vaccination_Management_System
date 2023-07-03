package com.example.Vaccination.Management.Services;

import com.example.Vaccination.Management.Enums.Gender;
import com.example.Vaccination.Management.Exceptions.AddressNotFoundException;
import com.example.Vaccination.Management.Exceptions.CenterNotFoundException;
import com.example.Vaccination.Management.Models.Doctor;
import com.example.Vaccination.Management.Models.VaccinationCenter;
import com.example.Vaccination.Management.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationCenterServices {
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    public List<Doctor> getDoctorsOfThisCenter(int centerId) throws CenterNotFoundException {
        Optional<VaccinationCenter> centerOptional = vaccinationCenterRepository.findById(centerId);
        if(centerOptional.isEmpty()) throw new CenterNotFoundException("Invalid center Id!");
        return centerOptional.get().getDoctorList();
    }

    public String addCenter(VaccinationCenter center) throws AddressNotFoundException {
         if(center.getAddress() == null){
             throw new AddressNotFoundException("Address is empty");
         }
         vaccinationCenterRepository.save(center);
         return "center added at location "+ center.getAddress();
    }

    public List<Doctor> getDoctorsOfThisCenterByGender(int centerId, Gender gender) throws CenterNotFoundException {
        Optional<VaccinationCenter> centerOptional = vaccinationCenterRepository.findById(centerId);
        if(centerOptional.isEmpty()) throw new CenterNotFoundException("Invalid center Id!");

        List<Doctor> doctorList = centerOptional.get().getDoctorList();
        List<Doctor> resultList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(gender)){
                resultList.add(doctor);
            }
        }
        return resultList;
    }

    public List<Doctor> getDoctorsOfThisCenterByGenderByAge(int centerId, Gender gender, int age) throws CenterNotFoundException {
        Optional<VaccinationCenter> centerOptional = vaccinationCenterRepository.findById(centerId);
        if(centerOptional.isEmpty()) throw new CenterNotFoundException("Invalid center Id!");

        List<Doctor> doctorList = centerOptional.get().getDoctorList();
        List<Doctor> resultList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            if(doctor.getGender().equals(gender) && doctor.getAge() > age){
                resultList.add(doctor);
            }
        }
        return resultList;
    }
}
