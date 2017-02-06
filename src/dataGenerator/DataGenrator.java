package dataGenerator;

import java.awt.geom.GeneralPath;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

public class DataGenrator {
	
	public static HashMap<String, Integer> frequencyGenerator(){
		
		HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();
		
		double sum = 0;
		
		
		for(int i = 1; i <= Constants.elementArray.length; i++){
			
			double frequency  = 1.0/(Math.pow(i, Constants.ZIPF_EXPONENTIAL) * harmonicDenominator());
			System.out.println(i + "  : " + Math.round(frequency*Constants.NO_OF_ELEMENTS));
			frequencyMap.put(Constants.elementArray[i-1], (int) Math.round(frequency*Constants.NO_OF_ELEMENTS));
			
			sum = sum + frequency*Constants.NO_OF_ELEMENTS;
			//frequencyMap.put(Constants.elementArray[i], 1.0)
		
			
		}
		
		
		
		
		System.out.println(frequencyMap);
		
		
		return frequencyMap;
		
		
	}
	
	public static void generateStatisticalDataSet(HashMap<String,Integer> frequencyMap){
		
		//counter to check the total number of items used
		int totaltElemetnsAdded = 0;
		int itemSetCount = 0;
		
		//creating a writer for inputting to the file
	    BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File("stasticalInput.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		while( (Constants.NO_OF_ELEMENTS - totaltElemetnsAdded) >= 5){
			
			int itemSetRandomSize = (int )(5 + Math.random()*3 );

			String itemSet = "";
						
			
			for(int i = 0; i < itemSetRandomSize; i ++){
				
				int randomItem = (int)(Math.random()*10 + 1);

				
				while(frequencyMap.get(Constants.elementArray[randomItem-1]) == 0){
					
					randomItem = (int)(Math.random()*10 + 1);

				}
				
				if(i == 0){
					
					itemSet = itemSet + Constants.elementArray[randomItem - 1];

				}else{
					
					itemSet = itemSet + "," + Constants.elementArray[randomItem - 1];

				}
				
				frequencyMap.put(Constants.elementArray[randomItem-1]
						, frequencyMap.get(Constants.elementArray[randomItem-1])-1);
				
				
				
			}
			
			try {
				writer.write(itemSet);
				writer.newLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(itemSet);
			
			totaltElemetnsAdded += itemSetRandomSize;
			itemSetCount++;
			
			
			
		}
		
		/*
		System.out.println("total number of item sets generated : " + itemSetCount   );
		System.out.println("total number of items used : " + totaltElemetnsAdded   );
		
		*/
		
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void DataGenerator(){
		
	}
	
	
	public static double harmonicDenominator(){
		
		double sum = 0;
		for(int i = 1; i <= Constants.elementArray.length; i++){
			
			sum = sum + Math.pow(1.0/i, Constants.ZIPF_EXPONENTIAL);
			
			//System.out.println(Math.pow(1.0/i, Constants.ZIPF_EXPONENTIAL) + "     " + sum);
			
			
		}
		

		return sum;
		
	}
	
	
	public static void main(String args[]) {
		
		HashMap<String, Integer> frequencyMap = frequencyGenerator();

		//harmonicDenominator();
		//frequencyGenerator();
		generateStatisticalDataSet(frequencyMap);
	}
	
	

}
