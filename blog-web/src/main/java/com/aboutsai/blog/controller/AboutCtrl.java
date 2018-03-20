package com.aboutsai.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aboutsai.blog.common.controller.BaseCtrl;
import com.aboutsai.blog.common.datadict.DataDictionary;

@Controller
@RequestMapping({ "/about" })
public class AboutCtrl extends BaseCtrl {
	@RequestMapping({ "/index" })
	public String index(Model model) {
		super.setCurrentModule(model, DataDictionary.Module.about.name());
		return "about/about";
	}
}
