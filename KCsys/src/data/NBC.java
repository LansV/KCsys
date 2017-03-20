package data;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import security.Lock;
import test.PrintNBC;
public class NBC {
	public NBC(String user){
		wData w=new wData();
		getData gd=new getData();        //调用数据类
		List<String> spcount=new ArrayList<String>();
		List<Integer> kccount=new ArrayList<Integer>();
		//-------------------------------------------------主面板-----------------------------------------------
		JFrame mf=new JFrame("内部出库");
		mf.setResizable(false);
		Container mc=mf.getContentPane();
		mf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mf.setBounds(20,50,750,615);
		JPanel mp=new JPanel();
		mp.setLayout(null);
		mp.setBounds(0, 0, 750, 630);
		JScrollPane jsp=new JScrollPane();
		JTable mt=new JTable(){
			private static final long serialVersionUID = 1L;
			public void setValueAt(Object aValue, int rowIndex, int columnIndex){
				if(columnIndex==4){
	                try {
	                	String st=(String) aValue;
						int num =Integer.parseInt(st);
						if(num>kccount.get(rowIndex)){
							JOptionPane.showMessageDialog(null,"超出库存");
							return;
						}
						if(num==0){
							JOptionPane.showMessageDialog(null,"不能为0");
							return;
						}
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "只能输入数字!");
	                    return;
	                }
				}  
			      super.setValueAt(aValue,rowIndex,columnIndex);
			}
		};
		String[] mcn={"序号","商品型号","商品名称","单位","数量","备注"};
		DefaultTableModel mdm=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				if(colunm==4||colunm==6){
					return true;
				}
				return false;
			}
		};
		mt.setRowHeight(25);
		mt.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    mt.setDefaultRenderer(Object.class, tcr);
		mdm.setColumnIdentifiers(mcn);
		mt.setModel(mdm);
		jsp.setViewportView(mt);
		jsp.setBounds(10,10,720,530);
		mp.add(jsp);
		Object[] row=new Object[mcn.length];
		row[0]="";
		row[1]="";
		row[2]="";
		row[3]="";
		row[4]="";
		row[5]="";
		//--------------------------------商品数量------------------------------------------------------
		JFrame spsl=new JFrame("填写数量");
		spsl.setResizable(false);
		Container spslc=spsl.getContentPane();
		spslc.setLayout(null);
		JLabel splb=new JLabel();
		splb.setBounds(10,5,120,25);
		JTextField sptx=new JTextField();
		sptx.setBounds(10,35,120,25);
		JTextField spzk=new JTextField();
		spzk.setBounds(10,65,120,25);
		spslc.add(spzk);
		spslc.add(sptx);
		spslc.add(splb);
		//------------------------------------商品选择面板---------------------------------------------------
		JFrame sp=new JFrame("商品");
		sp.setResizable(false);
		Container spc=sp.getContentPane();
		spc.setLayout(null);
		JTextField spjt=new JTextField();
		spjt.setBounds(20,10,120,25);
		String[] spcn={"商品型号","商品名称","单位","单价","数量"};
		JTable sptable=new JTable();
		sptable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int spr=sptable.getSelectedRow();
				if(e.getClickCount()==2&&e.getButton()==1){
					Boolean sp=true;
					for(int i=0;i<spcount.size();i++){
						if(spcount.get(i).equals(sptable.getValueAt(spr,1).toString().trim())){
							sp=false;
						}
					}
					if(sp==false){
						JOptionPane.showMessageDialog(null,"已添加商品");
					}else{
						String s=sptable.getValueAt(spr,4).toString();
						int i=Integer.parseInt(s);
						if(i==0){
							JOptionPane.showMessageDialog(null,"库存为零");
						}else{
							sptx.setText("");
							spsl.setVisible(true);
							splb.setText(sptable.getValueAt(spr,1).toString().trim());
						}
					}
				}
			}
		});
		sptable.getTableHeader().setReorderingAllowed(false);
		String[][] sparr=gd.spcxdj(spjt.getText().trim());
		DefaultTableModel spdm=new DefaultTableModel(sparr,spcn){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		spdm.setColumnIdentifiers(spcn);
		sptable.setModel(spdm);
		sptable.setDefaultRenderer(Object.class, tcr);
		sptable.setRowHeight(25);
    	TableColumn sptablecl1=sptable.getColumnModel().getColumn(0);   //设置列宽    
    	sptablecl1.setPreferredWidth(80);   
    	sptablecl1.setMinWidth(80);
    	sptablecl1.setMaxWidth(80);
    	TableColumn sptablecl=sptable.getColumnModel().getColumn(1);   //设置列宽    
    	sptablecl.setPreferredWidth(180);   
    	sptablecl.setMinWidth(180);
    	sptablecl.setMaxWidth(180);
		JPanel spj=new JPanel();
		spj.setLayout(null);
		spj.setBounds(10,50,500,620);
		JScrollPane spjs=new JScrollPane();
		spjs.setViewportView(sptable);
		spjs.setBounds(0,0,465,555);
		spjt.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					String[][] sparr=gd.spcxdj(spjt.getText().trim());
					DefaultTableModel spdm=new DefaultTableModel(sparr,spcn){
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						public boolean isCellEditable(int row,int colunm){
							return false;
						}
					};
					spdm.setColumnIdentifiers(spcn);
					sptable.setModel(spdm);
					TableColumn sptablecl1=sptable.getColumnModel().getColumn(0);   //设置列宽    
			    	sptablecl1.setPreferredWidth(80);   
			    	sptablecl1.setMinWidth(80);
			    	sptablecl1.setMaxWidth(80);
			    	TableColumn sptablecl=sptable.getColumnModel().getColumn(1);   //设置列宽    
			    	sptablecl.setPreferredWidth(180);   
			    	sptablecl.setMinWidth(180);
			    	sptablecl.setMaxWidth(180);
				}
			}
		});
		spj.add(spjs);
		spc.add(spj);
		spc.add(spjt);
		sp.setBounds(760,10,500,650);
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//------------------------------------主表模型监听-------------------------------------------------
/*		mdm.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int r=e.getFirstRow();
				int c=e.getColumn();
				if(c==6||c==4){
					String c4=mt.getValueAt(r,4).toString().trim();
					String c6=mt.getValueAt(r,6).toString().trim();
					if(c6.length()==0){
						
					}else{
						String s=mt.getValueAt(r,6).toString().trim();
						int sl=Integer.parseInt(s);
							if(c4.length()==0){
								String d=mt.getValueAt(r,5).toString().trim();
								System.out.println(d+" "+s);
								Double dj = null;
								dj=Double.parseDouble(d);
								String zj=String.format("%.2f",dj*sl);
								mt.setValueAt(zj,r,7);	
							}else{
								String z=mt.getValueAt(r,4).toString().trim();
								String d=mt.getValueAt(r,5).toString().trim();
								Double dj = null;
								try{
									dj=Double.parseDouble(d);
								}catch(Exception e1){
									JOptionPane.showMessageDialog(null,"输入非法");
								}
								Double zk=Double.parseDouble(z)/10;
								System.out.println(d+" "+s+" "+zk);
								String zj=String.format("%.2f",dj*sl*zk);
								mt.setValueAt(zj,r,7);
						}
					}
				}
			}
		});*/
		//------------------------------------商品填写数量-------------------------------------------------
		sptx.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int mr=mt.getRowCount();
				int spr=sptable.getSelectedRow();
				if(e.getKeyCode()=='\n'){
					if(sptx.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,"未填写数量");
					}else{
						Boolean b=true;
						try{
							String zk=spzk.getText().trim();
							if(zk.length()==0){
								
							}else{
								@SuppressWarnings("unused")
								Double s=Double.parseDouble(zk);
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"折扣输入非法");
							b=false;
						}
						try{
							String s=sptx.getText().trim();
							int pdnum=Integer.parseInt(s);
							String skc=sptable.getValueAt(spr,4).toString().trim();
							int kcsl=Integer.parseInt(skc);
								if(pdnum>kcsl){
									JOptionPane.showMessageDialog(null, "超出库存");
								}else{
									if(pdnum==0){
										JOptionPane.showMessageDialog(null, "不能为0");
									}else{
										if(b==true){
											mdm.addRow(row);
											mt.setValueAt(mr+1,mr,0);
											mt.setValueAt(sptable.getValueAt(spr,0),mr,1);
											mt.setValueAt(sptable.getValueAt(spr,1),mr,2);
											mt.setValueAt(sptable.getValueAt(spr,2),mr,3);
											kccount.add(kcsl);
											mt.setValueAt(sptx.getText().trim(),mr,4);
											spsl.dispose();
											spcount.add(sptable.getValueAt(spr,1).toString().trim());
										}
									}
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"请填写数字");
							sptx.setText("");
						}
					}
				}
			}
		});
		JLabel dhl=new JLabel(gd.nbcdh());
		dhl.setBounds(30,550,90,25);
		spsl.setBounds(700,150,250,150);
		spsl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JButton bt=new JButton("添加");
		bt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sp.setVisible(true);
			}
		});
		bt.setBounds(620,550,60,25);
		JButton bc=new JButton("出单");
		bc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mt.getRowCount()==0){
					JOptionPane.showMessageDialog(null,"至少添加一件产品");
				}else{
					int cr=mt.getRowCount();
					String[][] parr=new String[cr][6];
					for(int i=0;i<cr;i++){
						for(int j=0;j<6;j++){
							String s=mt.getValueAt(i,j).toString();
							if(s.length()==0){
								
							}else{
								s.trim();
							}
							parr[i][j]=s;
						}
						w.wnbc(dhl.getText(),Integer.parseInt(parr[i][0]),parr[i][1],
						parr[i][2],parr[i][3],Integer.parseInt(parr[i][4]),parr[i][5]);
						//w.wkcout(parr[i][1],parr[i][2],Integer.parseInt(parr[i][4]),"内部出库"+dhl.getText());
					}
					PrintNBC.setdata(dhl.getText(),user,parr);
					new PrintNBC();
					mdm.setRowCount(0);
					dhl.setText(gd.nbcdh());
					spcount.clear();
					kccount.clear();
					String[][] sparr=gd.spcxdj(spjt.getText().trim());
					DefaultTableModel spdm=new DefaultTableModel(sparr,spcn){
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						public boolean isCellEditable(int row,int colunm){
							return false;
						}
					};
					spdm.setColumnIdentifiers(spcn);
					sptable.setModel(spdm);
					TableColumn sptablecl1=sptable.getColumnModel().getColumn(0);   //设置列宽    
			    	sptablecl1.setPreferredWidth(80);   
			    	sptablecl1.setMinWidth(80);
			    	sptablecl1.setMaxWidth(80);
			    	TableColumn sptablecl=sptable.getColumnModel().getColumn(1);   //设置列宽    
			    	sptablecl.setPreferredWidth(180);   
			    	sptablecl.setMinWidth(180);
			    	sptablecl.setMaxWidth(180);
				}
			}
		});
		bc.setBounds(320,550,60,25);
		mp.add(dhl);
		mp.add(bc);
		mp.add(bt);
		mc.add(mp);
		mf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(Lock.SingleUnLock(mf, "lock/NBC.txt")){
					mf.dispose();
				}
				sp.dispose();
			}
		});
		mf.setVisible(true);
	}
}
