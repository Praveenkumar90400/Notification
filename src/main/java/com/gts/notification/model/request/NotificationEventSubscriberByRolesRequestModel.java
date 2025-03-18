package com.gts.notification.model.request;

public class NotificationEventSubscriberByRolesRequestModel {

	private Long gts_notification_event_subscriber_by_role_id;
	private Integer gts_notification_role_id;
	private String gts_notification_event_id;
	
	public NotificationEventSubscriberByRolesRequestModel() {
		super();
	}

	public Long getGts_notification_event_subscriber_by_role_id() {
		return gts_notification_event_subscriber_by_role_id;
	}

	public void setGts_notification_event_subscriber_by_role_id(Long gts_notification_event_subscriber_by_role_id) {
		this.gts_notification_event_subscriber_by_role_id = gts_notification_event_subscriber_by_role_id;
	}

	public Integer getGts_notification_role_id() {
		return gts_notification_role_id;
	}

	public void setGts_notification_role_id(Integer gts_notification_role_id) {
		this.gts_notification_role_id = gts_notification_role_id;
	}

	public String getGts_notification_event_id() {
		return gts_notification_event_id;
	}

	public void setGts_notification_event_id(String gts_notification_event_id) {
		this.gts_notification_event_id = gts_notification_event_id;
	}
	
	
	
	
}
