package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Collect;
import com.model.Follow;
import com.util.DbUtil;

public class CollectDao {
	
	public static int addCollect(Collect collect){
		String sql = "insert into t_collect(userid,illustid) values" 
		        +"(?,?)";
      Connection connection= DbUtil.getConnection();
		
	  PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setInt(1, collect.getUserid());
			ps.setInt(2, collect.getIllustid());
			
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
	
	public  static ArrayList<Collect> getCollect(int userid){
		
		String sql = "select * from t_collect where userid = ?";
	     Connection connection= DbUtil.getConnection();
	 	 PreparedStatement ps = null;
	 	 ResultSet rs = null;
	 	ArrayList<Collect> results = new ArrayList<Collect>();
	 	 
	 	 
	 	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Collect collect = new Collect();
				collect.setId(rs.getInt("id"));
				collect.setUserid(rs.getInt("userid"));
				collect.setIllustid(rs.getInt("illustid"));
				results.add(collect);
		
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
	
	public static int delCollect(Collect collect){
		String sql = "delete from t_collect where userid=? and  illustid=?"; 
      Connection connection= DbUtil.getConnection();
		
	  PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setInt(1, collect.getUserid());
			ps.setInt(2, collect.getIllustid());
			
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
	public static Collect find(Collect collect){
		String sql = "select * from t_collect where userid = ? and illustid = ?";
	     Connection connection= DbUtil.getConnection();
	 	 PreparedStatement ps = null;
	 	 ResultSet rs = null;
	 	 Collect result = null;
	 	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, collect.getUserid());
			ps.setInt(2, collect.getIllustid());
			rs = ps.executeQuery();
			
			while(rs.next()){
				result = new Collect();
				result.setId(rs.getInt("id"));
				result.setUserid(rs.getInt("userid"));
				result.setIllustid(rs.getInt("illustid"));
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
