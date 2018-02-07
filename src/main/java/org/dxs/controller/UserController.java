package org.dxs.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.dxs.entity.User;
import org.dxs.service.UserService;
import org.dxs.util.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Message login(@ModelAttribute User user,Boolean rememberMe) {
		System.out.println(user.getUname());
		//获得当前的Subject对象
		Subject currentUser = SecurityUtils.getSubject();
//		if(!currentUser.isAuthenticated()) {  //isAuthenticated()验证是否已被认证
			//将从前台传来的用户名和密码封装到一个UsernamePasswordToken对象中
			UsernamePasswordToken upToken = new UsernamePasswordToken(user.getUname(),user.getUpwd());
			rememberMe = rememberMe == null ? false : rememberMe;
			upToken.setRememberMe(rememberMe);
			System.out.println(rememberMe);
			try {
				currentUser.login(upToken);
				System.out.println("登录成功！");
				return new Message("登录成功！");
			}catch (UnknownAccountException e) {
				System.out.println("用户名不存在！");
				return new Message("用户名不存在！");
			}catch (IncorrectCredentialsException e) {
				System.out.println("密码错误！");
				return new Message("密码错误！");
			}catch (LockedAccountException e) {
				System.out.println("账号已被冻结！");
				return new Message("账号已被冻结！");
			}
//		}
//		System.out.println("登录成功！");
//		return new Message("登录成功！");
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	@ResponseBody
	public Message register(@ModelAttribute User user) {
		user.setRid(3);
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUname());
        Object obj = new SimpleHash("MD5", user.getUpwd(), credentialsSalt, 1024);
        user.setUpwd(obj.toString());
        int a = userService.insert(user);
        if(a>0) {
        	return new Message("注册成功！");
        }else {
        	return new Message("注册失败！");
        }
		
	}
	
	@RequiresPermissions(value= {"query"})
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public Message query() {
        return new Message("查询操作！");
	}
	
	@RequiresPermissions(value= {"insert"})
	@RequestMapping(value="add",method=RequestMethod.GET)
	@ResponseBody
	public Message add() {
        return new Message("添加操作！");
	}
	
	@RequiresPermissions(value= {"update"})
	@RequestMapping(value="update",method=RequestMethod.GET)
	@ResponseBody
	public Message update() {
        return new Message("修改操作！");
	}
	
	@RequiresPermissions(value= {"del"})
	@RequestMapping(value="del",method=RequestMethod.GET)
	@ResponseBody
	public Message del() {
        return new Message("删除操作！");
	}
	
	@RequiresRoles(value= {"admin"})
	@RequestMapping(value="roleAdmin",method=RequestMethod.GET)
	@ResponseBody
	public Message roleAdmin() {
        return new Message("admin权限！");
	}
	
	@RequiresRoles(value= {"manager"})
	@RequestMapping(value="roleManager",method=RequestMethod.GET)
	@ResponseBody
	public Message roleManager() {
        return new Message("manager权限！");
	}
	
	@RequiresRoles(value= {"normal"})
	@RequestMapping(value="roleNormal",method=RequestMethod.GET)
	@ResponseBody
	public Message roleNormal() {
        return new Message("normal权限！");
	}
}
