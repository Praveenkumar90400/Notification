package com.gts.notification.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.notification.entities.GTSNotificationEvents;

@Repository
public interface NotificationEventsRepo extends JpaRepository<GTSNotificationEvents,Long> {
	
	@Query(
			value="SELECT max(gts_notification_event_id) FROM GTS_NOTIFICATION_EVENTS",
			nativeQuery=true)
	Long getMaxOfNotificationEventID();
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_EVENTS WHERE gts_notification_event_name like %:gts_notification_event_name%",
			nativeQuery=true)
	List<GTSNotificationEvents> getByNotificationName(String gts_notification_event_name);
	
	@Query(
			value="SELECT gts_notification_event_id FROM GTS_NOTIFICATION_EVENTS WHERE gts_notification_event_id=?1",
			nativeQuery=true)
	Long isNotificationEventIdPresent(Long gts_notification_event_id);
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_EVENTS WHERE gts_notification_event_name=:gts_notification_event_name",
			nativeQuery=true)
	List<GTSNotificationEvents> isNotificationNamePresent(String gts_notification_event_name);
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_EVENTS WHERE gts_notification_event_id=?1 AND gts_notification_event_status=false",
			nativeQuery=true)
	List<GTSNotificationEvents> isNotificationDeactivated(Long gts_notification_event_id);
	
	
	@Modifying
	@Transactional
	@Query(
			value="UPDATE GTS_NOTIFICATION_EVENTS SET gts_notification_event_status=1 WHERE gts_notification_event_id=:gts_notification_event_id",
			nativeQuery = true)
	        Integer activateNotification(@Param (value="gts_notification_event_id")Long gts_notification_event_id);

	@Modifying
	@Transactional
	@Query(
			value="UPDATE GTS_NOTIFICATION_EVENTS SET gts_notification_event_status=0 WHERE gts_notification_event_id=:gts_notification_event_id",
			nativeQuery = true)
	        Integer deactivateNotification(@Param (value="gts_notification_event_id")Long gts_notification_event_id);

	@Query(
			  value = "SELECT * FROM GTS_NOTIFICATION_EVENTS  WHERE gts_notification_event_id IN (:gts_notification_event_id)", 
			  nativeQuery = true)
	List<GTSNotificationEvents> findByNotificationEventIds(@Param ("gts_notification_event_id") List<Long> gts_notification_event_id);
	
	@Query(
	      value="SELECT * FROM GTS_NOTIFICATION_EVENTS WHERE gts_notification_event_id=?1",
			nativeQuery=true)
	GTSNotificationEvents findByNotificationEventId(Long gts_notification_event_id);

}
