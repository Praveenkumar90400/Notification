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

import com.gts.notification.entities.GTSNotificationEventSubscriberByRoles;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.model.request.NotificationEventSubscriberByRolesRequestModel;
import com.gts.notification.model.response.JsonResponseModel;
import com.gts.notification.model.response.NotificationEvents;
import com.gts.notification.service.NotificationEventSubscribersByRolesService;

@RestController
@RequestMapping("api/v1/notifications/subscriber/role")
@CrossOrigin(origins="*")

public class NotificationEventSubscriberByRolesController {
	
	@Autowired
	NotificationEventSubscribersByRolesService notificationEventSubscribersByRolesService;
	
      @PostMapping( produces = { MediaType.APPLICATION_JSON_VALUE })
         public ResponseEntity<JsonResponseModel>  addNotificationSubscriberByRole(@RequestBody NotificationEventSubscriberByRolesRequestModel[] notificationEventSubscriberByRolesRequestModels) throws Exception {
		   JsonResponseModel notificationObj=null;
		   
		   for(NotificationEventSubscriberByRolesRequestModel notificationEventSubscriberByRolesRequestModel :notificationEventSubscriberByRolesRequestModels) {
		   
			if(notificationEventSubscriberByRolesRequestModel.getGts_notification_event_id()==null) {
				throw new ApplicationException("Notification event id is invalid");
			}
			
			if(notificationEventSubscriberByRolesRequestModel.getGts_notification_role_id()==null || notificationEventSubscriberByRolesRequestModel.getGts_notification_role_id()<=0) {
				throw new ApplicationException("Notification role id is invalid");
			}
			
			NotificationEvents.validateNotificationEvents(notificationEventSubscriberByRolesRequestModel.getGts_notification_event_id());

		    boolean isNotificationRoleIdPresent = notificationEventSubscribersByRolesService.isNotificationRoleIdPresent(notificationEventSubscriberByRolesRequestModel.getGts_notification_role_id());
		    if(!isNotificationRoleIdPresent){
		     	throw new IDNotFoundException("Notification role id does not exist");
		    }

	        long nextNotificationID=generateNotificationId();
	        notificationEventSubscriberByRolesRequestModel.setGts_notification_event_subscriber_by_role_id(nextNotificationID);
	        notificationObj= notificationEventSubscribersByRolesService.addNotificationSubscriberByRole(notificationEventSubscriberByRolesRequestModel);
		   }
		return new ResponseEntity<>(notificationObj,HttpStatus.CREATED);
	    }
		
		@GetMapping(path = "/notification_role_id/{gts_notification_role_id}")
		   public ResponseEntity<List<GTSNotificationEventSubscriberByRoles>> findByNotificationRoleId(@PathVariable Integer gts_notification_role_id) {
			
			if(gts_notification_role_id==null || gts_notification_role_id<=0) {
				throw new ApplicationException("Notification role id is invalid");
			}

			List<GTSNotificationEventSubscriberByRoles> notificationEventObj = notificationEventSubscribersByRolesService.findByNotificationRoleId(gts_notification_role_id);
		    if(notificationEventObj.isEmpty()){
		     	throw new IDNotFoundException("Notification role id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventObj, HttpStatus.OK);
		   }
		

		@DeleteMapping(path ="/notification_role_id/{gts_notification_role_id}")
		   public ResponseEntity<JsonResponseModel> deleteNotificationById(@PathVariable Integer gts_notification_role_id) {
			
			if(gts_notification_role_id==null || gts_notification_role_id<=0) {
				throw new ApplicationException("Notification role id is invalid");
			}

			boolean isNotificationRoleIdPresent = notificationEventSubscribersByRolesService.isRoleIdPresent(gts_notification_role_id);
		    if(!isNotificationRoleIdPresent){
		     	throw new IDNotFoundException("Notification role id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventSubscribersByRolesService.deleteNotificationEventByRoleId(gts_notification_role_id), HttpStatus.OK);
		   }
		

		@DeleteMapping(path ="/notification_event_subscriber_by_role_id/{gts_notification_event_subscriber_by_role_id}")
		   public ResponseEntity<JsonResponseModel> deleteNotificationByEventId(@PathVariable Long gts_notification_event_subscriber_by_role_id) {
			
			if(gts_notification_event_subscriber_by_role_id==null || gts_notification_event_subscriber_by_role_id<=0) {
				throw new ApplicationException("Notification event subscriber role by id is invalid");
			}

			boolean isNotificationEventByRoleIdPresent = notificationEventSubscribersByRolesService.isNotificationEventByRoleIdPresent(gts_notification_event_subscriber_by_role_id);
		    if(!isNotificationEventByRoleIdPresent){
		     	throw new IDNotFoundException("Notification event subscriber role by id does not exist");
		    } 
			 return new ResponseEntity<>(notificationEventSubscribersByRolesService.deleteNotificationEventById(gts_notification_event_subscriber_by_role_id), HttpStatus.OK);
		   }

		
	   /* Generate Primary Key Id */
		private synchronized long generateNotificationId() {
		     return notificationEventSubscribersByRolesService.generateNotificationEventId()+1;	
	    }
}
