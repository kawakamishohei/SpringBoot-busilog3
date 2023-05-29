package com.example.demo;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DAO.Logdao;
import com.example.demo.entity.Logform;

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
		
		@RequestMapping("/input")
		public String form(Model model,Loginput loginput) {
			model.addAttribute("title", "入力フォーム");
			return "input";
		}
		
		@RequestMapping("/confirm")
		public String confirm(@Validated Loginput Loginput, BindingResult result, Model model) {

			if (result.hasErrors()) {
				model.addAttribute("title", "入力フォーム");
				return "input";
			}

			model.addAttribute("title", "確認ページ");
			return "confirm";
		}
		
		@RequestMapping("/complete")
		public String complete(Loginput loginput, Model model) {
			Logform logform = new Logform();
			logform.setShopname(loginput.getShopname());
			logform.setFoodname(loginput.getFoodname());
			logform.setPrice(loginput.getPrice());
			logform.setGenre(loginput.getGenre());
			logform.setScore(loginput.getScore());
			logform.setComment(loginput.getComment());
			logform.setShopaddress(loginput.getShopaddress());
			logdao.insertDb(logform);
			return "complete";
		}
		
		//全件検索(SELECT)
		@RequestMapping("/view")
		public String view(Model model) {
			List<Logform> list = logdao.searchDb();
			model.addAttribute("dbList",list);
			model.addAttribute("title","一覧ページ");
			return "view";
		}
		
		//
		
		
		//削除(DELETE)
		@RequestMapping("/del/{id}")
		public String destroy(@PathVariable Long id) {
			logdao.deleteDb(id);
			return "redirect:/view";
		}
		
		//更新画面の表示(SELECT)
		@RequestMapping("/edit/{id}")
		public String editView(@PathVariable Long id,Model model) {
			//DBからデータを1件取ってくる(リストの形)
			List<Logform> list = logdao.selectOne(id);

			//リストから、オブジェクトだけをピックアップ
			Logform entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("loginput", entformdb);
			model.addAttribute("title", "編集ページ");
			return "edit";
		}
		
		//更新処理(UPDATE)
		@RequestMapping("/edit/{id}/exe")
		public String editExe(@PathVariable Long id,Model model,Loginput loginput) {
			//フォームの値をエンティティに入れ直し
			Logform logform = new Logform();
			System.out.println(loginput.getShopname());//取得できているかの確認
			logform.setShopname(loginput.getShopname());
			System.out.println(loginput.getFoodname());
			logform.setFoodname(loginput.getFoodname());
			logform.setPrice(loginput.getPrice());
			logform.setGenre(loginput.getGenre());
			logform.setScore(loginput.getScore());
			logform.setComment(loginput.getComment());
			logform.setShopaddress(loginput.getShopaddress());
			//更新の実行
			logdao.updateDb(id,logform);
			//一覧画面へリダイレクト
			return "redirect:/view";
		}
}
