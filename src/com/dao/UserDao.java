package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.User;
import com.util.DbUtil;

public class UserDao {
	
	
  public static User query(int id){
  	
	     String sql = "select * from t_user where id = ?";
	     Connection connection= DbUtil.getConnection();
	 	 PreparedStatement ps = null;
	 	 ResultSet rs = null;
	 	 User user = new User();
	 	try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				user.setId(id);
				user.setUserName(rs.getString("UserName"));
				user.setPassWord(rs.getString("PassWord"));
				user.setEmail(rs.getString("Email"));
				user.setUserImg(rs.getString("UserImg"));
				user.setSumOfillust(rs.getInt("SumOfillust"));
				user.setSumOffollow(rs.getInt("SumOffollow"));
				user.setBeconcerned(rs.getInt("beconcerned"));
				user.setRemark(rs.getString("remark"));
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
	 	
	 	return user;
	
   }
  
  public static int save(User user){
		
	  String sql = "insert into t_user(UserName,PassWord,Email) values" 
		        +"(?,?,?)";
      Connection connection= DbUtil.getConnection();
		
	  PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.setString(3, user.getEmail());
		
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
  public static User login(User user) {
		
		User resultUser = null;
		Connection connection= DbUtil.getConnection();
		
	    PreparedStatement ps = null;
		
		String sql = "select * from t_user where UserName=? and PassWord=?" ;
		
		try {
			ps=  connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			
			ps.setString(2, user.getPassWord());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				resultUser = new User();
				
				resultUser.setId(rs.getInt("id"));
				resultUser.setUserName(rs.getString("UserName"));
				resultUser.setPassWord(rs.getString("PassWord"));
				resultUser.setEmail(rs.getString("Email"));
				resultUser.setBeconcerned(rs.getInt("beconcerned"));
				resultUser.setSumOffollow(rs.getInt("SumOffollow"));
				resultUser.setSumOfillust(rs.getInt("SumOfillust"));
				resultUser.setRemark(rs.getString("remark"));
				resultUser.setUserImg(rs.getString("UserImg"));
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
		
		return resultUser;
	}
  public static User find(User user) {
		
		User resultUser = null;
		Connection connection= DbUtil.getConnection();
		
	    PreparedStatement ps = null;
		
		String sql = "select * from t_user where UserName=? and PassWord=?" ;
		
		try {
			ps=  connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			
			ps.setString(2, user.getPassWord());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				resultUser = new User();
				
				resultUser.setId(rs.getInt("id"));
				resultUser.setUserName(rs.getString("UserName"));
				resultUser.setPassWord(rs.getString("PassWord"));
				resultUser.setEmail(rs.getString("Email"));
				resultUser.setBeconcerned(rs.getInt("beconcerned"));
				resultUser.setSumOffollow(rs.getInt("SumOffollow"));
				resultUser.setSumOfillust(rs.getInt("SumOfillust"));
				resultUser.setRemark(rs.getString("remark"));
				resultUser.setUserImg(rs.getString("UserImg"));
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
		
		return resultUser;
	}
  public static ArrayList<User> queryAll(){
	  
	    ArrayList<User> results = new ArrayList<User>();
		Connection connection= DbUtil.getConnection();
		
	    PreparedStatement ps = null;
		
		String sql = "select * from t_user order by beconcerned" ;
		
		try {
			ps=  connection.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				User resultUser = new User();
				
				resultUser.setId(rs.getInt("id"));
				resultUser.setUserName(rs.getString("UserName"));
				resultUser.setPassWord(rs.getString("PassWord"));
				resultUser.setEmail(rs.getString("Email"));
				resultUser.setBeconcerned(rs.getInt("beconcerned"));
				resultUser.setSumOffollow(rs.getInt("SumOffollow"));
				resultUser.setSumOfillust(rs.getInt("SumOfillust"));
				resultUser.setRemark(rs.getString("remark"));
				resultUser.setUserImg(rs.getString("UserImg"));
				
				results.add(resultUser);
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
	return results;	
   }
   public static int follow(int id,int followid){
	   String sql = "update t_user set SumOffollow = SumOffollow+1 where id = ? ";
	   String sql2 = "update t_user set beconcerned = beconcerned+1 where id = ? ";
	   Connection connection= DbUtil.getConnection();
		
		  PreparedStatement ps = null;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();
				ps = connection.prepareStatement(sql2);
				ps.setInt(1, followid);
					
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
   public static int unfollow(int id,int followid){
	   String sql = "update t_user set SumOffollow = SumOffollow - 1 where id = ? ";
	   String sql2 = "update t_user set beconcerned = beconcerned -1 where id = ? ";
	   Connection connection= DbUtil.getConnection();
		
		  PreparedStatement ps = null;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				 ps.executeUpdate();
				ps = connection.prepareStatement(sql2);
				ps.setInt(1, followid);
					
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
   public static int addillust(int authorid){
	   String sql = "update t_user set SumOfillust = SumOfillust + 1 where id = ? ";
	   Connection connection= DbUtil.getConnection();
		
		  PreparedStatement ps = null;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, authorid);
				 ps.executeUpdate();
			
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
}
