package com.gts.notification.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.notification.entities.GTSNotificationEventSubscriberByRoles;

@Repository
public interface NotificationEventSubscriberByRolesRepo extends JpaRepository<GTSNotificationEventSubscriberByRoles,Long>{

	@Query(
			value="SELECT max(gts_notification_event_subscriber_by_role_id) FROM GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLES",
			nativeQuery=true)
	Long getMaxOfNotificationEventID();
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLES WHERE gts_notification_role_id=?1",
			nativeQuery=true)
	List<GTSNotificationEventSubscriberByRoles> getByNotificationByRoleId(Integer gts_notification_role_id);
	
	@Query(
			value="SELECT gts_notification_event_subscriber_by_role_id FROM GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLES WHERE gts_notification_event_subscriber_by_role_id=?1",
			nativeQuery=true)
	Long isEventSubscriberByRoleIdPresent(Long gts_notification_event_subscriber_by_role_id);
	
	@Query(
			value="SELECT gts_notification_role_id FROM GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLES WHERE gts_notification_role_id=?1",
			nativeQuery=true)
	Integer isRoleIdPresent(Integer gts_notification_role_id);

	@Modifying
	@Transactional
	@Query(
			value="DELETE FROM GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLES WHERE gts_notification_role_id=:gts_notification_role_id",
			nativeQuery = true)
	        Integer deleteEventSubscriberByRoleId(@Param (value="gts_notification_role_id")Integer gts_notification_role_id);
}

