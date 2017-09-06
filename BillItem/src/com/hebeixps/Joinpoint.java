package com.hebeixps;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.Cell;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Joinpoint {

	public void  createJoin(File file,ArrayList<String> storeData){//�ļ��Ƕ���

		try{
			if(!file.exists()){//�ļ�������
				 //�����ļ�
				file.createNewFile();
				ControlExcel storeExcel=new ControlExcel();
				storeExcel.createExcel(file, storeData);
				///��ձ�������
				if(ControlExcel.checkException==0){
					Zhengyang.productNameField.setText("");//ͨ��������̬���������ʵ���޸��������еı���ֵ��
					Zhengyang.specificationField.setText("");
					Zhengyang.sliceField.setText("");
					Zhengyang.amountField.setText("");
					Zhengyang.priceField.setText("");
					Zhengyang.remarkField.setText("");
					Zhengyang.unitField.setText("");	
				}else{
					ControlExcel.checkException=0;
				}
			}	else {//�ļ��Ѿ����ڣ�׷������,ͨ�������ļ�����׷�ӡ�			
				Workbook parentWorkbook = Workbook.getWorkbook(file);
				 int parentRowNum= parentWorkbook.getSheet(0).getRows();
				Cell check = parentWorkbook.getSheet(0).getCell(0,parentRowNum-1);//��õ�Ԫ����//excel ����ǰ�����ں�
				 if(check.getContents().equals("��˾�绰:")){//׷�Ӽ�¼//ɾ�������У����������
					 int n =JOptionPane.showConfirmDialog(null, "��ȷ�Ż�Ҫ���Ѿ����ɵ����۵��ϼ������뵱ǰ�ļ�¼��?","ѯ��",JOptionPane.YES_NO_OPTION);//i=0/1
					if(n==0){
						WritableWorkbook newWorkbook = parentWorkbook.createWorkbook(file,parentWorkbook);
				        WritableSheet sheet=newWorkbook.getSheet(0);
				        sheet.removeRow(parentRowNum-1);
				        sheet.removeRow(parentRowNum-2);
				        newWorkbook.write();
				        newWorkbook.close();
						GridData  gridRecord=new GridData();
						gridRecord.gridData(file, storeData);	
						 if(GridData.checkExcep==0){
								Zhengyang.productNameField.setText("");//ͨ��������̬���������ʵ���޸��������еı���ֵ��
								Zhengyang.specificationField.setText("");
								Zhengyang.sliceField.setText("");
								Zhengyang.amountField.setText("");
								Zhengyang.priceField.setText("");
								Zhengyang.remarkField.setText("");
								Zhengyang.unitField.setText("");
						 }else{
						GridData.checkExcep=0;
					}
					}else{
						Zhengyang.productNameField.setText("");//ͨ��������̬���������ʵ���޸��������еı���ֵ��
						Zhengyang.specificationField.setText("");
						Zhengyang.sliceField.setText("");
						Zhengyang.amountField.setText("");
						Zhengyang.priceField.setText("");
						Zhengyang.remarkField.setText("");
						Zhengyang.unitField.setText("");	
					}				 
				 }else{
						GridData  gridRecord=new GridData();
						gridRecord.gridData(file, storeData);	
						 if(GridData.checkExcep==0){
								Zhengyang.productNameField.setText("");//ͨ��������̬���������ʵ���޸��������еı���ֵ��
								Zhengyang.specificationField.setText("");
								Zhengyang.sliceField.setText("");
								Zhengyang.amountField.setText("");
								Zhengyang.priceField.setText("");
								Zhengyang.remarkField.setText("");
								Zhengyang.unitField.setText("");
						 }else{
						GridData.checkExcep=0;
					}
					}	
				 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
}
