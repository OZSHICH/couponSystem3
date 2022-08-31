package com.OzProject.couponSystem.repository;

import com.OzProject.couponSystem.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {


    boolean existsByEmail(String email);


    boolean existsByEmailAndPassword(String email, String password);

    Company findByEmail(String email);

    Optional<Company> findByEmailAndPassword(String email, String password);

    boolean existsByName(String name);

}

