package com.gts.notification.controller;

import java.util.Optional;

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

import com.gts.notification.entities.NotificationEntity;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.model.request.NotificationRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.service.NotificationService;


@RestController
@RequestMapping("api/v1/notifications")
@CrossOrigin(origins="*")

public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
      @PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
         public ResponseEntity<JsonResponseModel>  createNotification(@RequestBody NotificationRequestModel notificationRequestModel) throws Exception {
		   JsonResponseModel notificationObj=null;
		   
			if(notificationRequestModel.getGts_notification_event_id()==null || notificationRequestModel.getGts_notification_event_id()<=0) {
				throw new ApplicationException("Notification event id is invalid");
			}
			
			if(notificationRequestModel.getGts_notification_message()==null || notificationRequestModel.getGts_notification_message().trim().isEmpty()) {
				throw new ApplicationException("Notification message cannot be empty");
			}
			
			if(notificationRequestModel.getGts_notification_receiver_user_id()==null || notificationRequestModel.getGts_notification_receiver_user_id()<=0) {
				throw new ApplicationException("Notification receiver user id is invalid");
			}
			
			if(notificationRequestModel.getGts_notification_receiver_role_id()==null || notificationRequestModel.getGts_notification_receiver_role_id()<=0) {
				throw new ApplicationException("Notification receiver role id is invalid");
			}
			
			if(notificationRequestModel.getGts_notification_timezone_id()==null || notificationRequestModel.getGts_notification_timezone_id()<=0) {
				throw new ApplicationException("Notification time zone id is invalid");
			}

			boolean isNotificationEventIdPresent = notificationService.isNotificationEventIdPresent(notificationRequestModel.getGts_notification_event_id());
		    if(!isNotificationEventIdPresent){
		     	throw new IDNotFoundException("Notification event id does not exist");
		    } 
		    
		    boolean isTimezoneIdPresent = notificationService.isTimezoneIdPresent(notificationRequestModel.getGts_notification_timezone_id());
		    if(!isTimezoneIdPresent){
		     	throw new IDNotFoundException("Notification time zone id does not exist");
		    }

	        long nextNotificationID=generateNotificationId();
	        notificationRequestModel.setGts_notification_id(nextNotificationID);
	        notificationObj= notificationService.createNotification(notificationRequestModel);
		      
		return new ResponseEntity<>(notificationObj,HttpStatus.CREATED);
	    }
		
		@GetMapping(path = "/notification_id/{gts_notification_id}")
		   public ResponseEntity<Optional<NotificationEntity>> findByNotificationId(@PathVariable Long gts_notification_id) {
			
			if(gts_notification_id==null || gts_notification_id<=0) {
				throw new ApplicationException("Notification id is invalid");
			}

			Optional<NotificationEntity> notificationEventObj = notificationService.findByNotificationId(gts_notification_id);
		    if(!notificationEventObj.isPresent()){
		     	throw new IDNotFoundException("Notification id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		

		@DeleteMapping(path ="/notification_id/{gts_notification_id}")
		   public ResponseEntity<JsonResponseModel> deleteNotificationById(@PathVariable Long gts_notification_id) {
			
			if(gts_notification_id==null || gts_notification_id<=0) {
				throw new ApplicationException("Notification id is invalid");
			}

			boolean isNotificationIdPresent = notificationService.isNotificationIdPresent(gts_notification_id);
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification id does not exist");
		    } 
			 return new ResponseEntity<>(notificationService.deleteNotificationById(gts_notification_id), HttpStatus.OK);
		   }
		

		@DeleteMapping(path ="/notification_event_id/{gts_notification_event_id}")
		   public ResponseEntity<JsonResponseModel> deleteNotificationByEventId(@PathVariable Long gts_notification_event_id) {
			
			if(gts_notification_event_id==null || gts_notification_event_id<=0) {
				throw new ApplicationException("Notification event id is invalid");
			}

			boolean isNotificationEventIdPresent = notificationService.isNotificationEventIdPresent(gts_notification_event_id);
		    if(!isNotificationEventIdPresent){
		     	throw new IDNotFoundException("Notification event id does not exist");
		    } 
			 return new ResponseEntity<>(notificationService.deleteNotificationByEventId(gts_notification_event_id), HttpStatus.OK);
		   }

		
	   /* Generate Primary Key Id */
		private synchronized long generateNotificationId() {
		     return notificationService.generateNotificationId()+1;	
	    }

}
