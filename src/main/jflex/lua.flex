package ic7cc.ovchinnikov.compiler.lexer;

import static ic7cc.ovchinnikov.compiler.parser.Token.*;

import java_cup.runtime.*;import java.util.regex.Pattern;

%%

%public
%class Lexer // Имя генерируемого класса лексического анализатора

%unicode

%line
%column

%cup
%cupdebug
%cupsym Token // Имя класса с токенами, который сгенерил cup

%{
  StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

  /** 
   * assumes correct representation of a long value for 
   * specified radix in scanner buffer from <code>start</code> 
   * to <code>end</code> 
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
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
CommentContent = (!("]""="*"]"))

%state STRINGDOUBLE
%state STRINGSINGLE
%state STRINGBRACKET
%state MULTILINE

%%

<YYINITIAL> {

  // Ключевые слова
      "break"                 { return symbol(BREAK); }
      "goto"                  { return symbol(GOTO); }
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

  // Бинарные операторы,
      "+"                     { return symbol(PLUS); }
      "-"                     { return symbol(MINUS); }
      "*"                     { return symbol(MULTIPLICATION); }
      "/"                     { return symbol(DIVISION); }
      "^"                     { return symbol(EXPONENTIATION); }
      "%"                     { return symbol(MOD); }
      "&"                     { return symbol(BITWISEAND); }
      "|"                     { return symbol(BITWISEOR); }
      ">>"                    { return symbol(BITWISERIGHT); }
      "<<"                    { return symbol(BITWISELEFT); }
      ".."                    { return symbol(CONCAT); }
      "<"                     { return symbol(LESS);}
      "<="                    { return symbol(LESSEQ); }
      ">"                     { return symbol(MORE); }
      ">="                    { return symbol(MOREEQ); }
      "=="                    { return symbol(EQEQ); }
      "~="                    { return sybmol(NOTEQ); }
      "and"                   { return symbol(AND); }
      "or"                    { return symbol(OR); }
      "."                     { return symbol(DOT); }
      ":"                     { return symbol(COLON); }

  // Унарные операторы (исключение, нет минуса - он входит и в бинарные, и в унарные операторы
      "~"                     { return symbol(BITWISENOT); }
      "not"                   { return sybmol(NOT); }
      "#"                     { return symbol(LATTICE); } // длина строки

  // Идентификатор
      {Name}                  { return symbol(ID, yytext()); }

  // Числовой литерал
      {Number}                { return symbol(NUMBER, new Double(Double.parseDouble(yytext())));}

  // Присваивание
      "="                     { return symbol(EQ); }

  // Пробелы
      {WhiteSpace}            { }

  // Комментарии
      {Comment}               { }
}

<STRINGDOUBLE> {
      "\""                    { yybegin(YYINITIAL); return symbol(STRING, string.toString()); }
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
      \]\]                    { yybegin(YYINITIAL); return symbol(STRING, string.toString()); }
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
      "'"                     { yybegin(YYINITIAL); return symbol(STRING, string.toString()); }
      [^\n\r"'"\\]+           { string.append(yytext()); }
      "\\n"                   { string.append("\n"); }
      "\\r"                   { string.append("\r"); }
      "\\t"                   { string.append("\t"); }
      \\\\                    { string.append("\\"); }
      "\\\n"                  { string.append("\n"); }
      \\\'                    { string.append("\'"); }
}

.|\n                          { throw new RuntimeException("Illegal character <" + Pattern.quote(yytext()) + ">"); }