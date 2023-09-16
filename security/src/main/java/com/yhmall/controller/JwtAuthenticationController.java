package com.yhmall.controller;

import com.yhmall.bean.User;
import com.yhmall.configs.JwtTokenUtil;
import com.yhmall.service.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/security/user")
@Api(tags = "用户登录鉴权管理")
// @CrossOrigin
public class  JwtAuthenticationController {

	//SpringSecurity提供的验证管理器
	@Autowired
	private AuthenticationManager authenticationManager;

	//生成和验证token的工具类
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	//访问数据库,验证用户名和密码的业务类
	@Autowired
	private JwtUserDetailsService userDetailsService; //访问数据库+验证用户名和密码的  业务类

	@ApiOperation(value = "Hello World")
	@GetMapping("/hello")
	public Claims firstPage(@RequestHeader("Authorization") String bearerToken) {
		System.out.println("接收到的token为: " + bearerToken);
		//去掉 Bearer 注意Bearer后面有个空格
		String token = bearerToken.substring(7);
		Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
		return claims;
	}

	@RequestMapping({"/signout"})
	public Map<String,Object> signout(@RequestHeader("Authorization") String bearerToken,HttpSession session){
		Map<String,Object> result = new HashMap<>();
		session.removeAttribute("user");
		session.invalidate();
		result.put("code",1);
		return result;
	}

	@RequestMapping({"/loginCheck"})
	public Map<String,Object> loginCheck(@RequestHeader("Authorization") String bearerToken,HttpSession session){
		Map<String,Object> result = new HashMap<>();
		if (session.getAttribute("user")==null){
			result.put("code",0);
			result.put("msg","用户暂未登录");
			return result;
		}
		System.out.println("接收到的  token为："+bearerToken);
		String token = bearerToken.substring(7);
		User user = (User) session.getAttribute("user");
		UserDetails ud = userDetailsService.loadUserByUsername(user.getUsername());
		boolean b = jwtTokenUtil.validateToken(token,ud);
		if (b){
			result.put("code",1);
			result.put("data",user);
		}else {
			result.put("code",0);
		}
		return result;
	}

	@RequestMapping({"/getTokenDetail"})
	public Claims getTokenDetail(@RequestHeader("Authorization")String bearerToken){
		System.out.println("接受到的 token为："+bearerToken);
		String token = bearerToken.substring(7);
		Claims c= jwtTokenUtil.getAllClaimsFromToken(token);

		return c;
	}

	@ApiOperation(value = "用户登录操作")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uname",value = "用户名",required = true),
			@ApiImplicitParam(name = "upass",value = "密码",required = true)
	})
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public Map<String,Object> createAuthenticationToken(String username, String password,HttpSession session) throws Exception {
		Map<String,Object> result = new HashMap<>();

		authenticate(username, password);
		//根据用户名取出详情
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		//根据用户详情生成token
		final String token = jwtTokenUtil.generateToken(userDetails);

		//在业务层多增加一个方法 根据uname再查一次User(com.yc.bean)
		User user = userDetailsService.findUserByUname(username);
		session.setAttribute("user",user);

		//将token回传给客户端
		result.put("code",1);
		result.put("data",token);
		return result;
	}

	@ApiOperation(value = "用户注册操作")
	@ApiImplicitParams({
			@ApiImplicitParam(name="uname",value = "用户名",required = true),
			@ApiImplicitParam(name="upass",value = "密码",required = true),
			@ApiImplicitParam(name="head",value = "头像",required = true),
			@ApiImplicitParam(name="gender",value = "性别",required = true),
			@ApiImplicitParam(name="code",value = "验证码",required = true),
	})

	@RequestMapping(value = "/reg.action",method = RequestMethod.POST)
	public Map<String,Object> reg(User user, HttpSession session ,String valcode) throws Exception{
		Map<String,Object> result = new HashMap<>();
		String code = (String) session.getAttribute("code");
		if (!code.equals(valcode)){
			result.put("code",0);
			result.put("msg","验证码错误");
			return result;
		}
		//密码加密
		BCryptPasswordEncoder be = new BCryptPasswordEncoder();
		user.setPassword(be.encode(user.getPassword()));

		User u = this.userDetailsService.reg(user);  //业务注册
		result.put("code",1);
		result.put("date",u);
		return result;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			//调用认证管理器,对输入的用户名和密码进行认证
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
