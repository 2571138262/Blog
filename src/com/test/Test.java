package com.test;

import java.util.ArrayList;
import java.util.Date;

import com.baidu.dao.ArticleDao;
import com.baidu.dao.UserDao;
import com.baidu.entity.Article;

public class Test {
	public static void main(String[] args) {
//		Article a = new Article(1, "����22", "��ľ������", "����һ������22", "002.png", new Date(
//				System.currentTimeMillis()), new Date(
//				System.currentTimeMillis()), "����2", true);
		ArticleDao ad = new ArticleDao();
//		ad.addArticle(a);
//		ArrayList<Article> arts = ad.selectAllArticles();
//		if(arts != null && arts.size() > 0){
//			for (Article a1 : arts) {
//				System.out.println(a1.toString());
//			}
//		}
//		if(){
//			System.out.println("�޸ĳɹ�");
//		}else{
//			System.out.println("�޸�ʧ��");
//		}
//		ArrayList<Article> arts = ad.query("����");
		
//		for (Article a3 : arts) {
//			System.out.println(a3.toString());
//		}
//		int count = ad.query(31);
//		System.out.println(count);
		
//		UserDao ud = new UserDao();
//		System.out.println("����"+ud.queryPassword("������"));
		ArrayList<Integer> ids = ad.selectAllArticlesOrderByUpdateTime();
		for (Integer i : ids) {
			System.out.println(i);
		}
		String str = "";
		System.out.println(str==null);
	}
}
