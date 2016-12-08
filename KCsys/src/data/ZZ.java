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
public class ZZ {
	public ZZ(){
		wData w=new wData();
		getData gd=new getData();
		List<String> spcount=new ArrayList<String>();
		List<Integer> kccount=new ArrayList<Integer>();
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);
	    //------------------------------------------组装数量-----------------------------------------------------
	    JFrame zzsl=new JFrame("数量");
		zzsl.setBounds(300,150,220,120);
		zzsl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container zzslc=zzsl.getContentPane();
		zzslc.setLayout(null);
		JTextField zzslt=new JTextField();
		zzslt.setBounds(10,10,180,25);
		zzslc.add(zzslt);
		zzsl.setResizable(false);
	    //-------------------------------------------历史纪录-----------------------------------------------------
	    JFrame lsf=new JFrame("历史纪录");
	    lsf.setResizable(false);
	    lsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    lsf.setBounds(50,150,250,400);
	    Container lsfc=lsf.getContentPane();
	    lsfc.setLayout(null);
	    JTable lstable=new JTable();
	    lstable.getTableHeader().setReorderingAllowed(false);
		String[] lscn={"名称","识别号"};
		DefaultTableModel lsdm=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		lstable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					if(e.getClickCount()==2){
						lsf.setEnabled(false);
						zzsl.setVisible(true);	
					}
				}
			}
		});
		lstable.setDefaultRenderer(Object.class, tcr);
		lstable.setRowHeight(20);
		lsdm.setColumnIdentifiers(lscn);
		lstable.setModel(lsdm);
		JPanel lsp=new JPanel();
		JScrollPane lsjsp=new JScrollPane();
		lsjsp.setViewportView(lstable);
		lsjsp.setBounds(7,0,230,370);
		lsp.setLayout(null);
		lsp.setBounds(0,0,250,400);
		lsp.add(lsjsp);
		lsfc.add(lsp);
		//-------------------------------------------关闭监听-------------------------------------------------------
		zzsl.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e) {
				zzslt.setText("");
				lsf.setEnabled(true);
			}
		});
	    //-------------------------------------------组装命名-------------------------------------------------------
	    JFrame zm=new JFrame("命名");
		zm.setBounds(300,150,220,150);
		zm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container zmc=zm.getContentPane();
		zmc.setLayout(null);
		JLabel zml=new JLabel("名称:");
		zml.setBounds(10,10,60,25);
		JTextField zmt=new JTextField();
		zmt.setBounds(60,10,150,25);
		JLabel xhl=new JLabel("型号:");
		xhl.setBounds(10,45,60,25);
		JTextField xht=new JTextField();
		xht.setBounds(60,45,150,25);
		JLabel jgl=new JLabel("价格:");
		jgl.setBounds(10,80,60,25);
		JTextField jgt=new JTextField();
		jgt.setBounds(60,80,150,25);
		zmc.add(zmt);
		zmc.add(zml);
		zmc.add(xht);
		zmc.add(xhl);
		zmc.add(jgt);
		zmc.add(jgl);
		zm.setResizable(false);
		//-----------------------------------------主界面---------------------------------------------------------
		JFrame mframe=new JFrame("组装");
		Container mc=mframe.getContentPane();
		mframe.setBounds(100,20,500,650);
		mframe.setResizable(false);
		mc.setLayout(null);
		JPanel mpane=new JPanel();
		mpane.setLayout(null);
		JScrollPane mjsp=new JScrollPane();
		JTable mtable=new JTable(){
			private static final long serialVersionUID = 1L;
			public void setValueAt(Object aValue, int rowIndex, int columnIndex){
				if(columnIndex==5){
	                try{
	                	String st=(String) aValue;
						int num =Integer.parseInt(st);
						//System.out.println(kccount.get(rowIndex));
						if(num>kccount.get(rowIndex)){
							JOptionPane.showMessageDialog(null,"超出库存");
							return;
						}
						if(num==0){
							JOptionPane.showMessageDialog(null,"不能为0");
							return;
						}
	                }catch(Exception ex){
	                    JOptionPane.showMessageDialog(null, "只能输入数字!");
	                    return;
	                }
				}  
			      super.setValueAt(aValue,rowIndex,columnIndex);
			}
		};
		mtable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					if(e.getClickCount()==2){
						mtable.clearSelection();
					}
				}
			}
		});
		mtable.getTableHeader().setReorderingAllowed(false);
		String[] mcn={"序号","商品型号","商品名称","单位","单价","数量","金额"};
		DefaultTableModel mdm=new DefaultTableModel(){
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
		mdm.setColumnIdentifiers(mcn);
		mtable.setRowHeight(20);
		mtable.setModel(mdm);
		mtable.setDefaultRenderer(Object.class, tcr);
    	TableColumn mtablecl3=mtable.getColumnModel().getColumn(1);   //设置列宽    
    	mtablecl3.setPreferredWidth(80);   
    	mtablecl3.setMinWidth(80);
    	mtablecl3.setMaxWidth(80);
    	TableColumn mtablecl=mtable.getColumnModel().getColumn(2);   //设置列宽    
    	mtablecl.setPreferredWidth(130);   
    	mtablecl.setMinWidth(130);
    	mtablecl.setMaxWidth(130);
    	TableColumn mtablecl2=mtable.getColumnModel().getColumn(6);   //设置列宽    
    	mtablecl2.setPreferredWidth(60);   
    	mtablecl2.setMinWidth(60);
    	mtablecl2.setMaxWidth(60);
		mjsp.setViewportView(mtable);
		mjsp.setBounds(7,0,470,580);
		mpane.add(mjsp);
		JButton zzbutton=new JButton("组装");
		zzbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mtable.getRowCount()<2){
					JOptionPane.showMessageDialog(null,"至少添加两种商品进行组装");
				}else{
					mframe.setEnabled(false);
					zm.setVisible(true);
				}
			}
		});
		zzbutton.setBounds(200,585,60,25);
		JButton lszz=new JButton("组装记录");
		lszz.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lsdm.setDataVector(gd.zm(),lscn);
				mframe.setEnabled(false);
				lsf.setVisible(true);
			}
		});
		lszz.setBounds(10,585,90,25);
		JLabel zjl=new JLabel();
		zjl.setBounds(420,585,90,25);
		mpane.add(zjl);
		mpane.add(lszz);
		mpane.add(zzbutton);
		mpane.setBounds(0,0,500,630);
		mc.add(mpane);
		mframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mframe.setVisible(true);
		Object[] row=new Object[mcn.length];
		row[0]="";
		row[1]="";
		row[2]="";
		row[3]="";
		row[4]="";
		row[5]="";
		row[6]="";
		//-----------------------------------组装窗口关闭监听-------------------------------------------
		zm.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				mframe.setEnabled(true);
			}
		});
		//-----------------------------------历史窗口关闭监听-------------------------------------------
		lsf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				mframe.setEnabled(true);
			}
		});
		
		//--------------------------------商品数量------------------------------------------------------
		JFrame spsl=new JFrame("填写数量");
		spsl.setResizable(false);
		spsl.setBounds(700,150,250,150);
		spsl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container spslc=spsl.getContentPane();
		spslc.setLayout(null);
		JLabel splb=new JLabel();
		splb.setBounds(10,5,120,25);
		JTextField sptx=new JTextField();
		sptx.setBounds(10,35,120,25);
		JTextField spzk=new JTextField();
		spzk.setBounds(10,65,120,25);
		spzk.setEnabled(false);
		spslc.add(spzk);
		spslc.add(sptx);
		spslc.add(splb);
		//------------------------------------主表模型监听-------------------------------------------------
		mdm.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int r=e.getFirstRow();
				int c=e.getColumn();
				if(c==5){
					try{
				        String s=mtable.getValueAt(r,5).toString().trim();
				        int sl=Integer.parseInt(s);
						String d=mtable.getValueAt(r,4).toString().trim();
						Double dj=Double.parseDouble(d);
						String zj=String.format("%.2f",dj*sl);
						mtable.setValueAt(zj,r,6);
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"输入非法");
					}
				}
				if(c==6){
					int k=mtable.getRowCount();
					Double zj=0.0;
					for(int i=0;i<k;i++){
						zj=Double.parseDouble(mtable.getValueAt(i,c).toString())+zj;
					}
					zjl.setText(String.format("%.2f",zj));
				}
			}
		});
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
		String[][] sparr=gd.spcxjhj(spjt.getText().trim());
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
		sptable.setRowHeight(20);
		sptable.setModel(spdm);
		sptable.setDefaultRenderer(Object.class, tcr);
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
		spjs.setBounds(0,0,475,555);
		spjt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()=='\n'){
					String[][] sparr=gd.spcxjhj(spjt.getText().trim());
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
		sp.setVisible(true);
		//------------------------
		mframe.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				sp.dispose();
			}
		});
		//------------------------------------命名监听-------------------------------------------------------
		zmt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					String zmn=zmt.getText();
					if(zmn.length()==0){
						JOptionPane.showMessageDialog(null,"组名为空");
					}else{
						String zdj=jgt.getText();
						if(zdj.length()==0){
							JOptionPane.showMessageDialog(null,"定价为空");
						}else{
							boolean pdk=false;
							for(int j=0;j<gd.zzjl().size();j++){
								if(gd.zzjl().get(j).equals(zmn.trim())==true){
									pdk=true;
									JOptionPane.showMessageDialog(null,"命名重复");
								}
							}
							if(pdk==false){
								try{
									Double dje=Double.parseDouble(zdj);
									Double jhj=Double.parseDouble(zjl.getText());
									//System.out.println(dje);
									for(int i=0;i<mtable.getRowCount();i++){
										String sbh=mtable.getValueAt(i,1).toString().trim();
										String sp=mtable.getValueAt(i,2).toString().trim();
										String dw=mtable.getValueAt(i,3).toString().trim();
										String sdj=mtable.getValueAt(i,4).toString().trim();
										Double dj=Double.parseDouble(sdj);
										String ssl=mtable.getValueAt(i,5).toString().trim();
										int sl=Integer.parseInt(ssl);
										String sje=mtable.getValueAt(i,6).toString().trim();
										Double je=Double.parseDouble(sje);
										w.wzz(zmn.trim(),i+1,sbh,sp,dw,dj,sl,je);
										w.wkcout(sbh,sp,sl,"3,"+zmn.trim());
										if(i==mtable.getRowCount()-1){
											mframe.setEnabled(true);
											zm.dispose();
											mdm.setRowCount(0);
											kccount.clear();
											spcount.clear();
										}
									}
									w.wkc(xht.getText().trim(),zmn.trim(),jhj,dje);
									zjl.setText("");
									String[][] ar=gd.spcxjhj(spjt.getText().trim());
									spdm.setDataVector(ar,spcn);
									TableColumn sptablecl1=sptable.getColumnModel().getColumn(0);   //设置列宽    
							    	sptablecl1.setPreferredWidth(80);   
							    	sptablecl1.setMinWidth(80);
							    	sptablecl1.setMaxWidth(80);
							    	TableColumn sptablecl=sptable.getColumnModel().getColumn(1);   //设置列宽    
							    	sptablecl.setPreferredWidth(180);   
							    	sptablecl.setMinWidth(180);
							    	sptablecl.setMaxWidth(180);
									JOptionPane.showMessageDialog(null,"组装完成");
								}catch(Exception e2){
									JOptionPane.showMessageDialog(null,"输入非法");
								}
							}
						}
					}
				}
			}
		});
		//-------------------------------------------组装数量监听---------------------------------------------------
		zzslt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					int r=lstable.getSelectedRow();
					try{
						String ssl=zzslt.getText().trim();
						int sl=Integer.parseInt(ssl);
						String zmn=lstable.getValueAt(r,0).toString();
						String xh=lstable.getValueAt(r,1).toString();
						String[][] arr=gd.zpdkc(zmn,sl);
						int cot=0;
						List<String> jl=new ArrayList<String>();
						for(int i=0;i<arr.length;i++){
							cot=cot+Integer.parseInt(arr[i][2]);
							if(Integer.parseInt(arr[i][2])>0){
								jl.add(arr[i][0]+"库存不足，还需"+arr[i][2]);
							}
						}
						if(cot==0){
							for(int i=0;i<arr.length;i++){
								w.wkcout(arr[i][3],arr[i][0],Integer.parseInt(arr[i][1])*sl,"3,"+zmn);
							}
							w.wkcin(xh,zmn,sl,zmn);
							String[][] ar=gd.spcxjhj(spjt.getText().trim());
							spdm.setDataVector(ar,spcn);
							TableColumn sptablecl1=sptable.getColumnModel().getColumn(0);   //设置列宽    
					    	sptablecl1.setPreferredWidth(80);   
					    	sptablecl1.setMinWidth(80);
					    	sptablecl1.setMaxWidth(80);
					    	TableColumn sptablecl=sptable.getColumnModel().getColumn(1);   //设置列宽    
					    	sptablecl.setPreferredWidth(180);   
					    	sptablecl.setMinWidth(180);
					    	sptablecl.setMaxWidth(180);
							zzslt.setText("");
							zzsl.dispose();
							JOptionPane.showMessageDialog(null,"组装完成");
						}else{
							for(int i=0;i<jl.size();i++){
								JOptionPane.showMessageDialog(null,jl.get(i));
							}
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"输入非法");
					}
				}
			}
		});
		//------------------------------------商品填写数量-------------------------------------------------
		sptx.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int mr=mtable.getRowCount();
				int spr=sptable.getSelectedRow();
				if(e.getKeyCode()=='\n'){
					if(sptx.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null,"未填写数量");
					}else{
						try{
							String s=sptx.getText().trim();
							int pdnum=Integer.parseInt(s);
							String skc=sptable.getValueAt(spr,4).toString().trim();
							int kcsl=Integer.parseInt(skc);
								if(pdnum>kcsl){
									JOptionPane.showMessageDialog(null, "超出库存");
								}else{
									if(pdnum==0){
										JOptionPane.showMessageDialog(null,"不能为0");
									}else{
										int mtselect=mtable.getSelectedRow();
										if(mtselect<0){
											mdm.addRow(row);
											mtable.setValueAt(mr+1,mr,0);
											mtable.setValueAt(sptable.getValueAt(spr,0),mr,1);
											mtable.setValueAt(sptable.getValueAt(spr,1),mr,2);
											mtable.setValueAt(sptable.getValueAt(spr,2),mr,3);
											mtable.setValueAt(sptable.getValueAt(spr,3),mr,4);
											kccount.add(kcsl);
											mtable.setValueAt(s,mr,5);
											spsl.dispose();
											spcount.add(sptable.getValueAt(spr,1).toString().trim());	
										}else{
											String spn=mtable.getValueAt(mtselect,2).toString().trim();
											for(int i=0;i<spcount.size();i++){
												if(spcount.get(i).equals(spn)){
													spcount.set(i,sptable.getValueAt(spr,1).toString().trim());
												}
											}
											kccount.set(mtselect,kcsl);
											mtable.setValueAt(sptable.getValueAt(spr,0),mtselect,1);
											mtable.setValueAt(sptable.getValueAt(spr,1),mtselect,2);
											mtable.setValueAt(sptable.getValueAt(spr,2),mtselect,3);
											mtable.setValueAt(sptable.getValueAt(spr,3),mtselect,4);
											mtable.setValueAt(s,mtselect,5);
											mtable.clearSelection();
											spsl.dispose();
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
	}
	public static void main(String[] args){
		new ZZ();
	}
}
