package data;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class KCTable {
	getData gd=new getData();
	 public JScrollPane jsp(){
		   JScrollPane jsp=new JScrollPane();
	   	   //jsp.setViewportView(table());
	   	   jsp.setBounds(0,0,565,300);
		   return jsp;	
	   }
	public JTable table(){
		JTable table=new JTable();
		//table.setModel(model());
		table.getTableHeader().setReorderingAllowed(false);
    	DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    	tcr.setHorizontalAlignment(SwingConstants.CENTER);
    	tcr.setHorizontalAlignment(JLabel.CENTER);
    	table.setDefaultRenderer(Object.class, tcr);  //���Ƽ����п��JTable
		return table;
	}
	public DefaultTableModel model(){
		String[][] arr=gd.KCdata("");
		String[] columnName={"��Ʒ����","����","��λ","����޸�����","������"};
		DefaultTableModel DefaultModel=new DefaultTableModel(arr,columnName){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		return DefaultModel;
	}
}
