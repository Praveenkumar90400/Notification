package com.gts.notification.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GTS_USERS")
public class UserEntity implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5371448876895586344L;
	@Id
    @Column(name="gts_user_id")
	private Long gts_user_id;
	
	@Column(name="gts_user_email")
	private String gts_user_email;
	
	@Column(name="gts_user_country_code")
	private short gts_country_code;
	
	@Column(name="gts_primary_contact_number")
	private Long gts_primary_contact_number;

	public UserEntity() {
	}
	
//	public Long getGts_recruiter_id() {
//		return gts_recruiter_id;
//	}
	public String getGts_user_email() {
		return gts_user_email;
	}


	public short getGts_country_code() {
		return gts_country_code;
	}


	public Long getGts_primary_contact_number() {
		return gts_primary_contact_number;
	}


	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    private Set<GTSNotificationsGroupSubscribers> notificationsGroupSubscribers;
	

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    private Set<GTSNotificationEventSubscribers> notificationEventSubscribers;
	
	
	@ManyToOne(targetEntity=UserPersonalDetail.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gts_user_id",referencedColumnName = "gts_user_id",insertable=false,updatable=false)
	private UserPersonalDetail UserDetail;
	
	

	public UserPersonalDetail getUserDetail() {
		return UserDetail;
	}
}


