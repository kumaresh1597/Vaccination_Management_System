package com.example.Vaccination.Management.Repository;

import com.example.Vaccination.Management.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

        User findByEmail(String email);

}
