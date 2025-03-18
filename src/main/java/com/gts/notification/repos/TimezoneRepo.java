package com.gts.notification.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gts.notification.entities.TimeZoneEntity;


@Repository
public interface TimezoneRepo extends JpaRepository<TimeZoneEntity, Integer>{
	
	@Query(
			value = "select gts_timezone_id from GTS_TIMEZONES where gts_timezone_id= ?1",
			nativeQuery = true)
	Integer isTimezoneIdPresent( Integer gts_notification_timezone_id);
}