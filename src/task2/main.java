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
				
				break;
			case "-inkelvin":
				inkelvin = true;
				break;
			case "-indegree":
				indegree = true;
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

		
		
		if(verbose){
			System.out.print(expression +" = ");
			
		}
		if(point == false){
			result = result.replace(".", ",");
		}
		System.out.println(result);
	}
}
