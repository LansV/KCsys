package data;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Dorder {
	Dorderdate d=new Dorderdate();
	public JTable spt(){  // 商品添加
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);  
		JTable zmt=new JTable();
		String[] cn={"识别号","商品名称","单位","单价","数量"};
		String[][] arr={};
		DefaultTableModel dm=new DefaultTableModel(arr,cn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		zmt.setModel(dm);
		zmt.getTableHeader().setReorderingAllowed(false);
		zmt.setDefaultRenderer(Object.class,new ctcr());
		zmt.setRowHeight(24);
		return zmt;
	}
	public JTable amt(){   //添加订单
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();  //创建渲染器
	    tcr.setHorizontalAlignment(JLabel.CENTER);  
		JTable zmt=new JTable();
		String[] cn={"序号","识别号","商品","单位","数量","单价","合计","备注"};
		String[][] arr={};
		DefaultTableModel dm=new DefaultTableModel(arr,cn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		zmt.setModel(dm);
		zmt.getTableHeader().setReorderingAllowed(false);
		zmt.setDefaultRenderer(Object.class,tcr);
		zmt.setRowHeight(24);
		return zmt;
	}
	public JTable xmt(){  //详细
		JTable zmt=new JTable();
		String[] cn={"单号","识别号","商品","单位","数量","单价","合计","日期","备注","状态","差值"};
		String[][] arr={{"单号","名称","跟单人","","","","","","","","8"}};
		DefaultTableModel dm=new DefaultTableModel(arr,cn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		zmt.setModel(dm);
		zmt.getTableHeader().setReorderingAllowed(false);
		zmt.setDefaultRenderer(Object.class,new ctcr());
		zmt.setRowHeight(24);
		return zmt;
	}
	public JTable zmt(){   //总览
		JTable zmt=new JTable();
		String[] cn={"单号","名称","跟单人","状态"};
		String[][] arr={{"单号","名称","跟单人","差"},{"单号","名称","跟单人","齐"}};
		DefaultTableModel dm=new DefaultTableModel(arr,cn){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){
				return false;  //返回不可编辑
			}
		};
		zmt.setModel(dm);
		zmt.getTableHeader().setReorderingAllowed(false);
		zmt.setDefaultRenderer(Object.class,new zmtcr());
		zmt.setRowHeight(24);
		return zmt;
	}
	public Dorder(){
		//=========================================订单详情===================================
		JFrame xt=new JFrame("订单详情");
		xt.setResizable(false);
		xt.setBounds(400,100,1000,700);
		xt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container xtc=xt.getContentPane();
		xtc.setLayout(null);
		JPanel xtp=new JPanel();
		xtp.setLayout(null);
		xtp.setBounds(0,0,1000,700);
		JScrollPane xtjsp=new JScrollPane();
		xtjsp.setBounds(10,40,975,600);
		xtjsp.setViewportView(xmt());
		xtp.add(xtjsp);
		xtc.add(xtp);
		//=========================================订单总览===================================
		JFrame zm=new JFrame("订单总览");
		zm.setResizable(false);
		zm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		zm.setBounds(200,100,385,700);
		Container zmc=zm.getContentPane();
		zmc.setLayout(null);
		JPanel zmp=new JPanel();
		zmp.setBounds(0,0,400,700);
		zmp.setLayout(null);
		JScrollPane zmjsp=new JScrollPane();
		JTable zmt=zmt();
		zmt.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					xt.setVisible(true);
				}
			}
		});
		zmjsp.setViewportView(zmt);
		zmjsp.setBounds(10,30,360,600);
		zmp.add(zmjsp);
		JButton addorder=new JButton("添加订单");
		addorder.setBounds(10,635,100,20);
		zmc.add(addorder);
		zmc.add(zmp);
		zm.setVisible(true);
		//=============================================添加订单======================================
		JFrame am=new JFrame("添加订单");
		am.setResizable(false);
		am.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		am.setBounds(400,100,1000,700);
		Container amc=am.getContentPane();
		JPanel amp=new JPanel();
		amp.setLayout(null);
		amp.setBounds(0,0,1000,700);
		JLabel amdhl=new JLabel("单号:");
		amdhl.setBounds(10,10,40,20);
		amp.add(amdhl);
		JLabel amdh=new JLabel("O1610001");
		amdh.setBounds(45,10,80,20);
		amp.add(amdh);
		JLabel ammcl=new JLabel("名称:");
		ammcl.setBounds(380,10,40,20);
		amp.add(ammcl);
		JTextField ammc=new JTextField();
		ammc.setBounds(420,10,180,20);
		amp.add(ammc);
		JLabel amgdrl=new JLabel("跟单人:");
		amgdrl.setBounds(850,10,50,20);
		amp.add(amgdrl);
		JTextField amgdr=new JTextField();
		amgdr.setBounds(900,10,80,20);
		amp.add(amgdr);
		JTable amt=amt();
		JScrollPane amjsp=new JScrollPane();
		amjsp.setViewportView(amt);
		amjsp.setBounds(10,40,975,600);
		amp.add(amjsp);
		amc.add(amp);
		JButton amaddsp=new JButton("添加商品");
		amaddsp.setBounds(10,645,100,20);
		amp.add(amaddsp);
		JButton amaddb=new JButton("添加");
		amaddb.setBounds(460,645,80,20);
		amp.add(amaddb);
		//==============================================商品面板=====================================
		JFrame sp=new JFrame("商品");
		sp.setResizable(false);
		Container spc=sp.getContentPane();
		spc.setLayout(null);
		sp.setBounds(760,10,500,700);
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JTextField spt=new JTextField();
		spt.setBounds(10,10,120,20);
		JPanel spp=new JPanel();
		spp.add(spt);
		spp.setLayout(null);
		spp.setBounds(0,0,500,700);
		JTable sptable=spt();
		JScrollPane spjsp=new JScrollPane();
		spjsp.setBounds(10,40,470,620);
		spjsp.setViewportView(sptable);
		spp.add(spjsp);
		spc.add(spp);
		//==============================================添加订单监听==================================
		addorder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				am.setVisible(true);
			}
		});
		//===============================================添加添加商品监听==============================
		amaddsp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sp.setVisible(true);
			}
		});
	}
	public static void main(String[] args){
		new Dorder();
	}
}
//================================================详细详情判断===========================================
class ctcr extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	public Component getTableCellRendererComponent
        (JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent (table,value,isSelected,hasFocus,row,column);
        setHorizontalAlignment(JLabel.CENTER);
        Color foreground, background;   
        if(isSelected){
        	int r=table.getSelectedRow();
        	int c=table.getSelectedColumn();
        	if(r==row&&c==column){
        		foreground = Color.black;   
                background = Color.WHITE;  
        	}else{
        		foreground = Color.black;   
                background = Color.LIGHT_GRAY; 
        	}
            
        }else{     
            foreground = Color.black;   
            background = Color.WHITE;    
        }
        cell.setForeground(foreground);   
        cell.setBackground(background);  
        if(column==10){
        	String n=table.getValueAt(row,10).toString().trim();
        	if(n.length()==0){
        		
        	}else{
        		int c=Integer.parseInt(n);
        		if(c>0){
        			cell.setBackground(Color.RED);
        		}
        	}
        }
        return cell;
    }
}
//=========================================================订单总览判断==================================
class zmtcr extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	public Component getTableCellRendererComponent
        (JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent (table,value,isSelected,hasFocus,row,column);
        setHorizontalAlignment(JLabel.CENTER);
        Color foreground, background;   
        if(isSelected){
        	int r=table.getSelectedRow();
        	int c=table.getSelectedColumn();
        	if(r==row&&c==column){
        		foreground = Color.black;   
                background = Color.WHITE;  
        	}else{
        		foreground = Color.black;   
                background = Color.LIGHT_GRAY; 
        	}
            
        }else{     
            foreground = Color.black;   
            background = Color.WHITE;    
        }
        cell.setForeground(foreground);   
        cell.setBackground(background);  
        if(column==3){
        	String n=table.getValueAt(row,column).toString().trim();
        	if(n.length()==0){
        		
        	}else{
        		if(n.equals("差")){
        			cell.setBackground(Color.RED);
        		}else{
        			
        		}
        	}
        }
        return cell;
    }
}


