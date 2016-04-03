package com.chenjie.demo.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenjie.demo.command.AbstractCommand;
import com.chenjie.demo.command.CommandMapping;
import com.chenjie.demo.util.SpringContextUtils;


public class ApiServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4703753377234333442L;

	public ApiServlet() {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        processRequest(req, resp);
    }


    @SuppressWarnings("unchecked")
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    	Response response = new Response();
		Map<String, Object[]> params = new HashMap<String, Object[]>();
		params.putAll(req.getParameterMap());
		
		utf8Fixup(req, params);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		Set<String> keys = params.keySet();
		Iterator<String> keysIter = keys.iterator();
		while (keysIter.hasNext()) {
			String key = (String) keysIter.next();
			String[] value = (String[]) params.get(key);
			paramMap.put(key, value[0]);
		}
		
		String command = paramMap.get("command");
		if(command!=null){
			response.setCommand(command);
			String commandName = CommandMapping.commandMap.get(command);
			if(commandName == null){
				response.setCode(Response.RESPONSE_CODE_COMMAND_NOT_FOUND);
				response.setResult("comand "+command+" not found");
			}else{
				try{
					Class<?> c = Class.forName(commandName);
					AbstractCommand cmd = (AbstractCommand) c.newInstance();
					SpringContextUtils.autowire(cmd);
					processParameters(cmd,c,paramMap);
					cmd.execute();
					response.setCode(Response.RESPONSE_CODE_SUCCESS);
					response.setResult(cmd.getResponseObject());
				}catch(Exception e){
					response.setCode(Response.RESPONSE_CODE_FAILED);
					response.setResult(e.getMessage());
				}
			}
		}
		
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(response.toString());
		resp.flushBuffer();
   
    }
    

    
    private void utf8Fixup(HttpServletRequest req, Map<String, Object[]> params) {
        if (req.getQueryString() == null) {
            return;
        }

        String[] paramsInQueryString = req.getQueryString().split("&");
        if (paramsInQueryString != null) {
            for (String param : paramsInQueryString) {
                String[] paramTokens = param.split("=", 2);
                if (paramTokens != null && paramTokens.length == 2) {
                    String name = paramTokens[0];
                    String value = paramTokens[1];

                    try {
                        name = URLDecoder.decode(name, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                    try {
                        value = URLDecoder.decode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                    params.put(name, new String[] { value });
                } else {
                }
            }
        }
    }
    
	private void processParameters(AbstractCommand action, Class<?> classname ,Map<String, String> params) {
		classname.getDeclaredFields();
		List<Field> fields = new ArrayList<Field>();
        Collections.addAll(fields, classname.getDeclaredFields());
        for (Field field : fields) {
        	field.setAccessible(true);
        	String fieldName = field.getName();
        	String fieldType = field.getType().getSimpleName();
        	if(!params.containsKey(fieldName)){
        		continue;
        	}
			try {
				if (fieldType.equals("String")) {
					field.set(action, String.valueOf(params.get(fieldName)));
				}else if(fieldType.equals("Boolean")){
					field.set(action, Boolean.valueOf(params.get(fieldName)));
				}else if(fieldType.equals("int")||fieldType.equals("Integer")){
					field.set(action, Integer.valueOf(params.get(fieldName)));
				}else if(fieldType.equals("Long")){
					field.set(action, Long.valueOf(params.get(fieldName)));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        }
	}


}
