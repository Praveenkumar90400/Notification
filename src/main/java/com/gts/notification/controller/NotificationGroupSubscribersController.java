package com.gts.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gts.notification.entities.GTSNotificationsGroupSubscribers;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.model.request.NotificationGroupSubscriberRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.model.response.NotificationGroups;
import com.gts.notification.service.NotificationGroupSubscribersService;

@RestController
@RequestMapping("api/v1/notifications/group/subscriber")
@CrossOrigin(origins="*")

public class NotificationGroupSubscribersController {

	@Autowired
	NotificationGroupSubscribersService notificationGroupSubscribersService;
	
    @PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseModel>  addNotificationSubscriber(@RequestBody NotificationGroupSubscriberRequestModel[] notificationGroupSubscriberRequestModels) throws Exception {
	   JsonResponseModel notificationObj=null;
	   
	   for(NotificationGroupSubscriberRequestModel notificationGroupSubscriberRequestModel :notificationGroupSubscriberRequestModels) {
	   
		if(notificationGroupSubscriberRequestModel.getGts_notification_group_id()==null ) {
			throw new ApplicationException("Notification group subscriber id is invalid");
		}
		
		if(notificationGroupSubscriberRequestModel.getGts_user_id()==null || notificationGroupSubscriberRequestModel.getGts_user_id()<=0) {
			throw new ApplicationException("Notification user id is invalid");
		}
		
		NotificationGroups.validateNotificationGroups(notificationGroupSubscriberRequestModel.getGts_notification_group_id());
	    
	    boolean isNotificationUserIdPresent = notificationGroupSubscribersService.isNotificationUserIdPresent(notificationGroupSubscriberRequestModel.getGts_user_id());
	    if(!isNotificationUserIdPresent){
	     	throw new IDNotFoundException("Notification user id does not exist");
	    }

       long nextNotificationID=generateNotificationId();
       notificationGroupSubscriberRequestModel.setGts_notification_group_subscriber_id(nextNotificationID);;
       notificationObj= notificationGroupSubscribersService.addNotificationSubscriber(notificationGroupSubscriberRequestModel);
	   }
	return new ResponseEntity<>(notificationObj,HttpStatus.CREATED);
   }
	
	@GetMapping(path = "/user_id/{gts_user_id}")
	   public ResponseEntity<List<GTSNotificationsGroupSubscribers>> findByNotificationId(@PathVariable Long gts_user_id) {
		
		if(gts_user_id==null || gts_user_id<=0) {
			throw new ApplicationException("Notification user id is invalid");
		}

		List<GTSNotificationsGroupSubscribers> notificationEventObj = notificationGroupSubscribersService.findByNotificationUserId(gts_user_id);
	    if(notificationEventObj.isEmpty()){
	     	throw new IDNotFoundException("Notification user id does not exist");
	    } 
		 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
	   }
	

	@DeleteMapping(path ="/user_id/{gts_user_id}")
	   public ResponseEntity<JsonResponseModel> deleteNotificationById(@PathVariable Long gts_user_id) {
		
		if(gts_user_id==null || gts_user_id<=0) {
			throw new ApplicationException("Notification user id is invalid");
		}

		boolean isNotificationUserIdPresent = notificationGroupSubscribersService.isUserIdPresent(gts_user_id);
	    if(!isNotificationUserIdPresent){
	     	throw new IDNotFoundException("Notification user id does not exist");
	    } 
		 return new ResponseEntity<>(notificationGroupSubscribersService.deleteNotificationGroupByUserId(gts_user_id), HttpStatus.OK);
	   }
	

	@DeleteMapping(path ="/notification_group_subscriber_id/{gts_notification_group_subscriber_id}")
	   public ResponseEntity<JsonResponseModel> deleteNotificationByEventId(@PathVariable Long gts_notification_group_subscriber_id) {
		
		if(gts_notification_group_subscriber_id==null || gts_notification_group_subscriber_id<=0) {
			throw new ApplicationException("Notification group subscriber id is invalid");
		}

		boolean isNotificationGroupSubscriberIdPresent = notificationGroupSubscribersService.isNotificationGroupSubscriberIdPresent(gts_notification_group_subscriber_id);
	    if(!isNotificationGroupSubscriberIdPresent){
	     	throw new IDNotFoundException("Notification group subscriber id does not exist");
	    } 
		 return new ResponseEntity<>(notificationGroupSubscribersService.deleteNotificationGroupById(gts_notification_group_subscriber_id), HttpStatus.OK);
	   }

	
  /* Generate Primary Key Id */
	private synchronized long generateNotificationId() {
	     return notificationGroupSubscribersService.generateNotificationGroupId()+1;	
   }
}
