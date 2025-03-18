package com.gts.notification.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GTS_USER_PERSONAL_DETAILS")
public class UserPersonalDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gts_user_personal_detail_id")
	private Long gts_user_personal_detail_id;

    @Column(name="gts_user_id")
	private Long gts_user_id;
	
	@Column(name="gts_user_first_name")
	private String gts_user_first_name;
	
	@Column(name="gts_user_middle_name")
	private String gts_user_middle_name;
	
	@Column(name="gts_user_last_name")
	private String gts_user_last_name;

	public UserPersonalDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGts_user_first_name() {
		return gts_user_first_name;
	}
	
	public String getGts_user_middle_name() {
		return gts_user_middle_name;
	}

	public String getGts_user_last_name() {
		return gts_user_last_name;
	}


//	public Long getGts_applicant_personal_detail_id() {
//		return gts_applicant_personal_detail_id;
//	}

	
}
