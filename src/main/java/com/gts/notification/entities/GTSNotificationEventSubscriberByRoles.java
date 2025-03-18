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
@Table(name="GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLES")
public class GTSNotificationEventSubscriberByRoles {

	@Id
	private Long gts_notification_event_subscriber_by_role_id;
	private Integer gts_notification_role_id;
	private String gts_notification_event_id;
	
	public GTSNotificationEventSubscriberByRoles() {
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
	
//	@ManyToOne(targetEntity=GTSNotificationEvents.class,cascade = CascadeType.MERGE)
//    @JoinColumn(name = "gts_notification_event_id",referencedColumnName = "gts_notification_event_id",insertable=false,updatable=false)
//	private GTSNotificationEvents notificationEvents;
	
	@ManyToOne(targetEntity=RoleEntity.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gts_notification_role_id",referencedColumnName = "gts_role_id",insertable=false,updatable=false)
	private RoleEntity roles;

//	public GTSNotificationEvents getNotificationEvents() {
//		return notificationEvents;
//	}
//
//	public void setNotificationEvents(GTSNotificationEvents notificationEvents) {
//		this.notificationEvents = notificationEvents;
//	}

	public RoleEntity getRoles() {
		return roles;
	}

//	public void setRoles(RoleEntity roles) {
//		this.roles = roles;
//	}
	
	
}

