package com.gts.notification.model.request;

public class NotificationGroupSubscriberRequestModel {

	private Long gts_notification_group_subscriber_id;
	private Long gts_user_id;
	private String gts_notification_group_id;
	
	public NotificationGroupSubscriberRequestModel() {
		super();
	}

	public Long getGts_notification_group_subscriber_id() {
		return gts_notification_group_subscriber_id;
	}

	public void setGts_notification_group_subscriber_id(Long gts_notification_group_subscriber_id) {
		this.gts_notification_group_subscriber_id = gts_notification_group_subscriber_id;
	}

	public Long getGts_user_id() {
		return gts_user_id;
	}

	public void setGts_user_id(Long gts_user_id) {
		this.gts_user_id = gts_user_id;
	}

	public String getGts_notification_group_id() {
		return gts_notification_group_id;
	}

	public void setGts_notification_group_id(String gts_notification_group_id) {
		this.gts_notification_group_id = gts_notification_group_id;
	}

}
