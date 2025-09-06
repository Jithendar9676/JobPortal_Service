package com.jobsadda.utility;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

	private String message;
	private Integer code;
	private LocalDateTime timestamp;
    private String path;
	

}
