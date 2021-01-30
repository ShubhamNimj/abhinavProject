package org.abhinav.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;


public class CreateUOBTDS
{
	private String ApplicationFolderPath;
	private String ApplicationFontFileLocation;
	private String ApplicationFolder;
	private String AgentID ;
	

	public CreateUOBTDS(String ApplicationFolderPath,String ApplicationFontFileLocation,String ApplicationFolder,String AgentID)
	{
		this.ApplicationFolderPath = ApplicationFolderPath;
		this.ApplicationFontFileLocation = ApplicationFontFileLocation;				
		this.ApplicationFolder=ApplicationFolder;
		this.AgentID=AgentID;
			
	}
	public String createTransactionDetailsPDFUOB(String TDSFileSample,String TDSFilePath,String response,String Rookie_Agent_ID,String E_Reference_ID)
	{
			String ret_val="1";
			try
			{	
			NumberFormat nf = NumberFormat.getInstance() ;		
			System.out.println("Generating TDS AgentID=>"+Rookie_Agent_ID+"E_Reference_ID==>"+E_Reference_ID+" Start") ;	
				
			char delimiter = 1 ;
			System.out.println(response);	
			String[] response_array = response.split("~");	
			System.out.println(response_array[0]+" "+response_array[1]+" "+response_array[2]+" "+response_array[3]);		
			String[] Data_Str = new String[9] ;
			Data_Str[0]="Name";
			Data_Str[1]="Age";
			Data_Str[2]="Date Of Birth";
			Data_Str[3]="Blood Group";
			Data_Str[4]="Address";
			Data_Str[5]="Phone";
			Data_Str[6]="E-mail";
			Data_Str[7]="Date Of Appoitment";
			
			

			String[] Data_Value = new String[9] ;
			System.out.println(response_array);
			
			Data_Value[0]=response_array[1];
			Data_Value[1]=response_array[2];
			Data_Value[2]=response_array[3];
			Data_Value[3]=response_array[4];
			Data_Value[4]=response_array[5];
			Data_Value[5]=response_array[6];
			Data_Value[6]=response_array[7];
			Data_Value[7]=response_array[8] ;
			
			
			
			
			//System.out.println(ApplicationFontFileLocation+"Calibri_Regular.ttf");
			//System.out.println(ApplicationFontFileLocation+"Calibri_Bold.ttf");
			BaseFont bf_times = BaseFont.createFont(
					ApplicationFontFileLocation+"Calibri_Regular.ttf", "UTF-8", BaseFont.EMBEDDED);
									
			Font N1 = new Font(bf_times, 22, Font.BOLD);
			Font N2 = new Font(bf_times, 18, Font.BOLD);
			
			BaseFont bf_times_1 = BaseFont.createFont(
					ApplicationFontFileLocation+"Calibri_Bold.ttf", "UTF-8", BaseFont.EMBEDDED);
			
			
			Font N3 = new Font(bf_times_1, 13, Font.NORMAL);


			File f1 = new File(TDSFileSample) ;
			f1.delete();
			File f2 = new File(TDSFilePath) ;
			f2.delete();
			
			Document document1 = new Document();
			
			Rectangle largePage = new RectangleReadOnly(720,842);
			// step 1
			
			document1.setPageSize(largePage);
			document1.setMargins(0,0,0,0);
			
	        PdfWriter writer1 = PdfWriter.getInstance(document1, new FileOutputStream(TDSFileSample));
	        
	        document1.open();
			//PdfReader reader = new PdfReader("/home/atdesk-28/Rectangle.pdf");
	        //Rectangle mediabox = reader.getPageSize(1);
	        
	        //System.out.println(mediabox.getWidth());
	        float llx = 70;
	        float lly = 700;
	        float urx = 650;
	        float ury = 740;
	        PdfContentByte canvas = writer1.getDirectContent();
	        Rectangle rect1 = new Rectangle(llx, lly, urx, ury);
	        rect1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        //rect1.setBorder(Rectangle.BOX);
	        //rect1.setBorderWidth(1);
	        canvas.rectangle(rect1);
	        
	        //writer1.close();
	        document1.close();
	        
	        PdfImportedPage page;
			PdfCopy.PageStamp page_stamp;
			
			Document document = new Document();  // PageSize.A4
			
			document.setPageSize(largePage);
			document.setMargins(0,0,0,0);
			
			
			PdfCopy writer = new PdfCopy(document, new FileOutputStream(TDSFilePath));
					
			document.open();

			PdfReader reader = new PdfReader(TDSFileSample);
			
			page = writer.getImportedPage(reader, 1);
			
			Rectangle mediabox = reader.getPageSize(1);
			//System.out.println(" page_number "+mediabox.getHeight());
			//System.out.println(" page_number "+mediabox.getWidth());
			//System.out.println(page_number+" "+reader.getPageSizeWithRotation(page_number));

			page_stamp = writer.createPageStamp(page);
	        
			Phrase Line1 = new Phrase();
			Line1.add(new Chunk("Appointment Details", N1)); // "Quotation for Rajesh & Rajani"
			
			/*
			String.format("Halaman %d dari %d", act_page, page_nos),  				
			new Font(FontFamily.COURIER , 8, Font.BOLD, new BaseColor(0, 0, 0 ))
			*/
			ColumnText.showTextAligned(
					page_stamp.getOverContent(), Element.ALIGN_LEFT,
					Line1 , 185, 760, 0);
			
			ColumnText.showTextAligned(
					page_stamp.getUnderContent(), Element.ALIGN_LEFT,
					Line1 , 185, 760, 0);
			
			
			Phrase Line2 = new Phrase();
			Line2.add(new Chunk("PATIENT DETAILS", N2)); // "Quotation for Rajesh & Rajani"
			
			/*
			String.format("Halaman %d dari %d", act_page, page_nos),  				
			new Font(FontFamily.COURIER , 8, Font.BOLD, new BaseColor(0, 0, 0 ))
			*/
			ColumnText.showTextAligned(
					page_stamp.getOverContent(), Element.ALIGN_LEFT,
					Line2 ,80, 720, 0);
			
			ColumnText.showTextAligned(
					page_stamp.getUnderContent(), Element.ALIGN_LEFT,
					Line2,80, 720, 0);
			
			Phrase Line3 = new Phrase();
			
			Phrase Line4 = new Phrase();
			
			Line4.add(new Chunk(":" , N3)); // "Quotation for Rajesh & Rajani"
			
			Phrase Line5 = new Phrase();
			
			int y = 650 ;
			
			for(int i=0 ; i<8 ; i++)
			{
				Line3.clear() ;
				Line3.add(new Chunk(Data_Str[i], N3)); // "Quotation for Rajesh & Rajani"
				
				ColumnText.showTextAligned(
						page_stamp.getOverContent(), Element.ALIGN_LEFT,
						Line3 ,80, y, 0);
				
				ColumnText.showTextAligned(
						page_stamp.getUnderContent(), Element.ALIGN_LEFT,
						Line3 ,80, y, 0);
				
				ColumnText.showTextAligned(
						page_stamp.getOverContent(), Element.ALIGN_LEFT,
						Line4 ,370, y, 0);
				
				ColumnText.showTextAligned(
						page_stamp.getUnderContent(), Element.ALIGN_LEFT,
						Line4 ,370, y, 0);
				
				Line5.clear() ;
				Line5.add(new Chunk(Data_Value[i], N3)); // "Quotation for Rajesh & Rajani"
				
				ColumnText.showTextAligned(
						page_stamp.getOverContent(), Element.ALIGN_LEFT,
						Line5 ,380, y, 0);
				
				ColumnText.showTextAligned(
						page_stamp.getUnderContent(), Element.ALIGN_LEFT,
						Line5 ,380, y, 0);
						
						
						
				
				y = y - 30 ;
			}
			//System.out.println(y);
			Phrase Line6 = new Phrase();
			
			Line6.add(new Chunk("Abhinave@itSolutions" , N3)); // "Quotation for Rajesh & Rajani"
			
			ColumnText.showTextAligned(
					page_stamp.getUnderContent(), Element.ALIGN_LEFT,
					Line6 ,80, 80, 0);
			
			/*
			ColumnText.showTextAligned(
					page_stamp.getUnderContent(), Element.ALIGN_CENTER,
					new Phrase(String.format("Halaman %d dari %d", act_page, page_nos),  				
							new Font(FontFamily.COURIER,8, Font.BOLD, new BaseColor(0, 0, 0 ))),
							(float)(mediabox.getWidth()/2), 6, 0);
			*/
			
			page_stamp.alterContents();

			writer.addPage(page);
			
			//writer.close();
			//page_stamp.close();
			document.close();
			reader.close();
			
			//System.out.println("Generating TDS AgentID=>"+Rookie_Agent_ID+"E_Reference_ID==>"+E_Reference_ID+" End") ;
			
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception generating PDF "+e.toString());
			 ret_val = "0";
		 }	
		 return ret_val ;
	}


