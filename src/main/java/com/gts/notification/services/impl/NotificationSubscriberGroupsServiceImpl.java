package com.gts.notification.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.notification.entities.GTSNotificationSubscriberGroups;
import com.gts.notification.model.request.NotificationGroupActivateRequestModel;
import com.gts.notification.model.request.NotificationSubscriberGroupsRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.repos.NotificationSubscriberGroupsRepo;
import com.gts.notification.service.NotificationSubscriberGroupsService;

@Service
public class NotificationSubscriberGroupsServiceImpl implements NotificationSubscriberGroupsService{

	@Autowired
	NotificationSubscriberGroupsRepo notificationSubscriberGroupsRepo;

	@Override
	public JsonResponseModel createNotificationGroup(NotificationSubscriberGroupsRequestModel notificationGroupsRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		 ModelMapper modelMapper = new ModelMapper();
		 GTSNotificationSubscriberGroups notificationEvent = modelMapper.map(notificationGroupsRequestModel,GTSNotificationSubscriberGroups.class);
		try {
			notificationSubscriberGroupsRepo.save(notificationEvent);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification subscriber group created successfully");
			jsonResponseModel.setStatus_code("201");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to create a notification subscriber group ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel updateNotificationGroup(NotificationSubscriberGroupsRequestModel notificationGroupRequestModel){
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		 ModelMapper modelMapper = new ModelMapper();
		 GTSNotificationSubscriberGroups notificationEvent = modelMapper.map(notificationGroupRequestModel,GTSNotificationSubscriberGroups.class);
		try {
			notificationSubscriberGroupsRepo.save(notificationEvent);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification subscriber group updated successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to update a notification subscriber group ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel activateNotificationGroup(NotificationGroupActivateRequestModel notificationGroupActivateRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
//		 ModelMapper modelMapper = new ModelMapper();
//		 GTSNotificationSubscriberGroups notificationEvent = modelMapper.map(NotificationActivateRequestModel,GTSNotificationSubscriberGroups.class);
		try {
			notificationSubscriberGroupsRepo.activateNotification(notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id());
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification subscriber group activated successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to activate a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel deactivateNotificationGroup(NotificationGroupActivateRequestModel notificationGroupActivateRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
//		 ModelMapper modelMapper = new ModelMapper();
//		 GTSNotificationSubscriberGroups notificationEvent = modelMapper.map(notificationEventsrequestModel,GTSNotificationSubscriberGroups.class);
		try {
			notificationSubscriberGroupsRepo.deactivateNotification(notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id());
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event deactivated successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to deactivate a notification subscriber group ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public List<GTSNotificationSubscriberGroups> getAllNotificationGroups() {
		return notificationSubscriberGroupsRepo.findAll();
	}

	@Override
	public Optional<GTSNotificationSubscriberGroups> findByNotificationGroupId(Long gts_notification_subscriber_group_id) {
		return notificationSubscriberGroupsRepo.findById(gts_notification_subscriber_group_id);
	}

	@Override
	public List<GTSNotificationSubscriberGroups> findByNotificationGroupName(String gts_notification_subscriber_group_name) {
		return notificationSubscriberGroupsRepo.getByNotificationGroupName(gts_notification_subscriber_group_name);
	}

	@Override
	public JsonResponseModel deleteNotificationGroup(Long gts_notification_subscriber_group_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationSubscriberGroupsRepo.deleteById(gts_notification_subscriber_group_id);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification subscriber group deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public long generateNotificationGroupId() {
		 long numberOfNotification = 0;
		 if(notificationSubscriberGroupsRepo.getMaxOfNotificationGroupID()!=null)
		 {
			 numberOfNotification=notificationSubscriberGroupsRepo.getMaxOfNotificationGroupID();
		 }
		return numberOfNotification;
	}

	@Override
	public boolean isNotificationGroupIdPresent(Long gts_notification_subscriber_group_id) {
		Long isNotificationGroupIdPresent=notificationSubscriberGroupsRepo.isNotificationGroupIdPresent(gts_notification_subscriber_group_id);
		if(isNotificationGroupIdPresent==gts_notification_subscriber_group_id) return true;
		return false;
	}

	@Override
	public boolean isNotificationGroupNamePresent(String gts_notification_subscriber_group_name) {
		List<GTSNotificationSubscriberGroups> isNotificationGroupNamePresent=notificationSubscriberGroupsRepo.isNotificationGroupNamePresent(gts_notification_subscriber_group_name);
		if(!isNotificationGroupNamePresent.isEmpty()) return true;
		return false;
	}

	@Override
	public boolean isNotificationDeactivated(Long gts_notification_subscriber_group_id) {
		List<GTSNotificationSubscriberGroups> isNotificationDeactivated=notificationSubscriberGroupsRepo.isNotificationDeactivated(gts_notification_subscriber_group_id);
		if(isNotificationDeactivated.isEmpty()) return true;
		return false;
	}
	
	@Override
	public GTSNotificationSubscriberGroups getByNotificationGroupId(Long gts_notification_subscriber_group_id) {
		return notificationSubscriberGroupsRepo.findByNotificationGroupId(gts_notification_subscriber_group_id) ;
	}

	@Override
	public List<GTSNotificationSubscriberGroups> getByIds(List<Long> gts_notification_subscriber_group_id) {
		return notificationSubscriberGroupsRepo.findByNotificationGroupIds(gts_notification_subscriber_group_id);
	}

}
