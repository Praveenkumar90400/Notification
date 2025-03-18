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
import com.gts.notification.model.response.NotificationGroups;

@Entity
@Table(name="GTS_NOTIFICATIONS_GROUP_SUBSCRIBERS")
public class GTSNotificationsGroupSubscribers {
	
	@Id
	private Long gts_notification_group_subscriber_id;
	private Long gts_user_id;
	private String gts_notification_group_id;
	
	public GTSNotificationsGroupSubscribers() {
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

	public List<GTSNotificationSubscriberGroups> getGts_notification_groups() {
		List<Long> groupIds=NotificationGroups.convertIdsToNotificationGroupID(gts_notification_group_id);
		return NotificationGroups.getNotificationGroup(groupIds);	
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getGts_notification_group_id() {
		return gts_notification_group_id;
	}

	public void setGts_notification_group_id(String gts_notification_group_id) {
		this.gts_notification_group_id = gts_notification_group_id;
	}
	
//	@ManyToOne(targetEntity=GTSNotificationSubscriberGroups.class,cascade = CascadeType.MERGE)
//    @JoinColumn(name = "gts_notification_group_id",referencedColumnName = "gts_notification_subscriber_group_id",insertable=false,updatable=false)
//	private GTSNotificationSubscriberGroups notificationSubscriberGroups;
	
	@ManyToOne(targetEntity=UserEntity.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gts_user_id",referencedColumnName = "gts_user_id",insertable=false,updatable=false)
	private UserEntity user;

//	public GTSNotificationSubscriberGroups getNotificationSubscriberGroups() {
//		return notificationSubscriberGroups;
//	}
//
//	public void setNotificationSubscriberGroups(GTSNotificationSubscriberGroups notificationSubscriberGroups) {
//		this.notificationSubscriberGroups = notificationSubscriberGroups;
//	}

	public UserEntity getUser() {
		return user;
	}

//	public void setUser(UserEntity user) {
//		this.user = user;
//	}
	
	

}
