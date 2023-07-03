package com.example.Vaccination.Management.Repository;

import com.example.Vaccination.Management.Enums.Gender;
import com.example.Vaccination.Management.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Doctor findByEmail(String emailId);
    @Query(value = "select * from doctors where age>= :givenAge and gender= :givenGender",nativeQuery = true)
    List<Doctor> getAllByGenderAndAge(Integer givenAge, Gender givenGender);
}
