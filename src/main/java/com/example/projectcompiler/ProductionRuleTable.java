package com.example.projectcompiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionRuleTable {
    //I define non terminals array in order to pass through them
    public static final String[] nonTerminal = {
            "module-decl", "module-heading","block","declarations", "const-decl", "const-list",
            "var-decl", "var-list", "var-item", "name-list", "more-names",
            "data-type", "procedure-decl", "procedure-heading", "stmt-list",
            "statement", "ass-stmt", "exp", "exp-prime", "term", "term-prime",
            "factor", "add-oper", "mul-oper", "read-stmt", "write-stmt",
            "write-list", "more-write-value", "write-item", "if-stmt",
            "else-part", "while-stmt", "loop-stmt", "exit-stmt", "call-stmt",
            "condition", "relational-oper", "name-value", "value"
    };
    public static final String[] terminal = {
           "=","|=" ,"<","<=",">",">=",",", "+", "-", "*", "/","(", ")", ":=",";",":",".",
            "module", "name", "begin", "end", "const", "if", "then", "else", "while",
            "do", "loop", "until", "exit", "call","integer-value","real-value",
            "ε", "var","integer", "real", "char", "procedure", "exp",
            "mod", "div", "readint", "readreal", "readchar", "readln",
            "writeint", "writereal", "writechar", "writeln",
    };
    ProducationRules rules=new ProducationRules();
    Map<String, List<String>> firstForProduction=rules.FirstCalaculations();
  Map<String, List<String>> productionRules=rules.productionRules();
///in order to build the parser table which the columns are the terminals and the rows are the non terminals
    //I did for loop in order to pass on the nonterminal and fill each terminal with its production rules
    public static final Map<String, Map<String, String>> parsingTable = new HashMap<>();
    public void buildParserTable( ){
        for(int i=0;i< nonTerminal.length;++i){

            parsingTable.put(nonTerminal[i],new HashMap<>());
            //here i will retrieve the list of terminals for each non terminal
            List<String>terminals =firstForProduction.get(nonTerminal[i]);

            int k=0;

            for(int j=0;j<terminals.size();++j){
                //here i will get the production rule for the non terminals
                if (j < productionRules.get(nonTerminal[i]).size()) {
                    k = j;
                }
               // this method aims to save under each terminal from which production it come as i explained previous
                //the terminals which separeted by ! indicates that they came from the same production rule
                if(!terminals.get(j).contains("!")) {
                    parsingTable.get(nonTerminal[i]).put(terminals.get(j), productionRules.get(nonTerminal[i]).get(k));
                }
                else{
                    splitTerminal(terminals.get(j),k,nonTerminal[i]);
                }
            }

        }

    }
    public void splitTerminal( String terminal,int k,String nonTerminal ) {
String [] token=terminal.split("!");
for(int i=0;i<token.length;++i){
    parsingTable.get(nonTerminal).put(token[i], productionRules.get(nonTerminal).get(k));
}
    }
}
