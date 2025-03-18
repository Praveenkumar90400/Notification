package com.gts.notification.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.notification.entities.GTSNotificationEvents;
import com.gts.notification.model.request.NotificationEventActivateRequestModel;
import com.gts.notification.model.request.NotificationEventsRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.repos.NotificationEventsRepo;
import com.gts.notification.service.NotificationEventsService;

@Service
public class NotificationEventsServiceImpl implements NotificationEventsService{
	
	@Autowired
	NotificationEventsRepo notificationEventsRepo;

	@Override
	public JsonResponseModel createNotificationEvent(NotificationEventsRequestModel notificationEventsRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		 ModelMapper modelMapper = new ModelMapper();
		 GTSNotificationEvents notificationEvent = modelMapper.map(notificationEventsRequestModel,GTSNotificationEvents.class);
		try {
			notificationEventsRepo.save(notificationEvent);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event created successfully");
			jsonResponseModel.setStatus_code("201");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to create a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel updateNotificationEvent(NotificationEventsRequestModel notificationEventsRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		 ModelMapper modelMapper = new ModelMapper();
		 GTSNotificationEvents notificationEvent = modelMapper.map(notificationEventsRequestModel,GTSNotificationEvents.class);
		try {
			notificationEventsRepo.save(notificationEvent);
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event updated successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to update a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel activateNotificationEvent(NotificationEventActivateRequestModel notificationEventActivateRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
//		 ModelMapper modelMapper = new ModelMapper();
//		 GTSNotificationEvents notificationEvent = modelMapper.map(NotificationActivateRequestModel,GTSNotificationEvents.class);
		try {
			notificationEventsRepo.activateNotification(notificationEventActivateRequestModel.getGts_notification_event_id());
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event activated successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to activate a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public JsonResponseModel deactivateNotificationEvent(NotificationEventActivateRequestModel notificationEventActivateRequestModel) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
//		 ModelMapper modelMapper = new ModelMapper();
//		 GTSNotificationEvents notificationEvent = modelMapper.map(notificationEventsrequestModel,GTSNotificationEvents.class);
		try {
			notificationEventsRepo.deactivateNotification(notificationEventActivateRequestModel.getGts_notification_event_id());
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event deactivated successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to deactivate a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public List<GTSNotificationEvents> getAllNotificationEvents() {
		return notificationEventsRepo.findAll();
	}

	@Override
	public Optional<GTSNotificationEvents> findByNotificationId(Long gts_notification_event_id) {
		return notificationEventsRepo.findById(gts_notification_event_id);
	}

	@Override
	public List<GTSNotificationEvents> findByNotificationName(String gts_notification_event_name) {
		return notificationEventsRepo.getByNotificationName(gts_notification_event_name);
	}

	@Override
	public JsonResponseModel deleteNotificationEvent(Long gts_notification_event_id) {
		JsonResponseModel jsonResponseModel=new JsonResponseModel();
		try {
			notificationEventsRepo.deleteById(gts_notification_event_id);;
			jsonResponseModel.setSuccess("True");
			jsonResponseModel.setMessage("Notification event deleted successfully");
			jsonResponseModel.setStatus_code("200");
		} catch (Exception e) {
			jsonResponseModel.setSuccess("False");
			jsonResponseModel.setMessage("Not able to delete a notification event ");
			jsonResponseModel.setStatus_code("403");
		}
		return jsonResponseModel;
	}

	@Override
	public long generateNotificationEventId() {
		 long numberOfNotification = 0;
		 if(notificationEventsRepo.getMaxOfNotificationEventID()!=null)
		 {
			 numberOfNotification=notificationEventsRepo.getMaxOfNotificationEventID();
		 }
		return numberOfNotification;
	}

	@Override
	public boolean isNotificationIdPresent(Long gts_notification_event_id) {
		Long isNotificationIdPresent=notificationEventsRepo.isNotificationEventIdPresent(gts_notification_event_id);
		if(isNotificationIdPresent==gts_notification_event_id) return true;
		return false;
	}

	@Override
	public boolean isNotificationNamePresent(String gts_notification_event_name) {
		List<GTSNotificationEvents> isNotificationNamePresent=notificationEventsRepo.isNotificationNamePresent(gts_notification_event_name);
		if(!isNotificationNamePresent.isEmpty()) return true;
		return false;
	}

	@Override
	public boolean isNotificationDeactivated(Long gts_notification_event_id) {
		List<GTSNotificationEvents> isNotificationDeactivated=notificationEventsRepo.isNotificationDeactivated(gts_notification_event_id);
		if(isNotificationDeactivated.isEmpty()) return true;
		return false;
	}

	@Override
	public GTSNotificationEvents getByNotificationEventId(Long gts_notification_event_id) {
		return notificationEventsRepo.findByNotificationEventId(gts_notification_event_id) ;
	}

	@Override
	public List<GTSNotificationEvents> getByIds(List<Long> gts_notification_event_ids) {
		return notificationEventsRepo.findByNotificationEventIds(gts_notification_event_ids);
	}

}
