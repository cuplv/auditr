
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;


public class main {
	public static String prefixFinal;
	public static void main(String[] args) {
		boolean format;
		String infixToPrefix;
		int FinalResult;
		main t1 = new main();
		format = t1.checkForm();
		if(format==false)
		{
			System.out.println("Wrong Input: Please revise the format of your assignment");
			return;
		}
		prefixFinal = t1.infixToPre();
		if(prefixFinal.isEmpty())
		{
			System.out.println("Wrong Input: Please revise the format of your assignment");
			return;
		}
		FinalResult = t1.evaluation(0);
		System.out.println("Evaluation is: " + FinalResult);
	}
	
	public String infixToPre()
	{
		String input = "";

		try (BufferedReader br = new BufferedReader(new FileReader("./test")))
		{
			String temp;
			
			while ((temp = br.readLine()) != null)
			{
				input = temp;
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	       Stack stack = new Stack();
	        String prefix = "";
	        for(int i=input.length()-1;i>=0;i--){
	            char c = input.charAt(i);
	            if(Character.isDigit(c)){
	                prefix = ((Character)c).toString() + prefix;
	            }
	            else if(c == '('){
	                prefix = ((Character)stack.pop()).toString() + prefix;
	            }
	            else if(c == ')'){
	                continue;
	            }
	            else if (c == '+' || c == '-' || c == '*' || c == '/'){
	                stack.push(c);
	            }
	            else
	            {
	            	return "";
	            }
	        }
	        return prefix;		
	}
	
	public String readFile()
	{
		return "sample";
	}

	public boolean checkForm()
	{
		String input = "";

		try (BufferedReader br = new BufferedReader(new FileReader("./test")))
		{
			String temp;
			
			while ((temp = br.readLine()) != null)
			{
				input = temp;
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
        char[] c=input.toCharArray();
    	int count1=0;
    	int count2=0;
 
        for(int i=0;i<c.length;i++)
        {
            if(Character.isDigit(c[i]) && Character.isDigit(c[i+1]))
                return false;
        	if (c[i]=='(')
        		count1++;
        	if (c[i]==')')
        		count2++;
        }
        if(count1==count2)
        	return true;
        else
        	return false;
	}
	
    public int evaluation(int depth)
    {
	int output=0;
	System.out.println("depth="+depth);
	    if(!prefixFinal.isEmpty()){
		char c = prefixFinal.charAt(0);
		if(Character.isDigit(c)){
		    prefixFinal=prefixFinal.substring(1);
		    return Character.getNumericValue(c);
		}
		else if(c=='*'){
		    prefixFinal=prefixFinal.substring(1);
		    output = evaluation(depth+1) * evaluation(depth+1);
		}
		else if(c=='+'){
		    prefixFinal=prefixFinal.substring(1);
		    output = evaluation(depth+1) + evaluation(depth+1);
		}
		else if(c=='-'){
		    prefixFinal=prefixFinal.substring(1);
		    output = evaluation(depth+1) - evaluation(depth+1);
		}
		else if(c=='/'){
		    prefixFinal=prefixFinal.substring(1);
		    output = evaluation(depth+1) / evaluation(depth+1);
		}
		else
		    {
			return 0;
		    }
	    }
	    return output;
	}
}

