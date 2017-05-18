package data;

import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class AddReceiptVoucher {
	static private JFrame MFrame;
	static private JTable MTable;
	static private JComboBox<String> ReceiptMethod;
	static private JComboBox<String> LeftSubject;
	static private JLabel ReceiptNo;

	public AddReceiptVoucher() {
		MFrame = new JFrame();
		MFrame.setTitle("收款凭证");
		MFrame.setResizable(false);
		Container MFrameC = MFrame.getContentPane();
		MFrameC.setLayout(null);
		MFrame.setBounds(100, 100, 700, 500);
		MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LeftSubject=new JComboBox<String>();
		LeftSubject.addItem("库存现金");
		LeftSubject.addItem("银行存款");
		MFrameC.add(LeftSubject);
		MFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new AddReceiptVoucher();
	}
}
