package com.yhmall.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhmall.dao.UserDao;
import com.yhmall.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService,UserService {

	@Autowired
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

	public User reg(User user) {
		userDao.insert(user);
		return user;
	}

	@Override
	public User findUserByUname(String username) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username",username);
		User user = userDao.selectOne(wrapper);
		return user;
	}
}
