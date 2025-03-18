package com.gts.notification.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.notification.entities.GTSNotificationEventSubscriberByRoles;
import com.gts.notification.model.request.NotificationEventSubscriberByRolesRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.repos.NotificationEventSubscriberByRolesRepo;
import com.gts.notification.repos.NotificationEventsRepo;
import com.gts.notification.repos.RoleRepository;
import com.gts.notification.service.NotificationEventSubscribersByRolesService;


@Service
public class NotificationEventSubscribersByRolesServiceImpl implements NotificationEventSubscribersByRolesService  {

	@Autowired
	NotificationEventsRepo notificationEventsRepo;
	
	@Autowired
	NotificationEventSubscriberByRolesRepo notificationEventSubscriberByRolesRepo;
	
	@Autowired
	RoleRepository roleRepo;
	

	@Override
	public JsonResponseModel addNotificationSubscriberByRole(NotificationEventSubscriberByRolesRequestModel notificationEventSubscriberByRolesRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		 ModelMapper modelMapper = new ModelMapper();
		 GTSNotificationEventSubscriberByRoles notificationEvent = modelMapper.map(notificationEventSubscriberByRolesRequestModel,GTSNotificationEventSubscriberByRoles.class);
		try {
			notificationEventSubscriberByRolesRepo.save(notificationEvent);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event subscriber by role added successfully");
			jsonResponseModel.setStatus_code("201");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to add a notification event subscriber by role");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public List<GTSNotificationEventSubscriberByRoles> findByNotificationRoleId(Integer gts_notification_role_id) {
		return notificationEventSubscriberByRolesRepo.getByNotificationByRoleId(gts_notification_role_id);
	}

	@Override
	public JsonResponseModel deleteNotificationEventByRoleId(Integer gts_notification_role_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationEventSubscriberByRolesRepo.deleteEventSubscriberByRoleId(gts_notification_role_id);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event subscriber by role deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event subscriber by role");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel deleteNotificationEventById(Long gts_notification_event_subscriber_by_role_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationEventSubscriberByRolesRepo.deleteById(gts_notification_event_subscriber_by_role_id);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event subscriber by role deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event subscriber by role");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public long generateNotificationEventId() {
		 long numberOfNotification = 0;
		 if(notificationEventSubscriberByRolesRepo.getMaxOfNotificationEventID()!=null)
		 {
			 numberOfNotification=notificationEventSubscriberByRolesRepo.getMaxOfNotificationEventID();
		 }
		return numberOfNotification;
	}

	@Override
	public boolean isNotificationEventIdPresent(Long gts_notification_event_id) {
		Long isNotificationEventIdPresent=notificationEventsRepo.isNotificationEventIdPresent(gts_notification_event_id);
		if(isNotificationEventIdPresent==gts_notification_event_id) return true;
		return false;
	}

	@Override
	public boolean isNotificationRoleIdPresent(Integer gts_notification_role_id) {
		Integer isNotificationRoleIdPresent=roleRepo.findRoleIdByNative(gts_notification_role_id);
		if(isNotificationRoleIdPresent==gts_notification_role_id) return true;
		return false;
	}

	@Override
	public boolean isNotificationEventByRoleIdPresent(Long gts_notification_event_subscriber_by_role_id) {
		Long isNotificationEventByRoleIdPresent=notificationEventSubscriberByRolesRepo.isEventSubscriberByRoleIdPresent(gts_notification_event_subscriber_by_role_id);
		if(isNotificationEventByRoleIdPresent==gts_notification_event_subscriber_by_role_id) return true;
		return false;
	}

	@Override
	public boolean isRoleIdPresent(Integer gts_notification_role_id) {
		Integer isRoleIdPresent=notificationEventSubscriberByRolesRepo.isRoleIdPresent(gts_notification_role_id);
		if(isRoleIdPresent==gts_notification_role_id) return true;
		return false;
	}

}
