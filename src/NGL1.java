import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import dataGenerator.Constants;

public class NGL1 {

	public static HashMap<String, Integer> permutation(String input, HashMap<String, Integer> firstPassResult){
		
		HashMap<String, Integer> twoStringInput = new HashMap<String, Integer>();
		String belowThreshold = "";
		
		
		//checking for apriori principle basically
		Set set = firstPassResult.entrySet();
	    Iterator iterator = set.iterator();
	    
	    while(iterator.hasNext()){
	    	
		       Map.Entry mentry = (Map.Entry)iterator.next();
		       if((int)mentry.getValue() < Constants.SUPPORT_VALUE){
		    	   belowThreshold = belowThreshold + mentry.getKey();
		    	   
		       }
	    }
	    System.out.println(belowThreshold + "");
		
		
		for(int i = 0; i < input.length(); i++){
    		for(int j = i+1; j < input.length(); j++){
    			//System.out.println(input.charAt(i)+ "" + input.charAt(j));
    			if(!belowThreshold.contains(""+input.charAt(i)) && !belowThreshold.contains(""+input.charAt(j))){
    				twoStringInput.put(input.charAt(i)+ "" + input.charAt(j), 0);
    		
    			}
    		}
    	}
		
		//System.out.println(twoStringInput);
		return twoStringInput;
		
	}
	public static void main(String args[]) {
		
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
		hmapFirstPass.put("A", 0);
		hmapFirstPass.put("B", 0);
		hmapFirstPass.put("C", 0);
		hmapFirstPass.put("D", 0);
		hmapFirstPass.put("E", 0);
		
		
		//checking for occurnaces of a,b,c,d,e first pass
		Set set = basketInputHmap.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()) {
	    	
	       Map.Entry mentry = (Map.Entry)iterator.next();
	       
	       System.out.print("<"+ mentry.getKey() + ", {");
	       System.out.println(mentry.getValue() + "}>");
	       
	       if(mentry.getValue().toString().contains("A")){
	   			hmapFirstPass.put("A", hmapFirstPass.get("A") + 1);
	       } 
	       if(mentry.getValue().toString().contains("B")){
	   			hmapFirstPass.put("B", hmapFirstPass.get("B") + 1);
	       }
	       if(mentry.getValue().toString().contains("C")){
	   			hmapFirstPass.put("C", hmapFirstPass.get("C") + 1);
	       }
	       if(mentry.getValue().toString().contains("D")){
	   			hmapFirstPass.put("D", hmapFirstPass.get("D") + 1);
	       }
	       if(mentry.getValue().toString().contains("E")){
	   			hmapFirstPass.put("E", hmapFirstPass.get("E") + 1);
	       }
	    }
	    
	    
	   System.out.println("After First Pass" + hmapFirstPass);
	   
	   HashMap<String, Integer> secondPassMap = permutation("ABCDE", hmapFirstPass);
	   //System.out.println(secondPassMap);

	   set = basketInputHmap.entrySet();
	   iterator = set.iterator();
	   
	   while(iterator.hasNext()){
	       Map.Entry mentry = (Map.Entry)iterator.next();
	       
	       Set twoItemsetSet = secondPassMap.entrySet();
	       Iterator twoItemsetIetrator = twoItemsetSet.iterator();
	       
	       while(twoItemsetIetrator.hasNext()){
	    	   Map.Entry twoItemsetEntry = (Map.Entry) twoItemsetIetrator.next();
	    	   
		       if(mentry.getValue().toString().contains(""+twoItemsetEntry.getKey().toString().charAt(0))
		    		   && mentry.getValue().toString().contains(""+twoItemsetEntry.getKey().toString().charAt(1))){
		    	   
		    	   secondPassMap.put((String) twoItemsetEntry.getKey(), (int)twoItemsetEntry.getValue()+1);
		       }

	       }
		   
	   }
	   
	   System.out.println("After Second Pass" + secondPassMap);

	   
	   
		//Integer var= hmap.get("E");
	      //System.out.println("Value at index 2 is: "+var);
		

	
	}
    

}
