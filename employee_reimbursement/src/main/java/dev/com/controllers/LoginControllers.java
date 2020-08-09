package dev.com.controllers;

import javax.servlet.http.Cookie;

import com.google.gson.Gson;

import dev.com.dto.LoginDTO;
import dev.com.entities.Employee;
import dev.com.services.LoginServices;
import io.javalin.http.Handler;

public class LoginControllers {
	
	public static LoginServices logserv = new LoginServices();
	
	public static Handler createSession = (ctx) ->{
		Gson gson = new Gson();
		LoginDTO loginInfo =  gson.fromJson(ctx.body(), LoginDTO.class); 
		Employee employee = logserv.loginUser(loginInfo); 
		ctx.sessionAttribute("loggedInAs", employee);

		
	};
	
	public static Handler getUserInfo = (ctx) ->{
		Gson gson = new Gson();
		Employee employee = ctx.sessionAttribute("loggedInAs");
		ctx.result(gson.toJson(employee));
		
	};
	
	public Handler giveCookie = (ctx) ->{

		Cookie cookie = new Cookie("ID","1");
		ctx.cookie(cookie);
	};

}
