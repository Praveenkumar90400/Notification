package com.gts.notification.service;

import java.util.List;

import com.gts.notification.entities.GTSNotificationEventSubscribers;
import com.gts.notification.model.request.NotificationEventSubscribersRequestModel;
import com.gts.notification.model.response.JsonResponseModel;

public interface NotificationEventSubscribersService {

    JsonResponseModel addNotificationSubscriber(NotificationEventSubscribersRequestModel notificationEventSubscribersRequestModel);
	
	List<GTSNotificationEventSubscribers> findByNotificationUserId(Long gts_user_id);
	
	JsonResponseModel deleteNotificationEventByUserId(Long gts_user_id);
	
	JsonResponseModel deleteNotificationEventById(Long gts_notification_event_subscriber_id);

	long generateNotificationEventId();
	
	boolean isNotificationEventIdPresent(Long gts_notification_event_id);
	
	boolean isNotificationUserIdPresent(Long gts_user_id);
	
	boolean isNotificationEventSubscriberIdPresent(Long gts_notification_event_subscriber_id);
	
	boolean isUserIdPresent(Long gts_user_id);
}
