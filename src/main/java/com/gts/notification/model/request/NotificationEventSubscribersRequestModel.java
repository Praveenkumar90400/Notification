package com.gts.notification.model.request;

public class NotificationEventSubscribersRequestModel {

	private Long gts_notification_event_subscriber_id;
	private String gts_notification_event_id;
	private Long gts_user_id;
	
	public NotificationEventSubscribersRequestModel() {
		super();
	}


	public Long getGts_notification_event_subscriber_id() {
		return gts_notification_event_subscriber_id;
	}


	public void setGts_notification_event_subscriber_id(Long gts_notification_event_subscriber_id) {
		this.gts_notification_event_subscriber_id = gts_notification_event_subscriber_id;
	}


	public String getGts_notification_event_id() {
		return gts_notification_event_id;
	}

	public void setGts_notification_event_id(String gts_notification_event_id) {
		this.gts_notification_event_id = gts_notification_event_id;
	}

	public Long getGts_user_id() {
		return gts_user_id;
	}

	public void setGts_user_id(Long gts_user_id) {
		this.gts_user_id = gts_user_id;
	}
	
	
	
	
}
