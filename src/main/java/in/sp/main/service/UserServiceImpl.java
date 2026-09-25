package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.User;
import in.sp.main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;

	@Override
	public boolean addUser(User user) {

		try {
			userrepo.save(user);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public User loginUser(String email, String password) {

		try {
			User user = userrepo.findByEmailAndPassword(email, password);

			if (user != null) {

				return user;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateUser(User user) {

		try {
			userrepo.save(user);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public User getUserBYID(int id) {

		return userrepo.findById(id).orElse(null);
	}

	@Override
	public boolean deleteUser(int id) {

		try {

			userrepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<User> findAll() {
		
		return userrepo.findAll();
	}

	@Override
	public boolean AddUserAdmin(User user) {
		try {
		userrepo.save(user);
		return true;
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
