package com.example.projectcompiler;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProducationRules {

   public Map<String, List<String>> FirstCalaculations() {
       //here I calculate the first for each nonterminal and store them in map which store each nonterminals with its first sets
       final Map<String, List<String>> firstForProductionRule = new LinkedHashMap<>();
       firstForProductionRule.put("module-decl", Arrays.asList("module"));
       firstForProductionRule.put("module-heading", Arrays.asList("module"));
       firstForProductionRule.put("block", Arrays.asList("begin"));
       firstForProductionRule.put("declarations", Arrays.asList("const","procedure","begin","var"));
       firstForProductionRule.put("const-decl", Arrays.asList("const","procedure","begin","var"));
       firstForProductionRule.put("const-list", Arrays.asList("name","procedure","begin","var"));
       firstForProductionRule.put("var-decl", Arrays.asList("var","procedure","begin"));
       firstForProductionRule.put("var-list", Arrays.asList("name","procedure","begin"));
       firstForProductionRule.put("var-item", Arrays.asList("name"));
       firstForProductionRule.put("name-list", Arrays.asList("name"));
       firstForProductionRule.put("more-names", Arrays.asList(",",":",")"));
       firstForProductionRule.put("data-type", Arrays.asList("integer", "real", "char"));
       firstForProductionRule.put("procedure-decl", Arrays.asList("procedure", "begin"));
       firstForProductionRule.put("procedure-heading", Arrays.asList("procedure"));
       // i seperate the first sets that comes from the first production by !  for example
       //stmt-list 🡪     statement     ;     stmt-list         |        λ  here is the production rule
       //in order to know that the first sets for the production(  statement     ;     stmt-list) cames from these production i seperate them by !
       //so the remaining  first sets will be determined  that come from lamda which make it easy in order to build the parsing table
       firstForProductionRule.put("stmt-list", Arrays.asList("name!readint!readreal!readchar!readln!writeint!writereal!writechar!writeln!if!while!loop!exit!call!begin!;","end","else","until"));
       firstForProductionRule.put("statement", Arrays.asList("name","readint!readreal!readchar!readln","writeint!writechar!writereal!writeln","if","while","loop","exit","call","begin",";"));
       firstForProductionRule.put("ass-stmt", Arrays.asList("name"));
       firstForProductionRule.put("exp", Arrays.asList("(","name","integer-value","real-value"));
       firstForProductionRule.put("exp-prime", Arrays.asList("+!-",";",")"));
       firstForProductionRule.put("term", Arrays.asList("(","name","integer-value","real-value"));
       firstForProductionRule.put("term-prime", Arrays.asList("*!/!mod!div","+","-",";",")"));
       firstForProductionRule.put("factor", Arrays.asList("(","name","integer-value","real-value"));
       firstForProductionRule.put("add-oper", Arrays.asList("+", "-"));
       firstForProductionRule.put("mul-oper", Arrays.asList("*", "/", "mod", "div"));
       firstForProductionRule.put("read-stmt", Arrays.asList("readint","readreal" ,"readchar","readln"));
       firstForProductionRule.put("write-stmt", Arrays.asList("writeint","writereal","writechar", "writeln"));
       firstForProductionRule.put("write-list", Arrays.asList("name","integer-value", "real-value"));
       firstForProductionRule.put("more-write-value", Arrays.asList(",", ")"));
       firstForProductionRule.put("write-item", Arrays.asList("name","integer-value", "real-value"));
       firstForProductionRule.put("if-stmt", Arrays.asList("if"));
       firstForProductionRule.put("else-part", Arrays.asList("else", "end"));
       firstForProductionRule.put("while-stmt", Arrays.asList("while"));
       firstForProductionRule.put("loop-stmt", Arrays.asList("loop"));
       firstForProductionRule.put("exit-stmt", Arrays.asList("exit"));
       firstForProductionRule.put("call-stmt", Arrays.asList("call"));
       firstForProductionRule.put("condition", Arrays.asList("name","integer-value", "real-value"));
       firstForProductionRule.put("relational-oper", Arrays.asList("=", "|=", "<", "<=", ">", ">="));
       firstForProductionRule.put("name-value", Arrays.asList("name","integer-value","real-value"));
       firstForProductionRule.put("value", Arrays.asList("integer-value", "real-value"));
        return firstForProductionRule;
    }
    //here I store the production rules in a map
    public Map<String, List<String>>  productionRules() {
       Map<String, List<String>> productionRules = new LinkedHashMap<>();
            productionRules.put("module-decl", Arrays.asList("module-heading declarations block name ."));
            productionRules.put("module-heading", Arrays.asList("module name ;"));
            productionRules.put("block", Arrays.asList("begin stmt-list end"));
            productionRules.put("declarations", Arrays.asList("const-decl var-decl procedure-decl"));
            productionRules.put("const-decl", Arrays.asList("const const-list", "ε"));
            productionRules.put("const-list", Arrays.asList("name = value ; const-list", "ε"));
            productionRules.put("var-decl", Arrays.asList("var var-list", "ε"));
            productionRules.put("var-list", Arrays.asList("var-item ; var-list", "ε"));
            productionRules.put("var-item", Arrays.asList("name-list : data-type"));
            productionRules.put("name-list", Arrays.asList("name more-names"));
            productionRules.put("more-names", Arrays.asList(", name-list", "ε"));
            productionRules.put("data-type", Arrays.asList("integer", "real", "char"));
            productionRules.put("procedure-decl", Arrays.asList("procedure-heading declarations block name ; procedure-decl", "ε"));
            productionRules.put("procedure-heading", Arrays.asList("procedure name ;"));
            productionRules.put("stmt-list", Arrays.asList("statement ; stmt-list", "ε"));
            productionRules.put("statement", Arrays.asList("ass-stmt", "read-stmt", "write-stmt", "if-stmt", "while-stmt", "loop-stmt", "exit-stmt", "call-stmt", "block", "ε"));
            productionRules.put("ass-stmt", Arrays.asList("name := exp"));
            productionRules.put("exp", Arrays.asList("term exp-prime"));
            productionRules.put("exp-prime", Arrays.asList("add-oper term exp-prime", "ε"));
            productionRules.put("term", Arrays.asList("factor term-prime"));
            productionRules.put("term-prime", Arrays.asList("mul-oper factor term-prime", "ε"));
            productionRules.put("factor", Arrays.asList("( exp )", "name-value"));
            productionRules.put("add-oper", Arrays.asList("+", "-"));
            productionRules.put("mul-oper", Arrays.asList("*", "/", "mod", "div"));
            productionRules.put("read-stmt", Arrays.asList("readint ( name-list )", "readreal ( name-list )", "readchar ( name-list )", "readln"));
            productionRules.put("write-stmt", Arrays.asList("writeint ( write-list )", "writereal ( write-list )", "writechar ( write-list )", "writeln"));
            productionRules.put("write-list", Arrays.asList("write-item more-write-value"));
            productionRules.put("more-write-value", Arrays.asList(", write-list", "ε"));
            productionRules.put("write-item", Arrays.asList("name", "value"));
            productionRules.put("if-stmt", Arrays.asList("if condition then stmt-list else-part end"));
            productionRules.put("else-part", Arrays.asList("else stmt-list", "ε"));
            productionRules.put("while-stmt", Arrays.asList("while condition do stmt-list end"));
            productionRules.put("loop-stmt", Arrays.asList("loop stmt-list until condition"));
            productionRules.put("exit-stmt", Arrays.asList("exit"));
            productionRules.put("call-stmt", Arrays.asList("call name"));
            productionRules.put("condition", Arrays.asList("name-value relational-oper name-value"));
            productionRules.put("relational-oper", Arrays.asList("=", "|=", "<", "<=", ">", ">="));
            productionRules.put("name-value", Arrays.asList("name", "value"));
            productionRules.put("value", Arrays.asList("integer-value", "real-value"));
        return productionRules;
    }
}

