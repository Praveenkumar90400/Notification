package com.gts.notification.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gts.notification.entities.GTSNotificationSubscriberGroups;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.model.request.NotificationGroupActivateRequestModel;
import com.gts.notification.model.request.NotificationSubscriberGroupsRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.service.NotificationSubscriberGroupsService;

@RestController
@RequestMapping("api/v1/notifications/subscriber/group")
@CrossOrigin(origins="*")

public class NotificationSubscriberGroupsController {

	
	@Autowired
	NotificationSubscriberGroupsService notificationSubscriberGroupsService;
	
      @PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
         public ResponseEntity<JsonResponseModel>  createNotificationGroup(@RequestBody NotificationSubscriberGroupsRequestModel notificationSubscriberGroupsRequestModel) throws Exception {
		   JsonResponseModel notificationEventObj=null;
		   
			if(notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_name()==null || notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_name().trim().isEmpty()) {
				throw new ApplicationException("Notification subscriber group name cannot be empty");
			}

			boolean isNotificationNamePresent = notificationSubscriberGroupsService.isNotificationGroupNamePresent(notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_name());
		    if(isNotificationNamePresent){
		     	throw new IDNotFoundException("Notification group name already exist");
		    } 

	        long nextNotificationEventID=generateNotificationEventId();
	        notificationSubscriberGroupsRequestModel.setGts_notification_subscriber_group_id(nextNotificationEventID);
	        notificationEventObj= notificationSubscriberGroupsService.createNotificationGroup(notificationSubscriberGroupsRequestModel);
		      
		return new ResponseEntity<>(notificationEventObj,HttpStatus.CREATED);
	    }
	 
	 @PutMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
     public ResponseEntity<JsonResponseModel>  updateNotificationGroup(@RequestBody NotificationSubscriberGroupsRequestModel notificationSubscriberGroupsRequestModel) throws Exception {
		 JsonResponseModel notificationEventObj=null;
		 
		    if(notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_id()==null || notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_id()<=0) {
				throw new ApplicationException("Notification subscriber group id is invalid");
			}
		   
			if(notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_name()==null || notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_name().trim().isEmpty()) {
				throw new ApplicationException("Notification subscriber group name cannot be empty");
			}
			
			boolean isNotificationIdPresent = notificationSubscriberGroupsService.isNotificationGroupIdPresent(notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_id());
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification subscriber group id does not exist");
		    } 
		    
		    boolean isNotificationDeactivated = notificationSubscriberGroupsService.isNotificationDeactivated(notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_id());
		    if(!isNotificationDeactivated){
		     	throw new IDNotFoundException("Notification subscriber group is deactivated");
		    }

			boolean isNotificationNamePresent = notificationSubscriberGroupsService.isNotificationGroupNamePresent(notificationSubscriberGroupsRequestModel.getGts_notification_subscriber_group_name());
		    if(isNotificationNamePresent){
		    	 notificationEventObj = notificationSubscriberGroupsService.updateNotificationGroup(notificationSubscriberGroupsRequestModel);
		    }
		    else {
		    	throw new IDNotFoundException("Notification subscriber group name does not exist");
		    }
		   
		      
		return new ResponseEntity<>(notificationEventObj,HttpStatus.CREATED);
	    }
	 
		@PutMapping(path = "/activate")
		   public ResponseEntity<JsonResponseModel> activateNotificationGroup(@RequestBody NotificationGroupActivateRequestModel notificationGroupActivateRequestModel) {
			 JsonResponseModel notificationEventObj=null;
			 
			if(notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id()==null || notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id()<=0) {
				throw new ApplicationException("Notification subscriber group id is invalid");
			}

			boolean isNotificationIdPresent = notificationSubscriberGroupsService.isNotificationGroupIdPresent(notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id());
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification subscriber group id does not exist");
		    } 
		    
		    notificationEventObj=notificationSubscriberGroupsService.activateNotificationGroup(notificationGroupActivateRequestModel);
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@PutMapping(path = "/deactivate")
		   public ResponseEntity<JsonResponseModel> deactivateNotificationGroup(@RequestBody NotificationGroupActivateRequestModel notificationGroupActivateRequestModel) {
			 JsonResponseModel notificationEventObj=null;
			 
			if(notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id()==null || notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id()<=0) {
				throw new ApplicationException("Notification subscriber group id is invalid");
			}

			boolean isNotificationIdPresent = notificationSubscriberGroupsService.isNotificationGroupIdPresent(notificationGroupActivateRequestModel.getGts_notification_subscriber_group_id());
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification subscriber group id does not exist");
		    } 
		    
		    notificationEventObj=notificationSubscriberGroupsService.deactivateNotificationGroup(notificationGroupActivateRequestModel);
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@GetMapping
		   public ResponseEntity<List<GTSNotificationSubscriberGroups>> getAllNotificationGroups() {
			
			List<GTSNotificationSubscriberGroups> getAllEvents=notificationSubscriberGroupsService.getAllNotificationGroups();
			
			 return new ResponseEntity<>(getAllEvents, HttpStatus.OK);
		   }
		
		@GetMapping(path = "/notification_subscriber_group_id/{gts_notification_subscriber_group_id}")
		   public ResponseEntity<Optional<GTSNotificationSubscriberGroups>> findByNotificationGroupId(@PathVariable Long gts_notification_subscriber_group_id) {
			
			if(gts_notification_subscriber_group_id==null || gts_notification_subscriber_group_id<=0) {
				throw new ApplicationException("Notification subscriber group id is invalid");
			}

			Optional<GTSNotificationSubscriberGroups> notificationEventObj = notificationSubscriberGroupsService.findByNotificationGroupId(gts_notification_subscriber_group_id);
		    if(!notificationEventObj.isPresent()){
		     	throw new IDNotFoundException("Notification subscriber group id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@GetMapping(path = "/notification_subscriber_group_name/{gts_notification_subscriber_group_name}")
		   public ResponseEntity<List<GTSNotificationSubscriberGroups>> findByNotificationGroupName(@PathVariable String gts_notification_subscriber_group_name) {
			
			if(gts_notification_subscriber_group_name==null || gts_notification_subscriber_group_name.trim().isEmpty()) {
				throw new ApplicationException("Notification subscriber group name cannot be empty");
			}

			List<GTSNotificationSubscriberGroups> notificationEventObj = notificationSubscriberGroupsService.findByNotificationGroupName(gts_notification_subscriber_group_name);
		    if(notificationEventObj.isEmpty()){
		     	throw new IDNotFoundException("Notification subscriber group name does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@DeleteMapping(path ="/notification_subscriber_group_id/{gts_notification_subscriber_group_id}")
		   public ResponseEntity<JsonResponseModel> deleteNotificationGroup(@PathVariable Long gts_notification_subscriber_group_id) {
			
			if(gts_notification_subscriber_group_id==null || gts_notification_subscriber_group_id<=0) {
				throw new ApplicationException("Notification subscriber group id is invalid");
			}

			boolean isNotificationIdPresent = notificationSubscriberGroupsService.isNotificationGroupIdPresent(gts_notification_subscriber_group_id);
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification subscriber group id does not exist");
		    } 
			 return new ResponseEntity<>(notificationSubscriberGroupsService.deleteNotificationGroup(gts_notification_subscriber_group_id), HttpStatus.OK);
		   }

		
	   /* Generate Primary Key Id */
		private synchronized long generateNotificationEventId() {
		     return notificationSubscriberGroupsService.generateNotificationGroupId()+1;	
	    }
}
