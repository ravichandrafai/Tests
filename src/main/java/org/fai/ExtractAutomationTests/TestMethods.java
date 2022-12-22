package org.fai.ExtractAutomationTests;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.db.DbOperations;
import org.fai.enums.PropertyEnums;
import org.fai.pages.extactions.ExtractedFields;
import org.fai.utils.ReadProperties;

import com.github.sisyphsu.dateparser.DateParserUtils;

public class TestMethods {
	
	public static void main(String args[]) throws Exception {
		//verifyFileNameFormat();
		convertDateFormat("09/02/2021","YYYY-MM-dd");
	}
	
	public static void verifyFileNameFormat() throws Exception {
		
		String[] categoryNames = {"1.CORRESPONDENCE","2.MEDICALS","3.Investigation","4.LIENS","5.MEDICALS","6.PLEADINGS-LEGAL","7.RFA-UR","8.Settlement Docs","9.Subpoena"};
		String catName;
		List<Map<String,String>> fieldNameFormatDetails = DbOperations.getFileNameFormat(ReadProperties.get(PropertyEnums.CLIENTNAME));
		for(int i=0;i<categoryNames.length;i++) {
			catName=categoryNames[i];
			for(int j=0; j<fieldNameFormatDetails.size();j++) {
				String dbCatName=fieldNameFormatDetails.get(j).get("category_name");
				if(catName.contains(dbCatName))
				{
					String dbDocType = fieldNameFormatDetails.get(j).get("customer_subcategory");
					String nameFields = fieldNameFormatDetails.get(j).get("fileNameFields");
					String seperators = fieldNameFormatDetails.get(j).get("seperators");
//					
//					new ExtractedFields()
//					.setDocType(catName, dbDocType);
//					
					new ExtractedFields()
							.validateFileNameFormat(nameFields, seperators,"");
					//Assertions.assertThat(isFileNameValid).isEqualTo(true);
					
				}
			}

		}
	}
	
	public static String convertDateFormat(String inputDate, String inputFormat) throws ParseException {
		Date date = DateParserUtils.parseDate(inputDate);
		SimpleDateFormat last_date_date = new SimpleDateFormat(inputFormat);
		String outputDate = last_date_date.format(date);
		return outputDate;
		
	
		
	}

}
