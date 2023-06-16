package com.example.Vaccination.Management.Services;

import com.example.Vaccination.Management.Exceptions.EmailAlreadyExistsException;
import com.example.Vaccination.Management.Exceptions.EmailIdIsEmptyException;
import com.example.Vaccination.Management.Models.Doctor;
import com.example.Vaccination.Management.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    public String addDoctor(Doctor doc) throws EmailAlreadyExistsException, EmailIdIsEmptyException {
        if(doc.getEmail() == null){
            throw new EmailIdIsEmptyException("Email is Mandatory");
        }
        if(doctorRepository.findByEmail(doc.getEmail()) != null){
            throw new EmailAlreadyExistsException("Doctor already registered with this email");
        }

        doctorRepository.save(doc);
        return "Doctor Successfully registered with doctorId : "+ doc.getDocId();
    }
}
