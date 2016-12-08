package data;

import javax.swing.JPanel;


public class rKCx extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public rKCx(){
		super();
		KCTable t=new KCTable();
		setLayout(null);
		this.add(t.jsp());
		this.setBounds(10,150,600,350);
	}
}
