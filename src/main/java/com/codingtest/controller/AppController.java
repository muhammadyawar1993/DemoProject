package com.codingtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingtest.model.User;
import com.codingtest.repository.UserRepository;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user, Model model) {
		boolean exists = userRepo.existsUserByUsername(user.getUsername());
		if(exists) {
			model.addAttribute("error", "username already exist!!");
			return "signup_form";
		}
		userRepo.save(user);
		
		return "register_success";
	}
}
