package com.gts.notification.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.gts.notification.model.response.NotificationEvents;

@Entity
@Table(name="GTS_NOTIFICATION_EVENT_SUBSCRIBERS")
public class GTSNotificationEventSubscribers {

	@Id
	private Long gts_notification_event_subscriber_id;
	private String gts_notification_event_id;
	private Long gts_user_id;
	
	public GTSNotificationEventSubscribers() {
		super();
	}

	public Long getGts_notification_event_subscriber_id() {
		return gts_notification_event_subscriber_id;
	}

	public void setGts_notification_event_subscriber_id(Long gts_notification_event_subscriber_id) {
		this.gts_notification_event_subscriber_id = gts_notification_event_subscriber_id;
	}

	public List<GTSNotificationEvents> getGts_notification_events() {
		List<Long> eventIds=NotificationEvents.convertIdsToNotificationEventID(gts_notification_event_id);
		return NotificationEvents.getNotificationEvent(eventIds);	
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
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
	
//	@ManyToOne(targetEntity=GTSNotificationEvents.class,cascade = CascadeType.MERGE)
//    @JoinColumn(name = "gts_notification_event_id",referencedColumnName = "gts_notification_event_id",insertable=false,updatable=false)
//	private GTSNotificationEvents notificationEvents;
	
	@ManyToOne(targetEntity=UserEntity.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gts_user_id",referencedColumnName = "gts_user_id",insertable=false,updatable=false)
	private UserEntity user;

//	public GTSNotificationEvents getNotificationEvents() {
//		return notificationEvents;
//	}
//
//	public void setNotificationEvents(GTSNotificationEvents notificationEvents) {
//		this.notificationEvents = notificationEvents;
//	}

	public UserEntity getUser() {
		return user;
	}

//	public void setUser(UserEntity user) {
//		this.user = user;
//	}
	
	
}
