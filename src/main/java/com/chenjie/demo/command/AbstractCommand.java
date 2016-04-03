package com.chenjie.demo.command;

import java.util.Map;

public abstract class AbstractCommand {
	
	private Map<String, String> fullUrlParams;
	
	private Object _responseObject = null;
	
	public abstract void execute() ;

	public Map<String, String> getFullUrlParams() {
		return fullUrlParams;
	}

	public void setFullUrlParams(Map<String, String> fullUrlParams) {
		this.fullUrlParams = fullUrlParams;
	}

	public Object getResponseObject() {
		return _responseObject;
	}

	public void setResponseObject(Object _responseObject) {
		this._responseObject = _responseObject;
	}
	

}
