package tool;

import java.util.ArrayList;

public class CutRemark {
	public static ArrayList<String> getRemark(String temp){
		ArrayList<String> result=new ArrayList<String>();
		temp=temp.replaceAll("；", ";");
//		System.out.println("原始字符串："+temp);
//		System.out.println("java字符串长度："+temp.length());
//		System.out.println("字符串字节："+temp.getBytes().length);
//		System.out.println("字符串类别：汉英混集");
		while(temp.getBytes().length>12){
			if(temp.substring(0, 6).getBytes().length==12){
				result.add(temp.substring(0, 6));
				temp=temp.substring(6);
			}else{
				int count=6;
				while(temp.substring(0, count).getBytes().length<12){
					count++;
					//System.out.println(count);
				}
				result.add(temp.substring(0, count));
				temp=temp.substring(count);
			}
		}
		result.add(temp);
//		int r=result.size();
//		System.out.println("结果集个数："+r);
//		for(int i=0;i<r;i++){
//			System.out.println("结果集"+(i+1)+"："+result.get(i));
//			System.out.println("结果集字节"+(i+1)+"："+result.get(i).getBytes().length+"字节");
//		}
		return result;
	}
//	public static void main(String[] args){
//		CutRemark c=new CutRemark();
//		c.getRemark("cccccccc测试ccc测试测ccc试，测试ccc测试测试");
//		System.out.println();
//		c.getRemark("MF175000037；MF175000038；MF175000039;");
//	}
}
