package org.dxs.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.dxs.dao.UserMapper;
import org.dxs.entity.Permission;
import org.dxs.entity.User;
import org.dxs.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	@Override
	public User selectByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectByName(name);
	}

	@Override
	public Set<String> findRoles(String name) {
		User user = userMapper.selectByName(name);
		Set<String> roles = new HashSet<String>();
		roles.add(user.getRole().getRname());
		return roles;
	}

	@Override
	public Set<String> findPermission(String name) {
		List<Permission> plist = userMapper.selectPermission(name);
		Set<String> permissions = new HashSet<String>();
		for (Permission p : plist) {
			permissions.add(p.getPname());
		}
		return permissions;
	}

	@Override
	public int insert(User user) {

		return userMapper.insert(user);
	}
	
	
}
