package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerIP {
	static FileOutputStream out = null;
	static String filename="config/config.inf";
	public static ArrayList<String> getServerInfo(JFrame JOP){
		ArrayList<String> ls = new ArrayList<String>();
		File file = new File(filename);
		if (file.exists() == true) {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "get lockfile error");
			}
			byte b1[] = new byte[1024];
			int i = 0;
			try {
				i = in.read(b1);
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "read lockfile error");
			}
			String s = new String(b1, 0, i);
			String[] cs=s.split("\n");
			//System.out.println("i");
			String sip="";
			String suser="";
			int count=0;
			for(String st:cs){
				if(count==0){
					sip=st;
				}
				if(count==1){
					suser=st;
				}
				count++;
				
			}
			count=0;
			sip=sip.split(":")[1];
			sip=sip.replace('\n', ' ');
			suser=suser.split(":")[1];
			//System.out.print(sip.trim()+"   "+suser);
			ls.add(sip.trim());
			ls.add(suser.trim());
		}else{
			ls.add("");
			ls.add("");
		}
		return ls;
	}
	public static boolean writeSeverIP(JFrame JOP,String info) {
		File file = new File(filename);
		if (file.exists() == true) {
			try {
				out = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "get lockfile error");
				return false;
			}
			byte b[] = info.getBytes();
			try {
				out.write(b);
				out.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "write lockfile error");
				return false;
			}
			
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "create lockfile error");
				return false;
			}
			try {
				out = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "get lockfile error");
				return false;
			}
			byte b[] = info.getBytes();
			try {
				out.write(b);
				out.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "write lockfile error");
				return false;
			}
		}
	}
}
