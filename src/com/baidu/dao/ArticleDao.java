package com.baidu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.baidu.entity.Article;
import com.baidu.util.DB;
import com.sun.org.apache.regexp.internal.recompile;

public class ArticleDao {
	// ��
	public boolean addArticle(Article a) {
		Connection conn = null;
		PreparedStatement ptmt = null;

		try {
			conn = DB.getConnection();
			String sql = " insert into articles"
					+ " ( title, author, content, poster, create_time, update_time, type, ishow)"
					+ " values(" + " ?,?,?,?,?,?,?,?)";
			ptmt = conn.prepareStatement(sql);
			// ptmt.setInt(1, a.getId());
			ptmt.setString(1, a.getTitle());
			ptmt.setString(2, a.getAuthor());
			ptmt.setString(3, a.getContent());
			ptmt.setString(4, a.getPoster());
			//PreparedStatement��setDate()�����Ὣsql����Date��ʱ������,ʱ����������ݿ��Ϊ00:00:00
			//PreparedStatement��setTimestamp�����ſ�����ȷ�洢ʱ��,����������ʱ����
			ptmt.setTimestamp(5, new Timestamp(a.getCreate_time().getTime()));
			ptmt.setTimestamp(6, new Timestamp(a.getUpdate_time().getTime()));
			ptmt.setString(7, a.getType());
			ptmt.setInt(8, a.isFlag() == true ? 1 : 0);
			ptmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ɾ
	public boolean deleteArticle(Integer id) {
		Connection conn = null;
		PreparedStatement ptmt = null;

		try {
			conn = DB.getConnection();
			String sql = " delete from articles" + " where id = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ��
	public boolean updateArticle(Article a) {
		Connection conn = null;
		PreparedStatement ptmt = null;

		try {
			conn = DB.getConnection();
			String sql = " update articles"
					+ " set title=?,author=?,content=?,poster=?,update_time=?,type=?,ishow=?"
					+ " where id=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, a.getTitle());
			ptmt.setString(2, a.getAuthor());
			ptmt.setString(3, a.getContent());
			ptmt.setString(4, a.getPoster());
			//PreparedStatement��setDate()�����Ὣsql����Date��ʱ������,ʱ����������ݿ��Ϊ00:00:00
			//PreparedStatement��setTimestamp�����ſ�����ȷ�洢ʱ��,����������ʱ����
			ptmt.setTimestamp(5, new Timestamp(a.getUpdate_time().getTime()));
			ptmt.setString(6, a.getType());
			ptmt.setInt(7, a.isFlag() == true ? 1 : 0);
			ptmt.setInt(8, a.getId());
			ptmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ��ȫ��
	public ArrayList<Article> selectAllArticles() {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<Article> arts = new ArrayList<Article>();

		try {
			conn = DB.getConnection();
			String sql = " select * from articles";
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setAuthor(rs.getString("author"));
				a.setContent(rs.getString("content"));
				a.setPoster(rs.getString("poster"));
				a.setCreate_time(rs.getDate("create_time"));
				a.setUpdate_time(rs.getDate("update_time"));
				a.setType(rs.getString("type"));
				a.setFlag(rs.getInt("ishow") == 1 ? true : false);
				arts.add(a);
			}
			return arts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ��ȫ�� ͨ��ʱ������
	public ArrayList<Integer> selectAllArticlesOrderByUpdateTime() {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<Integer> artsId = new ArrayList<Integer>();

		try {
			conn = DB.getConnection();
			String sql = " select id from articles order by update_time desc ";
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				artsId.add(rs.getInt("id"));
			}
			return artsId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	// ������
	// ��ȫ��
	public ArrayList<Article> selectFiveArticles(String index) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<Article> arts = new ArrayList<Article>();

		try {
			conn = DB.getConnection();
			String sql = " select * from articles order by update_time desc limit ?, 5";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, Integer.parseInt(index));
			rs = ptmt.executeQuery();
			while (rs.next()) {
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setAuthor(rs.getString("author"));
				a.setContent(rs.getString("content"));
				a.setPoster(rs.getString("poster"));
				a.setCreate_time(rs.getDate("create_time"));
				a.setUpdate_time(rs.getDate("update_time"));
				a.setType(rs.getString("type"));
				a.setFlag(rs.getInt("ishow") == 1 ? true : false);
				arts.add(a);
			}
			return arts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	// ��1�� ��ͨ���ڼ�����¼
//	public Article selectOneArticles(Integer count) {
//		Connection conn = null;
//		PreparedStatement ptmt = null;
//		ResultSet rs = null;
//		Article a = null;
//
//		try {
//			conn = DB.getConnection();
//			String sql = " select * from articles order by update_time desc limit ?,1";
//			ptmt = conn.prepareStatement(sql);
//			ptmt.setInt(1, count);
//			rs = ptmt.executeQuery();
//			if (rs.next()) {
//				a = new Article();
//				a.setId(rs.getInt("id"));
//				a.setTitle(rs.getString("title"));
//				a.setAuthor(rs.getString("author"));
//				a.setContent(rs.getString("content"));
//				a.setPoster(rs.getString("poster"));
//				a.setCreate_time(rs.getDate("create_time"));
//				a.setUpdate_time(rs.getDate("update_time"));
//				a.setType(rs.getString("type"));
//				a.setFlag(rs.getInt("ishow") == 1 ? true : false);
//			}
//			return a;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//					rs = null;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (ptmt != null) {
//				try {
//					ptmt.close();
//					ptmt = null;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	// �鵥��--ͨ��ID
	public Article selectArticle(Integer id) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Article a = null;

		try {
			conn = DB.getConnection();
			String sql = " select * from articles" + " where id =?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				a = new Article();
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setAuthor(rs.getString("author"));
				a.setContent(rs.getString("content"));
				a.setPoster(rs.getString("poster"));
				a.setCreate_time(rs.getDate("create_time"));
				a.setUpdate_time(rs.getDate("update_time"));
				a.setType(rs.getString("type"));
				a.setFlag(rs.getInt("ishow") == 1 ? true : false);
			}
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// �鵥��ͨ��title
	public ArrayList<Article> query(String title) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<Article> arts = new ArrayList<Article>();

		try {
			conn = DB.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from articles");
			sql.append(" where title like ? ");
			ptmt = conn.prepareStatement(sql.toString());
			ptmt.setString(1, "%" + title + "%");
			rs = ptmt.executeQuery();
			while (rs.next()) {
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setAuthor(rs.getString("author"));
				a.setContent(rs.getString("content"));
				a.setPoster(rs.getString("poster"));
				a.setCreate_time(rs.getDate("create_time"));
				a.setUpdate_time(rs.getDate("update_time"));
				a.setType(rs.getString("type"));
				a.setFlag(rs.getInt("ishow") == 1 ? true : false);
				arts.add(a);
			}
			return arts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ptmt != null) {
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	// ͨ����֪ID��ѯ�ǵڼ�����¼
//	public Integer query(Integer id) {
//		Connection conn = null;
//		PreparedStatement ptmt = null;
//		ResultSet rs = null;
//		Integer count = null;
//
//		try {
//			conn = DB.getConnection();
//			String sql = " select count(*) from articles where id < ?";
//			ptmt = conn.prepareStatement(sql);
//			ptmt.setInt(1, id);
//			rs = ptmt.executeQuery();
//			if (rs.next()) {
//				count = rs.getInt(1);
//			}
//			return count;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//					rs = null;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (ptmt != null) {
//				try {
//					ptmt.close();
//					ptmt = null;
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
