package task2;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;

/*
 * Features: -c Punkt zu Komma ändern
 * -v Rechenweg ausgeben
 * -inkelvin || -indegree Celsius <--> Kelvin
 * calculation
 * Konstanten benutzen: $pi
 * 
 */
public class main {

	//Punkt oder Komma in der Ausgabe
	private static boolean point = true;
	//Rechenweg anzeigen
	private static boolean verbose = false;
	private static boolean inkelvin = false;
	private static boolean indegree = false;
	private static List<String> operand_stack = new ArrayList<String>();

	public static String calc(String expression) throws ScriptException{
		 ScriptEngineManager mgr = new ScriptEngineManager();
		 ScriptEngine engine = mgr.getEngineByName("JavaScript");
		 return engine.eval(expression).toString();
    } 
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
	    return true;
	}
	
	public static void main(String[] args) {
		
		for (String string : args) {
			
			switch (string) {
			case "-c":
				point = false;
				break;
			case "-v":
				verbose = true;
				break;
			case "$pi":
				operand_stack.add("3.14159");
				break;
			case "-inkelvin":
				inkelvin = true;
				/* Weg 1 - innerhalb der Gesamtrechnung
				 *   operand_stack.add("+");
				 *   operand_stack.add("273.15");
				 */
				break;
			case "-indegree":
				indegree = true;
				/*
				 *   operand_stack.add("-");
				 *   operand_stack.add("273.15");
				 */
				break;
			case "*":
			case "-":
			case "/":
			case "+":
				operand_stack.add(string);
				break;
			default:
				if(isDouble(string)){
					operand_stack.add(string);
				}
				break;
			}
		}
		
		// Weg 2 - am Ende der Gesamtrechnung
		if(inkelvin){
			operand_stack.add("+");
			operand_stack.add("273.15");
		}
		
		if(indegree){
			operand_stack.add("-");
			operand_stack.add("273.15");
		}
		
		String expression = "";
		for(String operand : operand_stack){
			expression += operand;
		}
		
		String result = "";

		try {
			result = calc(expression);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Weg 3 - als extra Rechnung und nicht im Rechenweg (verbose)
		if (inkelvin) {
			result += "+273.15";
			try {
				result = calc(result);
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		
		if (indegree) {
			result += "-273.15";
			try {
				result = calc(result);
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		*/
		
		if(point == false){
			expression = expression.replace(".", ",");
			result = result.replace(".", ",");
		}
		
		if(verbose){
			System.out.print(expression +" = ");
		}
		
		System.out.print(result);
		
		// Ausgabefeedback beim Umrechnen, optional
		if(inkelvin) {
			System.out.print(" K ");
		}

		if(indegree) {
			System.out.print(" °C ");
		}
	}
}
