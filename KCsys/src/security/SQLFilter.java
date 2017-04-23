package security;

import java.awt.Component;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class SQLFilter {
	static String reg = "(')|(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)"; 
	static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE); 
	public SQLFilter(Component n,String text,String user){
		if(sqlPattern.matcher(text).find()){
			JOptionPane.showMessageDialog(n, "系统监测到[ SQL注入信息 ]:"
					+ "\n[ "+text+" ];"
					+ "\n系统已冻结该账号[ "+user+" ]"
					+ "\n系统已收集[ SQL注入信息 ]及[ 安全管控所需信息 ]"
					+ "\n[ 解冻账号 ]请联系[ 天澜清洗设备有限公司 ]"
					+ "\n确认后系统自动退出...");
			System.exit(0);
		}
	}
}
