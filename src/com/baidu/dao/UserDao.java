package com.baidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baidu.encryption.EncrypMD5;
import com.baidu.entity.Users;
import com.baidu.util.DB;

/**
 * 管理员类
 * @author Administrator
 *
 */
public class UserDao {
	//根据用户名查对应的密码
	public String queryPassword(String username){
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String password = null;
		
		try {
			conn = DB.getConnection();
			String sql = " select password from users where username = ? ";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, username);
			rs = ptmt.executeQuery();
			if(rs.next()){
				password = rs.getString("password");
			}
			return password;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
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
	//根据用户名查对应的ID
		public Integer queryID(String username){
			Connection conn = null;
			PreparedStatement ptmt = null;
			ResultSet rs = null;
			Integer id = null;
			
			try {
				conn = DB.getConnection();
				String sql = " select id from users where username = ? ";
				ptmt = conn.prepareStatement(sql);
				ptmt.setString(1, username);
				rs = ptmt.executeQuery();
				if(rs.next()){
					id = rs.getInt("id");
				}
				return id;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally{
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
		//根据ID查对应的用户名
				public String queryUsername(Integer id){
					Connection conn = null;
					PreparedStatement ptmt = null;
					ResultSet rs = null;
					String username = null;
					
					try {
						conn = DB.getConnection();
						String sql = " select username from users where id = ? ";
						ptmt = conn.prepareStatement(sql);
						ptmt.setInt(1, id);
						rs = ptmt.executeQuery();
						if(rs.next()){
							username = rs.getString("username");
						}
						return username;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}finally{
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
	//添加新的用户
	public boolean addNewUser(Users u){
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		try {
			conn = DB.getConnection();
			String sql = " insert into users" +
					" (username,password)" +
					" values( ?,?) ";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, u.getUsername());
			ptmt.setString(2, new String(EncrypMD5.eccrypt(u.getPassword())));
			ptmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
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
}
