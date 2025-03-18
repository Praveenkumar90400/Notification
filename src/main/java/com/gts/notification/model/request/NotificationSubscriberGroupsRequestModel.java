package com.gts.notification.model.request;

public class NotificationSubscriberGroupsRequestModel {

	private Long gts_notification_subscriber_group_id;
	private String gts_notification_subscriber_group_name;
	private String gts_notification_subscriber_group_description;
	private boolean gts_notification_subscriber_group_status=true;
	
	public NotificationSubscriberGroupsRequestModel() {
		super();
	}

	public Long getGts_notification_subscriber_group_id() {
		return gts_notification_subscriber_group_id;
	}

	public void setGts_notification_subscriber_group_id(Long gts_notification_subscriber_group_id) {
		this.gts_notification_subscriber_group_id = gts_notification_subscriber_group_id;
	}

	public String getGts_notification_subscriber_group_name() {
		return gts_notification_subscriber_group_name;
	}

	public void setGts_notification_subscriber_group_name(String gts_notification_subscriber_group_name) {
		this.gts_notification_subscriber_group_name = gts_notification_subscriber_group_name;
	}

	public String getGts_notification_subscriber_group_description() {
		return gts_notification_subscriber_group_description;
	}

	public void setGts_notification_subscriber_group_description(String gts_notification_subscriber_group_description) {
		this.gts_notification_subscriber_group_description = gts_notification_subscriber_group_description;
	}

	public boolean isGts_notification_subscriber_group_status() {
		return gts_notification_subscriber_group_status;
	}

	public void setGts_notification_subscriber_group_status(boolean gts_notification_subscriber_group_status) {
		this.gts_notification_subscriber_group_status = gts_notification_subscriber_group_status;
	}
	
	
	
	
}
