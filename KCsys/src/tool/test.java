package tool;

import java.util.ArrayList;

public class test {
	public static void main(String[] args){
		String e="MF175000037£»MF175000038£»MF175000039£»";
		String c="²âÊÔ²âÊÔ²âÊÔ";
			
			ArrayList<String> result=new ArrayList<String>();
			String temp=e.trim();
			temp=temp.replaceAll("£»", ";");
			System.out.println("java×Ö·û´®³¤¶È£º"+temp.length());
			System.out.println("×Ö·û´®×Ö½Ú£º"+temp.getBytes().length);
			if(temp.length()==temp.getBytes().length){
				if(temp.length()%12==0){
					int en=temp.length()/12;
					for(int i=0;i<en;i++){
						result.add(temp.substring(0, 12));
						temp=temp.substring(12);
					}
				System.out.println("½á¹û¼¯£º"+result.get(0)+result.get(1)+result.get(2));
				}else{
					System.out.println("ÆäËû×Ö·û´®");
				}
			}else{
				
			}
			System.out.println(c.length());
			System.out.println(c.getBytes().length);
	}
}
