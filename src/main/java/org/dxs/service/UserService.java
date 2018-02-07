package org.dxs.service;

import java.util.Set;

import org.dxs.entity.User;

public interface UserService {
	public User selectByName(String name);
	public Set<String> findRoles(String name);
	public Set<String> findPermission(String name);
	public int insert(User user);
}
