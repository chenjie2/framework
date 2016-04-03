package com.chenjie.demo.servlet;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("serial")
public class Response implements Serializable {

	private int code=1;
	private String command;	
	private Object result;

	public static final int RESPONSE_CODE_SUCCESS = 0;

	public static final int RESPONSE_CODE_FAILED = 1;

	public static final int RESPONSE_CODE_PART_SUCCESS = 2;

	public static final int RESPONSE_CODE_SESSION_OVERTIME = 3;
	
	public static final int RESPONSE_CODE_COMMAND_NOT_FOUND = 4;
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	public String toString(){
		GsonBuilder gsonBuilder = new GsonBuilder().serializeSpecialFloatingPointValues();
		Gson gson = gsonBuilder.create();
		return gson.toJson(this);
	}

}
