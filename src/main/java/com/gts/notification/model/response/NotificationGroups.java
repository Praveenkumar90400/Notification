package com.gts.notification.model.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gts.notification.entities.GTSNotificationSubscriberGroups;
import com.gts.notification.exceptions.ApplicationException;
import com.gts.notification.exceptions.IDNotFoundException;
import com.gts.notification.service.NotificationSubscriberGroupsService;

@Component
public class NotificationGroups {

		@Autowired
		private static NotificationSubscriberGroupsService notificationSubscriberGroupsService;
		
		@Autowired
		public void setJobTitleRepository(NotificationSubscriberGroupsService notificationSubscriberGroupsService) {
			NotificationGroups.notificationSubscriberGroupsService = notificationSubscriberGroupsService;
		}
		
		public NotificationGroups() {
			
		}
		
		public static void validateNotificationGroups(String gts_notification_group_ids) {
			 String[] groupIds=gts_notification_group_ids.split(",");
			 for(int i=0;i<groupIds.length;i++)
			 {
				 long id = Long.parseLong(groupIds[i]);
				 if(id <= 0) {
						throw new ApplicationException("Notification group id " + id +" is invalid");
					}
					
				 GTSNotificationSubscriberGroups groupId= notificationSubscriberGroupsService.getByNotificationGroupId(id);
					if(groupId == null) {
						throw new IDNotFoundException("Notification group id " + id +" not found");
					}
			 }
	     }
		
		public static List<Long> convertIdsToNotificationGroupID(String gts_notification_group_ids) {
			 
			 String[] stringLong=gts_notification_group_ids.split(",");
			 List<Long> ids=new ArrayList<Long>();
			 for(int i=0;i<stringLong.length;i++)
			 {
				 ids.add(Long.parseLong(stringLong[i]));
			 }
			
			 return ids;
	    }
		
		public static List<GTSNotificationSubscriberGroups> getNotificationGroup(List<Long> gts_notification_group_ids) {
			
			return  notificationSubscriberGroupsService.getByIds(gts_notification_group_ids);
			
		}
	}
