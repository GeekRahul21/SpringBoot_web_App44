package in.sp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.entities.User;

import in.sp.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userser;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		
		
		// sare data layega database se
		List<User> users=userser.findAll();
		
		
		// dashboard page me bhejega 
		
		model.addAttribute("users",users);
		
		//total users
		
		model.addAttribute("totalUsers",users.size());
		
		
		//total admins
		
		Long totalAdmins=users.stream().filter(user->"ADMIN".equals(user.getRole())).count();
		
		model.addAttribute("totalAdmins",totalAdmins);
		
		
		
		return"adminDashboard";
	}
	
	@GetMapping("/deleteProfile")
	public String deleteUser(@RequestParam("id") int id) {
		
		boolean valid=userser.deleteUser(id);
		
		if(valid) {
			return "redirect:/admin/dashboard";
		}
		else {
			return "redirect:/Profile?id="+id;
		}
	}
	
	@PostMapping("/updateProfile")

	public String updateProfile(@ModelAttribute("user") User user)
	{
		
		user.setRole("USER");
		
		userser.updateUser(user);
		
		return "redirect:/admin/dashboard";
	}
	
	
	@GetMapping("/editProfile")
	public String editProfile(@RequestParam("id") int id ,Model model) {
		
		
		// getting id from request param and putting that id int variable id and here below method we are geeting full user details from that
		User user1=userser.getUserBYID(id);
		
		// sending user details to edit page
		model.addAttribute("user",user1);
		
		return "editProfile";
	}
	
	
	@GetMapping("/Logout")
	public String Logout(HttpServletRequest req) {
		
		HttpSession session =req.getSession(false);
		
		if(session!=null) {
			session.invalidate();
		}
		return"redirect:/LoginPage";  // here we are redirecting to login page beacuse we dont have object to pass we only want to logout
	}
	
	
	@GetMapping("/addUser")
	public String addUser(Model model) {
		
		model.addAttribute("user",new User());
		
		return"addUser";
	}
	
	@PostMapping("/saveUser")
	public String SaveUser(@ModelAttribute("user") User user) {
		
		user.setRole("USER");
		
	boolean status=	userser.AddUserAdmin(user);
	
	if(status) {
		return "redirect:/admin/dashboard";
	}
	else {
		return"addUser";
	}
			
	}

}
