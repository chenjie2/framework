package com.chenjie.demo.command;

import java.util.List;

import javax.annotation.Resource;

import com.chenjie.demo.annotation.Command;
import com.chenjie.demo.entity.User;
import com.chenjie.demo.service.IUserService;
import com.chenjie.demo.service.TestService;
import com.chenjie.demo.service.UserService;

@Command(name="test", auth = "")
public class ListHostsCommand extends AbstractCommand {

	
    @Resource(name="userService")
    private UserService userService;
    
    @Resource(name="testService")
    private TestService testService;
	
	@Override
	public void execute() {
		List<User> test =  userService.findAll();
		User vo = new User();
		vo.setAge(11);
		vo.setName("chenjie");
		vo.setNice_name("chenjie");
		userService.create(vo);
		this.setResponseObject(test);
	}

}
