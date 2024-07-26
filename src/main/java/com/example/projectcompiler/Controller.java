package com.example.projectcompiler;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Controller {
    public String browseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String[] nonterminals = {
                    "module-decl", "module-heading","block","declarations", "const-decl", "const-list",
                    "var-decl", "var-list", "var-item", "name-list", "more-names",
                    "data-type", "procedure-decl", "procedure-heading", "stmt-list",
                    "statement", "ass-stmt", "exp", "exp-prime", "term", "term-prime",
                    "factor", "add-oper", "mul-oper", "read-stmt", "write-stmt",
                    "write-list", "more-write-value", "write-item", "if-stmt",
                    "else-part", "while-stmt", "loop-stmt", "exit-stmt", "call-stmt",
                    "condition", "relational-oper", "name-value", "value"
            };
            final String[] terminals = {
                    "=", "|=", "<", "<=", ">", ">=", ",", "+", "-", "*", "/", "(", ")", ":=", ";", ":",".",
                    "module", "name", "begin", "end", "const", "if", "then", "else", "while",
                    "do", "loop", "until", "exit", "call", "integer-value", "real-value",
                    "var", "integer", "real", "char", "procedure", "exp",
                    "mod", "div", "readint", "readreal", "readchar", "readln",
                    "writeint", "writereal", "writechar", "writeln",
            };
            ProductionRuleTable table = new ProductionRuleTable();
            table.buildParserTable();
            // here we read the file and split the tokens using the scanner class
            ScannerClass scanner=new ScannerClass();
            List<String> token=scanner.tokens(scanner.readFile(selectedFile.getAbsolutePath()));
            String content = new String(scanner.readFile(selectedFile.getAbsolutePath()));
            String[] lines = content.split("\\R");

         /*   for(int i=0;i<token.size();++i){
                System.out.println("the "+token.get(i));
            }*/
            //starting the parsing operation by calling the parsing class

            Parsing parse=new Parsing();
            String[] tokenArray = token.toArray(new String[0]);
            System.out.println();
            System.out.println(parse.Parse(tokenArray,lines));
            //printing the parsing table in order to identify how the parsing operation work
           System.out.println("LL(1) Parsing Table:");
            System.out.printf("%-15s", ""); // Print empty cell for formatting
            for (String terminal : terminals) {
                System.out.printf("%-15s", terminal);
            }
            System.out.println(); // New line
            for (String nonterminal : nonterminals) {
                System.out.printf("%-15s", nonterminal);
                Map<String, String> nonterminalRow = table.parsingTable.get(nonterminal);
                for (String terminal : terminals) {
                    String productionRule = nonterminalRow.get(terminal);
                    System.out.printf("%-15s", productionRule);
                }
                System.out.println(); // New line
            }
        }
        return selectedFile.getAbsolutePath();
        }
    }

