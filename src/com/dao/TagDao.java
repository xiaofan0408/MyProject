package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Tag;
import com.util.DbUtil;

public class TagDao {
	
	public static int addTag(Tag tag ){
		String sql = "insert into t_tag(name) values" 
		        +"(?)";
      Connection connection= DbUtil.getConnection();
		
	  PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setString(1, tag.getName());
			
			
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
	public static Tag query(int id){
	    Tag result = null;
		Connection connection= DbUtil.getConnection();
		
	    PreparedStatement ps = null;
		
		String sql = "select * from t_tag where id = ?" ;
		
		try {
			ps=  connection.prepareStatement(sql);
			ps.setInt(1, id);
			

			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				result = new Tag();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
			}
			
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
		
		return result;
	}

}
