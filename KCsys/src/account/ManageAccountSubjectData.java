package account;

import java.math.BigInteger;
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
			//System.out.println(i);
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
	public int deleteSubject(int grade,String deleteid){
		int i=0;
		String[] subname={"classid","firstsubjectid","secondsubjectid","thirdsubjectid","fourthsubjectid","fifthsubjectid",
				"sixthsubjectid","seventhsubjectid"};
		String sn=subname[grade];
		try {
			sql = con.createStatement();
			sql.execute("delete accountsubject where "+sn+" = '"+deleteid+"'");
			i=1;
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
	//----------------------------------------------------------------------------------------------------------------
	public String getSubId(int level,String frontid){
		String s=null;
		String[] subname={"classid","firstsubjectid","secondsubjectid","thirdsubjectid","fourthsubjectid","fifthsubjectid",
				"sixthsubjectid","seventhsubjectid"};
		String sn=subname[level];
		System.out.println(sn);
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select max("+sn+") as subid from accountsubject where "+subname[level-1]+"='"+frontid+"'");
			String s2 = null;
			while(res.next()){
				s2=res.getString("subid");
			}
			if(s2!=null){
				BigInteger bigi1=new BigInteger("1");
				BigInteger bigi2=new BigInteger(s2);
				BigInteger result=bigi2.add(bigi1);
				s=result.toString();
			}else{
				s=frontid+"001";
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
		return s;
	}
	public List<String> getExistSubId(int level,String frontid){
		ArrayList<String> list =new ArrayList<String>();
		String[] subname={"classid","firstsubjectid","secondsubjectid","thirdsubjectid","fourthsubjectid","fifthsubjectid",
				"sixthsubjectid","seventhsubjectid"};
		String sn=subname[level];
		System.out.println(sn);
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select distinct "+sn+" from accountsubject where "+subname[level-1]+"='"+frontid+"'");
			while(res.next()){
				list.add(res.getString(sn));
				System.out.println(res.getString(sn));
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
