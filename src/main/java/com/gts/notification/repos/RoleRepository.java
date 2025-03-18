package com.gts.notification.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gts.notification.entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

	@Query(
			  value = "SELECT gts_role_id FROM GTS_ROLES WHERE gts_role_id = ?1", 
			  nativeQuery = true)
			  Integer findRoleIdByNative(int gts_role_id);
}
