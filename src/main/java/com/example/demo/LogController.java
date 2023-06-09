package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DAO.Logdao;
import com.example.demo.entity.Logform;

@Controller
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
		public String confirm(@Validated Loginput loginput, BindingResult result, Model model) {

			if (result.hasErrors()) {
				model.addAttribute("title", "入力フォーム");
				return "input";
			}

			model.addAttribute("title", "確認");
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
		@RequestMapping("/list")
		public String list(Model model) {
			List<Logform> list = logdao.searchDb();
			model.addAttribute("dbList",list);
			model.addAttribute("title","タベータベース");
			return "list";
		}
		
		//条件検索(SELECT * WHERE)
		@RequestMapping("/search/{price}")
		public String list_limited(@RequestParam("price") Long price,@RequestParam("price2") Long price2,Model model) {
			List<Logform> list = logdao.searchDb_limited(price,price2);
			model.addAttribute("dbList",list);
			model.addAttribute("title","タベータベース");
			return "list";
		}
		
		
		//条件検索(SELECT * WHERE)
		@RequestMapping("/search2/{score}")
		public String list_limited_score(@RequestParam("score") Long score,Model model) {
		List<Logform> list = logdao.searchDb_limited_score(score);
		model.addAttribute("dbList",list);
		model.addAttribute("title","タベータベース");
		return "list";
		}
		
		//ソート(金額)
		@RequestMapping("/sort")
		public String list_sort(Model model) {
		List<Logform> list = logdao.sortDb();
		model.addAttribute("dbList",list);
		model.addAttribute("title","タベータベース");
		return"list";
		}
		
		
		//ソート(スコア)
		@RequestMapping("/sort2")
		public String list_sort2(Model model) {
		List<Logform> list = logdao.sort2Db();
		model.addAttribute("dbList",list);
		model.addAttribute("title","タベータベース");
		return"list";
		}
		
		
		//削除(DELETE)
		@RequestMapping("/del/{id}")
		public String destroy(@PathVariable Long id) {
			logdao.deleteDb(id);
			return "redirect:/list";
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
			model.addAttribute("title", "編集フォーム");
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
			return "redirect:/list";
		}
}
