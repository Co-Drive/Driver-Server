package io.driver.codrive.modules.record.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import io.driver.codrive.modules.record.model.RecordCountDto;

@Repository
public interface RecordRepositoryCustom {
	Page<Record> getMonthlyRecords(Long userId, LocalDate pivotDate, Pageable pageable);
	List<RecordCountDto> getYearlyRecordCountBoard(Long userId, LocalDate pivotDate);
	List<RecordCountDto> getMonthlyRecordCountBoard(Long userId, LocalDate pivotDate);
	Integer getRecordCountByWeek(Long userId, LocalDate pivotDate);
	List<Record> getRecentRecords(Long userId);
}
