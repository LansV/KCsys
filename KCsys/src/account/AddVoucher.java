package account;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

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
		MTabbedPane.add("收款凭证", recipteVoucherPanel());
		MTabbedPane.add("付款凭证", recipteVoucherPanel());
		MTabbedPane.add("转账凭证", recipteVoucherPanel());
		MFC.add(MTabbedPane);
		MFrame.setVisible(true);
	}
	public static void main(String[] args){
		new AddVoucher();
	}
	public JTable tableTemplate(JPanel p,Color c){
		JTable MTable;
		DefaultTableModel dm;
		MTable=new JTable(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 3032682468682010521L;
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
								JOptionPane.showMessageDialog(p, "只能输入整数");
								return;
							}
						}catch(Exception e){
							JOptionPane.showMessageDialog(p, "只能输入整数");
							return;
						}
					}
				}
				if(columnIndex==2&&rowIndex!=7){
					String s=aValue.toString();
					if(s.length()!=0){
						try{
							double d=Double.parseDouble(s);
							super.setValueAt(String.format("%.2f", d), rowIndex, columnIndex);
							return;
						}catch(Exception e){
							JOptionPane.showMessageDialog(p, "只能输入数字");
							return;
						}
					}
				}
				super.setValueAt(aValue, rowIndex, columnIndex);
			}
		};
		String[] cn={"摘要","贷方科目","金额"};
		String[][] arr=new String[8][3];
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
		MTable.setDefaultRenderer(Object.class, tcr);
		MTable.setModel(dm);
		dm.addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO 自动生成的方法存根
				if(e.getColumn()==2){
					int r = e.getFirstRow();
					int c = e.getColumn();
					if(r<7&&c==2){
						if(MTable.getValueAt(r, c-1)==null){
							System.out.println("get");
						}
						MTable.setValueAt("2", 7, c);
					}
				}
			}
			
		});
		MTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(e.getButton()==1){
					int c=MTable.columnAtPoint(e.getPoint());
					if(c==2){
						int r=MTable.rowAtPoint(e.getPoint());
						System.out.println("press");
						if(MTable.getValueAt(r, 1)==null){
							JOptionPane.showMessageDialog(MTable, "前端值不能为空");
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
		});
		MTable.setValueAt("合              计", 7, 1);
		MTable.setGridColor(c);
		MTable.getTableHeader().setReorderingAllowed(false);
		JTableHeader th=MTable.getTableHeader();
		th.setPreferredSize(new Dimension(th.getWidth(),30)); 
		MTable.setRowHeight(24);
		setTableHeaderColor(MTable,0,c);
		setTableHeaderColor(MTable,1,c);
		setTableHeaderColor(MTable,2,c);
		setTableCellColor(MTable,1,c);
		setTableCellColor(MTable,0,c);
		return MTable;
		
	}
	public JPanel recipteVoucherPanel(){
		JPanel panel;
		JLabel skL, leftsubL, selectsubL, subdateL, voucherNoL,footerL;
		panel=new JPanel();
		panel.setLayout(null);
		skL=new JLabel("收款凭证");
		leftsubL=new JLabel("借方科目：");
		selectsubL=new JLabel("请选择借方科目",JLabel.CENTER);
		panel.add(selectsubL);
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
		subdateL.setForeground(new Color(252,65,83));
		subdateL.setBounds(360,50,120,20);
		panel.add(subdateL);
		JLabel voucherNoTL=new JLabel("001");
		voucherNoTL.setBounds(732,50,80,20);
		panel.add(voucherNoTL);
		voucherNoL=new JLabel("字第            号");
		voucherNoL.setForeground(new Color(252,65,83));
		voucherNoL.setBounds(700,50,80,20);
		panel.add(voucherNoL);
		//-----------------------------------------------------------
		skL.setBounds(350,10,120,25);
		panel.add(skL);
		Font skfont=new Font("宋体",1,24);
		skL.setForeground(new Color(252,65,83));
		skL.setFont(skfont);
		leftsubL.setBounds(10,50,80,20);
		leftsubL.setForeground(new Color(252,65,83));
		panel.add(leftsubL);
		selectsubL.setBounds(75,50,160,20);
		selectsubL.setOpaque(true);
		selectsubL.setBackground(Color.white);
		selectsubL.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(e.getClickCount()==2){
					JOptionPane.showMessageDialog(selectsubL, "open");
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
		JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(tableTemplate(panel,new Color(252,65,83)));
		jsp.setBounds(10,75,770,225);
		panel.add(jsp);
		footerL=new JLabel("会计主管                                          记账                                           出纳                                            审核                                          制证");
		footerL.setBounds(10,300,700,25);
		footerL.setForeground(new Color(252,65,83));
		panel.add(footerL);
		//-----------------------------------------
		return panel;
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
