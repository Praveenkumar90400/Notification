package com.gts.notification.service;

import java.util.List;
import java.util.Optional;

import com.gts.notification.entities.GTSNotificationSubscriberGroups;
import com.gts.notification.model.request.NotificationGroupActivateRequestModel;
import com.gts.notification.model.request.NotificationSubscriberGroupsRequestModel;
import com.gts.notification.model.response.JsonResponseModel;

public interface NotificationSubscriberGroupsService {

    JsonResponseModel createNotificationGroup(NotificationSubscriberGroupsRequestModel notificationGroupsRequestModel);
	
	JsonResponseModel updateNotificationGroup(NotificationSubscriberGroupsRequestModel notificationGroupsRequestModel);
	
	JsonResponseModel activateNotificationGroup(NotificationGroupActivateRequestModel notificationGroupActivateRequestModel);
	
	JsonResponseModel deactivateNotificationGroup(NotificationGroupActivateRequestModel notificationGroupActivateRequestModel);

	List<GTSNotificationSubscriberGroups> getAllNotificationGroups();
	
	Optional<GTSNotificationSubscriberGroups> findByNotificationGroupId(Long gts_notification_subscriber_group_id);
	
	List<GTSNotificationSubscriberGroups> findByNotificationGroupName(String gts_notification_subscriber_group_name);
	
	JsonResponseModel deleteNotificationGroup(Long gts_notification_subscriber_group_id);

	long generateNotificationGroupId();
	
	boolean isNotificationGroupIdPresent(Long gts_notification_subscriber_group_id);
	
	boolean isNotificationGroupNamePresent(String gts_notification_subscriber_group_name); 
	
	boolean isNotificationDeactivated(Long gts_notification_subscriber_group_id);

	List<GTSNotificationSubscriberGroups> getByIds(List<Long> gts_notification_subscriber_group_id);

	GTSNotificationSubscriberGroups getByNotificationGroupId(Long gts_notification_subscriber_group_id);
}
