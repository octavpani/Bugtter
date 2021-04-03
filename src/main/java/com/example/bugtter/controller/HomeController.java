package com.example.bugtter.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	@GetMapping("/top")
	public String top(Model model) {
		return makeLoginForm(model);
	}

	@PostMapping("/error")
	public String loginError(Model model, @RequestAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) Exception ex) {
		model.addAttribute("error", ex.getMessage());
		return makeLoginForm(model);
	}

	public String makeLoginForm(Model model) {
		return  "top";
	}

	@GetMapping("/home")
	public String login(Authentication loginUser, Model model) {
		return "home";
	}

}
