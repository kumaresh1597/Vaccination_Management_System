package com.example.Vaccination.Management.Services;

import com.example.Vaccination.Management.Dtos.RequestDtos.AppointmentDto;
import com.example.Vaccination.Management.Exceptions.DoctorNotFoundException;
import com.example.Vaccination.Management.Exceptions.UserNotFoundException;
import com.example.Vaccination.Management.Models.Appointment;
import com.example.Vaccination.Management.Models.Doctor;
import com.example.Vaccination.Management.Models.User;
import com.example.Vaccination.Management.Repository.AppointmentRepository;
import com.example.Vaccination.Management.Repository.DoctorRepository;
import com.example.Vaccination.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;
    public String bookAppointments(AppointmentDto appointmentDto) throws DoctorNotFoundException, UserNotFoundException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(appointmentDto.getDocId());
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Doctor Id is wrong");
        }
        Optional<User> optionalUser =  userRepository.findById(appointmentDto.getUserId());
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User Id is wrong");
        }
        Doctor doctor = optionalDoctor.get();
        User user = optionalUser.get();

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setDoctor(doctor);
        appointment.setUser(user);

        appointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);

        String userBody = "Hi!! "+user.getName()+"\n"+
                "You have Successfully booked an appointment on,"+"\n"+
                "Date : "+appointment.getAppointmentDate()+"\n"+
                "Time : "+appointment.getAppointmentTime()+"."+"\n"+
                "Your doctor is "+doctor.getName()+"."+"\n"+
                "Please reach at Vaccination center "+doctor.getVaccinationCenter().getCenterName()+" located at "+doctor.getVaccinationCenter().getAddress()+"."+"\n"+
                "Please reach on time."+"\n"+
                "Mask is mandatory.";

        String docBody = "Hi!! "+doctor.getName()+"\n"+
                "There is an Appointment is booked for you on,"+"\n"+
                "Date : "+appointment.getAppointmentDate()+"\n"+
                "Time : "+appointment.getAppointmentTime()+"\n"+
                "at Vaccination center "+doctor.getVaccinationCenter().getCenterName()+"."+"\n"+
                "Patient details follows, \n"+
                "Name :"+user.getName()+",\n"+
                "Age :"+user.getAge()+",\n"+
                "Gender :"+user.getGender()+",\n"+
                "Mobile no :"+user.getMobileNo()+".\n"+
                "\n"+
                "Please confirm your availability...";


        SimpleMailMessage mailMessageForPatient = new SimpleMailMessage();
        SimpleMailMessage mailMessageForDoctor = new SimpleMailMessage();

        mailMessageForPatient.setFrom("forspring587@gmail.com");
        mailMessageForPatient.setTo(user.getEmail());
        mailMessageForPatient.setSubject("Appointment confirmed for vaccination");
        mailMessageForPatient.setText(userBody);

        mailMessageForDoctor.setFrom("forspring587@gmail.com");
        mailMessageForDoctor.setTo(doctor.getEmail());
        mailMessageForDoctor.setSubject("Appointment Booked for you !!!");
        mailMessageForDoctor.setText(docBody);

        emailSender.send(mailMessageForPatient);
        emailSender.send(mailMessageForDoctor);

        return "Appointment booked Successfully";
    }
}
