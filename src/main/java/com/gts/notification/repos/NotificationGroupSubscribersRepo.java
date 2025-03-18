package com.gts.notification.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.notification.entities.GTSNotificationsGroupSubscribers;

@Repository
public interface NotificationGroupSubscribersRepo extends JpaRepository<GTSNotificationsGroupSubscribers,Long>{

	@Query(
			value="SELECT max(gts_notification_group_subscriber_id) FROM GTS_NOTIFICATIONS_GROUP_SUBSCRIBERS",
			nativeQuery=true)
	Long getMaxOfNotificationID();
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATIONS_GROUP_SUBSCRIBERS WHERE gts_user_id=?1",
			nativeQuery=true)
	List<GTSNotificationsGroupSubscribers> getNotificationByUserId(Long gts_user_id);
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATIONS_GROUP_SUBSCRIBERS WHERE gts_user_id=?1",
			nativeQuery=true)
	List<GTSNotificationsGroupSubscribers> isUserIdPresent(Long gts_user_id);
	
	@Query(
			value="SELECT gts_notification_group_subscriber_id FROM GTS_NOTIFICATIONS_GROUP_SUBSCRIBERS WHERE gts_notification_group_subscriber_id=?1",
			nativeQuery=true)
	Long isEventSubscriberIdPresent(Long gts_notification_group_subscriber_id);

	@Modifying
	@Transactional
	@Query(
			value="DELETE FROM GTS_NOTIFICATIONS_GROUP_SUBSCRIBERS WHERE gts_user_id=:gts_user_id",
			nativeQuery = true)
	        Integer deleteGroupSubscriberByUserId(@Param (value="gts_user_id")Long gts_user_id);
}
