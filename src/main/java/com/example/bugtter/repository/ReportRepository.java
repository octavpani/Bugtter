package com.example.bugtter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bugtter.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
