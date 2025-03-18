package com.gts.notification.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.notification.entities.GTSNotificationsGroupSubscribers;
import com.gts.notification.model.request.NotificationGroupSubscriberRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.repos.NotificationGroupSubscribersRepo;
import com.gts.notification.repos.NotificationSubscriberGroupsRepo;
import com.gts.notification.repos.UserRepo;
import com.gts.notification.service.NotificationGroupSubscribersService;

@Service
public class NotificationGroupSubscribersServiceImpl implements NotificationGroupSubscribersService{
	
	@Autowired
	NotificationGroupSubscribersRepo notificationGroupSubscribersRepo;

	@Autowired
	NotificationSubscriberGroupsRepo notificationSubscriberGroupsRepo;

	@Autowired
	UserRepo userRepo;

		@Override
		public JsonResponseModel addNotificationSubscriber(NotificationGroupSubscriberRequestModel notificationGroupSubscriberRequestModel) {
			JsonResponseModel jsonResponseModel=new JsonResponseModel();
			 ModelMapper modelMapper = new ModelMapper();
			 GTSNotificationsGroupSubscribers notificationEvent = modelMapper.map(notificationGroupSubscriberRequestModel,GTSNotificationsGroupSubscribers.class);
			try {
				notificationGroupSubscribersRepo.save(notificationEvent);
				jsonResponseModel.setSuccess("True");
				jsonResponseModel.setMessage("Notification group subscriber added successfully");
				jsonResponseModel.setStatus_code("201");
			} catch (Exception e) {
				jsonResponseModel.setSuccess("False");
				jsonResponseModel.setMessage("Not able to add a notification group subscriber");
				jsonResponseModel.setStatus_code("403");
			}
			return jsonResponseModel;
		}

		@Override
		public List<GTSNotificationsGroupSubscribers> findByNotificationUserId(Long gts_user_id) {
			return notificationGroupSubscribersRepo.getNotificationByUserId(gts_user_id);
		}

		@Override
		public JsonResponseModel deleteNotificationGroupByUserId(Long gts_user_id){
			JsonResponseModel jsonResponseModel=new JsonResponseModel();
			try {
				notificationGroupSubscribersRepo.deleteGroupSubscriberByUserId(gts_user_id);
				jsonResponseModel.setSuccess("True");
				jsonResponseModel.setMessage("Notification group subscriber deleted successfully");
				jsonResponseModel.setStatus_code("200");
			} catch (Exception e) {
				jsonResponseModel.setSuccess("False");
				jsonResponseModel.setMessage("Not able to delete a notification group subscriber");
				jsonResponseModel.setStatus_code("403");
			}
			return jsonResponseModel;
		}

		@Override
		public JsonResponseModel deleteNotificationGroupById(Long gts_notification_group_subscriber_id) {
			JsonResponseModel jsonResponseModel=new JsonResponseModel();
			try {
				notificationGroupSubscribersRepo.deleteById(gts_notification_group_subscriber_id);
				jsonResponseModel.setSuccess("True");
				jsonResponseModel.setMessage("Notification group subscriber deleted successfully");
				jsonResponseModel.setStatus_code("200");
			} catch (Exception e) {
				jsonResponseModel.setSuccess("False");
				jsonResponseModel.setMessage("Not able to delete a notification group subscriber");
				jsonResponseModel.setStatus_code("403");
			}
			return jsonResponseModel;
		}

		@Override
		public long generateNotificationGroupId() {
			long numberOfGroups=0;
			if(notificationGroupSubscribersRepo.getMaxOfNotificationID()!=null) {
				numberOfGroups=notificationGroupSubscribersRepo.getMaxOfNotificationID();
			}
			return numberOfGroups;
		}

		@Override
		public boolean isNotificationGroupIdPresent(Long gts_notification_group_id) {
			Long isNotificationEventIdPresent=notificationSubscriberGroupsRepo.isNotificationGroupIdPresent(gts_notification_group_id);
			if(isNotificationEventIdPresent==gts_notification_group_id) return true;
			return false;
		}

		@Override
		public boolean isNotificationUserIdPresent(Long gts_user_id) {
			Long isNotificationUserIdPresent=userRepo.isUserPresent(gts_user_id);
			if(isNotificationUserIdPresent==gts_user_id) return true;
			return false;
		}

		@Override
		public boolean isNotificationGroupSubscriberIdPresent(Long gts_notification_group_subscriber_id) {
			Long isNotificationEventSubscriberIdPresent=notificationGroupSubscribersRepo.isEventSubscriberIdPresent(gts_notification_group_subscriber_id);
			if(isNotificationEventSubscriberIdPresent==gts_notification_group_subscriber_id) return true;
			return false;
		}

		@Override
		public boolean isUserIdPresent(Long gts_user_id) {
			List<GTSNotificationsGroupSubscribers> isUserIdPresent=notificationGroupSubscribersRepo.isUserIdPresent(gts_user_id);
			if(!isUserIdPresent.isEmpty()) return true;
			return false;
		}

	}
