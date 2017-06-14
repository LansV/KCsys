package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lock {
	static FileOutputStream out = null;
	public static void deleteAllLock(JFrame jop,File oldPath){
		if(oldPath.isDirectory()){
			 File[] files = oldPath.listFiles();
	           for (File file : files) {
	             deleteAllLock(jop,file);
	           }
		}else{
			oldPath.delete();
		}
	}
	public static boolean SingleLock(JFrame JOP, String filename) {
		File file = new File(filename);
		if (file.exists() == true) {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "get lockfile error");
				return false;
			}
			byte b1[] = new byte[1024];
			int i = 0;
			try {
				i = in.read(b1);
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "read lockfile error");
				return false;
			}
			String s = new String(b1, 0, i);
			if (s.equals("lock")) {
				JOptionPane.showMessageDialog(JOP, "请勿重复打开界面");
			} else {
				try {
					out = new FileOutputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(JOP, "get lockfile error");
					return false;
				}
				byte b[] = "lock".getBytes();
				try {
					out.write(b);
					out.close();
					return true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(JOP, "write lockfile error");
					return false;
				}
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "create lockfile error");
				return false;
			}
			try {
				out = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "get lockfile error");
				return false;
			}
			byte b[] = "lock".getBytes();
			try {
				out.write(b);
				out.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "write lockfile error");
				return false;
			}
		}
		return false;
	}

	public static boolean SingleUnLock(JFrame JOP, String filename) {
		File file = new File(filename);
		if (file.exists() == true) {
			try {
				out = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "get lockfile error");
				return false;
			}
			byte b[] = "unlock".getBytes();
			try {
				out.write(b);
				out.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "write lockfile error");
				return false;
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "create lockfile error");
				return false;
			}
			try {
				out = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "get lockfile error");
				return false;
			}
			byte b[] = "unlock".getBytes();
			try {
				out.write(b);
				out.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(JOP, "write lockfile error");
				return false;
			}
		}
	}
}
