package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.Comment;
import com.util.DbUtil;





public class CommentDao {
	
	public static int save(Comment comment){
		
		  String sql = "insert into t_comment(content,userid,illustid) values" 
			        +"(?,?,?)";
	      Connection connection= DbUtil.getConnection();
			
		  PreparedStatement ps = null;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setString(1, comment.getContent());
				ps.setInt(2, comment.getUserid());
				ps.setInt(3, comment.getIllustid());

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
	
	public static ArrayList<Comment> query(int illustid){
		
		String sql = "select * from t_comment where illustid = ? order by time";
		
		ArrayList<Comment> results = new ArrayList<Comment>();
		
		Connection connection = DbUtil.getConnection();
		
		PreparedStatement stat = null;
		
		ResultSet rs = null;
		
		try {
		
			stat = connection.prepareStatement(sql);
			
			stat.setInt(1, illustid);
			
			rs = stat.executeQuery();
			
			while(rs.next()){
				
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setContent(rs.getString("content"));
				comment.setUserid(rs.getInt("userid"));
				comment.setIllustid(rs.getInt("illustid"));
				results.add(comment);
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
			
			if (connection!=null) {
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		if (stat!=null) {
			
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return results;
		
	}

}
