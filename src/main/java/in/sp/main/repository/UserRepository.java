package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

	User findByEmailAndPassword(String email,String password);
}
