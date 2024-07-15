package com.example.settlement.advertisement.repository.read;

import com.example.settlement.advertisement.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AdReadRepository extends JpaRepository<Advertisement, Long> {
}
