package exercise1;

import java.util.Stack;

public class LispParenthesesValidationExecutor {

	public static void main(String[] args){
        String testString = "(My (Firstname) is (Fernando) and my (lastname is) (Mejia Cano))";
        System.out.println(validateLISPCode(testString) ? "Success" : "Failure");
        System.out.println(validateLISPCode("") ? "Success" : "Failure");
        System.out.println(validateLISPCode(")") ? "Success" : "Failure");
        System.out.println(validateLISPCode("()()") ? "Success" : "Failure");
        System.out.println(validateLISPCode("()(()") ? "Success" : "Failure");
        System.out.println(validateLISPCode("()(())") ? "Success" : "Failure");
    }

   public static boolean validateLISPCode(String code) 
    {
        if (code == null || code.length() == 0) return true;
        String value = code.replaceAll("[^()]","");
        Stack<String> lispStack = new Stack<>();
        
        for (char c : value.toCharArray()) {
            switch(c){
                case('('):
                    lispStack.push("(");
                    break;
                case(')'):
                    if (lispStack.empty() || lispStack.peek() != "(") return false;
                    lispStack.pop();
                    break;
            }
        }
    
        return lispStack.empty();
    }

}
