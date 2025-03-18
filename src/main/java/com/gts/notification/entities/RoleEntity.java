package com.gts.notification.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GTS_ROLES")
public class RoleEntity {

	@Id
	@Column(name="gts_role_id")
	private Integer gts_role_id ; 
	private String gts_role_name;
	
	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getGts_role_id() {
		return gts_role_id;
	}

	public String getGts_role_name() {
		return gts_role_name;
	}

	@OneToMany(mappedBy = "roles", fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    private Set<GTSNotificationEventSubscriberByRoles> notificationEventSubscriberByRoles;
	
}
