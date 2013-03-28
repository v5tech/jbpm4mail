package com.jbpm.mail;
import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;


public class TestMail {
	
	public static void main(String[] args) {
		ProcessEngine processEngine=Configuration.getProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("mail.jpdl.xml").deploy();
		ExecutionService executionService=processEngine.getExecutionService();
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("mail");
		Execution execution = processInstance.findActiveExecutionIn("state");
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("to", "sxyx2008@sina.com");
		parameters.put("person", "zhangsan");
		processInstance=executionService.signalExecutionById(execution.getId(), parameters);
		System.out.println(processInstance.isActive("send rectify note"));
		System.out.println(processInstance.isEnded());
	}
}
