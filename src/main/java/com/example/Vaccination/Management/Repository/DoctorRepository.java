package com.example.Vaccination.Management.Repository;

import com.example.Vaccination.Management.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Doctor findByEmail(String emailId);
}
