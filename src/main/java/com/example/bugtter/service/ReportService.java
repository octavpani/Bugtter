package com.example.bugtter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.bugtter.exception.NotFoundStatusException;
import com.example.bugtter.form.ReportQuery;
import com.example.bugtter.model.Report;
import com.example.bugtter.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;

//	public Page<Attendance> getYourAttendance(Pageable pageable, AttendanceQuery aq, Principal principal) {
//		boolean anyYear = aq.getYear() == null;
//		boolean anyMonth = aq.getMonth() == null;
//		boolean anyDay = aq.getDay() == null;
//		return attendanceRepository.findYourAttendance(principal.getName(), anyYear, aq.getYear(), anyMonth,
//				aq.getMonth(), anyDay, aq.getDay(), pageable);
//	}

	public Page<Report> findReports(Pageable pageable, ReportQuery repoQue) {
		//文字列の検索が失敗。ページングでの表示は成功。次は、2ページ目以降の処理をするよ。
		if (repoQue.getUrgency() == null) {
			repoQue.setUrgency(0);
		}
		if (repoQue.getStatus_Id() == null) {
			repoQue.setStatus_Id(0);
		}
		boolean anyTitle = repoQue.getTitle() == "";
		boolean anyUrgency = repoQue.getUrgency() == 0;
		boolean anyStatus_Id = repoQue.getStatus_Id() == 0;
		String title = anyTitle? "" : ("%" + repoQue.getTitle() + "%");
		Integer urgency = anyUrgency ? 0 : repoQue.getUrgency();
		Integer status_Id = anyStatus_Id ? 0 : repoQue.getStatus_Id();

		return reportRepository.findReports(title, anyTitle,
				urgency, anyUrgency, status_Id, anyStatus_Id, pageable);
	}


	public Report verifyReport(Long id) {
		Optional<Report> maybeReport = reportRepository.findById(id);
		return maybeReport.orElseThrow(NotFoundStatusException::new);
	}

	public void save(Report report) {
		reportRepository.saveAndFlush(report);
	}

	public void delete(Long id) {
		reportRepository.deleteById(id);
	}
}
