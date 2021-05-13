package com.example.bugtter.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bugtter.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	Optional<Report> findById(Long id);

	//@Query を使用して、クエリメソッドでページネーションのネイティブカウントクエリを宣言します。
//	@Query(value = "SELECT * FROM attendance WHERE (username LIKE :username) AND (:anyYear OR year = :year) AND (:anyMonth OR month = :month) AND (:anyDay OR day = :day)",
//			countQuery = "SELECT COUNT(*) FROM attendance WHERE (username LIKE :username) AND (:anyYear OR year = :year) AND (:anyMonth OR month = :month) AND (:anyDay OR day = :day)",
//			nativeQuery = true)
//	Page<Attendance> findYourAttendance(@Param("username") String username,
//			@Param("anyYear") boolean anyYear, @Param("year") Integer year,
//			@Param("anyMonth") boolean anyMonth, @Param("month") Integer month,
//			@Param("anyDay") boolean anyDay, @Param("day") Integer day,
//			Pageable pageable);

	@Query(value = "SELECT * FROM report WHERE (:anyTitle OR title LIKE :title) AND (:anyUrgency OR urgency = :urgency) AND (:anyStatus_Id OR status_id = :status_Id)",
			countQuery = "SELECT COUNT(*) FROM report WHERE (:anyTitle OR title LIKE :title) AND (:anyUrgency OR urgency = :urgency) AND (:anyStatus_Id OR status_id = :status_Id)",
			nativeQuery = true)
		Page<Report> findReports(@Param("title") String title,
			@Param("anyTitle") boolean anyTitle, @Param("urgency") Integer urgency, @Param("anyUrgency") boolean anyUrgency,
			@Param("status_Id") Integer status_Id, @Param("anyStatus_Id") boolean any_Status_Id,
			Pageable pageable);

}
