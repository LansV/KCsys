package order;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int qx;
	static String Version="V1.170212";
	String UserName;
	String PcName=null;
	String PcMac=null;
	boolean EqualsVersion=false;
     public Login(){
    	 Dao d=new Dao();
         Connection con=d.getcon();
         Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		 
         //-----------------------------------------------
         JFrame changePassW=new JFrame("����������");
         changePassW.setResizable(false);
         changePassW.setAlwaysOnTop(true);
         changePassW.setBounds(p.x - 230 / 2, p.y - 160 / 2, 230, 160);
         Container changePassW_Content=changePassW.getContentPane();
         changePassW_Content.setLayout(null);
         JLabel changePassW_L1=new JLabel("����������:");
         changePassW_L1.setBounds(5,15,120,24);
         changePassW_Content.add(changePassW_L1);
         JTextField changePassW_T1=new JPasswordField();
         changePassW_T1.setBounds(80,15,130,24);
         changePassW_Content.add(changePassW_T1);
         JLabel changePassW_L2=new JLabel("ȷ��������:");
         changePassW_L2.setBounds(5,55,120,24);
         changePassW_Content.add(changePassW_L2);
         JTextField changePassW_T2=new JPasswordField();
         changePassW_T2.setBounds(80,55,130,24);
         changePassW_Content.add(changePassW_T2);
         JButton changePassW_B=new JButton("����");
         changePassW_B.setBounds(85,95,60,25);
         changePassW_Content.add(changePassW_B);
         changePassW_T2.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					changePassW_B.doClick();
				}
			}
         });
         //------------------------------------------------------------------------------
         String EqualStr = null;
    	 try{
    		Statement sql = con.createStatement();
			ResultSet res = sql.executeQuery("select*from VersionControl");
			while(res.next()){
				EqualStr=res.getString("CVersion").trim();
			}
    	 }catch(Exception e){
    		 JOptionPane.showMessageDialog(null,"��ȡ�汾����\n����ϵ������˾��ȡ����֧��");
    		 System.exit(0);
    	 }
    	 if(Version.equals(EqualStr)==true){
    		 InetAddress ia = null;
    		
	    		try {
					ia = InetAddress.getLocalHost();
					String n=ia.toString();
					String[] s=n.split("/");
					PcName=s[0];
					//System.out.println(PcName);
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					PcName="";
				}
	    		try{
	    			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
	    			//System.out.println("mac���鳤�ȣ�"+mac.length);
	    			StringBuffer sb = new StringBuffer("");
	    			for(int i=0; i<mac.length; i++) {
	    				if(i!=0) {
	    					sb.append("-");
	    				}
	    				//�ֽ�ת��Ϊ����
	    				int temp = mac[i]&0xff;
	    				String str = Integer.toHexString(temp);
	    				if(str.length()==1) {
	    					sb.append("0"+str);
	    				}else {
	    					sb.append(str);
	    				}
	    			}
	    			PcMac=sb.toString().toUpperCase();
	    			//System.out.println(PcMac);
	    		}catch(SocketException e){
	    			PcMac="";
	    		}
	    		try{
	    			Image img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	    			this.setIconImage(img);
	    		}catch(Exception e){
	    			
	    		}
    		   setResizable(false);
    	  	   setLayout(null);
    		   setBounds(100,100,300,200);
    		   setBounds(p.x - 300 / 2, p.y - 200 / 2, 300, 200);
    		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		   Container c=getContentPane();
    		   setTitle("�û���¼");
    		   JLabel JL_user=new JLabel("�˺ţ�");
    		   JL_user.setBounds(50,20,40,20);
    		   JLabel JL_pass=new JLabel("���룺");
    		   JL_pass.setBounds(50,65,40,20);
    		   JTextField JT_user=new JTextField();
    		   JT_user.setBounds(95,20,140,24);
    		   JPasswordField JT_pass=new JPasswordField();
    		   JT_pass.setBounds(95,65,140,24);
    		   JButton JB_login=new JButton("��½");
    		   JB_login.setBounds(50,120,60,25);
    		   JB_login.addActionListener(new ActionListener(){
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO Auto-generated method stub
    				String user=JT_user.getText().trim();
    				String pass=new String(JT_pass.getPassword());
    				try{
    					if(user.length()==0 || pass.length()==0){
    						if(user.length()==0&&pass.length()==0){
    							JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ��");
    							JT_user.requestFocus();
    						}else{
    							if(user.length()==0){
    								JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
    								JT_user.requestFocus();
    							}else{
    								if(pass.length()==0){
    									JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
    									JT_pass.requestFocus();
    								}
    							}
    						}
    					}else{
    						Statement sql = con.createStatement();
    						ResultSet res = sql.executeQuery("select*from UserB where username='"+user+"'");
    						if(res.next()){
    							String xuser=res.getString("username").trim();
    							String xpass=res.getString("pass").trim();
								int  id=res.getInt("id");
    							if(user.equals(xuser)&&pass.equals(xpass)){
    								if(xpass.equals("123456")){
    									JOptionPane.showMessageDialog(changePassW,"\t��ʼ����\n���޸�����");
    									changePassW.setVisible(true);
    								}else{
    									dispose();
        								sql.execute("insert into LoginLog(UserId,Pc_name,Pc_Mac) values("+id+",'"+PcName+"','"+PcMac+"')");
        								new MainFrame(id,xuser);
    								}
    							}else{
    								JOptionPane.showMessageDialog(null, "�û������������");
    								JT_pass.setText("");
    								JT_pass.requestFocus();
    							}		
    						}else{
    							JOptionPane.showMessageDialog(null, "�û������ڣ�");
    							JT_user.setText("");
    							JT_pass.setText("");
    							JT_user.requestFocus();
    						}	
    					};
    				}catch(Exception e1){
    					JOptionPane.showMessageDialog(null, "���ݿ����ӹ��ϣ��������Ա��ϵ");
    				}
    			}
    			   
    		   });
    		   JT_pass.addKeyListener(new KeyAdapter(){
    			   public void keyTyped(KeyEvent e){
    				   if(e.getKeyChar()=='\n')
    					   JB_login.doClick();
    			   }
    		   });
    		   JButton JB_exit=new JButton("��������");
    		   JB_exit.addActionListener(new ActionListener(){
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO Auto-generated method stub
    				String user=JT_user.getText().trim();
    				String pass=new String(JT_pass.getPassword());
    				try{
    					if(user.length()==0 || pass.length()==0){
    						if(user.length()==0&&pass.length()==0){
    							JOptionPane.showMessageDialog(null, "�������˺������������룡");
    							JT_user.requestFocus();
    						}else{
    							if(user.length()==0){
    								JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
    								JT_user.requestFocus();
    							}else{
    								if(pass.length()==0){
    									JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
    									JT_pass.requestFocus();
    								}
    							}
    						}
    					}else{
    						Statement sql = con.createStatement();
    						ResultSet res = sql.executeQuery("select*from UserB where username='"+user+"'");
    						if(res.next()){
    							String xuser=res.getString("username").trim();
    							String xpass=res.getString("pass").trim();
								//int  id=res.getInt("id");
    							if(user.equals(xuser)&&pass.equals(xpass)){
    								changePassW.setVisible(true);
    							}else{
    								JOptionPane.showMessageDialog(null, "�û������������");
    								JT_pass.setText("");
    								JT_pass.requestFocus();
    							}		
    						}else{
    							JOptionPane.showMessageDialog(null, "�û������ڣ�");
    							JT_user.setText("");
    							JT_pass.setText("");
    							JT_user.requestFocus();
    						}	
    					};
    				}catch(Exception e1){
    					JOptionPane.showMessageDialog(null, "���ݿ����ӹ��ϣ��������Ա��ϵ");
    				}
    			}
    		   });
    		   JB_exit.setBounds(180,120,90,25);
    		   changePassW_B.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(changePassW_T1.getText().equals(changePassW_T2.getText())){
						if(changePassW_T2.getText().equals(new String(JT_pass.getPassword()))){
							JOptionPane.showMessageDialog(changePassW,"������������벻����ͬ��");
						}else{
							try{
								Statement sql = con.createStatement();
								sql.execute("update UserB set pass='"+changePassW_T2.getText()+"' where "
										+ "username='"+JT_user.getText().trim()+"'");
								JOptionPane.showMessageDialog(changePassW,"�޸ĳɹ�");
								changePassW_T1.setText("");
								changePassW_T2.setText("");
								JT_pass.setText("");
								changePassW.dispose();
							}catch(Exception e1){
								JOptionPane.showMessageDialog(changePassW,"�޸��������");
							}
						}
					}else{
						JOptionPane.showMessageDialog(changePassW,"��ȷ����������һ��");
					}
				}
    		   });
    		   changePassW.addWindowListener(new WindowAdapter(){
    			   public void windowClosing(WindowEvent arg0){
    				   changePassW.dispose();
    			   }
    		   });
    		   c.add(JB_exit);
    		   c.add(JB_login);
    		   c.add(JT_pass);
    		   c.add(JT_user);
    		   c.add(JL_pass);
    		   c.add(JL_user);
    		   setVisible(true);
    	 }else{
    		JOptionPane.showMessageDialog(null,"\t��ǰ [ "+Version+ " ] �汾���ͣ�\n����ϵ������˾\n��ȡ���°汾������"); 
    		System.exit(0);
    	 }
     }
     public static void main(String[] args){
    	 new Login();
     }

}