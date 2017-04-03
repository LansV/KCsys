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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import security.CheckDate;
import security.Lock;
import tool.PrintAssembleSheet;

public class Assemble {
	List<String> spcount=new ArrayList<String>();
	AssembleData d=new AssembleData();
	public Assemble(String user){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// 输出北京时间
	   	Date date2=new Date();
	   	String s1=sdf.format(date2);
	   	CheckDate.ReturnCheckDateResult(s1);
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    String[] assembleFrame_ColumnN={"序号","商品型号","商品名称","单位","单价","数量","金额"};
	    //----------------------------------------------显示组装表-----------------------------------------
	    JFrame showAssemble=new JFrame("组装列表");
	    //showAssemble.setAlwaysOnTop(true);
		Container showAssemble_Content=showAssemble.getContentPane();
		showAssemble.setBounds(430,100,500,650);
		showAssemble.setResizable(false);
		showAssemble_Content.setLayout(null);
		JScrollPane showAssemble_JS=new JScrollPane();
		JTable showAssemble_Table=new JTable();
		DefaultTableModel showAssemble_TableModel=new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		showAssemble_TableModel.setColumnIdentifiers(assembleFrame_ColumnN);
		showAssemble_Table.setModel(showAssemble_TableModel);
		showAssemble_Table.setRowHeight(22);
		showAssemble_Table.setDefaultRenderer(Object.class, tcr);
    	TableColumn smtablecl3=showAssemble_Table.getColumnModel().getColumn(1);   //设置列宽    
    	smtablecl3.setPreferredWidth(80);   
    	smtablecl3.setMinWidth(80);
    	smtablecl3.setMaxWidth(80);
    	TableColumn smtablecl=showAssemble_Table.getColumnModel().getColumn(2);   //设置列宽    
    	smtablecl.setPreferredWidth(130);   
    	smtablecl.setMinWidth(130);
    	smtablecl.setMaxWidth(130);
    	TableColumn smtablecl2=showAssemble_Table.getColumnModel().getColumn(6);   //设置列宽    
    	smtablecl2.setPreferredWidth(60);   
    	smtablecl2.setMinWidth(60);
    	smtablecl2.setMaxWidth(60);
		showAssemble_JS.setBounds(10,10,470,580);
		showAssemble_JS.setViewportView(showAssemble_Table);
		showAssemble_Content.add(showAssemble_JS);
		JButton showAssemble_Print=new JButton("打印");
		showAssemble_Print.setBounds(230,595,60,24);
		showAssemble_Content.add(showAssemble_Print);
		JButton showAssemble_Assemble=new JButton("组装");
		showAssemble_Assemble.setBounds(90,595,60,24);
		showAssemble_Content.add(showAssemble_Assemble);
		//------------------------------------------------------------------------------
		JFrame AssembleQuantity=new JFrame("输入数量");
		AssembleQuantity.setResizable(false);
		AssembleQuantity.setBounds(600,300,230,100);
		Container AssembleQuantity_Content=AssembleQuantity.getContentPane();
		AssembleQuantity_Content.setLayout(null);
		JTextField AssembleQuantity_T=new JTextField();
		AssembleQuantity_T.setBounds(20,20,180,24);
		AssembleQuantity_Content.add(AssembleQuantity_T);
		//----------------------------右键菜单------------------------------------
		JPopupMenu AM_rightMenu=new JPopupMenu();
		JMenuItem AM_deleteItem=new JMenuItem("删除");
		AM_rightMenu.add(AM_deleteItem);
		//------------------------------------------------主面板------------------------------------------
		JFrame Assemble_MFrame=new JFrame("组装");
		Assemble_MFrame.setResizable(false);
		//Assemble_MFrame.setAlwaysOnTop(true);
		Assemble_MFrame.setBounds(30,100,400,705);
		Assemble_MFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Assemble_MFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(Lock.SingleUnLock(Assemble_MFrame, "lock/Assemble.txt")){
					Assemble_MFrame.dispose();
				}
			}
		});
		Container Assemble_MFrame_Content=Assemble_MFrame.getContentPane();
		Assemble_MFrame_Content.setLayout(null);
		JTextField Assemble_MFrame_JT=new JTextField();
		Assemble_MFrame_JT.setBounds(10, 10, 150, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_JT);
		JButton Assemble_MFrame_QueryB=new JButton("查询");
		Assemble_MFrame_QueryB.setBounds(220, 10, 60, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_QueryB);
		JButton Assemble_MFrame_AddB=new JButton("添加");
		Assemble_MFrame_AddB.setBounds(320, 645, 60, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_AddB);
		//------------------------------------------MFrame_Table--------------------------------------
		JTable Assemble_MFrame_Table=new JTable();
		Assemble_MFrame_Table.getTableHeader().setReorderingAllowed(false);
		String[] Assemble_MFrame_TableColumn={"识别号","品名"};
		DefaultTableModel Assemble_MFrame_TableModel=new DefaultTableModel(d.zm(Assemble_MFrame_JT.getText().trim()),Assemble_MFrame_TableColumn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		Assemble_MFrame_Table.setRowHeight(20);
		Assemble_MFrame_Table.setDefaultRenderer(Object.class, tcr);
		Assemble_MFrame_Table.setModel(Assemble_MFrame_TableModel);
		JScrollPane Assemble_MFrame_JS=new JScrollPane();
		Assemble_MFrame_JS.setViewportView(Assemble_MFrame_Table);
		Assemble_MFrame_JS.setBounds(10,40,375,600);
		Assemble_MFrame_Content.add(Assemble_MFrame_JS);
		Assemble_MFrame_Table.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==1){
					if(e.getClickCount()==2){
						int row=Assemble_MFrame_Table.getSelectedRow();
						String sbh=Assemble_MFrame_Table.getValueAt(row, 0).toString();
						showAssemble_TableModel.setDataVector(d.zdetail(sbh),assembleFrame_ColumnN);
						TableColumn smtablecl3=showAssemble_Table.getColumnModel().getColumn(1);   //设置列宽    
				    	smtablecl3.setPreferredWidth(80);   
				    	smtablecl3.setMinWidth(80);
				    	smtablecl3.setMaxWidth(80);
				    	TableColumn smtablecl=showAssemble_Table.getColumnModel().getColumn(2);   //设置列宽    
				    	smtablecl.setPreferredWidth(130);   
				    	smtablecl.setMinWidth(130);
				    	smtablecl.setMaxWidth(130);
				    	TableColumn smtablecl2=showAssemble_Table.getColumnModel().getColumn(6);   //设置列宽    
				    	smtablecl2.setPreferredWidth(60);   
				    	smtablecl2.setMinWidth(60);
				    	smtablecl2.setMaxWidth(60);
						Assemble_MFrame.setEnabled(false);
						showAssemble.setVisible(true);
					}
				}
			}
		});
		//================================================组装=================================================
		AssembleQuantity_T.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					int row=Assemble_MFrame_Table.getSelectedRow();
					String sbh=Assemble_MFrame_Table.getValueAt(row, 0).toString();
					String name=Assemble_MFrame_Table.getValueAt(row, 1).toString();
					String s=AssembleQuantity_T.getText();
					try{
						int sl=Integer.parseInt(s);
						String[][] arr=d.pdKC(sbh,sl);
						if(arr.length==0){
							int rowc=showAssemble_Table.getRowCount();
							for(int i=0;i<rowc;i++){
								String psbh=showAssemble_Table.getValueAt(i,1).toString();
								String pname=showAssemble_Table.getValueAt(i,2).toString();
								int psl=Integer.parseInt(showAssemble_Table.getValueAt(i,5).toString());
								d.wkcout(psbh, pname,psl*sl, "3,组装"+name, user);
							}
							d.wkcin(sbh,name,sl,"3", user,"组装"+name);
							AssembleQuantity_T.setText("");
							JOptionPane.showMessageDialog(AssembleQuantity,"组装完成");
							showAssemble.setEnabled(true);
							AssembleQuantity.dispose();
						}else{
							String st="";
							for(int i=0;i<arr.length;i++){
								st='\t'+st+"[ "+arr[i][0]+" ] "+"库存不足,还需"+arr[i][1]+'\n';
							}
							JOptionPane.showMessageDialog(AssembleQuantity, st);
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(AssembleQuantity, "请输入数字！");
					}
				}
			}
		});
		//-------------------------------------------------------------------------
		showAssemble_Assemble.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showAssemble.setEnabled(false);
				AssembleQuantity.setVisible(true);
			}
		});
		//---------------------------------------------------------------------
		AM_deleteItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int row=Assemble_MFrame_Table.getSelectedRow();
				String sbh=Assemble_MFrame_Table.getValueAt(row, 0).toString();
				d.wDeleteAssemble(sbh, user);
				Assemble_MFrame_TableModel.setDataVector(d.zm(Assemble_MFrame_JT.getText().trim()),Assemble_MFrame_TableColumn);
			}
		});
		//=====================================================================
		showAssemble_Print.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int row=Assemble_MFrame_Table.getSelectedRow();
				String sbh=Assemble_MFrame_Table.getValueAt(row, 0).toString();
				String spname=Assemble_MFrame_Table.getValueAt(row, 1).toString();
				PrintAssembleSheet.setdata(spname,user,d.zPrint(sbh));
				new PrintAssembleSheet();
			}
		});
		//-----------------------------------------------------------------
		Assemble_MFrame_Table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==3){
					int r=Assemble_MFrame_Table.rowAtPoint(e.getPoint());
					if(Assemble_MFrame_Table.getRowSelectionAllowed()==true){
						Assemble_MFrame_Table.setRowSelectionInterval(r,r);
						AM_rightMenu.show(Assemble_MFrame_Table, e.getX(), e.getY());
					}
				}
			}
		});
		//-------------------------------------------------------------
		Assemble_MFrame_JT.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					Assemble_MFrame_QueryB.doClick();
				}
			}
		});
		Assemble_MFrame_QueryB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Assemble_MFrame_TableModel.setDataVector(d.zm(Assemble_MFrame_JT.getText().trim()),Assemble_MFrame_TableColumn);
			}
		});
		//--------------------------------------------------------------------------------------
		Assemble_MFrame.setVisible(true);
	    //-------------------------------------------组装命名-------------------------------------------------------
	    JFrame assembleNameFrame=new JFrame("命名");
	    //assembleNameFrame.setAlwaysOnTop(true);
		assembleNameFrame.setBounds(300,150,220,150);
		assembleNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container assembleNameFrame_Content=assembleNameFrame.getContentPane();
		assembleNameFrame_Content.setLayout(null);
		JLabel assembleNameFrame_NameL=new JLabel("名称:");
		assembleNameFrame_NameL.setBounds(10,10,60,25);
		JTextField assembleNameFrame_NameT=new JTextField();
		assembleNameFrame_NameT.setBounds(60,10,150,25);
