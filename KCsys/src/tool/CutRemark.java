package tool;

import java.util.ArrayList;

public class CutRemark {
	public static ArrayList<String> getRemark(String temp){
		ArrayList<String> result=new ArrayList<String>();
		temp=temp.replaceAll("��", ";");
//		System.out.println("ԭʼ�ַ�����"+temp);
//		System.out.println("java�ַ������ȣ�"+temp.length());
//		System.out.println("�ַ����ֽڣ�"+temp.getBytes().length);
//		System.out.println("�ַ�����𣺺�Ӣ�켯");
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
//		System.out.println("�����������"+r);
//		for(int i=0;i<r;i++){
//			System.out.println("�����"+(i+1)+"��"+result.get(i));
//			System.out.println("������ֽ�"+(i+1)+"��"+result.get(i).getBytes().length+"�ֽ�");
//		}
		return result;
	}
//	public static void main(String[] args){
//		CutRemark c=new CutRemark();
//		c.getRemark("cccccccc����ccc���Բ�ccc�ԣ�����ccc���Բ���");
//		System.out.println();
//		c.getRemark("MF175000037��MF175000038��MF175000039;");
//	}
}
