package com.example.projectcompiler;

import java.util.*;
import java.util.regex.Pattern;
;
import static com.example.projectcompiler.ProductionRuleTable.parsingTable;

public class Parsing {

    private static final List<String> terminals = Arrays.asList("=","|=" ,"<","<=",">",">=",",", "+", "-", "*", "/","(", ")", ":=",";",":",".",
            "module", "name", "begin", "end", "const", "if", "then", "else", "while",
            "do", "loop", "until", "exit", "call","integer-value","real-value",
            "ε", "var","integer", "real", "char", "procedure", "exp",
            "mod", "div", "readint", "readreal", "readchar", "readln",
            "writeint", "writereal", "writechar", "writeln");
    private static final List<String> reservedWord = Arrays.asList("begin","module","end","integer","real","char","procedure","mod","div","readint","readreal","realln","readchar","writereal","writechar","writeint","writeln","then","do","if","else","while","loop","until","exit","call");
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private static final Pattern INTEGER_VALUE_PATTERN = Pattern.compile("\\d+");
    private static final Pattern REAL_VALUE_PATTERN = Pattern.compile("\\d*\\.?\\d+");
   public String  Parse(String [] tokens,String [] lines ) {
       Stack<String> stack = new Stack<>();
       // I push the ferst non terminal in stack in order to start the parser operation
       stack.push("module-decl");
       int index = 0;
       String topRule = "";
       String rule="";
       int line=0;
       while (!stack.isEmpty()){ //&& index< tokens.length) {
           // in order to determine which line we reached and if there a syntax error appear to detect it and display which line
           if(!lines[line].contains(tokens[index])){
              ++ line;
           }
           // we will determine the top on the stack and save it in top rule if the current token  equal to the toprule we will pop the stack
           //and go to the next token
           topRule = stack.peek();
           // the checker function is in order to determine if the token is name,integer,real value for example the top of the stack is name and the current token is num this
           //token will go to the checker function and then it will determined that it is name so as the parser work it will  pop the stack because the top of stack is name and the token is name
           if (checker(tokens[index]).equals(topRule)) {
           //    System.out.println("pop input " + tokens[index] + "pop rule" + topRule);
                   ++index;
               stack.pop();
           } else {
               //else we will use parsing table for example the peek is module-decl and the current token is module
               //it will get the production rule stored under module for this non terminal and push it in stack

               Map<String, String> ruleMap = parsingTable.get(topRule);
               if (ruleMap != null ) {
                   rule = ruleMap.get(checker(tokens[index]));
                   if (rule!=null) {
                       if (!rule.equals("ε")) {
                           stack.pop();
                        //   System.out.println("before reverse " + stack.pop()+"          ");
                           String[] splitRule = rule.split(" ");
                           Collections.reverse(Arrays.asList(splitRule));
                           for (String symbol : splitRule) {
             //                  System.out.println(" 999999999999Rule " + symbol+"    ");
                               stack.push(symbol);
                           }
                       } else {

                          // System.out.println("There is lamdaaaa");
                           stack.pop();
                       }
                   }else{
                  //     System.out.println("SYNTAX ERROR UNEXPECTED TOKEN "+tokens[index]);
                       return "SYNTAX ERROR UNEXPECTED TOKEN "+tokens[index]+" IN LINE "+(line+1);
                   }
               }
                else {
               //    System.out.println("Syntax ERROR"+tokens[index]);
                   return "SYNTAX ERROR UNEXPECTED TOKEN "+tokens[index]+" IN LINE "+(line+1);
               }
           }

       }
       // here in order to be sured if the program does not contain any syntax error we will check the stack and the index where reached in tokens
       //if everything is correct the program has no syntax error and it will return successfull
       if(stack.isEmpty() && index==tokens.length) {
           return "Succesfull";
       }else{
           return "SYNTAX ERROR UNEXPECTED TOKEN AT THE END";
       }
   }
   public String checker(String token){
       if(!terminals.contains(token)){
           if(reservedWord.contains(token)){
               return token;
           }
       if(isName(token)){
           //System.out.println("eliananananana");
         //  System.out.println(token);
           return "name";
       }if(isIntegerValue(token)){
           return "integer-value";
       }if(isRealValue(token)){
           return "real-value";
       }
       }
    //
       //System.out.print("Checker from the methodddddd "+token);
       return token;
   }
    public static boolean isName(String token) {
        return NAME_PATTERN.matcher(token).matches();
    }

    public static boolean isIntegerValue(String token) {
        return INTEGER_VALUE_PATTERN.matcher(token).matches();
    }

    public static boolean isRealValue(String token) {
        return REAL_VALUE_PATTERN.matcher(token).matches();
    }
}
