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

import com.gts.notification.entities.GTSNotificationEventSubscribers;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.model.request.NotificationEventSubscribersRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.model.response.NotificationEvents;
import com.gts.notification.service.NotificationEventSubscribersService;

@RestController
@RequestMapping("api/v1/notifications/event/subscriber")
@CrossOrigin(origins="*")

public class NotificationEventSubscribersController {

	
	@Autowired
	NotificationEventSubscribersService notificationEventSubscribersService;
	
      @PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
         public ResponseEntity<JsonResponseModel>  addNotificationSubscriber(@RequestBody NotificationEventSubscribersRequestModel[] notificationEventSubscribersRequestModels) throws Exception {
		   JsonResponseModel notificationObj=null;
		   
		   for(NotificationEventSubscribersRequestModel notificationEventSubscribersRequestModel :notificationEventSubscribersRequestModels) {
		   
			if(notificationEventSubscribersRequestModel.getGts_notification_event_id()==null) {
				throw new ApplicationException("Notification event id is invalid");
			}
			
			if(notificationEventSubscribersRequestModel.getGts_user_id()==null || notificationEventSubscribersRequestModel.getGts_user_id()<=0) {
				throw new ApplicationException("Notification user id is invalid");
			}
			
			NotificationEvents.validateNotificationEvents(notificationEventSubscribersRequestModel.getGts_notification_event_id());
		    
		    boolean isNotificationUserIdPresent = notificationEventSubscribersService.isNotificationUserIdPresent(notificationEventSubscribersRequestModel.getGts_user_id());
		    if(!isNotificationUserIdPresent){
		     	throw new IDNotFoundException("Notification user id does not exist");
		    }

	        long nextNotificationID=generateNotificationId();
	        notificationEventSubscribersRequestModel.setGts_notification_event_subscriber_id(nextNotificationID);
	        notificationObj= notificationEventSubscribersService.addNotificationSubscriber(notificationEventSubscribersRequestModel);
		   }
		return new ResponseEntity<>(notificationObj,HttpStatus.CREATED);
	    }
		
		@GetMapping(path = "/user_id/{gts_user_id}")
		   public ResponseEntity<List<GTSNotificationEventSubscribers>> findByNotificationId(@PathVariable Long gts_user_id) {
			
			if(gts_user_id==null || gts_user_id<=0) {
				throw new ApplicationException("Notification user id is invalid");
			}

			List<GTSNotificationEventSubscribers> notificationEventObj = notificationEventSubscribersService.findByNotificationUserId(gts_user_id);
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

			boolean isNotificationUserIdPresent = notificationEventSubscribersService.isUserIdPresent(gts_user_id);
		    if(!isNotificationUserIdPresent){
		     	throw new IDNotFoundException("Notification user id does not exist");
		    } 
		    
			 return new ResponseEntity<>(notificationEventSubscribersService.deleteNotificationEventByUserId(gts_user_id), HttpStatus.OK);
		   }
		

		@DeleteMapping(path ="/notification_event_subscriber_id/{gts_notification_event_subscriber_id}")
		   public ResponseEntity<JsonResponseModel> deleteNotificationByEventId(@PathVariable Long gts_notification_event_subscriber_id) {
			
			if(gts_notification_event_subscriber_id==null || gts_notification_event_subscriber_id<=0) {
				throw new ApplicationException("Notification event subscriber id is invalid");
			}

			boolean isNotificationEventSubscriberIdPresent = notificationEventSubscribersService.isNotificationEventSubscriberIdPresent(gts_notification_event_subscriber_id);
		    if(!isNotificationEventSubscriberIdPresent){
		     	throw new IDNotFoundException("Notification event subscriber id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventSubscribersService.deleteNotificationEventById(gts_notification_event_subscriber_id), HttpStatus.OK);
		   }

		
	   /* Generate Primary Key Id */
		private synchronized long generateNotificationId() {
		     return notificationEventSubscribersService.generateNotificationEventId()+1;	
	    }
}
