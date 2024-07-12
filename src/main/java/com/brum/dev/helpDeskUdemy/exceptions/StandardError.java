package com.brum.dev.helpDeskUdemy.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long timestamp;

	private Integer status;

	private String error;

	private String message;

	private String path;

}
