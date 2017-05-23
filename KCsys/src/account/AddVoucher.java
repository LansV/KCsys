package account;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class AddVoucher {
	private static JFrame MFrame;
	private static JTabbedPane MTabbedPane;

	public AddVoucher(){
		MFrame=new JFrame("���ƾ֤");
		MFrame.setResizable(false);
		MFrame.setBounds(100,100,800,420);
		MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MFC=MFrame.getContentPane();
		MTabbedPane=new JTabbedPane();
		MTabbedPane.add("�տ�ƾ֤", recipteVoucherPanel());
		MTabbedPane.add("����ƾ֤", recipteVoucherPanel());
		MTabbedPane.add("ת��ƾ֤", recipteVoucherPanel());
		MFC.add(MTabbedPane);
		MFrame.setVisible(true);
	}
	public static void main(String[] args){
		new AddVoucher();
	}
	public JTable tableTemplate(){
		JTable MTable = null;
		DefaultTableModel dm;
		MTable=new JTable();
		String[] cn={"ժҪ","������Ŀ","���"};
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
		return MTable;
		
	}
	public JPanel recipteVoucherPanel(){
		JPanel panel;
		JLabel skL, leftsubL, selectsubL, subdateL, voucherNoL;
		skL=new JLabel("�տ�ƾ֤");
		leftsubL=new JLabel("�跽��Ŀ��");
		selectsubL=new JLabel("��ѡ��跽��Ŀ",JLabel.CENTER);
		Date date=new Date();
		String year=String.format("%tY", date);
		String month=String.format("%tm", date);
		String day=String.format("%td", date);
		subdateL=new JLabel(year+" �� "+month+" �� "+day+" �� ");
		voucherNoL=new JLabel("�ֵ�            ��");
		//-----------------------------------------------------------
		panel=new JPanel();
		panel.setLayout(null);
		skL.setBounds(335,10,120,25);
		panel.add(skL);
		Font skfont=new Font("����",1,24);
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
				// TODO �Զ����ɵķ������
				if(e.getClickCount()==2){
					JOptionPane.showMessageDialog(selectsubL, "open");
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				selectsubL.setBackground(Color.gray);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO �Զ����ɵķ������
				selectsubL.setBackground(Color.WHITE);
			}
			
		});
		panel.add(selectsubL);
		subdateL.setBounds(350,50,120,20);
		panel.add(subdateL);
		voucherNoL.setBounds(700,50,80,20);
		panel.add(voucherNoL);
		JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(tableTemplate());
		jsp.setBounds(10,75,770,225);
		panel.add(jsp);
		
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
        		Font font=new Font("����",1,14);
        		comp.setFont(font);
        		comp.setForeground(c);
        		//comp.setBorder(BorderFactory.createRaisedBevelBorder());  
        		comp.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, c));  
        		//comp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        		return comp;
        	}
        };
        //Font font=new Font("����",1,20);
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        column.setHeaderRenderer(cellRenderer);
        
    }
}
