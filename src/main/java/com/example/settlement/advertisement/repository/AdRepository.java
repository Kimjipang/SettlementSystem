package com.example.settlement.advertisement.repository;


import com.example.settlement.advertisement.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Advertisement, Long> {
}
