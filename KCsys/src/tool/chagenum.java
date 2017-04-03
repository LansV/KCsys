package tool;

import java.util.ArrayList;
import java.util.List;

public class chagenum {

	public void ck(){
		String num[] = { "Áã", "Ò¼", "·¡", "Èþ", "ËÁ", "Îé", "Â½", "Æâ", "°Æ", "¾Á" };
		String d[]={"Ôª","Ê°", "°Û", "Çª", "Íò", "Ê°", "°Û", "Çª", "ÒÚ", "Ê°", "°Û", "Çª", "Õ×", "Ê°","°Û", "Çª" };
		String x[]={"½Ç"};
		Double dn=5010058.9;
		String sd=Double.toString(dn);
		String[] cf=sd.split("\\.");
		String s=cf[0];
		int n=new Integer(s);
		System.out.println(cf[0]);
		String xs=cf[1];
		int xl=new Integer(xs);
		StringBuilder sb=new StringBuilder();
		List<Integer> ls=new ArrayList<Integer>();
		int cl = 0;
		for(int i=0;i<s.length();i++){
			cl=(int) Math.pow(10,s.length()-(i+1));
			int st=n/cl;
			ls.add(st);
			n=n-st*cl;
		}
		for(int i=0;i<ls.size();i++){
			sb.append(num[ls.get(i)]);
			sb.append(d[ls.size()-1-i]);
		}
		sb.append(num[xl]);
		sb.append(x[0]);
	    String regex1[] = {"ÁãÇª", "Áã°Û", "ÁãÊ°"};  
	    String regex2[] = {"ÁãÒÚ", "ÁãÍò", "ÁãÔª"};  
	    String regex3[] = {"ÒÚ", "Íò", "Ôª"};  
	    String send=new String(sb);
	    for(int i=0;i<3;i++){
	    	send=send.replaceAll(regex1[i],"Áã");
	    }
	    for(int i=0;i<3;i++){
            send=send.replaceAll("ÁãÁãÁã", "Áã");  
            send=send.replaceAll("ÁãÁã", "Áã");  
            send=send.replaceAll(regex2[i], regex3[i]);
	    }
	    send=send.replaceAll("Áã½Ç","");
		System.out.println(send);
	}
	
	public static void main(String[] args){
		chagenum c=new chagenum();
		c.ck();
	}
}
