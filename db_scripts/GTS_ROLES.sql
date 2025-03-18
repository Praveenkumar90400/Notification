CREATE TABLE `gts_roles` (
  `gts_role_id` bigint NOT NULL AUTO_INCREMENT,
  `gts_role_description` varchar(255) NOT NULL,
  `gts_role_name` varchar(255) NOT NULL,
  `gts_role_status` varchar(255) NOT NULL,
  PRIMARY KEY (`gts_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



