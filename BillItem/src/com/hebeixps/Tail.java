package com.hebeixps;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Tail extends ControlExcel {
private Component jPanel;

public void tailSheet(File parentFile)throws BiffException, IOException, WriteException{
	//FileInputStream in = new FileInputStream(parentFile);
	 Workbook  parentWorkbook=Workbook.getWorkbook(parentFile);	 
	 File tempFile=new File("d:/Table/tempFile.xls");
	 tempFile.createNewFile();
	 WritableWorkbook childWorkbook = Workbook.createWorkbook(tempFile, parentWorkbook);
	 WritableSheet childSheet = childWorkbook.getSheet(0);
	 int parentRowNum= parentWorkbook.getSheet(0).getRows();
	 int rowNum = parentRowNum+1;
	 Cell check = childSheet.getCell(0,parentRowNum-1);//��õ�Ԫ����//excel ����ǰ�����ں�
	 if(check.getContents().equals("��˾�绰:")){//�����ظ��ӱ�β
		JOptionPane.showMessageDialog(jPanel,"��¼�Ѿ�ȫ���洢���벻Ҫ�ظ�������","���棡",JOptionPane.WARNING_MESSAGE);
	     childWorkbook.close();
	     parentWorkbook.close();	 	 
	     tempFile.delete();
	 }
	 else{ 
	 WritableFont subtitleFontStyle = new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD);//�ӱ����������ã�����12�żӴּӺ�
     WritableCellFormat subtitleCellStyle= new WritableCellFormat(subtitleFontStyle);//�ӱ��ⵥԪ���ʽ����
     subtitleCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
     subtitleCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
     WritableFont recordFontStyle = new WritableFont(WritableFont.createFont("����"),11,WritableFont.BOLD);//�˵���¼�������ã�����12�żӴּӺ�
     WritableCellFormat recordCellStyle= new WritableCellFormat(recordFontStyle);//�˵���¼��Ԫ���ʽ����
     recordCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
     recordCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
	 
    //�ϼ�
     double sumNum=0.0;
     double sumMoney=0.0;
     Label total = new Label(0,parentRowNum,"  �ϼƣ�",subtitleCellStyle);// ��Ϊ�ؼ���2���ո�,�����ñ߿���ʾ
     childSheet.addCell(total);
     childSheet.mergeCells(5, parentRowNum, 6, parentRowNum);//��������ʾ�߿򣬾Ϳ�������
     for(int i=3;i<parentRowNum;i++){
    	 Cell c = childSheet.getCell(5,i);//��õ�Ԫ����//excel ����ǰ�����ں�
    	 sumNum+=Double.parseDouble(c.getContents());
     }
     jxl.write.NumberFormat amountSty = new jxl.write.NumberFormat("######.####"); 
     jxl.write.WritableCellFormat amountStyle = new jxl.write.WritableCellFormat(amountSty);
     amountStyle.setAlignment(jxl.format.Alignment.LEFT);//��Ԫ���е�����ˮƽ�������
     amountStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ������� 
     jxl.write.Number totalRecord = new jxl.write.Number(5,parentRowNum,sumNum ,amountStyle); 
     childSheet.addCell(totalRecord);//��ʽ��������  
   
     childSheet.mergeCells(7, parentRowNum, 8, parentRowNum);
     for(int i=3;i<parentRowNum;i++){
    	 Cell b = childSheet.getCell(7,i);//��õ�Ԫ����//excel ����ǰ�����ں�
    	 sumMoney+=Double.parseDouble(b.getContents());
     }
     jxl.write.Number totalMoney = new jxl.write.Number(7,parentRowNum,sumMoney ,amountStyle); 
     childSheet.addCell(totalMoney);//��ʽ�������� 
     
     //��˾�绰
     Label telephone = new Label(0,rowNum,"��˾�绰:",subtitleCellStyle);// ��Ϊ�ؼ���2���ո�,�����ñ߿���ʾ
     childSheet.addCell(telephone);
     childSheet.mergeCells(1, rowNum, 3, rowNum);
     Label telephoneRecord = new Label(1, rowNum, Zhengyang.telephoneField.getText(),recordCellStyle); 
     childSheet.addCell(telephoneRecord);//�ı��ͣ��ַ���  
     
     //˾��
     Label driver = new Label(4,rowNum,"  ˾����",subtitleCellStyle);// ��Ϊ�ؼ���2���ո�,�����ñ߿���ʾ
     childSheet.addCell(driver);
     childSheet.mergeCells(5, rowNum, 6, rowNum);
     Label driverRecord = new Label(5, rowNum, Zhengyang.driverField.getText(),recordCellStyle); 
     childSheet.addCell(driverRecord);//�ı��ͣ��ַ���  
     
     //��Ʊ��
     Label drawer = new Label(7,rowNum,"��Ʊ�ˣ�",subtitleCellStyle);// ��Ϊ�ؼ���2���ո�,�����ñ߿���ʾ
     childSheet.addCell(drawer);
     Label drawerRecord = new Label(8, rowNum, Zhengyang.drawerField.getText(),recordCellStyle); 
     childSheet.addCell(drawerRecord);//�ı��ͣ��ַ���  	 
     
     childWorkbook.write();
     childWorkbook.close();
     parentWorkbook.close();
 	 
     parentFile.delete();
     tempFile.renameTo(parentFile);
	 }
}
}