    public static void main (String args[])
    {
    	String ProposalNumber="810007889";//"820247948";
		String[] all_files =null;
		String AgentID="1073900" ;
		String E_Reference_Id="E10739001212021171446234" ;
		
		String TDSFileSample="C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Report/Output/"+ProposalNumber+"_TDS_Sample.pdf" ;
		String TDSFilePath="C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Report/Output/"+ProposalNumber+"_TDS.pdf";
	//	String response_full="1Foo Sum MooiFoo Sum Mooi27/07/2020 03:22 PM810008431PRUMax CoverA1600000200000.00Auto DebitCHONG LILYRMB12FFlily.chong@uob.com.myE1073521277202014541123310735" +
	//			"2110410000000491010000100000001010010200000.00falseUOBOFFMAS0";
		CreateUOBTDS objPDF = new CreateUOBTDS("C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Report/",
				"C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Font/","C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Report/"+"Output"+"/",AgentID);  // 1006256
		String response_full="1Teoh Cheng SimTeoh Cheng Sim01/07/2020 02:11 PM810007889PRUMax CoverH32000020000.00Credit Card1FOO SEANG KITRMM12FFfoo.seangkit@uob.com.myE1125998172020135125490" +
		"2110410000000491010000100000001010010200000.00falseUOBOFFWB0";
		// System.out.println(strUserId+" "+response_jsp+" "+strButton+" "+response);
		objPDF.createTransactionDetailsPDFUOB(TDSFileSample,TDSFilePath,response_full,AgentID,E_Reference_Id);
		
    }
}



