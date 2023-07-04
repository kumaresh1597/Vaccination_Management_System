package com.example.Vaccination.Management.Services;

import com.example.Vaccination.Management.Dtos.RequestDtos.associateDocterDto;
import com.example.Vaccination.Management.Dtos.RequestDtos.updateDoctorEmailDto;
import com.example.Vaccination.Management.Enums.Gender;
import com.example.Vaccination.Management.Exceptions.CenterNotFoundException;
import com.example.Vaccination.Management.Exceptions.DoctorNotFoundException;
import com.example.Vaccination.Management.Exceptions.EmailAlreadyExistsException;
import com.example.Vaccination.Management.Exceptions.EmailIdIsEmptyException;
import com.example.Vaccination.Management.Models.Doctor;
import com.example.Vaccination.Management.Models.VaccinationCenter;
import com.example.Vaccination.Management.Repository.DoctorRepository;
import com.example.Vaccination.Management.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

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

    public String associateDoctor(associateDocterDto input) throws DoctorNotFoundException, CenterNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(input.getDocId());
        Optional<VaccinationCenter> center = vaccinationCenterRepository.findById(input.getCenterId());
        if(doctor.isEmpty()){
            throw new DoctorNotFoundException("Doctor Id is wrong");
        }
        if(center.isEmpty()){
            throw new CenterNotFoundException("Center not present");
        }
        // setting foreign key in doc table
        doctor.get().setVaccinationCenter(center.get());
        //adding the doc to doc list in center
        center.get().getDoctorList().add(doctor.get());
        // save the parent repo
        vaccinationCenterRepository.save(center.get());

        return "Doctor with "+doctor.get().getDocId()+" is associated with center "+center.get().getId();
    }

    public List<Doctor> getDoctorsWithMorethan10Appointments() {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<Doctor> resultList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            if(doctor.getAppointmentList().size() >= 10){
                resultList.add(doctor);
            }
        }
        return resultList;
    }

    public List<Doctor> getDoctorsGreaterThanAgeByGender(int age, Gender gender) {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<Doctor> resultList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            if(doctor.getAge() > age && doctor.getGender().equals(gender)){
                resultList.add(doctor);
            }
        }
        return resultList;
 //       return doctorRepository.getAllByGenderAndAge(age,gender);
    }

    public String updateAge(updateDoctorEmailDto emailDto) {
        Doctor doctor = doctorRepository.findByEmail(emailDto.getEmail());
        doctor.setAge(emailDto.getAge());
        doctorRepository.save(doctor);
        return "Age updated successfully";
    }
}
