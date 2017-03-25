package security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class ReturnDate {
	public static String netDate() {
		String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
		String s = null ;
		try {
			URL url = new URL(webUrl4);// 取得资源对象
			URLConnection uc = url.openConnection();// 生成连接对象
			uc.connect();// 发出连接
			long ld = uc.getDate();// 读取网站日期时间
			Date date = new Date(ld);// 转换为标准时间对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// 输出北京时间
			s= sdf.format(date);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "URL格式错误，请联系天澜公司处理BUG");
			System.exit(0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "请检查网络，确保网络畅通");
			System.exit(0);
		}
		return s;
	}
	
}
