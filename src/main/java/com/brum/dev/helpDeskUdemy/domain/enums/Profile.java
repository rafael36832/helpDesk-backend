package com.brum.dev.helpDeskUdemy.domain.enums;

public enum Profile {
	
	ADMIN(0, "ROLE_ADMIN"), DEFAULT(1, "ROLE_DEFAULT"), READONLY(2, "ROLE_READONLY");
	
	private Integer code;
	private String description;
	
	private Profile(Integer cod, String descr) {
		this.code = cod;
		this.description = descr;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Profile toEnum(Integer code) {
		if(code ==null) {
			return null;
		}
		
		for(Profile p: Profile.values()) {
			if(code.equals(p.code)) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("The code doesn't match with any profile");
	}
	
}
