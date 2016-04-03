package com.chenjie.demo.command;

import javax.annotation.Resource;

import com.chenjie.demo.service.TestService;

public class ListHostsCommand extends AbstractCommand {

	
    @Resource(name="testService")
    private TestService testService;
	
	@Override
	public void execute() {
		String test =  testService.test();
		this.setResponseObject(test);
	}

}
