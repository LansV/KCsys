package tool;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileSystemView;

import jxl.Workbook;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CreateExcel {
	static Image img=null;
	public static void exportExcel(JFrame frame,String filename,String[][] arr){
		FileSystemView fsv = FileSystemView.getFileSystemView(); 	
		FilterFileChooser jfch=new FilterFileChooser();
		jfch.setCurrentDirectory(fsv.getHomeDirectory());
		jfch.setSelectedFile(new File(filename));
		int chooserStatus=jfch.showDialog(frame, "保存");
		if(chooserStatus==FilterFileChooser.APPROVE_OPTION){
			File fi=jfch.getSelectedFile();
			String f = fi.getAbsolutePath();
			JDialog jf=new JDialog(frame, "导出进度");
			try{
		 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
		 	    jf.setIconImage(img);
			}catch(Exception e){
				//JOptionPane.showMessageDialog(frame,"获取系统图标错误");
			}
			frame.setEnabled(false);
			jf.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			jf.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					frame.setEnabled(true);
					jf.dispose();
				}
			});
			jf.setResizable(false);
			jf.setBounds(600,300,250,80);
			Container jfc=jf.getContentPane();
			jfc.setLayout(null);
			JProgressBar jpb=new JProgressBar();
			jpb.setStringPainted(true);
			jpb.setBounds(20,10,200,25);
			jfc.add(jpb);
			jf.setVisible(true);
			try{
				jpb.setValue(0);
				jpb.setString("创建Excel中...");
				WritableWorkbook book = Workbook.createWorkbook(new File(f));
				// 生成名为“第一页”的工作表，参数0表示这是第一页
				WritableSheet sheet = book.createSheet("PriceList", 0);
				jpb.setValue(20);
				jpb.setString("写入数据中...");
				// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
				WritableFont titlefont= new WritableFont(WritableFont.ARIAL,20,WritableFont.BOLD);
				WritableCellFormat titleformat=new WritableCellFormat(titlefont); 
				titleformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//把垂直对齐方式指定为居中 
				titleformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				Label title = new Label(0, 0, "天澜清洗设备有限公司报价单",titleformat);
				// 将定义好的单元格添加到工作表中
				sheet.addCell(title);
				sheet.setRowView(0,700); 
				WritableFont contentfont= new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD);
				WritableCellFormat contentformat=new WritableCellFormat(contentfont); 
				contentformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//把垂直对齐方式指定为居中 
				contentformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				contentformat.setWrap(true);
				contentformat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
				Label tableheader1=new Label(0,1,"序号",contentformat);
				Label tableheader2=new Label(1,1,"商品编号",contentformat);
				Label tableheader3=new Label(2,1,"商品名称",contentformat);
				Label tableheader4=new Label(3,1,"单位",contentformat);
				Label tableheader5=new Label(4,1,"折扣",contentformat);
				Label tableheader6=new Label(5,1,"单价",contentformat);
				Label tableheader7=new Label(6,1,"数量",contentformat);
				Label tableheader8=new Label(7,1,"金额",contentformat);
				Label tableheader9=new Label(8,1,"备注",contentformat);
				sheet.addCell(tableheader1);sheet.addCell(tableheader2);sheet.addCell(tableheader3);sheet.addCell(tableheader4);
				sheet.addCell(tableheader5);sheet.addCell(tableheader6);sheet.addCell(tableheader7);sheet.addCell(tableheader8);
				sheet.addCell(tableheader9);
				sheet.setColumnView(0,7);
				sheet.setColumnView(1,12);
				sheet.setColumnView(2,35);
				sheet.setColumnView(3,7);
				sheet.setColumnView(4,7);
				sheet.setColumnView(5,12);
				sheet.setColumnView(6,7);
				sheet.setColumnView(7,15);
				sheet.setColumnView(8,20);
				int row=arr.length;
				for(int i=0;i<row;i++){
					Number data1=new Number(0,i+2,Integer.parseInt(arr[i][0]),contentformat);
					Number data2=new Number(1,i+2,Integer.parseInt(arr[i][1]),contentformat);
					Label data3=new Label(2,i+2,arr[i][2],contentformat);Label data4=new Label(3,i+2,arr[i][3],contentformat);
					Number data5 = null;
					Label error=new Label(4,i+2,"",contentformat);
					if(arr[i][4].length()!=0){
						data5=new Number(4,i+2,Double.parseDouble(arr[i][4]),contentformat);
					}
					Number data6=new Number(5,i+2,Double.parseDouble(arr[i][5]),contentformat);
					Number data7=new Number(6,i+2,Integer.parseInt(arr[i][6]),contentformat);
					Number data8=new Number(7,i+2,Double.parseDouble(arr[i][7]),contentformat);
					Label data9=new Label(8,i+2,arr[i][8],contentformat);
					sheet.addCell(data1);sheet.addCell(data2);sheet.addCell(data3);sheet.addCell(data4);
					if(data5!=null){
						sheet.addCell(data5);
					}else{
						sheet.addCell(error);
					}
					sheet.addCell(data6);sheet.addCell(data7);sheet.addCell(data8);
					sheet.addCell(data9);
				}
				for(int i=1;i<=row+3;i++){
					sheet.setRowView(i,400);
				}
				Label tablefooter=new Label(0,row+2,"合計",contentformat);
				sheet.addCell(tablefooter);
				sheet.addCell(new Formula(6,row+2,"SUM(G3:G"+Integer.toString(row+2)+")",contentformat));
				sheet.addCell(new Formula(7,row+2,"SUM(H3:H"+Integer.toString(row+2)+")",contentformat));
				sheet.addCell(new Label(8,row+2,"",contentformat));
				Label notice=new Label(0,row+3,"注意事项：");
				Label notice1=new Label(0,row+4," 1、以上报价不含税，如需发票另加6％费用。");
				Label notice2=new Label(0,row+5," 2、所有设备按标准配置不包括安装所需配件，请自行购买。");
				Label notice3=new Label(0,row+6," 3、水、电、气管路安装由客户自行安装到指定位置。");
				Label notice4=new Label(0,row+7," 4、上门安装由客户提供食宿。");
				Label notice5=new Label(0,row+8," 5、订单确定需付30％定金，货到付60%，安装调试完毕付10%。");
				Label notice6=new Label(0,row+9," 6、本公司只负责高压清洗机的安装和调试，其他工程如高压鼓架焊接，鼓架焊接，水管布列等需另行收费。");
				Label notice7=new Label(0,row+10," 7、具体执行见购销合同");
				sheet.addCell(notice);sheet.addCell(notice1);sheet.addCell(notice2);sheet.addCell(notice3);
				sheet.addCell(notice4);sheet.addCell(notice5);sheet.addCell(notice6);sheet.addCell(notice7);
				sheet.mergeCells(0, 0, 8, 0);
				sheet.mergeCells(0, row+2, 5, row+2);
				jpb.setValue(80);
				jpb.setString("准备完成中...");
				// 写入数据并关闭文件
				book.write();
				book.close();
				jpb.setValue(100);
				jpb.setString("导出成功");
			}  
			catch(Exception e){
				jpb.setValue(0);
				jpb.setBackground(Color.RED);
				jpb.setForeground(Color.BLACK);
				jpb.setString("导出错误!");
			} 
		}
	}
	public static void main(String[] args){
		JFrame j=new JFrame();
		String[][] arr={{"1","100120","测试","台","9.2","10.2","2","18.768",""},{"2","100120","测试","台","9.2","10.2","2","18.768",""}};
		CreateExcel.exportExcel(j, "text.xls",arr);
	}
}
