package com.example.EcivilApi.repository;

import com.example.EcivilApi.models.Acten;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActenRepo extends JpaRepository<Acten,Long> {
    Acten findByNumvolet(String numvolet);

}
