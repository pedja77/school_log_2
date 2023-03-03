package com.iktpreobuka.schoollogtwo.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktpreobuka.schoollogtwo.entities.SemesterEntity;

public interface SemesterRepository extends CrudRepository<SemesterEntity, Integer> {

//	@Query("FROM SemesterEntity se"
//			+ "WHERE se.startDate <= :markDate "
//			+ "AND se.endDate >= :markDate")
	@Query(value = "SELECT * FROM semester s WHERE ?1 BETWEEN s.start_date AND s.end_date;", nativeQuery = true)
	public SemesterEntity findByMarkDate(LocalDate markDate);
	
	//public SemesterEntity findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate markDate);
}
