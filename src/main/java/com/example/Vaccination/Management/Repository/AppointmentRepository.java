package com.example.Vaccination.Management.Repository;

import com.example.Vaccination.Management.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

}
