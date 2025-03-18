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

import com.gts.notification.entities.GTSNotificationEvents;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.model.request.NotificationEventActivateRequestModel;
import com.gts.notification.model.request.NotificationEventsRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.repos.NotificationEventsRepo;
import com.gts.notification.service.NotificationEventsService;

@RestController
@RequestMapping("api/v1/notifications/events")
@CrossOrigin(origins="*")

public class NotificationEventsController {
	
	@Autowired
	NotificationEventsService notificationEventsService;
	
	@Autowired
	NotificationEventsRepo notificationEventsRepo;
	
      @PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
         public ResponseEntity<JsonResponseModel>  createNotificationEvent(@RequestBody NotificationEventsRequestModel notificationEventsRequestModel) throws Exception {
		   JsonResponseModel notificationEventObj=null;
		   
			if(notificationEventsRequestModel.getGts_notification_event_name()==null || notificationEventsRequestModel.getGts_notification_event_name().trim().isEmpty()) {
				throw new ApplicationException("Notification event name cannot be empty");
			}

			boolean isNotificationNamePresent = notificationEventsService.isNotificationNamePresent(notificationEventsRequestModel.getGts_notification_event_name());
			if(isNotificationNamePresent){
		     	throw new IDNotFoundException("Notification event name exist");
		    } 
			
	        long nextNotificationEventID=generateNotificationEventId();
	        notificationEventsRequestModel.setGts_notification_event_id(nextNotificationEventID);;
	        notificationEventObj= notificationEventsService.createNotificationEvent(notificationEventsRequestModel);
		      
		return new ResponseEntity<>(notificationEventObj,HttpStatus.CREATED);
	    }
	 
	 @PutMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
     public ResponseEntity<JsonResponseModel>  updateNotificationEvent(@RequestBody NotificationEventsRequestModel notificationEventsRequestModel) throws Exception {
		 JsonResponseModel notificationEventObj=null;
		 
		    if(notificationEventsRequestModel.getGts_notification_event_id()==null || notificationEventsRequestModel.getGts_notification_event_id()<=0) {
				throw new ApplicationException("Notification event id is invalid");
			}
		   
			if(notificationEventsRequestModel.getGts_notification_event_name()==null || notificationEventsRequestModel.getGts_notification_event_name().trim().isEmpty()) {
				throw new ApplicationException("Notification event name cannot be empty");
			}
			
			boolean isNotificationIdPresent = notificationEventsService.isNotificationIdPresent(notificationEventsRequestModel.getGts_notification_event_id());
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification event id does not exist");
		    } 
		    
		    boolean isNotificationDeactivated = notificationEventsService.isNotificationDeactivated(notificationEventsRequestModel.getGts_notification_event_id());
		    if(!isNotificationDeactivated){
		     	throw new IDNotFoundException("Notification event is deactivated");
		    }

			boolean isNotificationNamePresent = notificationEventsService.isNotificationNamePresent(notificationEventsRequestModel.getGts_notification_event_name());
		    if(isNotificationNamePresent){
		    	 notificationEventObj = notificationEventsService.updateNotificationEvent(notificationEventsRequestModel);
		    }
		    else {
		    	throw new IDNotFoundException("Notification event name does not exist");
		    }
		   
		      
		return new ResponseEntity<>(notificationEventObj,HttpStatus.CREATED);
	    }
	 
		@PutMapping(path = "/activate")
		   public ResponseEntity<JsonResponseModel> activateNotificationEvent(@RequestBody NotificationEventActivateRequestModel notificationEventActivateRequestModel) {
			 JsonResponseModel notificationEventObj=null;
			 
			if(notificationEventActivateRequestModel.getGts_notification_event_id()==null || notificationEventActivateRequestModel.getGts_notification_event_id()<=0) {
				throw new ApplicationException("Notification event id is invalid");
			}

			boolean isNotificationIdPresent = notificationEventsService.isNotificationIdPresent(notificationEventActivateRequestModel.getGts_notification_event_id());
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification event id does not exist");
		    } 
		    
		    notificationEventObj=notificationEventsService.activateNotificationEvent(notificationEventActivateRequestModel);
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@PutMapping(path = "/deactivate")
		   public ResponseEntity<JsonResponseModel> deactivateNotificationEvent(@RequestBody NotificationEventActivateRequestModel notificationEventActivateRequestModel) {
			 JsonResponseModel notificationEventObj=null;
			 
			if(notificationEventActivateRequestModel.getGts_notification_event_id()==null || notificationEventActivateRequestModel.getGts_notification_event_id()<=0) {
				System.out.println("id"+notificationEventActivateRequestModel.getGts_notification_event_id());
				throw new ApplicationException("Notification event id is invalid");
			}

			boolean isNotificationIdPresent = notificationEventsService.isNotificationIdPresent(notificationEventActivateRequestModel.getGts_notification_event_id());
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification event id does not exist");
		    } 
		    
		    notificationEventObj=notificationEventsService.deactivateNotificationEvent(notificationEventActivateRequestModel);
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@GetMapping
		   public ResponseEntity<List<GTSNotificationEvents>> getAllNotificationEvents() {
			
			List<GTSNotificationEvents> getAllEvents=notificationEventsService.getAllNotificationEvents();
			
			 return new ResponseEntity<>(getAllEvents, HttpStatus.OK);
		   }
		
		@GetMapping(path = "/notification_event_id/{gts_notification_event_id}")
		   public ResponseEntity<Optional<GTSNotificationEvents>> findByNotificationId(@PathVariable Long gts_notification_event_id) {
			
			if(gts_notification_event_id==null || gts_notification_event_id<=0) {
				throw new ApplicationException("Notification event id is invalid");
			}

			Optional<GTSNotificationEvents> notificationEventObj = notificationEventsService.findByNotificationId(gts_notification_event_id);
		    if(!notificationEventObj.isPresent()){
		     	throw new IDNotFoundException("Notification event id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@GetMapping(path = "/notification_event_name/{gts_notification_event_name}")
		   public ResponseEntity<List<GTSNotificationEvents>> findByNotificationName(@PathVariable String gts_notification_event_name) {
			
			if(gts_notification_event_name==null || gts_notification_event_name.trim().isEmpty()) {
				throw new ApplicationException("Notification event name cannot be empty");
			}

			List<GTSNotificationEvents> notificationEventObj = notificationEventsService.findByNotificationName(gts_notification_event_name);
		    if(notificationEventObj.isEmpty()){
		     	throw new IDNotFoundException("Notification event name does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		
		@DeleteMapping(path ="/notification_event_id/{gts_notification_event_id}")
		   public ResponseEntity<JsonResponseModel> deleteNotificationEvent(@PathVariable Long gts_notification_event_id) {
			
			if(gts_notification_event_id==null || gts_notification_event_id<=0) {
				throw new ApplicationException("Notification event id is invalid");
			}

			boolean isNotificationIdPresent = notificationEventsService.isNotificationIdPresent(gts_notification_event_id);
		    if(!isNotificationIdPresent){
		     	throw new IDNotFoundException("Notification event id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventsService.deleteNotificationEvent(gts_notification_event_id), HttpStatus.OK);
		   }

		
	   /* Generate Primary Key Id */
		private synchronized long generateNotificationEventId() {
		     return notificationEventsService.generateNotificationEventId()+1;	
	    }
}
