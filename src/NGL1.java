import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import dataGenerator.Constants;





public class NGL1 {

	public static HashMap<String, Integer> permutation(HashMap<String, Integer> firstPassResult){
		
		HashMap<String, Integer> twoStringInput = new HashMap<String, Integer>();
		String belowThreshold = "";
		
		//checking for below threshold before generating 2 - item itemsets
		for(String s : Constants.elementArray){
			
			

			if(firstPassResult.get(s) > Constants.SUPPORT_VALUE){
				//System.out.println(firstPassResult.get(s));
				belowThreshold += s;
			}
		}
		
		
		

	 
	    System.out.println(belowThreshold + "");
		
		
		for(int i = 0; i < belowThreshold.length(); i++){
    		for(int j = i+1; j < belowThreshold.length(); j++){
    			//System.out.println(input.charAt(i)+ "" + input.charAt(j));
    				twoStringInput.put(belowThreshold.charAt(i)+ "" + belowThreshold.charAt(j), 0);
    		
    		}
    	}
		
		//System.out.println(twoStringInput);
		return twoStringInput;
		
	}
	
	
	public static void main(String args[]) {
		
		BufferedReader br = null;
		FileReader fr = null;
		
		String[] firstPassArray = Constants.elementArray;

		
		HashMap<Integer, String> basketInputHmap = new HashMap<Integer, String>();
		basketInputHmap.put(1, "A,B,C");
		basketInputHmap.put(2, "A,C,B,D");
		basketInputHmap.put(3, "A,D,E");
		basketInputHmap.put(4, "C,A,D");
		basketInputHmap.put(5, "B,A,D,E");
	
		basketInputHmap.put(6, "A,D,C");
		basketInputHmap.put(7, "A,B,E,D");
		basketInputHmap.put(8, "A,C,E");
		basketInputHmap.put(9, "C,B,D");
		basketInputHmap.put(10, "B,C,D");
		
	HashMap<String, Integer> hmapFirstPass = new HashMap<String, Integer>();
		for(int i = 0; i < firstPassArray.length; i++){
			hmapFirstPass.put(firstPassArray[i], 0);
		}
		
		
		//checking for occurnaces of a,b,c,d,e,f,g,h,i,j first pass
		//Set set = basketInputHmap.entrySet();
	    //Iterator iterator = set.iterator();
	    
		try {
			fr = new FileReader(Constants.FILENAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);

		String transactionString;
		
		int noOfTransactions = 0;
		
		
	    
	    try {
			while((transactionString = br.readLine()) != null && noOfTransactions < Constants.NO_OF_ITEM_SETS) {
				
				noOfTransactions++;
			   
			   System.out.print("<"+noOfTransactions + ", {");
			   System.out.println(transactionString + "}>");
			   
			   
			   for(int i = 0; i < firstPassArray.length; i++){
				   
				   if(transactionString.contains(firstPassArray[i]) ){

						hmapFirstPass.put(firstPassArray[i], hmapFirstPass.get(firstPassArray[i]) + 1);
						
				   } 
				   
			   }
			  
			   
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	   System.out.println("After First Pass" + hmapFirstPass);
	   
	   
	   
	   HashMap<String, Integer> secondPassMap = permutation(hmapFirstPass);
	   supportValueSecondPass(secondPassMap);
	   System.out.println(secondPassMap);

	  
	  
		

	
	}
	
	
	public static void supportValueSecondPass(HashMap<String, Integer> secondPassMap){
		BufferedReader br = null;
		FileReader fr = null;
		 
		
		try {
				fr = new FileReader(Constants.FILENAME);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			br = new BufferedReader(fr);

			String transactionString;
			
			int noOfTransactions = 0;
		    try {

		    	while((transactionString = br.readLine()) != null && noOfTransactions < Constants.NO_OF_ITEM_SETS){
					   
		    			
		    		   noOfTransactions++;
				       Set twoItemsetSet = secondPassMap.entrySet();
				       Iterator twoItemsetIetrator = twoItemsetSet.iterator();
				       
				       while(twoItemsetIetrator.hasNext()){
				    	   Map.Entry twoItemsetEntry = (Map.Entry) twoItemsetIetrator.next();
				    	   
					       if(transactionString.contains(""+twoItemsetEntry.getKey().toString().charAt(0))
					    		   && transactionString.contains(""+twoItemsetEntry.getKey().toString().charAt(1))){
					    	   
					    	   secondPassMap.put((String) twoItemsetEntry.getKey(), (int)twoItemsetEntry.getValue()+1);
					       }

				       }
					   
				}
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		   
		   System.out.println("After Second Pass" + secondPassMap);

		   
		   
			//Integer var= hmap.get("E");
		      //System.out.println("Value at index 2 is: "+var); 
	}

}
