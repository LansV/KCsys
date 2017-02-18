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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Assemble {
	List<String> spcount=new ArrayList<String>();
	AssembleData d=new AssembleData();
	public Assemble(String user){
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //������Ⱦ��
	    tcr.setHorizontalAlignment(JLabel.CENTER);
		//---------------------------------------------------------------------------------------------
		JFrame Assemble_MFrame=new JFrame("��װ���");
		Assemble_MFrame.setResizable(false);
		Assemble_MFrame.setAlwaysOnTop(true);
		Assemble_MFrame.setBounds(30,100,400,705);
		Assemble_MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container Assemble_MFrame_Content=Assemble_MFrame.getContentPane();
		Assemble_MFrame_Content.setLayout(null);
		JTextField Assemble_MFrame_JT=new JTextField();
		Assemble_MFrame_JT.setBounds(10, 10, 150, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_JT);
		JButton Assemble_MFrame_QueryB=new JButton("��ѯ");
		Assemble_MFrame_QueryB.setBounds(220, 10, 60, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_QueryB);
		JButton Assemble_MFrame_AddB=new JButton("����");
		Assemble_MFrame_AddB.setBounds(320, 645, 60, 24);
		Assemble_MFrame_Content.add(Assemble_MFrame_AddB);
		//------------------------------------------MFrame_Table--------------------------------------
		JTable Assemble_MFrame_Table=new JTable();
		String[] Assemble_MFrame_TableColumn={"ʶ���","Ʒ��"};
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
	    //-------------------------------------------��װ����-------------------------------------------------------
	    JFrame assembleNameFrame=new JFrame("����");
	    assembleNameFrame.setAlwaysOnTop(true);
		assembleNameFrame.setBounds(300,150,220,150);
		assembleNameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container assembleNameFrame_Content=assembleNameFrame.getContentPane();
		assembleNameFrame_Content.setLayout(null);
		JLabel assembleNameFrame_NameL=new JLabel("����:");
		assembleNameFrame_NameL.setBounds(10,10,60,25);
		JTextField assembleNameFrame_NameT=new JTextField();
		assembleNameFrame_NameT.setBounds(60,10,150,25);
//		JLabel xhl=new JLabel("�ͺ�:");
//		xhl.setBounds(10,45,60,25);
//		JTextField xht=new JTextField();
//		xht.setBounds(60,45,150,25);
		JLabel assembleNameFrame_PriceL=new JLabel("�۸�:");
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
		//-----------------------------��װ���-----------------------------------------
		JFrame AssembleFrame=new JFrame("��װ��");
		AssembleFrame.setAlwaysOnTop(true);
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
							JOptionPane.showMessageDialog(AssembleFrame,"����С����0");
							return;
						}
	                }catch(Exception ex){
	                    JOptionPane.showMessageDialog(AssembleFrame, "ֻ����������!");
	                    return;
	                }
				}  
			      super.setValueAt(aValue,rowIndex,columnIndex);
			}
		};
		AssembleFrame_Table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					if(e.getClickCount()==2){
						AssembleFrame_Table.clearSelection();
					}
				}
			}
		});
		AssembleFrame_Table.getTableHeader().setReorderingAllowed(false);
		String[] AssembleFrame_Contentn={"���","��Ʒ�ͺ�","��Ʒ����","��λ","����","����","���"};
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
		//------------------------------------����ģ�ͼ���-------------------------------------------------
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
						JOptionPane.showMessageDialog(null,"����Ƿ�");
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
		AssembleFrame_TableModel.setColumnIdentifiers(AssembleFrame_Contentn);
		AssembleFrame_Table.setRowHeight(20);
		AssembleFrame_Table.setModel(AssembleFrame_TableModel);
		AssembleFrame_Table.setDefaultRenderer(Object.class, tcr);
    	TableColumn mtablecl3=AssembleFrame_Table.getColumnModel().getColumn(1);   //�����п�    
    	mtablecl3.setPreferredWidth(80);   
    	mtablecl3.setMinWidth(80);
    	mtablecl3.setMaxWidth(80);
    	TableColumn mtablecl=AssembleFrame_Table.getColumnModel().getColumn(2);   //�����п�    
    	mtablecl.setPreferredWidth(130);   
    	mtablecl.setMinWidth(130);
    	mtablecl.setMaxWidth(130);
    	TableColumn mtablecl2=AssembleFrame_Table.getColumnModel().getColumn(6);   //�����п�    
    	mtablecl2.setPreferredWidth(60);   
    	mtablecl2.setMinWidth(60);
    	mtablecl2.setMaxWidth(60);
    	AssembleFrame_JSP.setViewportView(AssembleFrame_Table);
    	AssembleFrame_JSP.setBounds(7,0,470,580);
		AssembleFrame_Pane.add(AssembleFrame_JSP);
		JButton assembleFrame_AssembleB=new JButton("����");
		assembleFrame_AssembleB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(AssembleFrame_Table.getRowCount()<2){
					JOptionPane.showMessageDialog(AssembleFrame,"��������������Ʒ������װ");
				}else{
					assembleNameFrame.setVisible(true);
					AssembleFrame.setEnabled(false);
					//zm.setVisible(true);
				}
			}
		});
		assembleFrame_AssembleB.setBounds(200,585,60,25);
		JButton assembleFrame_AddB=new JButton("ѡ����Ʒ");
		assembleFrame_AddB.setBounds(390,585,90,25);
		AssembleFrame_Pane.add(assembleFrame_AddB);
		AssembleFrame_Pane.add(assembleFrame_AssembleB);
		AssembleFrame_Pane.setBounds(0,0,500,630);
		AssembleFrame_Content.add(AssembleFrame_Pane);
		AssembleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Object[] row=new Object[AssembleFrame_Contentn.length];
		row[0]="";
		row[1]="";
		row[2]="";
		row[3]="";
		row[4]="";
		row[5]="";
		row[6]="";
		AssembleFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				Assemble_MFrame.setEnabled(true);
				AssembleFrame.dispose();
			}
		});
		//======================================��װ�����۸����=============================================
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
										JOptionPane.showMessageDialog(assembleNameFrame,"ѭ��ʧ��");
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
							}catch(Exception e1){
								JOptionPane.showMessageDialog(assembleNameFrame,"���������֣�");
							}
						}else{
							JOptionPane.showMessageDialog(assembleNameFrame,"��Ʒ�Ѵ��ڣ�");
						}
					}else{
						JOptionPane.showMessageDialog(assembleNameFrame,"��Ϣ��ȫ��");
					}
				}
			}
		});
		//--------------------------------��д��Ʒ����------------------------------------------------------
		JFrame productsQuantity=new JFrame("��д����");
		productsQuantity.setAlwaysOnTop(true);
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
		//------------------------------------��Ʒѡ�����---------------------------------------------------
		JFrame productsFrame=new JFrame("��Ʒ");
		productsFrame.setAlwaysOnTop(true);
		productsFrame.setResizable(false);
		Container productsFrame_Content=productsFrame.getContentPane();
		productsFrame_Content.setLayout(null);
		JTextField productsFrame_QueryT=new JTextField();
		productsFrame_QueryT.setBounds(20,10,120,25);
		String[] productsFrame_ColumnN={"��Ʒ�ͺ�","��Ʒ����","��λ","����","����"};
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
						JOptionPane.showMessageDialog(productsFrame,"��������Ʒ");
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
    	TableColumn sptablecl1=productsFrame_Table.getColumnModel().getColumn(0);   //�����п�    
    	sptablecl1.setPreferredWidth(80);   
    	sptablecl1.setMinWidth(80);
    	sptablecl1.setMaxWidth(80);
    	TableColumn sptablecl=productsFrame_Table.getColumnModel().getColumn(1);   //�����п�    
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
					TableColumn sptablecl1=productsFrame_Table.getColumnModel().getColumn(0);   //�����п�    
			    	sptablecl1.setPreferredWidth(80);   
			    	sptablecl1.setMinWidth(80);
			    	sptablecl1.setMaxWidth(80);
			    	TableColumn sptablecl=productsFrame_Table.getColumnModel().getColumn(1);   //�����п�    
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
		//------------------------------------��Ʒ��д����-------------------------------------------------
		productsQuantity_QuantityT.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int mr=AssembleFrame_Table.getRowCount();
				int spr=productsFrame_Table.getSelectedRow();
				if(e.getKeyCode()=='\n'){
					if(productsQuantity_QuantityT.getText().trim().length()==0){
						JOptionPane.showMessageDialog(productsQuantity,"δ��д����");
					}else{
						try{
							String s=productsQuantity_QuantityT.getText().trim();
							int pdnum=Integer.parseInt(s);
							//String skc=productsFrame_Table.getValueAt(spr,4).toString().trim();
							//int kcsl=Integer.parseInt(skc);
									if(pdnum==0){
										JOptionPane.showMessageDialog(productsQuantity,"����Ϊ0");
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
							JOptionPane.showMessageDialog(productsQuantity,"����д����");
							productsQuantity_QuantityT.setText("");
						}
					}
				}
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
				Assemble_MFrame.setEnabled(false);
				AssembleFrame.setVisible(true);
			}
		});
	}
	public static void main(String[] args){
		new Assemble("test");
	}
}