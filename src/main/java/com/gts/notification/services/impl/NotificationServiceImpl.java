package com.gts.notification.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.notification.entities.NotificationEntity;
import com.gts.notification.model.request.NotificationRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.repos.NotificationEventsRepo;
import com.gts.notification.repos.NotificationRepository;
import com.gts.notification.repos.TimezoneRepo;
import com.gts.notification.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	NotificationEventsRepo notificationEventsRepo;
	
	@Autowired
	private TimezoneRepo timezoneRepo;

	@Override
	public JsonResponseModel createNotification(NotificationRequestModel notificationRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		 ModelMapper modelMapper = new ModelMapper();
		 NotificationEntity notificationEvent = modelMapper.map(notificationRequestModel,NotificationEntity.class);
		try {
			notificationRepository.save(notificationEvent);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification generated successfully");
			jsonResponseModel.setStatus_code("201");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to generate a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public Optional<NotificationEntity> findByNotificationId(Long gts_notification_id) {
		return notificationRepository.findById(gts_notification_id);
	}

	@Override
	public JsonResponseModel deleteNotificationById(Long gts_notification_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationRepository.deleteById(gts_notification_id);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel deleteNotificationByEventId(Long gts_notification_event_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationRepository.deleteNotification(gts_notification_event_id);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public long generateNotificationId() {
		 long numberOfNotification = 0;
		 if(notificationRepository.getMaxOfNotificationID()!=null)
		 {
			 numberOfNotification=notificationRepository.getMaxOfNotificationID();
		 }
		return numberOfNotification;
	}

	@Override
	public boolean isNotificationIdPresent(Long gts_notification_id) {
		Long isNotificationIdPresent=notificationRepository.isNotificationIdPresent(gts_notification_id);
		if(isNotificationIdPresent==gts_notification_id) return true;
		return false;
	}

	@Override
	public boolean isNotificationEventIdPresent(Long gts_notification_event_id) {
		Long isNotificationEventIdPresent=notificationEventsRepo.isNotificationEventIdPresent(gts_notification_event_id);
		if(isNotificationEventIdPresent==gts_notification_event_id) return true;
		return false;
	}

	@Override
	public boolean isTimezoneIdPresent(Integer gts_notification_timezone_id) {
		Integer isTimezoneIdPresent=timezoneRepo.isTimezoneIdPresent(gts_notification_timezone_id);
		if(isTimezoneIdPresent==gts_notification_timezone_id) return true;
		return false;
	}

	

}
