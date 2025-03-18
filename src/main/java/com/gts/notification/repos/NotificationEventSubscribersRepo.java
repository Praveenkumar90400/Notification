package com.gts.notification.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.notification.entities.GTSNotificationEventSubscribers;

@Repository
public interface NotificationEventSubscribersRepo extends JpaRepository<GTSNotificationEventSubscribers,Long>{

	@Query(
			value="SELECT max(gts_notification_event_subscriber_id) FROM GTS_NOTIFICATION_EVENT_SUBSCRIBERS",
			nativeQuery=true)
	Long getMaxOfNotificationEventID();
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_EVENT_SUBSCRIBERS WHERE gts_user_id=?1",
			nativeQuery=true)
	List<GTSNotificationEventSubscribers> getNotificationByUserId(Long gts_user_id);
	
	@Query(
			value="SELECT gts_notification_event_subscriber_id FROM GTS_NOTIFICATION_EVENT_SUBSCRIBERS WHERE gts_notification_event_subscriber_id=?1",
			nativeQuery=true)
	Long isEventSubscriberIdPresent(Long gts_notification_event_subscriber_id);
	
	@Query(
			value="SELECT gts_user_id FROM GTS_NOTIFICATION_EVENT_SUBSCRIBERS WHERE gts_user_id=?1",
			nativeQuery=true)
	Long isUserIdPresent(Long gts_user_id);

	@Modifying
	@Transactional
	@Query(
			value="DELETE FROM GTS_NOTIFICATION_EVENT_SUBSCRIBERS WHERE gts_user_id=:gts_user_id",
			nativeQuery = true)
	        Integer deleteEventSubscriberByUserId(@Param (value="gts_user_id") Long gts_user_id);
}

