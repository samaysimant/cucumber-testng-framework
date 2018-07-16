package com.automationframeworks.Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelReader {

	public void readExcel() {

	}

	public static void main(String[] args) throws FilloException {}
	
	private static  ArrayList<String> getInputData(String scenarioName) throws FilloException{
		DataHandler.getData.clear();
		Fillo fillo = new Fillo();
		List<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		ArrayList<String> outputRecord = new ArrayList<String>();
		Connection connection = fillo.getConnection("src/test/resources/testdata.xlsx");
		String strQuery = "Select * from Sheet1 where Scenario_name='"+scenarioName+"'" ;
		Recordset recordset = connection.executeQuery(strQuery);
		System.out.println("Num of records:" + recordset.getCount());
		Object[] fieldName = recordset.getFieldNames().toArray();
		while (recordset.next()) {
			
			for (int i = 0; i < fieldName.length; i++) {
				//System.out.println("Field: " + fieldName[i] + " Value:" + recordset.getField(fieldName[i].toString()));
			   if(recordset.getField(fieldName[i].toString())!=""){
				   outputRecord.add(recordset.getField(fieldName[i].toString()));
			   }
			}
			output.add(outputRecord);
			
		}
         System.out.println(output);
		recordset.close();
		connection.close();
	return outputRecord;
	}
	
	public static void feedInputData(String scenarioName) throws FilloException {
		ArrayList<String> input = getInputData(scenarioName);
		for(int i=1;i<input.size();i++) {
			System.out.println("input.get(i):"+input.get(i));
			String key=input.get(i).split("::")[0];
			String value=input.get(i).split("::")[1];
			DataHandler.getData.put(key, value);
		}
	}
}
