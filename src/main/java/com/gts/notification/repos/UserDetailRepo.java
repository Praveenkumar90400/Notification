package com.gts.notification.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gts.notification.entities.UserPersonalDetail;

public interface UserDetailRepo extends JpaRepository<UserPersonalDetail,Long> {
		
		@Query(
				  value = "SELECT u.gts_user_first_name,u.gts_user_middle_name,u.gts_user_last_name FROM gts_user_personal_details u WHERE gts_user_id = ?1", 
				  nativeQuery = true)
		String getUserName(Long gts_user_id);
		

}
