package com.chenjie.demo.command;

import java.util.HashMap;
import java.util.Map;

public class CommandMapping {
	public static Map<String, String> commandMap = new HashMap<String, String>();
	static {
		commandMap.put("test", ListHostsCommand.class.getName());
	}

}
