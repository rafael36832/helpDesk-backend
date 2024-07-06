package com.brum.dev.helpDeskUdemy.domain.enums;

public enum Priority {
	
	LOW(0, "LOW"), MEDIUM(1, "MEDIUM"), HIGH(2, "HIGH");
	
	private Integer code;
	private String description;
	
	private Priority(Integer codigo, String descricao) {
		this.code = codigo;
		this.description = descricao;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Priority toEnum(Integer code) {
		if(code ==null) {
			return null;
		}
		
		for(Priority p: Priority.values()) {
			if(code.equals(p.code)) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("The code doesn't match with any priority");
	}
	
}