//		JLabel xhl=new JLabel("型号:");
//		xhl.setBounds(10,45,60,25);
//		JTextField xht=new JTextField();
//		xht.setBounds(60,45,150,25);
		JLabel assembleNameFrame_PriceL=new JLabel("价格:");
		assembleNameFrame_PriceL.setBounds(10,80,60,25);
		JTextField assembleNameFrame_PriceT=new JTextField();
		assembleNameFrame_PriceT.setBounds(60,80,150,25);
		assembleNameFrame_Content.add(assembleNameFrame_NameT);
		assembleNameFrame_Content.add(assembleNameFrame_NameL);
//		assembleNameFrame_Content.add(xht);
//		assembleNameFrame_Content.add(xhl);
		assembleNameFrame_Content.add(assembleNameFrame_PriceT);
		assembleNameFrame_Content.add(assembleNameFrame_PriceL);
		assembleNameFrame.setResizable(false);
		//----------------------------右键菜单------------------------------------
		JPopupMenu AF_rightMenu=new JPopupMenu();
		JMenuItem AF_deleteItem=new JMenuItem("删除");
		AF_rightMenu.add(AF_deleteItem);
		//-----------------------------组装面板-----------------------------------------
		JFrame AssembleFrame=new JFrame("组装表");
		//AssembleFrame.setAlwaysOnTop(true);
		Container AssembleFrame_Content=AssembleFrame.getContentPane();
		AssembleFrame.setBounds(430,100,500,650);
		AssembleFrame.setResizable(false);
		AssembleFrame_Content.setLayout(null);
		JPanel AssembleFrame_Pane=new JPanel();
		AssembleFrame_Pane.setLayout(null);
		JLabel assembleFrame_TotalL=new JLabel("");
		assembleFrame_TotalL.setBounds(320,585,90,25);
		AssembleFrame_Pane.add(assembleFrame_TotalL);
		JScrollPane AssembleFrame_JSP=new JScrollPane();
		JTable AssembleFrame_Table=new JTable(){
			private static final long serialVersionUID = 1L;
			public void setValueAt(Object aValue, int rowIndex, int columnIndex){
				if(columnIndex==5){
	                try{
	                	String st=(String) aValue;
						int num =Integer.parseInt(st);
						//System.out.println(kccount.get(rowIndex));
						if(num<=0){
							JOptionPane.showMessageDialog(AssembleFrame,"不能小等于0");
							return;
						}
	                }catch(Exception ex){
	                    JOptionPane.showMessageDialog(AssembleFrame, "只能输入数字!");
	                    return;
	                }
				}  
			      super.setValueAt(aValue,rowIndex,columnIndex);
			}
		};
		AssembleFrame_Table.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel AssembleFrame_TableModel=new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				if(colunm==5){
					return true;
				}
				return false;
			}
		};
		AssembleFrame_Table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==3){
					int r=AssembleFrame_Table.rowAtPoint(e.getPoint());
					if(AssembleFrame_Table.getRowSelectionAllowed()==true){
						AssembleFrame_Table.setRowSelectionInterval(r,r);
						AF_rightMenu.show(AssembleFrame_Table, e.getX(), e.getY());
					}
				}
			}
		});
		AF_deleteItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectRow=AssembleFrame_Table.getSelectedRow();
				AssembleFrame_TableModel.removeRow(selectRow);
				spcount.remove(selectRow);
				int rowcount=AssembleFrame_Table.getRowCount();
			    for(int i=0;i<rowcount;i++){
			    	AssembleFrame_Table.setValueAt(i+1,i,0);
				}
			    Double zj=0.0;
				for(int i=0;i<rowcount;i++){
					zj=Double.parseDouble(AssembleFrame_Table.getValueAt(i,6).toString())+zj;
				}
				assembleFrame_TotalL.setText(String.format("%.2f",zj));
			}
		});
		//------------------------------------主表模型监听-------------------------------------------------
		AssembleFrame_TableModel.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int r=e.getFirstRow();
				int c=e.getColumn();
				if(c==5){
					try{
				        String s=AssembleFrame_Table.getValueAt(r,5).toString().trim();
				        int sl=Integer.parseInt(s);
						String d=AssembleFrame_Table.getValueAt(r,4).toString().trim();
						Double dj=Double.parseDouble(d);
						String zj=String.format("%.2f",dj*sl);
						AssembleFrame_Table.setValueAt(zj,r,6);
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"输入非法");
					}
				}
				if(c==6){
					int k=AssembleFrame_Table.getRowCount();
					Double zj=0.0;
					for(int i=0;i<k;i++){
						zj=Double.parseDouble(AssembleFrame_Table.getValueAt(i,c).toString())+zj;
					}
					assembleFrame_TotalL.setText(String.format("%.2f",zj));
				}
			}
		});
		AssembleFrame_TableModel.setColumnIdentifiers(assembleFrame_ColumnN);
		AssembleFrame_Table.setRowHeight(20);
		AssembleFrame_Table.setModel(AssembleFrame_TableModel);
		AssembleFrame_Table.setDefaultRenderer(Object.class, tcr);
    	TableColumn mtablecl3=AssembleFrame_Table.getColumnModel().getColumn(1);   //设置列宽    
    	mtablecl3.setPreferredWidth(80);   
    	mtablecl3.setMinWidth(80);
    	mtablecl3.setMaxWidth(80);
    	TableColumn mtablecl=AssembleFrame_Table.getColumnModel().getColumn(2);   //设置列宽    
    	mtablecl.setPreferredWidth(130);   
    	mtablecl.setMinWidth(130);
    	mtablecl.setMaxWidth(130);
    	TableColumn mtablecl2=AssembleFrame_Table.getColumnModel().getColumn(6);   //设置列宽    
    	mtablecl2.setPreferredWidth(60);   
    	mtablecl2.setMinWidth(60);
    	mtablecl2.setMaxWidth(60);
    	AssembleFrame_JSP.setViewportView(AssembleFrame_Table);
    	AssembleFrame_JSP.setBounds(7,0,470,580);
		AssembleFrame_Pane.add(AssembleFrame_JSP);
		JButton assembleFrame_AssembleB=new JButton("添加");
		assembleFrame_AssembleB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(AssembleFrame_Table.getRowCount()<2){
					JOptionPane.showMessageDialog(AssembleFrame,"至少添加两种商品进行组装");
				}else{
					assembleNameFrame.setVisible(true);
					AssembleFrame.setEnabled(false);
					//zm.setVisible(true);
				}
			}
		});
		assembleFrame_AssembleB.setBounds(200,585,60,25);
		JButton assembleFrame_AddB=new JButton("选择商品");
		assembleFrame_AddB.setBounds(390,585,90,25);
		AssembleFrame_Pane.add(assembleFrame_AddB);
		AssembleFrame_Pane.add(assembleFrame_AssembleB);
		AssembleFrame_Pane.setBounds(0,0,500,630);
		AssembleFrame_Content.add(AssembleFrame_Pane);
		AssembleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Object[] row=new Object[assembleFrame_ColumnN.length];
		row[0]="";
		row[1]="";
		row[2]="";
		row[3]="";
		row[4]="";
		row[5]="";
		row[6]="";
		AssembleFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				AssembleFrame_TableModel.setRowCount(0);
				spcount.clear();
				Assemble_MFrame.setEnabled(true);
				AssembleFrame.dispose();
			}
		});
		//======================================组装命名价格监听=============================================
		assembleNameFrame_PriceT.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					String name=assembleNameFrame_NameT.getText().trim();
					String sprice=assembleNameFrame_PriceT.getText().trim();
					if(name.length()!=0&&sprice.length()!=0){
						if(d.zzjl(name)==false){
							String sbh=d.getspid(1);
							System.out.println(sbh);
							try{
								Double price=Double.parseDouble(sprice);
								int rowc=AssembleFrame_Table.getRowCount();
								for(int i=0;i<rowc;i++){
									int bh=Integer.parseInt(AssembleFrame_Table.getValueAt(i,0).toString());
									String psbh=AssembleFrame_Table.getValueAt(i,1).toString();
									String sp=AssembleFrame_Table.getValueAt(i,2).toString();
									String dw=AssembleFrame_Table.getValueAt(i,3).toString();
									Double dj=Double.parseDouble(AssembleFrame_Table.getValueAt(i,4).toString());
									int sl=Integer.parseInt(AssembleFrame_Table.getValueAt(i,5).toString());
									Double je=Double.parseDouble(AssembleFrame_Table.getValueAt(i,6).toString());
									boolean b=d.wzz(sbh,name, bh, psbh, sp, dw, dj, sl, je, user);
									if(b==false){
										JOptionPane.showMessageDialog(assembleNameFrame,"循环失败");
										break;
									}
									if(i==rowc-1){
										Double jhj=Double.parseDouble(assembleFrame_TotalL.getText().trim());
										d.wkc("",name, jhj,price,sbh, user);
									}
								}
								AssembleFrame.setEnabled(true);
								assembleFrame_TotalL.setText("");
								assembleNameFrame_PriceT.setText("");
								assembleNameFrame_NameT.setText("");
								assembleNameFrame.dispose();
								AssembleFrame_TableModel.setRowCount(0);
								Assemble_MFrame_TableModel.setDataVector(d.zm(Assemble_MFrame_JT.getText().trim()),Assemble_MFrame_TableColumn);
							}catch(Exception e1){
								JOptionPane.showMessageDialog(assembleNameFrame,"请输入数字！");
							}
						}else{
							JOptionPane.showMessageDialog(assembleNameFrame,"产品已存在！");
						}
					}else{
						JOptionPane.showMessageDialog(assembleNameFrame,"信息不全！");
					}
				}
			}
		});
		//--------------------------------填写商品数量------------------------------------------------------
		JFrame productsQuantity=new JFrame("填写数量");
		//productsQuantity.setAlwaysOnTop(true);
		productsQuantity.setResizable(false);
		productsQuantity.setBounds(700,150,250,150);
		productsQuantity.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container productsQuantity_Content=productsQuantity.getContentPane();
		productsQuantity_Content.setLayout(null);
		JLabel productsQuantity_ProductL=new JLabel();
		productsQuantity_ProductL.setBounds(10,5,250,25);
		JTextField productsQuantity_QuantityT=new JTextField();
		productsQuantity_QuantityT.setBounds(10,35,120,25);
