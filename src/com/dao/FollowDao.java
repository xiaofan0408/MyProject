package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Follow;
import com.model.User;
import com.util.DbUtil;

public class FollowDao {
	
	public static int addFollow(Follow follow){
		String sql = "insert into t_follow(userid,fansid) values" 
		        +"(?,?)";
      Connection connection= DbUtil.getConnection();
		
	  PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setInt(1, follow.getUserid());
			ps.setInt(2, follow.getFansid());
			
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if (ps!= null) {
				
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (connection!=null) {
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return -1;
	}
	
	public static ArrayList<Follow>  getFollow(int fansid){
		
		 String sql = "select * from t_follow where fansid = ?";
	     Connection connection= DbUtil.getConnection();
	 	 PreparedStatement ps = null;
	 	 ResultSet rs = null;
	 	ArrayList<Follow> results = new ArrayList<Follow>();
	 	 
	 	 
	 	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, fansid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Follow follow = new Follow();
				follow.setId(rs.getInt("id"));
				follow.setUserid(rs.getInt("userid"));
				follow.setFansid(rs.getInt("fansid"));
				results.add(follow);
		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if (rs!= null) {
				
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (ps!= null) {
				
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (connection!=null) {
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		 }
	 	
	 	return results;
		
	}
	
	public static ArrayList<Follow> getFans(int userid){
		 String sql = "select * from t_follow where userid = ?";
	     Connection connection= DbUtil.getConnection();
	 	 PreparedStatement ps = null;
	 	 ResultSet rs = null;
	 	ArrayList<Follow> results = new ArrayList<Follow>();
	 	 
	 	 
	 	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Follow follow = new Follow();
				follow.setId(rs.getInt("id"));
				follow.setUserid(rs.getInt("userid"));
				follow.setFansid(rs.getInt("fansid"));
				results.add(follow);
		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if (rs!= null) {
				
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (ps!= null) {
				
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (connection!=null) {
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		 }
	 	
	 	return results;
		
	}
	public static int deleteFollow(Follow follow){
		
		String sql ="delete from t_follow where userid=? and fansid = ?";
		Connection connection= DbUtil.getConnection();
		
		  PreparedStatement ps = null;
			
			try {
				ps = connection.prepareStatement(sql);
			
				ps.setInt(1, follow.getUserid());
				ps.setInt(2, follow.getFansid());
				
				return ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
				if (ps!= null) {
					
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				if (connection!=null) {
					
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			return -1;
		
	}
	public static Follow find(Follow follow){
		String sql = "select * from t_follow where userid = ? and fansid = ?";
	     Connection connection= DbUtil.getConnection();
	 	 PreparedStatement ps = null;
	 	 ResultSet rs = null;
	 	 Follow result = null;
	 	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, follow.getUserid());
			ps.setInt(2, follow.getFansid());
			rs = ps.executeQuery();
			
			while(rs.next()){
				result = new Follow();
				result.setId(rs.getInt("id"));
				result.setUserid(rs.getInt("userid"));
				result.setFansid(rs.getInt("fansid"));
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if (rs!= null) {
				
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (ps!= null) {
				
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (connection!=null) {
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		 }
	 	
	 	return result;
	}


}
