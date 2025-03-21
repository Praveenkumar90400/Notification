CREATE TABLE `GTS_NOTIFICATION_EVENT_SUBSCRIBERS` (
  `GTS_NOTIFICATION_EVENT_SUBSCRIBER_ID` BIGINT NOT NULL,
  `GTS_NOTIFICATION_EVENT_ID` VARCHAR(50) NOT NULL,
  `GTS_USER_ID` BIGINT NOT NULL,
  PRIMARY KEY(GTS_NOTIFICATION_EVENT_SUBSCRIBER_ID),
  FOREIGN KEY(GTS_USER_ID) REFERENCES GTS_USERS(GTS_USER_ID)
   );
   
INSERT INTO  GTS_NOTIFICATION_SUBSCRIBER_USERS(GTS_NOTIFICATION_SUBSCRIBER_USERS_ID,GTS_NOTIFICATION_EVENT_ID,GTS_USER_ID)VALUES(1,23,2);

INSERT INTO  GTS_NOTIFICATION_SUBSCRIBER_USERS(GTS_NOTIFICATION_SUBSCRIBER_USERS_ID,GTS_NOTIFICATION_EVENT_ID,GTS_USER_ID)VALUES(2,24,3);