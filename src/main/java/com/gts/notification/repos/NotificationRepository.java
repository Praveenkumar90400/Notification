package com.gts.notification.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.notification.entities.NotificationEntity;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

	@Query(
			value="SELECT max(gts_notification_id) FROM GTS_NOTIFICATIONS",
			nativeQuery=true)
	Long getMaxOfNotificationID();
	
	@Query(
			value="SELECT gts_notification_event_id FROM GTS_NOTIFICATIONS WHERE gts_notification_event_id=?1",
			nativeQuery=true)
	Long isNotificationEventIdPresent(Long gts_notification_event_id);
	
	@Query(
			value="SELECT gts_notification_id FROM GTS_NOTIFICATIONS WHERE gts_notification_id=?1",
			nativeQuery=true)
	Long isNotificationIdPresent(Long gts_notification_id);

	@Modifying
	@Transactional
	@Query(
			value="DELETE FROM GTS_NOTIFICATIONS WHERE gts_notification_event_id=:gts_notification_event_id",
			nativeQuery = true)
	        Integer deleteNotification(@Param (value="gts_notification_event_id")Long gts_notification_event_id);
}
