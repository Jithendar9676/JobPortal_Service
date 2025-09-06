package com.jobsadda.exception;

import org.mapstruct.ap.shaded.freemarker.core.ReturnInstruction.Return;

public class JobsAddaException extends Exception {

	public JobsAddaException(String message) {
		super(message);
	}
}
