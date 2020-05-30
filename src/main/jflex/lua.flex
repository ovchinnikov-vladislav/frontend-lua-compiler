package ic7cc.ovchinnikov.compiler.lexer;

import ic7cc.ovchinnikov.compiler.token.*;
import ic7cc.ovchinnikov.compiler.parser.Token;
import static ic7cc.ovchinnikov.compiler.parser.Token.*;
import java_cup.runtime.*;
import java.util.regex.Pattern;
import java.text.*;

%%

%public
// Имя генерируемого класса лексического анализатора
%class Lexer

%line
%column

%cup
%cupdebug
// Имя класса с токенами, который сгенерил cup
%cupsym Token
// Генерирование верного токена окончания файла
%eofval{
    return new java_cup.runtime.Symbol(Token.EOF);
%eofval}

%implements Token

%{

  SymbolFactory symbolFactory = new CustomSymbolFactory();

  public SymbolFactory getSymbolFactory() {
  	return symbolFactory;
  }

  StringBuffer string = new StringBuffer();

  private Symbol symbol(int sym) {
    Symbol symb = new Symbol(sym, yyline+1, yycolumn+1);
    return symb;
  }

  private Symbol symbol(int sym, Object val) {
    Symbol symb = new Symbol(sym, yyline+1, yycolumn+1, val);
    return symb;
  }

  private void error(String message) {
    System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }

%}

// Идентификатор
Name = [_A-Za-z]+[_A-Za-z0-9]*

// Числовой литерал
Number = (0 | [1-9][0-9]*) ("."[0-9]+)?

// Разделители строк
LineTerminator = \r|\n|\r\n

// Все символы, кроме символов перехода на новую строку
InputCharacter = [^\r\n]

// Символ, определяющий пробел
WhiteSpace = [ \r\n\t\f]+

Comment = {EndOfLineComment} | {MultipleLineComment}

// Однострочный комментраций
EndOfLineComment = "--" {InputCharacter}* {LineTerminator}

// Многострочный комментарий
MultipleLineComment = "--[""="*"[" {CommentContent} "]""="*"]"

// Пока не встретит закрывающие скобки, это комментарий
CommentContent = (!("]""="*"]"))*

%state STRINGDOUBLE
%state STRINGSINGLE
%state STRINGBRACKET
%state MULTILINE

%%

<YYINITIAL> {

       \"                     { string.setLength(0); yybegin(STRINGDOUBLE); }
       \'                     { string.setLength(0); yybegin(STRINGSINGLE); }
       \[\[                   { string.setLength(0); yybegin(STRINGBRACKET); }

  // Ключевые слова
      "break"                 { return symbol(BREAK); }
      "do"                    { return symbol(DO); }
      "end"                   { return symbol(END); }
      "while"                 { return symbol(WHILE); }
      "repeat"                { return symbol(REPEAT); }
      "until"                 { return symbol(UNTIL); }
      "if"                    { return symbol(IF); }
      "then"                  { return symbol(THEN); }
      "elseif"                { return symbol(ELSEIF); }
      "else"                  { return symbol(ELSE); }
      "for"                   { return symbol(FOR); }
      "in"                    { return symbol(IN); }
      "function"              { return symbol(FUNCTION); }
      "local"                 { return symbol(LOCAL); }
      "return"                { return symbol(RETURN); }

  // Булевы литералы
      "true"                  { return symbol(TRUE); }
      "false"                 { return symbol(FALSE); }

  // Специальные значения
      "nil"                   { return symbol(NIL); }
      "..."                   { return symbol(PARAMS); }

  // Разделители
      "["                     { return symbol(LBRACKET); }
      "]"                     { return symbol(RBRACKET); }
      "("                     { return symbol(LPAREN); }
      ")"                     { return symbol(RPAREN); }
      "{"                     { return symbol(LBRACE); }
      "}"                     { return symbol(RBRACE); }
      ";"                     { return symbol(SEMICOLON); }
      ","                     { return symbol(COMMA); }
      "."                     { return symbol(DOT); }
      ":"                     { return symbol(COLON); }

  // Бинарные операторы,
      "+"                     { return symbol(ADD); }
      "-"                     { return symbol(SUB); }
      "*"                     { return symbol(MUL); }
      "/"                     { return symbol(DIV); }
      "//"                    { return symbol(FDIV); }
      "^"                     { return symbol(POW); }
      "%"                     { return symbol(MOD); }
      "&"                     { return symbol(BAND); }
      "|"                     { return symbol(BOR); }
      ">>"                    { return symbol(BRIGHT); }
      "<<"                    { return symbol(BLEFT); }
      ".."                    { return symbol(CONCAT); }
      "<"                     { return symbol(LESS);}
      "<="                    { return symbol(LESSEQ); }
      ">"                     { return symbol(MORE); }
      ">="                    { return symbol(MOREEQ); }
      "=="                    { return symbol(EQUAL); }
      "~="                    { return symbol(NOTEQ); }
      "and"                   { return symbol(AND); }
      "or"                    { return symbol(OR); }

  // Унарные операторы (исключение, нет минуса - он входит и в бинарные, и в унарные операторы
      "not"                   { return symbol(NOT); }
      "#"                     { return symbol(LENGTH); } // длина строки

   // Операция побитового НЕ и побитового исключающего ИЛИ
      "~"                     { return symbol(BXOR); }


 // Присваивание
      "="                     { return symbol(ASSIGNMENT); }

  // Идентификатор
      {Name}                  { return symbol(NAME, yytext()); }

  // Числовой литерал
      {Number}                {
                                if (yytext().matches("\\d+\\.\\d+"))
                                   return symbol(NUMERAL, Double.parseDouble(yytext()));
                                else if (yytext().matches("\\d"))
                                   return symbol(NUMERAL, Long.parseLong(yytext()));
                                else
                                   throw new RuntimeException("Expected Numeral");
                              }

  // Пробелы
      {WhiteSpace}            { }

  // Комментарии
      {Comment}               { }
}

<STRINGDOUBLE> {
      "\""                    { yybegin(YYINITIAL); return symbol(LITERAL_STRING, string.toString()); }
      [^\n\r\"\\]+            { string.append(yytext()); }
      "\\n"                   { string.append("\n"); }
      "\\r"                   { string.append("\r"); }
      "\\t"                   { string.append("\t"); }
      \\\"                    { string.append("\""); }
      "\n"                    { string.append("\n"); }
      \\\\                    { string.append("\\"); }
      "\\"                    { string.append(""); }
}

<STRINGBRACKET> {
      \]\]                    { yybegin(YYINITIAL); return symbol(LITERAL_STRING, string.toString()); }
      [^\]]+                  { string.append(yytext()); }
      "\\n"                   { string.append("\n"); }
      "\\r"                   { string.append("\r"); }
      "\\t"                   { string.append("\t"); }
      \\\"                    { string.append("\""); }
      "\n"                    { string.append("\n"); }
      \\\\                    { string.append("\\"); }
      "\\"                    { string.append(""); }
}

<STRINGSINGLE> {
      "'"                     { yybegin(YYINITIAL); return symbol(LITERAL_STRING, string.toString()); }
      [^\n\r"'"\\]+           { string.append(yytext()); }
      "\\n"                   { string.append("\n"); }
      "\\r"                   { string.append("\r"); }
      "\\t"                   { string.append("\t"); }
      \\\\                    { string.append("\\"); }
      "\\\n"                  { string.append("\n"); }
      \\\'                    { string.append("\'"); }
}

      .|\n                    { throw new RuntimeException("Illegal character <" + Pattern.quote(yytext()) + ">"); }