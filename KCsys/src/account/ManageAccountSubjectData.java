package account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageAccountSubjectData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public int addSubject(String[] st){
		int i=0;
		int cd=st.length;
		String[] subjectcount={"classid","classname","firstsubjectid","firstsubjectname","secondsubjectid","secondsubjectname",
				"thirdsubjectid","thirdsubjectname","fourthsubjectid","fourthsubjectname","fifthsubjectid","fifthsubjectname",
				"sixthsubjectid","sixthsubjectname","seventhsubjectid","seventhsubjectname"};
		String sqlString="insert into accountsubject(";
		String values="";
		for(int n=0;n<cd;n++){
			sqlString=sqlString+subjectcount[n];
			values=values+"'"+st[n]+"'";
			if(n!=cd-1){
				sqlString=sqlString+",";
				values=values+",";
			}else if(n==cd-1){
				sqlString=sqlString+") values ("+values+")";
			}
		}
		System.out.println(sqlString);
		try {
			sql = con.createStatement();
			sql.execute(sqlString);
			i=1;
			System.out.println(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			i=2;
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return i;
	}
	public List<String> getAccountClass(){
		ArrayList<String> list =new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct classid,classname from accountsubject order by classid");
			while(res.next()){
				list.add(res.getString("classid")+":"+res.getString("classname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return list;
	}
	public List<String> getFirstSubject(String id){
		ArrayList<String> list =new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct firstsubjectid,firstsubjectname from accountsubject where classid = "+id);
			while(res.next()){
				if(res.getString("firstsubjectid")!=null){
					list.add(res.getString("firstsubjectid")+":"+res.getString("firstsubjectname"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return list;
	}
	public List<String> getSecondSubject(String id){
		ArrayList<String> list =new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct secondsubjectid,secondsubjectname from accountsubject where firstsubjectid = "+id);
			while(res.next()){
				if(res.getString("secondsubjectid")!=null){
				list.add(res.getString("secondsubjectid")+":"+res.getString("secondsubjectname"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return list;
	}
	public List<String> getThirdSubject(String id){
		ArrayList<String> list =new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct thirdsubjectid,thirdsubjectname from accountsubject where secondsubjectid = "+id);
			while(res.next()){
				if(res.getString("thirdsubjectid")!=null){
				list.add(res.getString("thirdsubjectid")+":"+res.getString("thirdsubjectname"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return list;
	}
	public List<String> getFourthSubject(String id){
		ArrayList<String> list =new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct fourthsubjectid,fourthsubjectname from accountsubject where thirdsubjectid = "+id);
			while(res.next()){
				if(res.getString("fourthsubjectid")!=null){
				list.add(res.getString("fourthsubjectid")+":"+res.getString("fourthsubjectname"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return list;
	}
	public List<String> getFifthSubject(String id){
		ArrayList<String> list =new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct fifthsubjectid,fifthsubjectname from accountsubject where fourthsubjectid = "+id);
			while(res.next()){
				if(res.getString("fifthsubjectid")!=null){
				list.add(res.getString("fifthsubjectid")+":"+res.getString("fifthsubjectname"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		   	 try{
		     	   if(res!=null){
		     		   res.close();
		     	   }
		     	   if(sql!=null){
		     		   sql.close();
		     	   }
		     	 }catch(Exception e){
		     		 
		     	 }
		}
		return list;
	}
}
