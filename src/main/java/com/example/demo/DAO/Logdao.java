package com.example.demo.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Logform;


@Repository
public class Logdao {
	private final JdbcTemplate db;

	@Autowired
	public Logdao(JdbcTemplate db) {
		this.db = db;
	}

	//データの入力
	public void insertDb(Logform logform) {
		db.update("INSERT INTO busilog (shopname,foodname,price,genre,score,comment,shopaddress) VALUES(?,?,?,?,?,?,?)", logform.getShopname(),logform.getFoodname(),
				logform.getPrice(),logform.getGenre(),logform.getScore(),logform.getComment(),logform.getShopaddress());
	}

	
	//一覧表示(select * from busilog)
	public List<Logform> searchDb() {
		String sql = "SELECT * FROM busilog";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<Logform> resultDb2 = new ArrayList<Logform>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			Logform entformdb = new Logform();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setShopname((String) result1.get("shopname"));
			entformdb.setFoodname((String) result1.get("foodname"));
			entformdb.setPrice((int) result1.get("price"));
			entformdb.setGenre((String) result1.get("genre"));
			entformdb.setScore((int) result1.get("score"));
			entformdb.setComment((String) result1.get("comment"));
			entformdb.setShopaddress((String) result1.get("shopaddress"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}
		
		//Controllerに渡す
		return resultDb2;
	}

	
	
	public List<Logform> searchDb_limited(Long price, Long price2) {
		//データベースから取り出したデータをresultDB1に入れる
		System.out.print(price);
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM busilog where price >= ? AND price <= ?", price,price2);
		
		//画面に表示しやすい形のList(resultDB2)を用意
		List<Logform> resultDb2 = new ArrayList<Logform>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			Logform entformdb = new Logform();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setShopname((String) result1.get("shopname"));
			entformdb.setFoodname((String) result1.get("foodname"));
			entformdb.setPrice((int) result1.get("price"));
			entformdb.setGenre((String) result1.get("genre"));
			entformdb.setScore((int) result1.get("score"));
			entformdb.setComment((String) result1.get("comment"));
			entformdb.setShopaddress((String) result1.get("shopaddress"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}
		System.out.print(resultDb2);
		//Controllerに渡す
		return resultDb2;
	}
	
	
	public List<Logform> searchDb_limited_score(Long score) {
		//データベースから取り出したデータをresultDB1に入れる
		System.out.print(score);
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM busilog where score >= ?", score);
		
		//画面に表示しやすい形のList(resultDB2)を用意
		List<Logform> resultDb2 = new ArrayList<Logform>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			Logform entformdb = new Logform();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setShopname((String) result1.get("shopname"));
			entformdb.setFoodname((String) result1.get("foodname"));
			entformdb.setPrice((int) result1.get("price"));
			entformdb.setGenre((String) result1.get("genre"));
			entformdb.setScore((int) result1.get("score"));
			entformdb.setComment((String) result1.get("comment"));
			entformdb.setShopaddress((String) result1.get("shopaddress"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}
		System.out.print(resultDb2);
		//Controllerに渡す
		return resultDb2;
	}
	
	
	
	
	
	//削除(DELETE)
	public void deleteDb(Long id) {
		//コンソールに表示
		System.out.println("削除しました");
		//DBからデータを削除
		db.update("delete from busilog where id=?", id);

	}

	//更新画面の表示(SELECT)
	public List<Logform> selectOne(Long id) {

		//コンソールに表示
		System.out.println("編集画面を出します");
		//データベースから目的の1件を取り出して、そのままresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM busilog where id=?", id);
		//画面に表示しやすい形のList(resultDB2)を用意
		List<Logform> resultDb2 = new ArrayList<Logform>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {
			//データ1件分を1つのまとまりとするので、EntForm型の「entformdb」を生成
			Logform entformdb = new Logform();
			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setShopname((String) result1.get("shopname"));
			entformdb.setFoodname((String) result1.get("foodname"));
			entformdb.setPrice((int) result1.get("price"));
			entformdb.setGenre((String) result1.get("genre"));
			entformdb.setScore((int) result1.get("score"));
			entformdb.setComment((String) result1.get("comment"));
			entformdb.setShopaddress((String) result1.get("shopaddress"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}

		//Controllerに渡す
		return resultDb2;
	}
	
	//更新の実行(UPDATE)
		public void updateDb(Long id, Logform logform) {
			//コンソールに表示
			System.out.println("編集の実行");
			//UPDATEを実行
			db.update("UPDATE busilog SET shopname = ?,foodname = ?,price = ?,genre = ?,score = ?,comment = ?,shopaddress = ? WHERE id = ?",logform.getShopname(),logform.getFoodname(),
					logform.getPrice(),logform.getGenre(),logform.getScore(),logform.getComment(),logform.getShopaddress(),id);
		}
}
