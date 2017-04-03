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
		int chooserStatus=jfch.showDialog(frame, "����");
		if(chooserStatus==FilterFileChooser.APPROVE_OPTION){
			File fi=jfch.getSelectedFile();
			String f = fi.getAbsolutePath();
			JDialog jf=new JDialog(frame, "��������");
			try{
		 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
		 	    jf.setIconImage(img);
			}catch(Exception e){
				//JOptionPane.showMessageDialog(frame,"��ȡϵͳͼ�����");
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
				jpb.setString("����Excel��...");
				WritableWorkbook book = Workbook.createWorkbook(new File(f));
				// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
				WritableSheet sheet = book.createSheet("PriceList", 0);
				jpb.setValue(20);
				jpb.setString("д��������...");
				// ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
				WritableFont titlefont= new WritableFont(WritableFont.ARIAL,20,WritableFont.BOLD);
				WritableCellFormat titleformat=new WritableCellFormat(titlefont); 
				titleformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//�Ѵ�ֱ���뷽ʽָ��Ϊ���� 
				titleformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				Label title = new Label(0, 0, "������ϴ�豸���޹�˾���۵�",titleformat);
				// ������õĵ�Ԫ����ӵ���������
				sheet.addCell(title);
				sheet.setRowView(0,700); 
				WritableFont contentfont= new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD);
				WritableCellFormat contentformat=new WritableCellFormat(contentfont); 
				contentformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//�Ѵ�ֱ���뷽ʽָ��Ϊ���� 
				contentformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				contentformat.setWrap(true);
				contentformat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
				Label tableheader1=new Label(0,1,"���",contentformat);
				Label tableheader2=new Label(1,1,"��Ʒ���",contentformat);
				Label tableheader3=new Label(2,1,"��Ʒ����",contentformat);
				Label tableheader4=new Label(3,1,"��λ",contentformat);
				Label tableheader5=new Label(4,1,"�ۿ�",contentformat);
				Label tableheader6=new Label(5,1,"����",contentformat);
				Label tableheader7=new Label(6,1,"����",contentformat);
				Label tableheader8=new Label(7,1,"���",contentformat);
				Label tableheader9=new Label(8,1,"��ע",contentformat);
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
				Label tablefooter=new Label(0,row+2,"��Ӌ",contentformat);
				sheet.addCell(tablefooter);
				sheet.addCell(new Formula(6,row+2,"SUM(G3:G"+Integer.toString(row+2)+")",contentformat));
				sheet.addCell(new Formula(7,row+2,"SUM(H3:H"+Integer.toString(row+2)+")",contentformat));
				sheet.addCell(new Label(8,row+2,"",contentformat));
				Label notice=new Label(0,row+3,"ע�����");
				Label notice1=new Label(0,row+4," 1�����ϱ��۲���˰�����跢Ʊ���6�����á�");
				Label notice2=new Label(0,row+5," 2�������豸����׼���ò�������װ��������������й���");
				Label notice3=new Label(0,row+6," 3��ˮ���硢����·��װ�ɿͻ����а�װ��ָ��λ�á�");
				Label notice4=new Label(0,row+7," 4�����Ű�װ�ɿͻ��ṩʳ�ޡ�");
				Label notice5=new Label(0,row+8," 5������ȷ���踶30�����𣬻�����60%����װ������ϸ�10%��");
				Label notice6=new Label(0,row+9," 6������˾ֻ�����ѹ��ϴ���İ�װ�͵��ԣ������������ѹ�ļܺ��ӣ��ļܺ��ӣ�ˮ�ܲ��е��������շѡ�");
				Label notice7=new Label(0,row+10," 7������ִ�м�������ͬ");
				sheet.addCell(notice);sheet.addCell(notice1);sheet.addCell(notice2);sheet.addCell(notice3);
				sheet.addCell(notice4);sheet.addCell(notice5);sheet.addCell(notice6);sheet.addCell(notice7);
				sheet.mergeCells(0, 0, 8, 0);
				sheet.mergeCells(0, row+2, 5, row+2);
				jpb.setValue(80);
				jpb.setString("׼�������...");
				// д�����ݲ��ر��ļ�
				book.write();
				book.close();
				jpb.setValue(100);
				jpb.setString("�����ɹ�");
			}  
			catch(Exception e){
				jpb.setValue(0);
				jpb.setBackground(Color.RED);
				jpb.setForeground(Color.BLACK);
				jpb.setString("��������!");
			} 
		}
	}
	public static void main(String[] args){
		JFrame j=new JFrame();
		String[][] arr={{"1","100120","����","̨","9.2","10.2","2","18.768",""},{"2","100120","����","̨","9.2","10.2","2","18.768",""}};
		CreateExcel.exportExcel(j, "text.xls",arr);
	}
}
