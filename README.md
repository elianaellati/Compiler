#LL(1) Parser
This repository contains the implementation of an LL(1) parser, a type of top-down parser for a subset of context-free grammars. LL(1) parsers are efficient and straightforward, using a single lookahead token to make parsing decisions.

#Overview
The LL(1) parser is constructed based on the LL(1) algorithm, which relies on the following key components:

1- Grammar Definition: The context-free grammar (CFG) to be parsed, defined in Backus-Naur Form (BNF).
2- First and Follow Sets: These sets are used to predict which production rule to apply, based on the current lookahead token.
3- Parse Table: A table that maps non-terminal symbols and lookahead tokens to the appropriate production rules.
4- Parsing Process: The actual parsing process, where input strings are processed to construct a parse tree or determine if the string belongs to the language defined by the grammar.
