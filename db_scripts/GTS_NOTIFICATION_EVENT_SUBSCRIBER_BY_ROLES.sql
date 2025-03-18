CREATE TABLE `GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLES` (
  `GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLE_ID` BIGINT NOT NULL,
  `GTS_NOTIFICATION_ROLE_ID` INT NOT NULL,
  `GTS_NOTIFICATION_EVENT_ID` VARCHAR(50) NOT NULL,
  PRIMARY KEY(GTS_NOTIFICATION_EVENT_SUBSCRIBER_BY_ROLE_ID),
  FOREIGN KEY(GTS_NOTIFICATION_ROLE_ID) REFERENCES GTS_ROLES(GTS_ROLE_ID)
   );
   
INSERT INTO GTS_NOTIFICATION_SUBSCRIBER_ROLES (GTS_NOTIFICATION_SUBSCRIBER_ROLE_ID,GTS_NOTIFICATION_EVENT_ID,GTS_NOTIFICATION_ROLE_ID) VALUES(1,2,1);

INSERT INTO GTS_NOTIFICATION_SUBSCRIBER_ROLES (GTS_NOTIFICATION_SUBSCRIBER_ROLE_ID,GTS_NOTIFICATION_EVENT_ID,GTS_NOTIFICATION_ROLE_ID) VALUES(2,3,4);