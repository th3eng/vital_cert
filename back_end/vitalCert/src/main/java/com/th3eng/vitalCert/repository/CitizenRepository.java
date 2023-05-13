package com.th3eng.vitalCert.repository;

import com.th3eng.vitalCert.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    //select citizen by ssn
    @Query("SELECT e FROM Citizen e WHERE e.ssn = ?1")
    Optional<Citizen> findBySsn(String ssn);

}
