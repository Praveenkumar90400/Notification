package com.gts.notification.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GTS_TIMEZONES")
public class TimeZoneEntity {
	
	@Id
	private Integer gts_timezone_id;
	private String gts_timezone_name;
	
	public TimeZoneEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getGts_timezone_id() {
		return gts_timezone_id;
	}

	public void setGts_timezone_id(Integer gts_timezone_id) {
		this.gts_timezone_id = gts_timezone_id;
	}

	public String getGts_timezone_name() {
		return gts_timezone_name;
	}

	public void setGts_timezone_name(String gts_timezone_name) {
		this.gts_timezone_name = gts_timezone_name;
	}
	
	@OneToMany(mappedBy = "timezone", fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    private Set<NotificationEntity> timezone;
	
	

}
