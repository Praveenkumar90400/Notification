package com.gts.notification.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GTS_NOTIFICATION_EVENTS")
public class GTSNotificationEvents {
	
	@Id
	private Long gts_notification_event_id;
	private String gts_notification_event_name;
	private String gts_notification_event_description;
	private boolean gts_notification_event_status=true;
	
	public GTSNotificationEvents() {
		super();
	}

	public Long getGts_notification_event_id() {
		return gts_notification_event_id;
	}

	public void setGts_notification_event_id(Long gts_notification_event_id) {
		this.gts_notification_event_id = gts_notification_event_id;
	}

	public String getGts_notification_event_name() {
		return gts_notification_event_name;
	}

	public void setGts_notification_event_name(String gts_notification_event_name) {
		this.gts_notification_event_name = gts_notification_event_name;
	}

//	public boolean isGts_notification_event_status() {
//		return gts_notification_event_status;
//	}

	public void setGts_notification_event_status(boolean gts_notification_event_status) {
		this.gts_notification_event_status = gts_notification_event_status;
	}
	
	public String getGts_notification_event_description() {
		return gts_notification_event_description;
	}

	public void setGts_notification_event_description(String gts_notification_event_description) {
		this.gts_notification_event_description = gts_notification_event_description;
	}

//	@OneToMany(mappedBy = "notificationEvents", fetch = FetchType.LAZY,
//            cascade = CascadeType.MERGE)
//    private Set<GTSNotificationEventSubscribers> notificationEventSubscribers;
	
//	@OneToMany(mappedBy = "notificationEvents", fetch = FetchType.LAZY,
//            cascade = CascadeType.MERGE)
//    private Set<GTSNotificationEventSubscriberByRoles> notificationEventSubscriberByRoles;

}
