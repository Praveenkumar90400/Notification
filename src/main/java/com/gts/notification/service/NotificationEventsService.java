package com.gts.notification.service;

import java.util.List;
import java.util.Optional;

import com.gts.notification.entities.GTSNotificationEvents;
import com.gts.notification.model.request.NotificationEventActivateRequestModel;
import com.gts.notification.model.request.NotificationEventsRequestModel;
import com.gts.notification.model.response.JsonResponseModel;

public interface NotificationEventsService {
	
	JsonResponseModel createNotificationEvent(NotificationEventsRequestModel notificationEventsRsequestModel);
	
	JsonResponseModel updateNotificationEvent(NotificationEventsRequestModel notificationEventsrequestModel);
	
	JsonResponseModel activateNotificationEvent(NotificationEventActivateRequestModel notificationEventActivateRequestModel);
	
	JsonResponseModel deactivateNotificationEvent(NotificationEventActivateRequestModel notificationEventActivateRequestModel);

	List<GTSNotificationEvents> getAllNotificationEvents();
	
	Optional<GTSNotificationEvents> findByNotificationId(Long gts_notification_event_id);
	
	List<GTSNotificationEvents> findByNotificationName(String gts_notification_event_name);
	
	JsonResponseModel deleteNotificationEvent(Long gts_notification_event_id);

	long generateNotificationEventId();
	
	boolean isNotificationIdPresent(Long gts_notification_event_id);
	
	boolean isNotificationNamePresent(String gts_notification_event_name);
	
	boolean isNotificationDeactivated(Long gts_notification_event_id);

	GTSNotificationEvents getByNotificationEventId(Long gts_notification_event_id);

	List<GTSNotificationEvents> getByIds(List<Long> gts_notification_event_ids);
}
