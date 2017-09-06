package com.hebeixps;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ControlExcel {
    public static int  checkException=0;
    private static int rowRecord=3;//ֻ�Ǽ�¼ֵ�����仯�����ⲻ�����仯��

	 public void  createExcel(File xlsfile,ArrayList<String> dataList)throws WriteException,IOException {

        //������������object��
        WritableWorkbook workbook = Workbook.createWorkbook(xlsfile);
        //������
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);      
        //������ͷ//
        sheet.mergeCells(0, 0, 8, 0);//��Ӻϲ���Ԫ�񣬵�һ����������ʼ�У��ڶ��������� ��ʼ�У���������������ֹ�У����ĸ���������ֹ��
        WritableFont headlineFontStyle = new WritableFont(WritableFont.createFont("����"),18,WritableFont.BOLD);//�������������壺���壬��С18����ɫ�Ӵ�
        WritableCellFormat headlineCellStyle = new WritableCellFormat(headlineFontStyle);//����һ����Ԫ���ʽ���ƶ���,�������ⵥԪ���ʽ�趨
        headlineCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        headlineCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        //ʵ���ϱ�ǩ�������˼���ǹ�����ĵ�Ԫ��,
        //Label label = new Label(col, row, title);���������ֱ��ʾcol+1�У�row+1�У�����������title�� 
        Label headline = new Label(0,0,"�ӱ�ʯ��ׯ�������²������޹�˾���۵�",headlineCellStyle);
        sheet.setRowView(0, 500, false);//���õ�һ�еĸ߶�
        sheet.addCell(headline);//����ǩ���뵽��������
        
        //����Ҫ��ʾ�ľ�������//
        //�����ӱ����¼
        WritableFont subtitleFontStyle = new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD);//�ӱ����������ã�����12�żӴּӺ�
        WritableCellFormat subtitleCellStyle= new WritableCellFormat(subtitleFontStyle);//�ӱ��ⵥԪ���ʽ����
        subtitleCellStyle.setAlignment(jxl.format.Alignment.LEFT);//��Ԫ���е�����ˮƽ�������
        subtitleCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        WritableFont recordFontStyle = new WritableFont(WritableFont.createFont("����"),11,WritableFont.BOLD);//�˵���¼�������ã�����12�żӴּӺ�
        WritableCellFormat recordCellStyle= new WritableCellFormat(recordFontStyle);//�˵���¼��Ԫ���ʽ����
        recordCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        recordCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        //���ڼ�¼
        WritableFont subtitleFontStyle2 = new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD);//�ӱ����������ã�����12�żӴּӺ�
        WritableCellFormat subtitleCellStyle2= new WritableCellFormat(subtitleFontStyle2);//�ӱ��ⵥԪ���ʽ����
        subtitleCellStyle2.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        subtitleCellStyle2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        subtitleCellStyle2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        WritableFont recordFontStyle2 = new WritableFont(WritableFont.createFont("����"),11,WritableFont.BOLD);//�˵���¼�������ã�����12�żӴּӺ�
        WritableCellFormat recordCellStyle2= new WritableCellFormat(recordFontStyle2);//�˵���¼��Ԫ���ʽ����
        recordCellStyle2.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        recordCellStyle2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        recordCellStyle2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        
        try{
        //����-�ͻ�
        Label client = new Label(0,1,"  �ͻ���",subtitleCellStyle);// ��Ϊ�ؼ���2���ո�,�����ñ߿���ʾ
        sheet.addCell(client);
        sheet.mergeCells(1, 1, 3, 1);//��Ԫ��ϲ�ǰ�󣬵�Ԫ�����겻�����仯���ϲ��ĵ�Ԫ������������ĵ�һ����Ԫ������
        Label clientRecord = new Label(1, 1, dataList.get(0),recordCellStyle); 
        sheet.addCell(clientRecord);//�ı��ͣ��ַ���
              
        //����-����
        Label date = new Label(4,1,"  ���ڣ�",subtitleCellStyle);
        sheet.addCell(date);
        sheet.mergeCells(5,1,6, 1);
        SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd"); 
        String newdate = dateStyle.format(new Date()); 
        Label dateRecord = new Label(5, 1, newdate,recordCellStyle); 
        sheet.addCell(dateRecord);//������
        
        //����-����Ա
        Label saler = new Label(7,1,"����Ա��",subtitleCellStyle);
        sheet.addCell(saler);
        Label salerRecord = new Label(8, 1, dataList.get(3),recordCellStyle); 
        sheet.addCell(salerRecord);//�ı��ͣ��ַ���
        
       
//       //��������// 
        //����-��Ʒ����
        Label productName = new Label(0,2,"��Ʒ����",subtitleCellStyle2);
        sheet.addCell(productName);
        Label productNameRecord = new Label(0, rowRecord,  dataList.get(4),recordCellStyle2); 
        sheet.addCell(productNameRecord);//�ı��ͣ��ַ���
        
        //����-���
        sheet.mergeCells(1, 2, 2, 2);
        Label specification = new Label(1,2,"���",subtitleCellStyle2);
        sheet.addCell(specification);
        sheet.mergeCells(1, rowRecord, 2, rowRecord);
        Label specificationRecord = new Label(1, rowRecord, dataList.get(5),recordCellStyle2); 
        sheet.addCell(specificationRecord);//�ı��ͣ��ַ���  
        
        //����-��λ
        Label unit = new Label(3,2,"��λ",subtitleCellStyle2);
        sheet.addCell(unit);
        Label unitRecord = new Label(3,rowRecord, dataList.get(10),recordCellStyle2); 
        sheet.addCell(unitRecord);//�ı��ͣ��ַ���
        
        //����-��/Ƭ
        Label slice = new Label(4,2,"��/Ƭ",subtitleCellStyle2);
        sheet.addCell(slice);
        jxl.write.Number sliceRecord = new jxl.write.Number(4,rowRecord, Integer.parseInt(dataList.get(6).trim()),recordCellStyle2); 
        sheet.addCell(sliceRecord);//����
        
        //����-����
        Label amount = new Label(5,2,"����(m3)",subtitleCellStyle2);
        sheet.addCell(amount);
        jxl.write.NumberFormat amountSty = new jxl.write.NumberFormat("######.####"); 
        jxl.write.WritableCellFormat amountStyle = new jxl.write.WritableCellFormat(amountSty);
        amountStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        amountStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        amountStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
        jxl.write.Number amountRecord = new jxl.write.Number(5,rowRecord,Double.parseDouble(dataList.get(7).trim()) ,amountStyle); 
        sheet.addCell(amountRecord);//��ʽ��������  
       
        //����-����
        Label price = new Label(6,2,"����(Ԫ)",subtitleCellStyle2);
        sheet.addCell(price);
        jxl.write.NumberFormat priceSty = new jxl.write.NumberFormat("#####.##"); 
        jxl.write.WritableCellFormat priceStyle = new jxl.write.WritableCellFormat(priceSty);
        priceStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        priceStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        priceStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
        jxl.write.Number priceRecord = new jxl.write.Number(6,rowRecord, Double.parseDouble(dataList.get(8).trim()),priceStyle); 
        sheet.addCell(priceRecord);//��ʽ��������  
        
        //����-�ܼ�
        Label money = new Label(7,2,"�ܼ�(Ԫ)",subtitleCellStyle2);
        sheet.addCell(money);
        jxl.write.NumberFormat moneySty = new jxl.write.NumberFormat("#######.##"); 
        jxl.write.WritableCellFormat moneyStyle = new jxl.write.WritableCellFormat(moneySty);
        moneyStyle.setAlignment(jxl.format.Alignment.CENTRE);//��Ԫ���е�����ˮƽ�������
        moneyStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//��Ԫ������ݴ�ֱ�������
        moneyStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
        double totalMoney=Double.parseDouble(dataList.get(7).trim())*Double.parseDouble( dataList.get(8).trim());
        jxl.write.Number moneyRecord = new jxl.write.Number(7,rowRecord, totalMoney,moneyStyle); 
        sheet.addCell(moneyRecord);//��ʽ��������    
       
        //����-��ע
        Label remark = new Label(8,2,"��ע",subtitleCellStyle2);
        sheet.addCell(remark);
        Label remarkRecord = new Label(8, rowRecord, dataList.get(9),recordCellStyle2); 
        sheet.addCell(remarkRecord);//�ı��ͣ��ַ���     
        
        workbook.write();
        workbook.close();
        }catch(Exception e){
        	checkException=1;
			e.printStackTrace();
			workbook.close();
			xlsfile.delete();//��һ�ξͳ���ֱ��ɾ�������Ч�����ļ��Ϳɣ�
			JOptionPane.showMessageDialog(null, "���벻�Ϸ������������룡.", "����",JOptionPane.ERROR_MESSAGE);  
		}

        
	 }
    }














