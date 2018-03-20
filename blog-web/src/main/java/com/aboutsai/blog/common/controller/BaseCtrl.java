package com.aboutsai.blog.common.controller;

import org.springframework.ui.Model;

public class BaseCtrl {
	protected void setCurrentModule(Model model, String moduleName) {
		model.addAttribute("currentModule", moduleName);
	}
}
