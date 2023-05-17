package com.th3eng.vitalCert.repository;

import com.th3eng.vitalCert.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
