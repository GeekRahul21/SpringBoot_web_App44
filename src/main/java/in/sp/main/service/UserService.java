package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.User;

public interface UserService {
	
	public boolean addUser(User user);
	public User loginUser(String email,String password);
	
	public boolean updateUser(User user);
	
	public User getUserBYID(int id);
	
	public boolean deleteUser(int id);
	
	List<User> findAll();
	
	public boolean AddUserAdmin(User user);

}
