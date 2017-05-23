package account;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class AddVoucher {
	private static JFrame MFrame;
	private static JTabbedPane MTabbedPane;
	private static JPanel mpanel1,mpanel2,mpanel3; 
	private static JLabel skL,fkL,zL,leftsubL,selectsubL,subdateL;
	private static JTextField leftsubT;
	private static JTable MTable;
	private static DefaultTableModel dm;
	public AddVoucher(){
		MFrame=new JFrame("添加凭证");
		MFrame.setResizable(false);
		MFrame.setBounds(100,100,800,600);
		MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MFC=MFrame.getContentPane();
		skL=new JLabel("收款凭证");
		fkL=new JLabel("付款凭证");
		zL=new JLabel("转账凭证");
		leftsubL=new JLabel("借方科目：");
		selectsubL=new JLabel("请选择借方科目",JLabel.CENTER);
		leftsubT=new JTextField();
		subdateL=new JLabel();
		//-----------------------------------------------------------
		mpanel1=new JPanel();
		mpanel1.setLayout(null);
		skL.setBounds(335,10,120,25);
		Font skfont=new Font("宋体",1,24);
		skL.setForeground(new Color(252,65,83));
		skL.setFont(skfont);
		leftsubL.setBounds(10,50,80,20);
		leftsubL.setForeground(new Color(252,65,83));
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
		mpanel1.add(selectsubL);
		mpanel1.add(leftsubL);
		mpanel1.add(skL);
		MTable=new JTable();
		String[] cn={"摘要","贷方科目","金额"};
		String[][] arr=new String[8][3];
		dm=new DefaultTableModel(arr,cn){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row ,int column){
				if(column!=1&&row!=7){
					return true;
				}
				return false;
			}
			
		};
		MTable.setModel(dm);
		MTable.setGridColor(new Color(252,65,83));
		MTable.getTableHeader().setReorderingAllowed(false);
		JTableHeader th=MTable.getTableHeader();
		th.setPreferredSize(new Dimension(th.getWidth(),30)); 
		MTable.setRowHeight(24);
		setTableHeaderColor(MTable,0,new Color(252,65,83));
		setTableHeaderColor(MTable,1,new Color(252,65,83));
		setTableHeaderColor(MTable,2,new Color(252,65,83));
		JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(MTable);
		jsp.setBounds(10,75,770,225);
		mpanel1.add(jsp);
		//-----------------------------------------
		mpanel2=new JPanel();
		mpanel3=new JPanel();
		MTabbedPane=new JTabbedPane();
		MTabbedPane.add("收款凭证", mpanel1);
		MTabbedPane.add("付款凭证", mpanel2);
		MTabbedPane.add("转账凭证", mpanel3);
		MFC.add(MTabbedPane);
		MFrame.setVisible(true);
	}
	public static void main(String[] args){
		new AddVoucher();
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
}
