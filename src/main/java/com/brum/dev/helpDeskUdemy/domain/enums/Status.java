package com.brum.dev.helpDeskUdemy.domain.enums;

public enum Status {
	
	OPEN(0, "OPEN"), IN_PROGRESS(1, "IN_PROGRESS"), CLOSED(2, "CLOSED");
	
	private Integer code;
	private String description;
	
	private Status(Integer codigo, String descricao) {
		this.code = codigo;
		this.description = descricao;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Status toEnum(Integer code) {
		if(code ==null) {
			return null;
		}
		
		for(Status p: Status.values()) {
			if(code.equals(p.code)) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("The code doesn't match with any status");
	}
	
}
