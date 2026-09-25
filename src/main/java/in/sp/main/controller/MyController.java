package in.sp.main.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.entities.User;
import in.sp.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {

	@Autowired
	private UserService userser;
	
	@GetMapping("/regPage")
	public String openregisterpage(Model model) {

		model.addAttribute("user", new User());

		return "register";
	}

	@PostMapping("/regForm")
	public String submitForm(@ModelAttribute("user") User user, Model model) {
		
		user.setRole("USER");

		boolean status = userser.addUser(user);

		if (status) {

			model.addAttribute("successMsg", "Registration Successfull");

		} else {
			model.addAttribute("errorMsg", "Failed to Add");
		}

		return "register";
	}

	@GetMapping("/LoginPage")
	public String openloginPage(Model model) {

		model.addAttribute("user", new User());

		return "login";
	}

	@PostMapping("/LoginForm")
	public String SubmitLoginForm(@ModelAttribute("user") User user, Model model) {

		User validuser = userser.loginUser(user.getEmail(), user.getPassword());

		if (validuser!=null) {

			model.addAttribute("user",validuser);
			
			if(validuser.getRole().equals("ADMIN")) {
				
				return "redirect:/admin/dashboard";
			}
			else {
				return "Profile";
			}
			
		} else {

			model.addAttribute("errorMsg", "Failed to Login");
			return "login";
		}

	}
	
	@GetMapping("/editProfile")
	public String editProfile(@RequestParam("id") int id ,Model model) {
		
		
		// getting id from request param and putting that id int variable id and here below method we are geeting full user details from that
		User user1=userser.getUserBYID(id);
		
		// sending user details to edit page
		model.addAttribute("user",user1);
		
		return "editProfile";
	}

	@PostMapping("/updateProfile")

	public String updateProfile(@ModelAttribute("user") User user)
	{
		
		user.setRole("USER");
		userser.updateUser(user);
		
		return "Profile";
	}
//	
//	@GetMapping("/Profile")
//	public String openprofile() {
//		return "Profile";
//	}
	
	
	@GetMapping("/deleteProfile")
	public String deleteUser(@RequestParam("id") int id) {
		
		boolean valid=userser.deleteUser(id);
		
		if(valid) {
			return "redirect:LoginPage";
		}
		else {
			return "redirect:/Profile?id="+id;
		}
	}
	
	@GetMapping("/Logout")
	public String Logout(HttpServletRequest req) {
		
		HttpSession session =req.getSession(false);
		
		if(session!=null) {
			session.invalidate();
		}
		return"redirect:/LoginPage";  // here we are redirecting to login page beacuse we dont have object to pass we only want to logout
	}

}
