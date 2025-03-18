package com.gts.notification.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.notification.entities.GTSNotificationSubscriberGroups;


@Repository
public interface NotificationSubscriberGroupsRepo extends JpaRepository<GTSNotificationSubscriberGroups,Long>{

	@Query(
			value="SELECT max(gts_notification_subscriber_group_id) FROM GTS_NOTIFICATION_SUBSCRIBER_GROUPS",
			nativeQuery=true)
	Long getMaxOfNotificationGroupID();
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_SUBSCRIBER_GROUPS WHERE gts_notification_subscriber_group_name like %:gts_notification_subscriber_group_name%",
			nativeQuery=true)
	List<GTSNotificationSubscriberGroups> getByNotificationGroupName(String gts_notification_subscriber_group_name);
	
	@Query(
			value="SELECT gts_notification_subscriber_group_id FROM GTS_NOTIFICATION_SUBSCRIBER_GROUPS WHERE gts_notification_subscriber_group_id=?1",
			nativeQuery=true)
	Long isNotificationGroupIdPresent(Long gts_notification_subscriber_group_id);
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_SUBSCRIBER_GROUPS WHERE gts_notification_subscriber_group_id=?1 and gts_notification_subscriber_group_status=false",
			nativeQuery=true)
	List<GTSNotificationSubscriberGroups> isNotificationDeactivated(Long gts_notification_subscriber_group_id);
	
	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_SUBSCRIBER_GROUPS WHERE gts_notification_subscriber_group_name=?1",
			nativeQuery=true)
	List<GTSNotificationSubscriberGroups> isNotificationGroupNamePresent(String gts_notification_subscriber_group_name);
	
	@Modifying
	@Transactional
	@Query(
			value="UPDATE GTS_NOTIFICATION_SUBSCRIBER_GROUPS SET gts_notification_subscriber_group_status=1 WHERE gts_notification_subscriber_group_id=:gts_notification_subscriber_group_id",
			nativeQuery = true)
	        Integer activateNotification(@Param (value="gts_notification_subscriber_group_id")Long gts_notification_subscriber_group_id);

	@Modifying
	@Transactional
	@Query(
			value="UPDATE GTS_NOTIFICATION_SUBSCRIBER_GROUPS SET gts_notification_subscriber_group_status=0 WHERE gts_notification_subscriber_group_id=:gts_notification_subscriber_group_id",
			nativeQuery = true)
	        Integer deactivateNotification(@Param (value="gts_notification_subscriber_group_id")Long gts_notification_subscriber_group_id);

	@Query(
			value="SELECT * FROM GTS_NOTIFICATION_SUBSCRIBER_GROUPS WHERE gts_notification_subscriber_group_id=?1",
			nativeQuery=true)
	GTSNotificationSubscriberGroups findByNotificationGroupId(Long gts_notification_subscriber_group_id);
	
	
	@Query(
			  value = "SELECT * FROM GTS_NOTIFICATION_SUBSCRIBER_GROUPS  WHERE gts_notification_subscriber_group_id IN (:gts_notification_subscriber_group_id)", 
			  nativeQuery = true)
	List<GTSNotificationSubscriberGroups> findByNotificationGroupIds(List<Long> gts_notification_subscriber_group_id);
}
