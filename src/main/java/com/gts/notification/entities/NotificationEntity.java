package com.gts.notification.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="GTS_NOTIFICATIONS")

public class NotificationEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long gts_notification_id;
	private Long gts_notification_event_id;
	private String gts_notification_message;
	private Long gts_notification_receiver_user_id;
	private Integer gts_notification_receiver_role_id;
	private LocalDateTime gts_notification_date=LocalDateTime.now();
	private Integer gts_notification_timezone_id;
	
	public NotificationEntity() {
		super();
	}

	public Long getGts_notification_id() {
		return gts_notification_id;
	}

	public void setGts_notification_id(Long gts_notification_id) {
		this.gts_notification_id = gts_notification_id;
	}

	public Long getGts_notification_event_id() {
		return gts_notification_event_id;
	}

	public void setGts_notification_event_id(Long gts_notification_event_id) {
		this.gts_notification_event_id = gts_notification_event_id;
	}

	public String getGts_notification_message() {
		return gts_notification_message;
	}

	public void setGts_notification_message(String gts_notification_message) {
		this.gts_notification_message = gts_notification_message;
	}

	public Long getGts_notification_receiver_user_id() {
		return gts_notification_receiver_user_id;
	}

	public void setGts_notification_receiver_user_id(Long gts_notification_receiver_user_id) {
		this.gts_notification_receiver_user_id = gts_notification_receiver_user_id;
	}

	public Integer getGts_notification_receiver_role_id() {
		return gts_notification_receiver_role_id;
	}

	public void setGts_notification_receiver_role_id(Integer gts_notification_receiver_role_id) {
		this.gts_notification_receiver_role_id = gts_notification_receiver_role_id;
	}

	public LocalDateTime getGts_notification_date() {
		return gts_notification_date;
	}

	public void setGts_notification_date(LocalDateTime gts_notification_date) {
		this.gts_notification_date = gts_notification_date;
	}	
	
	public Integer getGts_notification_timezone_id() {
		return gts_notification_timezone_id;
	}

	public void setGts_notification_timezone_id(Integer gts_notification_timezone_id) {
		this.gts_notification_timezone_id = gts_notification_timezone_id;
	}

	@ManyToOne(targetEntity=GTSNotificationEvents.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gts_notification_event_id",referencedColumnName = "gts_notification_event_id",insertable=false,updatable=false)
	private GTSNotificationEvents notificationEvents;
	
	@ManyToOne(targetEntity=TimeZoneEntity.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gts_notification_timezone_id",referencedColumnName = "gts_timezone_id",insertable=false,updatable=false)
	private TimeZoneEntity timezone;

	public GTSNotificationEvents getNotificationEvents() {
		return notificationEvents;
	}

//	public void setNotificationEvents(GTSNotificationEvents notificationEvents) {
//		this.notificationEvents = notificationEvents;
//	}

	public TimeZoneEntity getTimezone() {
		return timezone;
	}

//	public void setTimezone(TimeZoneEntity timezone) {
//		this.timezone = timezone;
//	}
	
	
	
}
