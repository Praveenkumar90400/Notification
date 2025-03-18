
package com.gts.notification.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gts.notification.entities.UserEntity;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

	@Query(
			value = "select gts_user_id from GTS_USERS where gts_user_id= ?1",
			nativeQuery = true)
	Long isUserPresent( Long gts_user_id);
	
	}