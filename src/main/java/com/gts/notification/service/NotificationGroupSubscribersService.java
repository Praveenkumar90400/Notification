package com.gts.notification.service;

import java.util.List;

import com.gts.notification.entities.GTSNotificationsGroupSubscribers;
import com.gts.notification.model.request.NotificationGroupSubscriberRequestModel;
import com.gts.notification.model.response.JsonResponseModel;

public interface NotificationGroupSubscribersService {

    JsonResponseModel addNotificationSubscriber(NotificationGroupSubscriberRequestModel notificationGroupSubscriberRequestModel);
	
	List<GTSNotificationsGroupSubscribers> findByNotificationUserId(Long gts_user_id);
	
	JsonResponseModel deleteNotificationGroupByUserId(Long gts_user_id);
	
	JsonResponseModel deleteNotificationGroupById(Long gts_notification_group_subscriber_id);

	long generateNotificationGroupId();
	
	boolean isNotificationGroupIdPresent(Long gts_notification_group_id);
	
	boolean isNotificationUserIdPresent(Long gts_user_id);
	
	boolean isNotificationGroupSubscriberIdPresent(Long gts_notification_group_subscriber_id);
	
	boolean isUserIdPresent(Long gts_user_id);
}
