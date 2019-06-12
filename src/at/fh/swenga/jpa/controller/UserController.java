package at.fh.swenga.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.fh.swenga.jpa.dao.UserRepository;
import at.fh.swenga.jpa.dao.UserRoleRepository;
import at.fh.swenga.jpa.model.UserModel;
import at.fh.swenga.jpa.model.UserRoleModel;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@RequestMapping("/fillUsers")
	@Transactional
	public String fillData(Model model) {

		UserRoleModel adminRole = userRoleRepository.findTop1ByRole("ROLE_ADMIN");
		if (adminRole == null)
			adminRole = new UserRoleModel("ROLE_ADMIN");

		UserRoleModel userRole = userRoleRepository.findTop1ByRole("ROLE_USER");
		if (userRole == null)
			userRole = new UserRoleModel("ROLE_USER");

		UserRoleModel godRole = userRoleRepository.findTop1ByRole("ROLE_GOD");
		if (godRole == null)
			godRole = new UserRoleModel("ROLE_GOD");

		UserModel admin = new UserModel("admin", "password", "admin@wtf.com", true);
		admin.encryptPassword();
		admin.addUserRole(userRole);
		admin.addUserRole(adminRole);
		userRepository.save(admin);

		UserModel tim = new UserModel("tim", "password", "admin@wtf.com", true);
		tim.encryptPassword();
		tim.addUserRole(userRole);
		tim.addUserRole(adminRole);
		tim.addUserRole(godRole);
		userRepository.save(tim);

		UserModel user = new UserModel("user", "password", "admin@wtf.com", true);
		user.encryptPassword();
		user.addUserRole(userRole);
		userRepository.save(user);

		return "forward:login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
		return "login";
	}

}
