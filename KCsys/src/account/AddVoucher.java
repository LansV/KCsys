package account;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class AddVoucher {
	private static JFrame MFrame;
	private static JTabbedPane MTabbedPane;
	public AddVoucher(){
		MFrame=new JFrame("添加凭证");
		MFrame.setResizable(false);
		MFrame.setBounds(100,100,800,420);
		MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MFC=MFrame.getContentPane();
		MTabbedPane=new JTabbedPane();
		MTabbedPane.add("收款凭证", voucherPanel(MFrame,"收款凭证",new Color(252,65,83)));
		MTabbedPane.add("付款凭证", voucherPanel(MFrame,"付款凭证",new Color(48,150,255)));
		MTabbedPane.add("转账凭证", voucherPanel(MFrame,"转账凭证",new Color(99,220,75)));
		MFC.add(MTabbedPane);
		MFrame.setVisible(true);
	}
	public static void main(String[] args){
		new AddVoucher();
	}
	public JFrame subjectFrame(JFrame mf,JLabel b,JTable tt,int r){
		JFrame f=new JFrame("选择科目");
		f.setBounds(100, 100, 370, 700);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container fc=f.getContentPane();
		JTabbedPane jt=new JTabbedPane();
		JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(MyTree(mf,"1",b,tt,r,f));
		jt.add("资产类", jsp);
		JScrollPane jsp2=new JScrollPane();
		jsp2.setViewportView(MyTree(mf,"2",b,tt,r,f));
		jt.addTab("负债类", jsp2);
		JScrollPane jsp3=new JScrollPane();
		jsp3.setViewportView(MyTree(mf,"3",b,tt,r,f));
		jt.addTab("所有者权益类", jsp3);
		JScrollPane jsp4=new JScrollPane();
		jsp4.setViewportView(MyTree(mf,"4",b,tt,r,f));
		jt.addTab("成本类", jsp4);
		JScrollPane jsp5=new JScrollPane();
		jsp5.setViewportView(MyTree(mf,"5",b,tt,r,f));
		jt.addTab("损益类", jsp5);
		fc.add(jt);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				mf.setEnabled(true);
				f.dispose();
			}
		});
		return f;
	}
	public JTree MyTree(JFrame mf,String classid,JLabel b,JTable tt,int r,JFrame f){
		
		AddVoucherData avd=new AddVoucherData();
		DefaultMutableTreeNode accountClassNode = new DefaultMutableTreeNode("请选择科目");
			List<String> firstlist=avd.getFirstSubject(classid);
				//添加一级科目
				for(String first:firstlist){
					String firstid=first.split(":")[0];
					DefaultMutableTreeNode firstnode=new DefaultMutableTreeNode(first);
					List<String> secondlist=avd.getSecondSubject(firstid);
					int secondlength=secondlist.size();
					if(secondlength!=0){
						//添加二级科目
						for(String second:secondlist){
							String secondid=second.split(":")[0];
							DefaultMutableTreeNode secondnode=new DefaultMutableTreeNode(second);
							List<String> thirdlist=avd.getThirdSubject(secondid);
							int thirdlength=thirdlist.size();
							if(thirdlength!=0){
								//添加三级科目
								for(String third:thirdlist){
									String thirdid=third.split(":")[0];
									DefaultMutableTreeNode thirdnode=new DefaultMutableTreeNode(third);
									List<String> fourthlist=avd.getFourthSubject(thirdid);
									int fourthlength=fourthlist.size();
									if(fourthlength!=0){
										//添加四级科目
										for(String fourth:fourthlist){
											String fourthid=fourth.split(":")[0];
											DefaultMutableTreeNode fourthnode=new DefaultMutableTreeNode(fourth);
											List<String> fifthlist=avd.getFifthSubject(fourthid);
											int fifthlength=fifthlist.size();
											if(fifthlength!=0){
												//添加五级科目
												for(String fifth:fifthlist){
													DefaultMutableTreeNode fifthnode=new DefaultMutableTreeNode(fifth);
													fourthnode.add(fifthnode);
												}
											}
											thirdnode.add(fourthnode);
										}
									}
									secondnode.add(thirdnode);
								}
							}
							firstnode.add(secondnode);
						}
					}
			accountClassNode.add(firstnode);
			 //dtm=new DefaultTreeModel(firstnode);
		}
		DefaultTreeModel dtm = new DefaultTreeModel(accountClassNode);
		JTree tree=new JTree(dtm);
		tree.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				TreePath path = tree.getPathForLocation(e.getX(), e.getY()); 
		         if (path == null) {  
		             return;  
		         }  
		         tree.setSelectionPath(path); 
				if(e.getButton()==1&&e.getClickCount()==2){
					 DefaultMutableTreeNode node=(DefaultMutableTreeNode) path.getLastPathComponent();
					 if(node.isLeaf()){
						 if(b!=null){
							 b.setText(node.toString());
						 }
						 if(tt!=null){
							 tt.setValueAt(node.toString(), r, 1);
						 }
						 mf.setEnabled(true);
						 f.dispose();
						 //JOptionPane.showMessageDialog(tree, "末级科目:"+node);
					 }
				}
			}

		});
		return tree;
	};
	public DefaultTableModel tableModel(JTable tt,int col){
		DefaultTableModel dm;
		String[] cn = null;
		String[][] arr = null;
		if(col==3){
			cn=new String[]{"摘要","贷方科目","金额"};
			arr=new String[8][3];
		}else{
			cn=new String[]{"摘要","会计科目","借方金额","贷方金额"};
			arr=new String[8][4];
		}
		dm=new DefaultTableModel(arr,cn){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row ,int column){
				if(column!=1&&row<7){
					return true;
				}else if(row==7&&column<1){
					return true;
				}
				return false;
			}
			
		};
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		tt.setDefaultRenderer(Object.class, tcr);
		tt.setModel(dm);
		dm.addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO 自动生成的方法存根
				if(e.getColumn()==2||e.getColumn()==3){
					int r = e.getFirstRow();
					int c = e.getColumn();
					if(r<7&&c==2||r<7&&c==3){
						if(tt.getValueAt(r, c)!=null){
							String s=tt.getValueAt(r, c).toString();
							if(s.length()!=0){
								try{
									Double res=0.0;
									for(int i=0;i<=r;i++){
										if(tt.getValueAt(i, c)!=null){
											if(tt.getValueAt(i, c).toString().length()!=0){
												String s1=tt.getValueAt(i, c).toString();
												Double d=Double.parseDouble(s1);
												res=res+d;
											}
										}
									}
									tt.setValueAt(res, 7, c);
								}catch(Exception e1){
									JOptionPane.showMessageDialog(tt, "合计错误！");
								}
							}
						}
					}
				}
			}
			
		});
		return dm;
	}
	public JPanel voucherPanel(JFrame mf,String name,Color c){
		JPanel panel;
		JLabel skL, leftsubL, selectsubL, subdateL, voucherNoL,footerL;
		panel=new JPanel();
		panel.setLayout(null);
		skL=new JLabel(name);
		if(!name.equals("转账凭证")){
			leftsubL=new JLabel("借方科目：");
			selectsubL=new JLabel("请选择借方科目",JLabel.CENTER);
			panel.add(selectsubL);
		}else{
			leftsubL=new JLabel("");
			selectsubL=new JLabel();
		}
		Date date=new Date();
		String year=String.format("%tY", date);
		String month=String.format("%tm", date);
		String day=String.format("%td", date);
		JLabel yearL=new JLabel(year);
		yearL.setBounds(345,50,40,20);
		panel.add(yearL);
		JLabel monthL=new JLabel(month);
		monthL.setBounds(390,50,40,20);
		panel.add(monthL);
		JLabel dayL=new JLabel(day);
		dayL.setBounds(422,50,40,20);
		panel.add(dayL);
		subdateL=new JLabel("     年"+"      月"+"       日 ");
		subdateL.setForeground(c);
		subdateL.setBounds(360,50,120,20);
		panel.add(subdateL);
		JLabel voucherNoTL=new JLabel("001");
		voucherNoTL.setBounds(732,50,80,20);
		panel.add(voucherNoTL);
		voucherNoL=new JLabel("字第            号");
		voucherNoL.setForeground(c);
		voucherNoL.setBounds(700,50,80,20);
		panel.add(voucherNoL);
		//-----------------------------------------------------------
		skL.setBounds(350,10,120,25);
		panel.add(skL);
		Font skfont=new Font("宋体",1,24);
		skL.setForeground(c);
		skL.setFont(skfont);
		leftsubL.setBounds(10,50,80,20);
		leftsubL.setForeground(c);
		panel.add(leftsubL);
		selectsubL.setBounds(75,50,250,20);
		selectsubL.setOpaque(true);
		selectsubL.setBackground(Color.white);
		selectsubL.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(e.getClickCount()==1&&e.getButton()==1){
					mf.setEnabled(false);
					JFrame f=subjectFrame(mf,selectsubL,null,-1);
					f.setVisible(true);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				selectsubL.setBackground(Color.gray);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				selectsubL.setBackground(Color.WHITE);
			}
			
		});
		JTable t= new VoucherJTable();
		if(!name.equals("转账凭证")){
			t.setModel(tableModel(t,3));
		}else{
			t.setModel(tableModel(t,4));
		}
		t.setValueAt("合              计", 7, 1);
		t.setGridColor(c);
		t.getTableHeader().setReorderingAllowed(false);
		JTableHeader th=t.getTableHeader();
		th.setPreferredSize(new Dimension(th.getWidth(),30)); 
		t.setRowHeight(24);
		setTableHeaderColor(t,0,c);
		setTableHeaderColor(t,1,c);
		setTableHeaderColor(t,2,c);
		if(name.equals("转账凭证")){
			setTableHeaderColor(t,3,c);
		}
		setTableCellColor(t,1,c);
		setTableCellColor(t,0,c);
		t.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(e.getButton()==1){
					int c=t.columnAtPoint(e.getPoint());
					int r=t.rowAtPoint(e.getPoint());
					if(c==1&&e.getClickCount()==2){
						if(r>0){
							if(t.getValueAt(r-1,c)!=null){
								mf.setEnabled(false);
								JFrame f=subjectFrame(mf,null, t, r);
								
								f.setVisible(true);
									//JOptionPane.showMessageDialog(MFrame, "open");
							}else{
								JOptionPane.showMessageDialog(t, "上行信息不全\n请先核对上行信息");
							}
						}else{
							mf.setEnabled(false);
							JFrame f=subjectFrame(mf,null, t, r);
							f.setVisible(true);
								//JOptionPane.showMessageDialog(MTable, "open");
						}
					}
				}
			}
		});
		JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(t);
		jsp.setBounds(10,75,770,225);
		panel.add(jsp);
		footerL=new JLabel("会计主管                                          记账                                           出纳                                            审核                                          制证");
		footerL.setBounds(10,300,700,25);
		footerL.setForeground(c);
		panel.add(footerL);
		JButton addB=new JButton("添加凭证");
		addB.setBounds(340,330,100,25);
		panel.add(addB);
		//-----------------------------------------
		return panel;
	}
	class VoucherJTable extends JTable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public VoucherJTable(){
			super();
		}
		public void processKeyEvent(KeyEvent e){
			 if(this.getEditorComponent() == null && 
			 e.getKeyCode() != KeyEvent.VK_UP && 
			 e.getKeyCode() != KeyEvent.VK_DOWN&&e.getKeyCode()!=KeyEvent.VK_RIGHT&&e.getKeyCode()!=KeyEvent.VK_LEFT&&
			 e.getKeyCode()!=KeyEvent.VK_TAB) 
			return;
			else
			super.processKeyEvent(e);
			}
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if(columnIndex==0&&rowIndex==7){
				String s=aValue.toString();
				if(s.length()!=0){
					try{
						int i=Integer.parseInt(s);
						if(i>=0){
							super.setValueAt("附件  "+Integer.toString(i)+"  张", rowIndex, columnIndex);
							return;
						}else{
							JOptionPane.showMessageDialog(this, "只能输入正整数");
							return;
						}
					}catch(Exception e){
						JOptionPane.showMessageDialog(this, "只能输入整数");
						return;
					}
				}
			}
			if(columnIndex==2&&rowIndex<7||columnIndex==3&&rowIndex<7){
					String s=aValue.toString();
					if(this.getValueAt(rowIndex, 1)!=null){
						if(this.getValueAt(rowIndex, 1).toString().length()!=0){
							if(s.length()!=0){
								try{
									double d=Double.parseDouble(s);
									if(d>0){
										super.setValueAt(String.format("%.2f", d), rowIndex, columnIndex);
										return;
									}else{
										JOptionPane.showMessageDialog(this, "只能输入正数");
										return;
									}
								}catch(Exception e){
									JOptionPane.showMessageDialog(this, "只能输入数字");
									return;
								}
							}else{
								return;
							}
						}else{
							JOptionPane.showMessageDialog(this, "请先选择科目");
							return;
						}
					}else{
						JOptionPane.showMessageDialog(this, "请先选择科目");
						return;
					}
					
			}
			super.setValueAt(aValue, rowIndex, columnIndex);
		}
	}
	void setTableHeaderColor(JTable table, int columnIndex, Color c)
    {
		TableColumn column = table.getTableHeader().getColumnModel().getColumn(columnIndex);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer(){
        	/**
			 * 
			 */
			private static final long serialVersionUID = 5093261657419683916L;

			public Component getTableCellRendererComponent
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)  
        	{
        		JComponent comp = (JComponent) 
        				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        		Font font=new Font("宋体",1,14);
        		comp.setFont(font);
        		comp.setForeground(c);
        		//comp.setBorder(BorderFactory.createRaisedBevelBorder());  
        		comp.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, c));  
        		//comp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        		return comp;
        	}
        };
        //Font font=new Font("宋体",1,20);
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        column.setHeaderRenderer(cellRenderer);
        
    }
	void setTableCellColor(JTable table, int columnIndex, Color c)
    {
		TableColumn column = table.getColumnModel().getColumn(columnIndex);
		if(columnIndex==0){
			column.setMaxWidth(200);
			column.setMinWidth(200);
		}
		if(columnIndex==1){
			column.setMaxWidth(400);
			column.setMinWidth(400);
		}
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer(){
        	/**
			 * 
			 */
			private static final long serialVersionUID = 5093261657419683916L;

			public Component getTableCellRendererComponent
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)  
        	{
        		JComponent comp = (JComponent) 
        				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        		Font font=new Font("宋体",1,14);
        		table.setCellSelectionEnabled(isEnabled());
        		//table.setSelectionBackground(c);
        		if(row==7){
        			comp.setForeground(c);
        			comp.setFont(font);
        			//comp.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, c)); 
        		}else{
        			comp.setForeground(Color.black);
        		}
            		//comp.setBorder(BorderFactory.createRaisedBevelBorder());  
            		//comp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        		return comp;
        	}
        };
        //Font font=new Font("宋体",1,20);
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        column.setCellRenderer(cellRenderer);
        
    }
}
