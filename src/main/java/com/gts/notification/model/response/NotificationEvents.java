package com.gts.notification.model.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gts.notification.entities.GTSNotificationEvents;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.service.NotificationEventsService;

@Component
public class NotificationEvents {

		@Autowired
		private static NotificationEventsService notificationEventsService;
		
		@Autowired
		public void setJobTitleRepository(NotificationEventsService notificationEventsService) {
			NotificationEvents.notificationEventsService = notificationEventsService;
		}
		
		public NotificationEvents() {
			
		}
		
		public static void validateNotificationEvents(String gts_notification_event_ids) {
			 String[] eventsIds=gts_notification_event_ids.split(",");
			 for(int i=0;i<eventsIds.length;i++)
			 {
				 long id = Long.parseLong(eventsIds[i]);
				 if(id <= 0) {
						throw new ApplicationException("Notification event id " + id +" is invalid");
					}
					
				 GTSNotificationEvents eventId= notificationEventsService.getByNotificationEventId(id);
					if(eventId == null) {
						throw new IDNotFoundException("Notification event id " + id +" not found");
					}
			 }
	     }
		
		public static List<Long> convertIdsToNotificationEventID(String gts_notification_event_ids) {
			 
			 String[] stringLong=gts_notification_event_ids.split(",");
			 List<Long> ids=new ArrayList<Long>();
			 for(int i=0;i<stringLong.length;i++)
			 {
				 ids.add(Long.parseLong(stringLong[i]));
			 }
			
			 return ids;
	    }
		
		public static List<GTSNotificationEvents> getNotificationEvent(List<Long> gts_notification_event_ids) {
			
			return  notificationEventsService.getByIds(gts_notification_event_ids);
			
		}
	}
