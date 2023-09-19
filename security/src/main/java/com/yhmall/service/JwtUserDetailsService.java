package com.yhmall.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yhmall.dao.UserDao;
import com.yhmall.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService,UserService {

	@Resource
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		User resuser = userDao.selectOne(wrapper);
		if (resuser!=null) {
			return new org.springframework.security.core.userdetails.User(resuser.getUsername(),resuser.getPassword(),new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public User reg(User user) {
		userDao.insert(user);
		return user;
	}

	public User backpwd(String username, String password){
		// 根据userName修改
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("userName",username);

		User user = new User();
		user.setPassword(password);

		userDao.update(user,updateWrapper);
		return user;
	}

	@Override
	public User findUserByUname(String username) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username",username);
		User user = userDao.selectOne(wrapper);
		return user;
	}

	//判断用户名是否重复
	public Object findAllUser(String usernames , HttpSession session) {
		int result = 1;
		List<String> list = userDao.findAllUseruame();
		String valname = (String) session.getAttribute("valname");
		if (list.contains(valname)){
			 result = 0;
		}
		return result;
	}
}
