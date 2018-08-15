package com.baidu.entity;

import java.util.Date;

/**
 *  µÃÂ¿‡
 * 
 * @author Administrator
 * 
 */
public class Article {
	private Integer id;
	private String title;
	private String author;
	private String content;
	private String poster;
	private Date create_time;
	private Date update_time;
	private String type;
	private boolean flag;

	public Article() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Article(Integer id, String title, String author, String content,
			String poster, Date create_time, Date update_time, String type,
			boolean flag) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.poster = poster;
		this.create_time = create_time;
		this.update_time = update_time;
		this.type = type;
		this.flag = flag;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.title + ":" + this.author;
	}
}
