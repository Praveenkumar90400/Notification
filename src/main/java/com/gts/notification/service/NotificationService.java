package com.gts.notification.service;

import java.util.Optional;

import com.gts.notification.entities.NotificationEntity;
import com.gts.notification.model.request.NotificationRequestModel;
import com.gts.notification.model.response.JsonResponseModel;

public interface NotificationService {
	
	
	JsonResponseModel createNotification(NotificationRequestModel notificationRequestModel);
	
	Optional<NotificationEntity> findByNotificationId(Long gts_notification_id);
	
	JsonResponseModel deleteNotificationById(Long gts_notification_id);
	
	JsonResponseModel deleteNotificationByEventId(Long gts_notification_event_id);

	long generateNotificationId();
	
	boolean isNotificationIdPresent(Long gts_notification_id);
	
	boolean isNotificationEventIdPresent(Long gts_notification_event_id); 
	
	boolean isTimezoneIdPresent(Integer gts_notification_timezone_id);
}
