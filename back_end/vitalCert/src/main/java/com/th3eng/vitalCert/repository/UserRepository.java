package com.th3eng.vitalCert.repository;

import com.th3eng.vitalCert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    //select citizen by ssn
    @Query("SELECT e FROM Citizen e WHERE e.ssn = ?1")
    Optional<User> findBySsn(String ssn);

}
