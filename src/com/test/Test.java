package com.test;

import java.util.ArrayList;
import java.util.Date;

import com.baidu.dao.ArticleDao;
import com.baidu.dao.UserDao;
import com.baidu.entity.Article;

public class Test {
	public static void main(String[] args) {
//		Article a = new Article(1, "测试22", "旗木卡卡西", "这是一个测试22", "002.png", new Date(
//				System.currentTimeMillis()), new Date(
//				System.currentTimeMillis()), "测试2", true);
		ArticleDao ad = new ArticleDao();
//		ad.addArticle(a);
//		ArrayList<Article> arts = ad.selectAllArticles();
//		if(arts != null && arts.size() > 0){
//			for (Article a1 : arts) {
//				System.out.println(a1.toString());
//			}
//		}
//		if(){
//			System.out.println("修改成功");
//		}else{
//			System.out.println("修改失败");
//		}
//		ArrayList<Article> arts = ad.query("测试");
		
//		for (Article a3 : arts) {
//			System.out.println(a3.toString());
//		}
//		int count = ad.query(31);
//		System.out.println(count);
		
//		UserDao ud = new UserDao();
//		System.out.println("密码"+ud.queryPassword("白晓文"));
		ArrayList<Integer> ids = ad.selectAllArticlesOrderByUpdateTime();
		for (Integer i : ids) {
			System.out.println(i);
		}
		String str = "";
		System.out.println(str==null);
	}
}
