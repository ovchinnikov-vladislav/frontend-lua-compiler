package ic7cc.ovchinnikov.compiler.lexer;

import static ic7cc.ovchinnikov.compiler.parser.Token.*;

import ic7cc.ovchinnikov.compiler.parser.Token;
import java_cup.runtime.*;

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

  // Операторы
      "="                     { return symbol(EQ); }
      "+"                     { return symbol(PLUS); }
      "-"                     { return symbol(MINUS); }
      "*"                     { return symbol(MULTIPLICATION); }
      "/"                     { return symbol(DIVISION); }
      "^"                     { return symbol(EXPONENTIATION); }
      "%"                     { return symbol()}
      "."                     { return symbol(DOT); }
      ":"                     { return symbol(COLON); }

  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(INTEGER_LITERAL, Integer.valueOf(Integer.MIN_VALUE)); }

  {DecIntegerLiteral}            { return symbol(INTEGER_LITERAL, Integer.valueOf(yytext())); }
  {DecLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }

  {HexIntegerLiteral}            { return symbol(INTEGER_LITERAL, Integer.valueOf((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }

  {OctIntegerLiteral}            { return symbol(INTEGER_LITERAL, Integer.valueOf((int) parseLong(0, yylength(), 8))); }
  {OctLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }

  {FloatLiteral}                 { return symbol(FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(CHARACTER_LITERAL, (char)val); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(EOF); }