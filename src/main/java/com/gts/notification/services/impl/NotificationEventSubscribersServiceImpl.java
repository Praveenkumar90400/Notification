package com.gts.notification.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.gts.notification.entities.GTSNotificationEventSubscribers;
import com.gts.notification.model.request.NotificationEventSubscribersRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.repos.NotificationEventSubscribersRepo;
import com.gts.notification.repos.NotificationEventsRepo;
import com.gts.notification.repos.UserRepo;
import com.gts.notification.service.NotificationEventSubscribersService;

@Service
public class NotificationEventSubscribersServiceImpl implements NotificationEventSubscribersService {

@Autowired
NotificationEventSubscribersRepo notificationEventSubscribersRepo;

@Autowired
NotificationEventsRepo notificationEventsRepo;

@Autowired
UserRepo userRepo;

	@Override
	public JsonResponseModel addNotificationSubscriber(NotificationEventSubscribersRequestModel notificationEventSubscribersRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		 ModelMapper modelMapper = new ModelMapper();
		 GTSNotificationEventSubscribers notificationEvent = modelMapper.map(notificationEventSubscribersRequestModel,GTSNotificationEventSubscribers.class);
		try {
			notificationEventSubscribersRepo.save(notificationEvent);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event subscriber added successfully");
			jsonResponseModel.setStatus_code("201");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to add a notification event subscriber");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public List<GTSNotificationEventSubscribers> findByNotificationUserId(Long gts_user_id) {
		return notificationEventSubscribersRepo.getNotificationByUserId(gts_user_id);
	}

	@Override
	public JsonResponseModel deleteNotificationEventByUserId(@PathVariable (value="gts_user_id")Long gts_user_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationEventSubscribersRepo.deleteEventSubscriberByUserId(gts_user_id);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event subscriber deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event subscriber");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel deleteNotificationEventById(Long gts_notification_event_subscriber_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationEventSubscribersRepo.deleteById(gts_notification_event_subscriber_id);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event subscriber deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event subscriber");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public long generateNotificationEventId() {
		long numberOfEvents=0;
		if(notificationEventSubscribersRepo.getMaxOfNotificationEventID()!=null) {
			numberOfEvents=notificationEventSubscribersRepo.getMaxOfNotificationEventID();
		}
		return numberOfEvents;
	}

	@Override
	public boolean isNotificationEventIdPresent(Long gts_notification_event_id) {
		Long isNotificationEventIdPresent=notificationEventsRepo.isNotificationEventIdPresent(gts_notification_event_id);
		if(isNotificationEventIdPresent==gts_notification_event_id) return true;
		return false;
	}

	@Override
	public boolean isNotificationUserIdPresent(Long gts_user_id) {
		Long isNotificationUserIdPresent=userRepo.isUserPresent(gts_user_id);
		if(isNotificationUserIdPresent==gts_user_id) return true;
		return false;
	}

	@Override
	public boolean isNotificationEventSubscriberIdPresent(Long gts_notification_event_subscriber_id) {
		Long isNotificationEventSubscriberIdPresent=notificationEventSubscribersRepo.isEventSubscriberIdPresent(gts_notification_event_subscriber_id);
		if(isNotificationEventSubscriberIdPresent==gts_notification_event_subscriber_id) return true;
		return false;
	}

	@Override
	public boolean isUserIdPresent(Long gts_user_id) {
		Long isUserIdPresent=notificationEventSubscribersRepo.isUserIdPresent(gts_user_id);
		if(isUserIdPresent==gts_user_id) return true;
		return false;
	}

}
