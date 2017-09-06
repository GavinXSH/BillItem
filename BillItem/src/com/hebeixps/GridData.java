package com.hebeixps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class GridData extends ControlExcel{
	public static int checkExcep=0;
 public void gridData(File file,ArrayList<String> dataList) throws BiffException, IOException, WriteException{
	 //FileInputStream in = new FileInputStream(file);
	 Workbook  parentWorkbook=Workbook.getWorkbook(file);	 
	 File tempFile=new File("d:/Table/tempFile.xls");
	 tempFile.createNewFile();
	 WritableWorkbook childWorkbook = Workbook.createWorkbook(tempFile, parentWorkbook);
	 WritableSheet childSheet = childWorkbook.getSheet(0);
	 int parentRowNum= parentWorkbook.getSheet(0).getRows();
	 int rowNum = parentRowNum;
	 try{
	 //���ڼ�¼
     WritableFont recordFontStyle2 = new WritableFont(WritableFont.createFont("����"),11,WritableFont.BOLD);//�˵���¼�������ã�����12�żӴּӺ�
     WritableCellFormat recordCellStyle2= new WritableCellFormat(recordFontStyle2);//�˵���¼��Ԫ���ʽ����
     recordCellStyle2.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
     recordCellStyle2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
     recordCellStyle2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
     
     //��������//   
    //����-��Ʒ����
    Label productNameRecord = new Label(0, rowNum, dataList.get(4),recordCellStyle2); 
    childSheet.addCell(productNameRecord);//�ı��ͣ��ַ���
    
    //����-���
    childSheet.mergeCells(1, rowNum, 2, rowNum);
    Label specificationRecord = new Label(1, rowNum, dataList.get(5),recordCellStyle2); 
    childSheet.addCell(specificationRecord);//�ı��ͣ��ַ���  
    
    //����-��λ
    Label unitRecord = new Label(3,rowNum, (String)dataList.get(10),recordCellStyle2);
    childSheet.addCell(unitRecord);//�ı��ͣ��ַ���
    
    //����-��/Ƭ
    jxl.write.Number sliceRecord = new jxl.write.Number(4,rowNum, Integer.parseInt(dataList.get(6).trim()),recordCellStyle2); 
    childSheet.addCell(sliceRecord);//����
    
    //����-����
    jxl.write.NumberFormat amountSty = new jxl.write.NumberFormat("######.####"); 
    jxl.write.WritableCellFormat amountStyle = new jxl.write.WritableCellFormat(amountSty);
    amountStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
    amountStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
    amountStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
    jxl.write.Number amountRecord = new jxl.write.Number(5,rowNum,Double.parseDouble(dataList.get(7).trim()) ,amountStyle); 
    childSheet.addCell(amountRecord);//��ʽ��������  
    //����-����
    jxl.write.NumberFormat priceSty = new jxl.write.NumberFormat("#####.##"); 
    jxl.write.WritableCellFormat priceStyle = new jxl.write.WritableCellFormat(priceSty);
    priceStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
    priceStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
    priceStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
    jxl.write.Number priceRecord = new jxl.write.Number(6,rowNum, Double.parseDouble(dataList.get(8).trim()),priceStyle); 
    childSheet.addCell(priceRecord);//��ʽ��������  
    
    //����-�ܼ�
    jxl.write.NumberFormat moneySty = new jxl.write.NumberFormat("#######.##"); 
    jxl.write.WritableCellFormat moneyStyle = new jxl.write.WritableCellFormat(moneySty);
    moneyStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
    moneyStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
    moneyStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
    double totalMoney=Double.parseDouble(dataList.get(7).trim())*Double.parseDouble( dataList.get(8).trim());
    jxl.write.Number moneyRecord = new jxl.write.Number(7,rowNum, totalMoney,moneyStyle); 
    childSheet.addCell(moneyRecord);//��ʽ��������    
   
    //����-��ע
    childSheet.mergeCells(12, rowNum, 13, rowNum);
    Label remarkRecord = new Label(8, rowNum, dataList.get(9),recordCellStyle2);
    childSheet.addCell(remarkRecord);//�ı��ͣ��ַ���  
    
     }catch(Exception e){
    	     checkExcep=1;
			e.printStackTrace();
			tempFile.delete();//�ָ�ԭ̬��
			JOptionPane.showMessageDialog(null, "���벻�Ϸ������������룡.", "����",JOptionPane.ERROR_MESSAGE);  
		}
	    childWorkbook.write();
	    childWorkbook.close();
	    parentWorkbook.close();
		 

	    if(tempFile.exists()){
		file.delete();
	    tempFile.renameTo(file);
	    }
	 
	 
 }
	
}
