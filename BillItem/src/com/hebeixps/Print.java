package com.hebeixps;
//import org.sqlite.util.StringUtils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
public class Print { 
		public static void printExcel(String filePath) {
				//String PRINT_NAME="pdfFactory Pro";
			String PRINT_NAME="HP LaserJet 1020";
			ComThread.InitSTA();
			ActiveXComponent xl=new ActiveXComponent("Excel.Application");//ʹ��Jacob����ActiveX��������
				try{
				Dispatch.put(xl, "visible", new Variant(true));//����Visible�ǿ����ĵ��򿪺��ǿɼ����ǲ��ɼ������Ǿ�Ĭ��ӡ����ô��������������Ϊfalse�ͺ���
				Dispatch workbooks =xl.getProperty("Workbooks").toDispatch();
				Dispatch excel=Dispatch.call(workbooks,"Open",filePath).toDispatch();//���ĵ�

			    //xl.setProperty("ActivePrinter", new Variant("HP LaserJet 1020 local on LPT1:"));
				xl.setProperty("ActivePrinter", new Variant(PRINT_NAME));
				//���������ӡ
//				Dispatch currentSheet = Dispatch.get(excel, "ActiveSheet").toDispatch();
//				Dispatch pageSetup = Dispatch.get(currentSheet, "PageSetup").toDispatch();
//				Dispatch.put(pageSetup, "Orientation", new Variant(1));
//				Dispatch.callN(excel,"PrintOut",new Object[]{Variant.DEFAULT,Variant.DEFAULT, new Integer(1),
//					   new Boolean(false),PRINT_NAME,new Boolean(true),Variant.DEFAULT, ""});
				Dispatch.call(excel,"Close",new Variant(false));
			   Dispatch.get(excel,"PrintOut");//��ʼ��ӡ
				
				}
				catch(Exception e){
					e.printStackTrace();
				//	ComThread.Release();//�ͷ���Դ
				}
				 finally {
					ComThread.Release();//�ͷ���Դ
					xl.invoke("Quit", new Variant[] {});//�ر�excel�ĵ�
					xl=null;
				}     
			}
}
