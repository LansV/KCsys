package order;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import security.Lock;
public class CustomerInfo {
	Image img=null;
	public static void main(String[] args){
		new CustomerInfo(1);
	}
	public CustomerInfo(int userid){
		System.out.println(userid);
		CustomerInfoData d=new CustomerInfoData();
		//---------------------------------客户资料主面板-----------------------------
		JFrame CI_MainFrame=new JFrame("客户资料");
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    CI_MainFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_MainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		CI_MainFrame.setAlwaysOnTop(true);
		CI_MainFrame.setResizable(false);
		try{
	 	    Image img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    CI_MainFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_MainFrame.setBounds(400,100,450,700);
		CI_MainFrame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if(Lock.SingleUnLock(CI_MainFrame,"order/lock/CustomerInfo.txt")){
					CI_MainFrame.dispose();
				}
			}
		});
		Container CI_MainFrame_Content=CI_MainFrame.getContentPane();
		CI_MainFrame_Content.setBackground(new Color(196,228,210));
		CI_MainFrame_Content.setLayout(null);
		//--------------------------------------主表单面板------------------------------
		JScrollPane CI_MainTableJS=new JScrollPane();
		JTable CI_MainTable=new JTable();
		CI_MainTable.getTableHeader().setReorderingAllowed(false);
		String[] CI_MainTable_Cn={"key","名称","联系人","联系电话"};
		DefaultTableModel CI_MainTable_Model=new DefaultTableModel(d.getCustomerInfo(userid,"","" ),CI_MainTable_Cn){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		CI_MainTable.setModel(CI_MainTable_Model);
		CI_MainTable.setRowHeight(24);
		TableColumn cI_MainTable_Col1=CI_MainTable.getColumnModel().getColumn(1);   //设置列宽    
    	cI_MainTable_Col1.setPreferredWidth(200);   
    	cI_MainTable_Col1.setMinWidth(200);
    	cI_MainTable_Col1.setMaxWidth(200);
    	TableColumn cI_MainTable_Col0=CI_MainTable.getColumnModel().getColumn(0);   //设置列宽    
    	cI_MainTable_Col0.setPreferredWidth(40);   
    	cI_MainTable_Col0.setMinWidth(40);
    	cI_MainTable_Col0.setMaxWidth(40);
    	DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    CI_MainTable.setDefaultRenderer(Object.class, tcr);
		CI_MainTableJS.setViewportView(CI_MainTable);
		CI_MainTableJS.setBounds(5,50,430,610);
		CI_MainFrame_Content.add(CI_MainTableJS);
		//--------------------------------------客户修改面板-----------------------------------------
		JFrame CI_AlterFrame=new JFrame("信息详情");
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    CI_AlterFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_AlterFrame.setAlwaysOnTop(true);
		CI_AlterFrame.setResizable(false);
		try{
	 	    Image img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    CI_AlterFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_AlterFrame.setBounds(850,100,470,180);
		Container CI_AlterFrame_Content=CI_AlterFrame.getContentPane();
		CI_AlterFrame_Content.setBackground(new Color(196,228,210));
		CI_AlterFrame_Content.setLayout(null);
		//--------------------------------------------
		JLabel CI_AlterFrame_NameL=new JLabel("名称:");
		CI_AlterFrame_NameL.setBounds(18,10,80,24);
		JTextField CI_AlterFrame_NameT=new JTextField();
		CI_AlterFrame_NameT.setBounds(50,10,200,24);
		CI_AlterFrame_Content.add(CI_AlterFrame_NameL);
		CI_AlterFrame_Content.add(CI_AlterFrame_NameT);
		//---------------------------------------------
		JLabel CI_AlterFrame_ContactL=new JLabel("联系人:");
		CI_AlterFrame_ContactL.setBounds(5,44,80,24);
		JTextField CI_AlterFrame_ContactT=new JTextField();
		CI_AlterFrame_ContactT.setBounds(50,44,100,24);
		CI_AlterFrame_Content.add(CI_AlterFrame_ContactL);
		CI_AlterFrame_Content.add(CI_AlterFrame_ContactT);
		//----------------------------------------------
		JLabel CI_AlterFrame_TelL=new JLabel("电话:");
		CI_AlterFrame_TelL.setBounds(180,44,80,24);
		JTextField CI_AlterFrame_TelT=new JTextField();
		CI_AlterFrame_TelT.setBounds(212,44,100,24);
		CI_AlterFrame_Content.add(CI_AlterFrame_TelL);
		CI_AlterFrame_Content.add(CI_AlterFrame_TelT);
		//---------------------------------------------
		JLabel CI_AlterFrame_AddrL=new JLabel("地址:");
		CI_AlterFrame_AddrL.setBounds(18,78,80,24);
		JTextField CI_AlterFrame_AddrT=new JTextField();
		CI_AlterFrame_AddrT.setBounds(50,78,400,24);
		CI_AlterFrame_Content.add(CI_AlterFrame_AddrL);
		CI_AlterFrame_Content.add(CI_AlterFrame_AddrT);
		//---------------------------------------------
		JButton CI_AlterFrame_AddB=new JButton("修改");
		CI_AlterFrame_AddB.setBounds(220,112,60,24);
		CI_AlterFrame_Content.add(CI_AlterFrame_AddB);
		CI_AlterFrame_AddB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String name=CI_AlterFrame_NameT.getText().trim();
				String contact=CI_AlterFrame_ContactT.getText().trim();
				String tel=CI_AlterFrame_TelT.getText().trim();
				String addr=CI_AlterFrame_AddrT.getText().trim();
				if(name.length()!=0&&contact.length()!=0&&tel.length()!=0&&addr.length()!=0){
					int row=CI_MainTable.getSelectedRow();
					d.alterCustomer(CI_AlterFrame,name, contact, tel, addr,CI_MainTable.getValueAt(row,0).toString());
					CI_MainTable_Model.setDataVector(d.getCustomerInfo(userid,"","" ),CI_MainTable_Cn);
					CI_MainTable.setRowSelectionInterval(row, row);
					TableColumn cI_MainTable_Col0=CI_MainTable.getColumnModel().getColumn(0);   //设置列宽    
			    	cI_MainTable_Col0.setPreferredWidth(40);   
			    	cI_MainTable_Col0.setMinWidth(40);
			    	cI_MainTable_Col0.setMaxWidth(40);
					TableColumn cI_MainTable_Col1=CI_MainTable.getColumnModel().getColumn(1);   //设置列宽    
			    	cI_MainTable_Col1.setPreferredWidth(200);   
			    	cI_MainTable_Col1.setMinWidth(200);
			    	cI_MainTable_Col1.setMaxWidth(200);
				}else{
					JOptionPane.showMessageDialog(CI_AlterFrame, "\t请补全信息\n留空请用 [ * ] 代替");
				}
			}
		});
		//----------------------------------主表单鼠标监听-------------------------------------------
	    CI_MainTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton()==1){
					if(arg0.getClickCount()==2){
						CI_MainFrame.setEnabled(false);
						int row=CI_MainTable.getSelectedRow();
						CI_AlterFrame_NameT.setText(CI_MainTable.getValueAt(row,1).toString());
						CI_AlterFrame_ContactT.setText(CI_MainTable.getValueAt(row,2).toString());
						CI_AlterFrame_TelT.setText(CI_MainTable.getValueAt(row,3).toString());
						CI_AlterFrame_AddrT.setText(d.getAddr(CI_MainTable.getValueAt(row,0).toString()));
						CI_AlterFrame.setVisible(true);
					}
				}
			}
	    });
		//-------------------------------------------------------------------------------------------
		CI_AlterFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				CI_MainFrame.setEnabled(true);
				CI_AlterFrame.dispose();
			}
		});
		//--------------------------------------客户添加面板-----------------------------------------
		JFrame CI_AddFrame=new JFrame("客户添加");
		try{
	 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    CI_AddFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_AddFrame.setAlwaysOnTop(true);
		CI_AddFrame.setResizable(false);
		try{
	 	    Image img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
	 	    CI_AddFrame.setIconImage(img);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_AddFrame.setBounds(850,100,470,180);
		Container CI_AddFrame_Content=CI_AddFrame.getContentPane();
		CI_AddFrame_Content.setBackground(new Color(196,228,210));
		CI_AddFrame_Content.setLayout(null);
		//--------------------------------------------
		JLabel CI_AddFrame_NameL=new JLabel("名称:");
		CI_AddFrame_NameL.setBounds(18,10,80,24);
		JTextField CI_AddFrame_NameT=new JTextField();
		CI_AddFrame_NameT.setBounds(50,10,200,24);
		CI_AddFrame_Content.add(CI_AddFrame_NameL);
		CI_AddFrame_Content.add(CI_AddFrame_NameT);
		//---------------------------------------------
		JLabel CI_AddFrame_ContactL=new JLabel("联系人:");
		CI_AddFrame_ContactL.setBounds(5,44,80,24);
		JTextField CI_AddFrame_ContactT=new JTextField();
		CI_AddFrame_ContactT.setBounds(50,44,100,24);
		CI_AddFrame_Content.add(CI_AddFrame_ContactL);
		CI_AddFrame_Content.add(CI_AddFrame_ContactT);
		//----------------------------------------------
		JLabel CI_AddFrame_TelL=new JLabel("电话:");
		CI_AddFrame_TelL.setBounds(180,44,80,24);
		JTextField CI_AddFrame_TelT=new JTextField();
		CI_AddFrame_TelT.setBounds(212,44,100,24);
		CI_AddFrame_Content.add(CI_AddFrame_TelL);
		CI_AddFrame_Content.add(CI_AddFrame_TelT);
		//---------------------------------------------
		JLabel CI_AddFrame_AddrL=new JLabel("地址:");
		CI_AddFrame_AddrL.setBounds(18,78,80,24);
		JTextField CI_AddFrame_AddrT=new JTextField();
		CI_AddFrame_AddrT.setBounds(50,78,400,24);
		CI_AddFrame_Content.add(CI_AddFrame_AddrL);
		CI_AddFrame_Content.add(CI_AddFrame_AddrT);
		//---------------------------------------------
		JButton CI_AddFrame_AddB=new JButton("添加");
		CI_AddFrame_AddB.setBounds(220,112,60,24);
		CI_AddFrame_Content.add(CI_AddFrame_AddB);
		CI_AddFrame_AddB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String name=CI_AddFrame_NameT.getText().trim();
				String contact=CI_AddFrame_ContactT.getText().trim();
				String tel=CI_AddFrame_TelT.getText().trim();
				String addr=CI_AddFrame_AddrT.getText().trim();
				if(name.length()!=0&&contact.length()!=0&&tel.length()!=0&&addr.length()!=0){
					d.addCustomer(CI_AddFrame,name, contact, tel, addr,userid);
					CI_AddFrame_NameT.setText("");
					CI_AddFrame_ContactT.setText("");
					CI_AddFrame_TelT.setText("");
					CI_AddFrame_AddrT.setText("");
					CI_MainTable_Model.setDataVector(d.getCustomerInfo(userid,"","" ),CI_MainTable_Cn);
					TableColumn cI_MainTable_Col0=CI_MainTable.getColumnModel().getColumn(0);   //设置列宽    
			    	cI_MainTable_Col0.setPreferredWidth(40);   
			    	cI_MainTable_Col0.setMinWidth(40);
			    	cI_MainTable_Col0.setMaxWidth(40);
					TableColumn cI_MainTable_Col1=CI_MainTable.getColumnModel().getColumn(1);   //设置列宽    
			    	cI_MainTable_Col1.setPreferredWidth(200);   
			    	cI_MainTable_Col1.setMinWidth(200);
			    	cI_MainTable_Col1.setMaxWidth(200);
				}else{
					JOptionPane.showMessageDialog(CI_AddFrame, "\t请补全信息\n留空请用 [ * ] 代替");
				}
			}
		});
		//-------------------------------------------------------------------------------------------
		CI_AddFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				CI_MainFrame.setEnabled(true);
				CI_AddFrame.dispose();
			}
		});
		//-------------------------------------主面板查询 添加客户------------------------------------
		JTextField CI_MainTable_QueryTF=new JTextField();
		CI_MainTable_QueryTF.setBounds(5,15,180,24);
		CI_MainFrame_Content.add(CI_MainTable_QueryTF);
		JButton CI_MainTable_QueryB=new JButton();
		try{
			Icon QueryIcon=new ImageIcon("order/Image/QueryCustomer.png");
	 	    CI_MainTable_QueryB.setIcon(QueryIcon);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_MainTable_QueryB.setContentAreaFilled(false);
		CI_MainTable_QueryB.setBounds(200,15,24,24);
		CI_MainTable_QueryB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CI_MainTable_Model.setDataVector(d.getCustomerInfo(userid, CI_MainTable_QueryTF.getText().trim(),CI_MainTable_QueryTF.getText().trim()),CI_MainTable_Cn);
				TableColumn cI_MainTable_Col0=CI_MainTable.getColumnModel().getColumn(0);   //设置列宽    
		    	cI_MainTable_Col0.setPreferredWidth(40);   
		    	cI_MainTable_Col0.setMinWidth(40);
		    	cI_MainTable_Col0.setMaxWidth(40);
				TableColumn cI_MainTable_Col1=CI_MainTable.getColumnModel().getColumn(1);   //设置列宽    
		    	cI_MainTable_Col1.setPreferredWidth(200);   
		    	cI_MainTable_Col1.setMinWidth(200);
		    	cI_MainTable_Col1.setMaxWidth(200);
			}
		});
		CI_MainTable_QueryTF.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode()=='\n'){
					CI_MainTable_QueryB.doClick();
				}
			}
		});
		CI_MainFrame_Content.add(CI_MainTable_QueryB);
		JButton CI_MainTable_addCustomer=new JButton();
		try{
			Icon Icon=new ImageIcon("order/Image/AddCustomer.png");
	 	    CI_MainTable_addCustomer.setIcon(Icon);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"获取系统图标错误");
		}
		CI_MainTable_addCustomer.setContentAreaFilled(false);
		CI_MainTable_addCustomer.setBounds(408,15,24,24);
		CI_MainTable_addCustomer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CI_MainFrame.setEnabled(false);
				CI_AddFrame.setVisible(true);
			}
		});
		CI_MainFrame_Content.add(CI_MainTable_addCustomer);
		//------------------------------------------------------------------------------------------
		CI_MainFrame.setVisible(true);
	}
}
