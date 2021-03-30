package com.example.bugtter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bugtter.model.Status;
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
