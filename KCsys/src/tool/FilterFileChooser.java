package tool;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FilterFileChooser extends JFileChooser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilterFileChooser(){
		super();
	}
    public void approveSelection() {
    	File file=getSelectedFile();
    	if(file.exists()){
    		JOptionPane.showMessageDialog(getParent(),"文件已存在");
    		return;
    	}
    	super.approveSelection();
    }
}
