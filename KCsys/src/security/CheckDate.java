package security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class CheckDate {
	public static boolean ReturnCheckDateResult(String sdate){
  	  String webUrl4 = "http://www.ntsc.ac.cn";//�й���ѧԺ������ʱ����
    	 try {
	            URL url = new URL(webUrl4);// ȡ����Դ����
	            URLConnection uc = url.openConnection();// �������Ӷ���
	            uc.connect();// ��������
	            long ld = uc.getDate();// ��ȡ��վ����ʱ��
	            Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// �������ʱ��
	            String s=sdf.format(date);
	            if(s.equals(sdate)==true){
	            	 //JOptionPane.showMessageDialog(null,"���ں˶Գɹ�");
	            	 return true;
	            }else{
	            	JOptionPane.showMessageDialog(null,"ϵͳ��������������ڲ����\n��ͬ��ϵͳ���ں�����");
		            System.exit(0);
	            }
	        } catch (MalformedURLException e) {
	        	JOptionPane.showMessageDialog(null,"URL��ʽ��������ϵ������˾����BUG");
	        	System.exit(0);
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null,"�������磬ȷ�����糩ͨ");
	            System.exit(0);
	        }
		return false;
	}
}
