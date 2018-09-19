package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.model.Illust;
import com.util.DbUtil;


public class IllustDao {
	
	public static int save(Illust illust){
			
		  String sql = "insert into t_illust(authorid,realUrl,thumbUrl,title,remark) values" 
			        +"(?,?,?,?,?)";
	      Connection connection= DbUtil.getConnection();
			
		  PreparedStatement ps = null;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, illust.getAuthorid());
				ps.setString(2, illust.getRealURL());
				ps.setString(3, illust.getThumbURL());
				ps.setString(4, illust.getTitle());
				ps.setString(5, illust.getRemark());
			
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
    public static Illust query(int id){
    	
	     String sql = "select * from t_illust where id = ?";
	     Connection connection= DbUtil.getConnection();
	 	 PreparedStatement ps = null;
	 	 ResultSet rs = null;
	 	 Illust illust = new Illust();
	 	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				illust.setId(id);
				illust.setAuthorid(rs.getInt("authorid"));
				illust.setRealURL(rs.getString("realURL"));
				illust.setThumbURL(rs.getString("thumbURL"));
				illust.setWidth(rs.getDouble("width"));
				illust.setHeight(rs.getDouble("height"));
				illust.setTitle(rs.getString("title"));
				illust.setRemark(rs.getString("remark"));
				illust.setTime(rs.getTimestamp("time"));
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
	 	
	 	return illust;
 	
     }
    
public static ArrayList<Illust> getTop(int num){
		
		String sql = "select * from t_illust where UNIX_TIMESTAMP(now())-UNIX_TIMESTAMP(time)<=864000 "
				+ "order by score desc limit 0,?";
		
		ArrayList<Illust> results = new ArrayList<Illust>();
		
		Connection connection = DbUtil.getConnection();
		
		PreparedStatement stat = null;
		
		ResultSet rs = null;
		
		try {
		
			stat = connection.prepareStatement(sql);
			
			stat.setInt(1, num);
			
			rs = stat.executeQuery();
			
			while(rs.next()){
				Illust illust = new Illust();
				illust.setId(rs.getInt("id"));
				illust.setAuthorid(rs.getInt("authorid"));
				illust.setRealURL(rs.getString("realURL"));
				illust.setThumbURL(rs.getString("thumbURL"));
				illust.setWidth(rs.getDouble("width"));
				illust.setHeight(rs.getDouble("height"));
				illust.setTitle(rs.getString("title"));
				illust.setScore(rs.getInt("score"));
				illust.setRemark(rs.getString("remark"));
				
				results.add(illust);
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
public static ArrayList<Illust> getHotPage(int first,int second){
	
	String sql = "select * from t_illust where UNIX_TIMESTAMP(now())-UNIX_TIMESTAMP(time)<=864000 "
			+ "order by score desc limit ?,?";
	
	ArrayList<Illust> results = new ArrayList<Illust>();
	
	Connection connection = DbUtil.getConnection();
	
	PreparedStatement stat = null;
	
	ResultSet rs = null;
	
	try {
	
		stat = connection.prepareStatement(sql);
		
		stat.setInt(1, first);
		stat.setInt(2, second);
		
		rs = stat.executeQuery();
		
		while(rs.next()){
			Illust illust = new Illust();
			illust.setId(rs.getInt("id"));
			illust.setAuthorid(rs.getInt("authorid"));
			illust.setRealURL(rs.getString("realURL"));
			illust.setThumbURL(rs.getString("thumbURL"));
			illust.setWidth(rs.getDouble("width"));
			illust.setHeight(rs.getDouble("height"));
			illust.setTitle(rs.getString("title"));
			illust.setScore(rs.getInt("score"));
			illust.setRemark(rs.getString("remark"));
			
			results.add(illust);
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

public static int getHotNum(){
	
	String sql = "select count(*) as num from t_illust where UNIX_TIMESTAMP(now())-UNIX_TIMESTAMP(time)<=864000";
	
	
	Connection connection = DbUtil.getConnection();
	
	PreparedStatement stat = null;
	
	ResultSet rs = null;
	
	try {
	
		stat = connection.prepareStatement(sql);
		
		rs = stat.executeQuery();
		
		rs.next();
		
		int num = rs.getInt("num");
		
		return num;
		
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
	
	return -1;
	
}   
public static ArrayList<Illust> queryAll(int userid){
	
	String sql = "select * from t_illust where authorid = ?";
	
	
	Connection connection = DbUtil.getConnection();
	
	ArrayList<Illust> results = new ArrayList<Illust>();
	
	PreparedStatement stat = null;
	
	ResultSet rs = null;
	
	try {
	
		stat = connection.prepareStatement(sql);
		stat.setInt(1, userid);
		rs = stat.executeQuery();
		
		while(rs.next()){
			Illust illust = new Illust();
			illust.setId(rs.getInt("id"));
			illust.setAuthorid(rs.getInt("authorid"));
			illust.setRealURL(rs.getString("realURL"));
			illust.setThumbURL(rs.getString("thumbURL"));
			illust.setWidth(rs.getDouble("width"));
			illust.setHeight(rs.getDouble("height"));
			illust.setTitle(rs.getString("title"));
			illust.setScore(rs.getInt("score"));
			illust.setRemark(rs.getString("remark"));
			illust.setTime(rs.getTimestamp("time"));
			results.add(illust);
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
public static ArrayList<Illust> queryAllIllust(){
	
	String sql = "select * from t_illust order by score";
	
	
	Connection connection = DbUtil.getConnection();
	
	ArrayList<Illust> results = new ArrayList<Illust>();
	
	PreparedStatement stat = null;
	
	ResultSet rs = null;
	
	try {
	
		stat = connection.prepareStatement(sql);
		rs = stat.executeQuery();
		
		while(rs.next()){
			Illust illust = new Illust();
			illust.setId(rs.getInt("id"));
			illust.setAuthorid(rs.getInt("authorid"));
			illust.setRealURL(rs.getString("realURL"));
			illust.setThumbURL(rs.getString("thumbURL"));
			illust.setWidth(rs.getDouble("width"));
			illust.setHeight(rs.getDouble("height"));
			illust.setTitle(rs.getString("title"));
			illust.setScore(rs.getInt("score"));
			illust.setRemark(rs.getString("remark"));
			illust.setTime(rs.getTimestamp("time"));
			results.add(illust);
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
	public static int delIllust(Illust illust){
		String sql = "delete into t_collect where id=?"; 
	  Connection connection= DbUtil.getConnection();
		
	  PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
		
			ps.setInt(1, illust.getId());

			
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
	public static ArrayList<Illust> search(String word){
		
		String sql = "select * from t_illust where title like '%"+word+"%' order by score";
		
		
		Connection connection = DbUtil.getConnection();
		
		ArrayList<Illust> results = new ArrayList<Illust>();
		
		PreparedStatement stat = null;
		
		ResultSet rs = null;
		
		try {
		
			stat = connection.prepareStatement(sql);
			rs = stat.executeQuery();
			
			while(rs.next()){
				Illust illust = new Illust();
				illust.setId(rs.getInt("id"));
				illust.setAuthorid(rs.getInt("authorid"));
				illust.setRealURL(rs.getString("realURL"));
				illust.setThumbURL(rs.getString("thumbURL"));
				illust.setWidth(rs.getDouble("width"));
				illust.setHeight(rs.getDouble("height"));
				illust.setTitle(rs.getString("title"));
				illust.setScore(rs.getInt("score"));
				illust.setRemark(rs.getString("remark"));
				illust.setTime(rs.getTimestamp("time"));
				results.add(illust);
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
