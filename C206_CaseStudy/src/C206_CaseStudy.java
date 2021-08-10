import java.util.ArrayList;
import java.util.regex.Pattern;

public class C206_CaseStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<currency> holdingList = new ArrayList<currency>();
				
		  holdingList.add(new currency("US Dollar", "USD", 100000, 1.35069));  
		  holdingList.add(new currency("Euro", "EUR", 5000000, 1.59881));  
		  holdingList.add(new currency("Canadian Dollar", "CAD", 10000000,  1.07936));  
		  holdingList.add(new currency("Australian Dollar", "AUD", 3000000,0.99942));  
		  
		  int option = 0;
		  
		  while (option != 3) {
			  C206_CaseStudy.menu();
			  option = Helper.readInt("Enter an option > ");
			  
			  if (option == 1) {
					// View 
				  C206_CaseStudy.viewCurrencyHoldings(holdingList);
				  C206_CaseStudy.doConvert(0.0, 0.0);

				} else if (option == 2) {
					// Search 
					C206_CaseStudy.viewResult("",holdingList);
					C206_CaseStudy.doSearch("",holdingList);
					C206_CaseStudy.checkInput("");
					
					} 
				else if ( option == 3) {
					System.out.println("Bye!");
				} else {
					System.out.println("Invalid option");
				}
			  
		  }
		  

	}
	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static void menu() {
		C206_CaseStudy.setHeader("MONEY MANAGEMENT SYSTEM");
		System.out.println("1. View Currency Holdings");
		System.out.println("2. Search for a Currency Holdings");
		System.out.println("3. Quit");
		Helper.line(80, "-");

	}
	
	public static String retrieveAllCurrencyHoldings(ArrayList<currency> holdingList) {
		String output = "";
		for (int i = 0; i < holdingList.size(); i++) {
			currency currency1 = holdingList.get(i);
			double amt = doConvert(currency1.getHoldAmtLocal(),currency1.getSellRate());
			output += String.format("%-30s %-30s %-30.2f %-30.5f %-30.2f\n", currency1.getName(), currency1.getISO(),currency1.getHoldAmtLocal(),currency1.getSellRate(),amt);
		}
		return output;
	}
	
	public static double doConvert(double sellRate, double holdAmt) {
		return sellRate * holdAmt;
	}
	
	public static void viewCurrencyHoldings(ArrayList<currency> holdingList) {
		C206_CaseStudy.setHeader("CURRENCY HOLDINGS LIST");
		String output = String.format("%-30s %-30s %-30s %-30s %-30s\n", "CURRENCY NAME", "CURRENCY ISO",
				"HOLDING AMOUNT (LOCAL)", "SELL RATE","HOLDING AMOUNT (SGD)");
		 output += retrieveAllCurrencyHoldings(holdingList);	
		System.out.println(output);
	}
	
	public static String doSearch(String input,ArrayList<currency> holdingList) {
		String output="";
		if (checkInput(input)) {
	for (currency a: holdingList) {
		double amt = doConvert(a.getHoldAmtLocal(),a.getSellRate());
		if (input.equalsIgnoreCase(a.getName()) || input.equalsIgnoreCase(a.getISO())){
			output += String.format("%-30s %-30s %-30.2f %-30.2f %-30.2f\n", a.getName(), a.getISO(),a.getHoldAmtLocal(),a.getSellRate(),amt);
		} 
	} 
	
	} else {
		output+="Invalid";
	}
	return output;
	}
	public static void viewResult(String input, ArrayList<currency> holdingList) {
		input = Helper.readString("Enter a name or ISO > ");
		String output = "";
		if(doSearch(input,holdingList).isEmpty()) {
			output+=String.format("Currency %s not found", input);
		} else if (doSearch(input,holdingList).contains("Invalid")) {
			output+="Invalid Output. Try again";
		}else {
		output+=String.format("%-30s %-30s %-30s %-30s %-30s\n", "CURRENCY NAME", "CURRENCY ISO",
				"HOLDING AMOUNT (LOCAL)", "SELL RATE","HOLDING AMOUNT (SGD)");
		output += doSearch(input,holdingList);
		}
	 System.out.println(output);
	}
	
	public static boolean checkInput(String input) {
		String pattern = "[a-zA-Z ]+";
		boolean matched = Pattern.matches(pattern, input);
		boolean check = false;
		if (matched) {
			check = true;
		} else {
			check = false;
		} return check;
	}
	


}
