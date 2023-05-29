package com.example.demo;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DAO.Logdao;

public class LogController {

	//SampleDaoの用意
		private final Logdao logdao;
	
		public LogController(Logdao logdao) {
			this.logdao = logdao;
		}
		
		@RequestMapping("/")
		public String top() {
			return "index";
		}
		
		@RequestMapping("/logform")
		public String form(Model model, Loginput loginput) {
			model.addAttribute("title", "入力フォーム");
			return "logform";
		}
		@RequestMapping("/logconfirm")
		public String confirm(@Validated Loginput Loginput, BindingResult result, Model model) {

			if (result.hasErrors()) {
				model.addAttribute("title", "入力フォーム");
				return "logform";
			}

			model.addAttribute("title", "確認ページ");
			return "logconfirm";
		}
		
		
		
}