//		JTextField spzk=new JTextField();
//		spzk.setBounds(10,65,120,25);
//		spzk.setEnabled(false);
//		productsQuantity_Content.add(spzk);
		productsQuantity_Content.add(productsQuantity_QuantityT);
		productsQuantity_Content.add(productsQuantity_ProductL);
		//------------------------------------商品选择面板---------------------------------------------------
		JFrame productsFrame=new JFrame("商品");
		//productsFrame.setAlwaysOnTop(true);
		productsFrame.setResizable(false);
		Container productsFrame_Content=productsFrame.getContentPane();
		productsFrame_Content.setLayout(null);
		JTextField productsFrame_QueryT=new JTextField();
		productsFrame_QueryT.setBounds(20,10,120,25);
		String[] productsFrame_ColumnN={"商品型号","商品名称","单位","单价","数量"};
		JTable productsFrame_Table=new JTable();
		productsFrame_Table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int spr=productsFrame_Table.getSelectedRow();
				if(e.getClickCount()==2&&e.getButton()==1){
					Boolean sp=true;
					for(int i=0;i<spcount.size();i++){
						if(spcount.get(i).equals(productsFrame_Table.getValueAt(spr,1).toString().trim())){
							sp=false;
						}
					}
					if(sp==false){
						JOptionPane.showMessageDialog(productsFrame,"已添加商品");
					}else{
						productsQuantity_QuantityT.setText("");
						productsFrame.setEnabled(false);
						productsQuantity.setVisible(true);
						productsQuantity_ProductL.setText(productsFrame_Table.getValueAt(spr,1).toString().trim());
					}
				}
			}
		});
		productsFrame_Table.getTableHeader().setReorderingAllowed(false);
		String[][] sparr=d.spcxjhj(productsFrame_QueryT.getText().trim());
		DefaultTableModel productsFrame_TableModel=new DefaultTableModel(sparr,productsFrame_ColumnN){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		productsFrame_TableModel.setColumnIdentifiers(productsFrame_ColumnN);
		productsFrame_Table.setRowHeight(20);
		productsFrame_Table.setModel(productsFrame_TableModel);
		productsFrame_Table.setDefaultRenderer(Object.class, tcr);
    	TableColumn sptablecl1=productsFrame_Table.getColumnModel().getColumn(0);   //设置列宽    
    	sptablecl1.setPreferredWidth(80);   
    	sptablecl1.setMinWidth(80);
    	sptablecl1.setMaxWidth(80);
    	TableColumn sptablecl=productsFrame_Table.getColumnModel().getColumn(1);   //设置列宽    
    	sptablecl.setPreferredWidth(180);   
    	sptablecl.setMinWidth(180);
    	sptablecl.setMaxWidth(180);
		JPanel productsFrame_Pane=new JPanel();
		productsFrame_Pane.setLayout(null);
		productsFrame_Pane.setBounds(10,50,500,620);
		JScrollPane productsFrame_JSP=new JScrollPane();
		productsFrame_JSP.setViewportView(productsFrame_Table);
		productsFrame_JSP.setBounds(0,0,475,555);
		productsFrame_QueryT.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					String[][] sparr=d.spcxjhj(productsFrame_QueryT.getText().trim());
//					DefaultTableModel spdm=new DefaultTableModel(sparr,productsFrame_ColumnN){
//						/**
//						 * 
//						 */
//						private static final long serialVersionUID = 1L;
//						public boolean isCellEditable(int row,int colunm){
//							return false;
//						}
//					};
					productsFrame_TableModel.setDataVector(sparr,productsFrame_ColumnN);
					productsFrame_Table.setModel(productsFrame_TableModel);
					TableColumn sptablecl1=productsFrame_Table.getColumnModel().getColumn(0);   //设置列宽    
			    	sptablecl1.setPreferredWidth(80);   
			    	sptablecl1.setMinWidth(80);
			    	sptablecl1.setMaxWidth(80);
			    	TableColumn sptablecl=productsFrame_Table.getColumnModel().getColumn(1);   //设置列宽    
			    	sptablecl.setPreferredWidth(180);   
			    	sptablecl.setMinWidth(180);
			    	sptablecl.setMaxWidth(180);
				}
			}
		});
		productsFrame_Pane.add(productsFrame_JSP);
		productsFrame_Content.add(productsFrame_Pane);
		productsFrame_Content.add(productsFrame_QueryT);
		productsFrame.setBounds(930,100,500,650);
		productsFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				productsFrame.dispose();
				AssembleFrame.setEnabled(true);
			}
		});
		//productsFrame.setVisible(true);
		//------------------------------------商品填写数量-------------------------------------------------
		productsQuantity_QuantityT.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int mr=AssembleFrame_Table.getRowCount();
				int spr=productsFrame_Table.getSelectedRow();
				if(e.getKeyCode()=='\n'){
					if(productsQuantity_QuantityT.getText().trim().length()==0){
						JOptionPane.showMessageDialog(productsQuantity,"未填写数量");
					}else{
						try{
							String s=productsQuantity_QuantityT.getText().trim();
							int pdnum=Integer.parseInt(s);
							//String skc=productsFrame_Table.getValueAt(spr,4).toString().trim();
							//int kcsl=Integer.parseInt(skc);
									if(pdnum==0){
										JOptionPane.showMessageDialog(productsQuantity,"不能为0");
									}else{
										AssembleFrame_TableModel.addRow(row);
										AssembleFrame_Table.setValueAt(mr+1,mr,0);
										AssembleFrame_Table.setValueAt(productsFrame_Table.getValueAt(spr,0),mr,1);
										AssembleFrame_Table.setValueAt(productsFrame_Table.getValueAt(spr,1),mr,2);
										AssembleFrame_Table.setValueAt(productsFrame_Table.getValueAt(spr,2),mr,3);
										AssembleFrame_Table.setValueAt(productsFrame_Table.getValueAt(spr,3),mr,4);
											//kccount.add(kcsl);
										AssembleFrame_Table.setValueAt(s,mr,5);
										productsFrame.setEnabled(true);
										productsQuantity.dispose();
										spcount.add(productsFrame_Table.getValueAt(spr,1).toString().trim());	
//										}else{
//											String spn=mtable.getValueAt(mtselect,2).toString().trim();
//											for(int i=0;i<spcount.size();i++){
//												if(spcount.get(i).equals(spn)){
//													spcount.set(i,sptable.getValueAt(spr,1).toString().trim());
//												}
//											}
//											kccount.set(mtselect,kcsl);
//											mtable.setValueAt(sptable.getValueAt(spr,0),mtselect,1);
//											mtable.setValueAt(sptable.getValueAt(spr,1),mtselect,2);
//											mtable.setValueAt(sptable.getValueAt(spr,2),mtselect,3);
//											mtable.setValueAt(sptable.getValueAt(spr,3),mtselect,4);
//											mtable.setValueAt(s,mtselect,5);
//											mtable.clearSelection();
//											spsl.dispose();
//										}	
									}
							
						}catch(Exception e1){
							JOptionPane.showMessageDialog(productsQuantity,"请填写数字");
							productsQuantity_QuantityT.setText("");
						}
					}
				}
			}
		});
		//---------------------------------------------------------------------
		AssembleQuantity.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				showAssemble.setEnabled(true);
				AssembleQuantity.dispose();
			}
		});
		//----------------------------------------------------------------------
		showAssemble.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				Assemble_MFrame_Table.clearSelection();
				Assemble_MFrame.setEnabled(true);
				showAssemble.dispose();
			}
		});
		//-----------------------------------------------------------------------
		assembleNameFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				AssembleFrame.setEnabled(true);
				assembleNameFrame.dispose();
			}
		});
		//-----------------------------------------------------------------------
		productsQuantity.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				productsFrame.setEnabled(true);
				productsQuantity.dispose();
			}
		});
		//--------------------------------------------------------------------------
		assembleFrame_AddB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//lsdm.setDataVector(gd.zm(),lscn);
				productsFrame.setVisible(true);
				AssembleFrame.setEnabled(false);
				//lsf.setVisible(true);
			}
		});
		//-----------------------------------------------------------------------------
		Assemble_MFrame_AddB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Assemble_MFrame_Table.clearSelection();
				Assemble_MFrame.setEnabled(false);
				AssembleFrame.setVisible(true);
			}
		});
	}
}
