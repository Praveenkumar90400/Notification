package com.gts.notification.service;

import java.util.List;

import com.gts.notification.entities.GTSNotificationEventSubscriberByRoles;
import com.gts.notification.model.request.NotificationEventSubscriberByRolesRequestModel;
import com.gts.notification.model.response.JsonResponseModel;

public interface NotificationEventSubscribersByRolesService {

    JsonResponseModel addNotificationSubscriberByRole(NotificationEventSubscriberByRolesRequestModel notificationEventSubscriberByRolesRequestModel);
	
	List<GTSNotificationEventSubscriberByRoles> findByNotificationRoleId(Integer gts_notification_role_id);
	
	JsonResponseModel deleteNotificationEventByRoleId(Integer gts_notification_role_id);
	
	JsonResponseModel deleteNotificationEventById(Long gts_notification_event_subscriber_by_role_id);

	long generateNotificationEventId();
	
	boolean isNotificationEventIdPresent(Long gts_notification_event_id);
	
	boolean isNotificationRoleIdPresent(Integer gts_notification_role_id);
	
	boolean isNotificationEventByRoleIdPresent(Long gts_notification_event_subscriber_by_role_id); 
	
	boolean isRoleIdPresent(Integer gts_notification_role_id);
}
