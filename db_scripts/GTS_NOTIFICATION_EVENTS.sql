CREATE TABLE `GTS_NOTIFICATION_EVENTS` (
  `GTS_NOTIFICATION_EVENT_ID` BIGINT NOT NULL,
  `GTS_NOTIFICATION_EVENT_NAME` VARCHAR(50) NOT NULL,
  `GTS_NOTIFICATION_EVENT_DESCRIPTION` VARCHAR(100) DEFAULT NULL,
  `GTS_NOTIFICATION_EVENT_STATUS` BIT NOT NULL,
  UNIQUE(GTS_NOTIFICATION_EVENT_NAME),
  PRIMARY KEY(GTS_NOTIFICATION_EVENT_ID)
   );

INSERT INTO  GTS_NOTIFICATION_EVENTS (GTS_NOTIFICATION_EVENT_ID,GTS_NOTIFICATION_EVENT_NAME,GTS_NOTIFICATION_EVENT_MESSAGE,GTS_NOTIFICATION_ENENT_STATUS)
VALUES (1,"ADISH","THIS NOTIFICATION ARE JOB CATOGERY",TRUE);

INSERT INTO  GTS_NOTIFICATION_EVENTS (GTS_NOTIFICATION_EVENT_ID,GTS_NOTIFICATION_EVENT_NAME,GTS_NOTIFICATION_EVENT_MESSAGE,GTS_NOTIFICATION_ENENT_STATUS)
VALUES (2,"AMIT","THIS NOTIFICATION ARE JOB CATOGERY",FALSE);