package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Relation;
import com.model.Tag;
import com.util.DbUtil;

public class RelationDao {
	
	public static int addRelation(Relation relation ){
		String sql = "insert into t_relation(illustid,tagid) values" 
		        +"(?,?)";
      Connection connection= DbUtil.getConnection();
		
	  PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setInt(1, relation.getIllustid());
			ps.setInt(2, relation.getTagid());
			
			
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
	public static ArrayList<Relation> query(int id){
	    ArrayList<Relation> result = new ArrayList<Relation>();
		Connection connection= DbUtil.getConnection();
		
	    PreparedStatement ps = null;
		
		String sql = "select * from t_relation where illustid = ?" ;
		
		try {
			ps=  connection.prepareStatement(sql);
			ps.setInt(1, id);
			

			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Relation relation = new Relation();
				
				relation.setId(rs.getInt("id"));
				relation.setIllustid(rs.getInt("illustid"));
				relation.setTagid(rs.getInt("tagid"));
				result.add(relation);
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
