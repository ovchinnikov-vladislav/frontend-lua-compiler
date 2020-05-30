/* The following code was generated by JFlex 1.4.3 on 30.05.2020, 22:14 */

package ic7cc.ovchinnikov.compiler.lexer;

import ic7cc.ovchinnikov.compiler.token.*;
import ic7cc.ovchinnikov.compiler.parser.Token;
import static ic7cc.ovchinnikov.compiler.parser.Token.*;
import java_cup.runtime.*;
import java.util.regex.Pattern;
import java.text.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 30.05.2020, 22:14 from the specification file
 * <tt>D:/Compiler Construction/Course Work/ic7cc-compiler/src/main/jflex/lua.flex</tt>
 */
public class Lexer implements java_cup.runtime.Scanner, Token {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRINGSINGLE = 4;
  public static final int YYINITIAL = 0;
  public static final int STRINGBRACKET = 6;
  public static final int MULTILINE = 8;
  public static final int STRINGDOUBLE = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4, 4
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\7\1\6\1\0\1\7\1\5\22\0\1\7\1\0\1\14"+
    "\1\61\1\0\1\53\1\54\1\15\1\40\1\41\1\50\1\47\1\45"+
    "\1\10\1\4\1\51\1\3\11\2\1\46\1\44\1\57\1\12\1\56"+
    "\2\0\32\1\1\11\1\62\1\13\1\52\1\1\1\0\1\21\1\16"+
    "\1\37\1\23\1\20\1\35\1\1\1\27\1\30\1\1\1\22\1\31"+
    "\1\1\1\25\1\24\1\32\1\1\1\17\1\36\1\33\1\34\1\1"+
    "\1\26\3\1\1\42\1\55\1\43\1\60\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\5\0\1\1\1\2\2\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\15\2\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\36\1\1\1\36\1\1\1\0"+
    "\1\42\1\0\1\43\1\44\5\2\1\45\1\46\3\2"+
    "\1\47\1\50\7\2\1\51\1\52\1\53\1\54\1\55"+
    "\1\56\1\57\1\60\1\61\1\62\1\63\1\3\1\64"+
    "\1\0\2\5\1\0\3\2\1\65\1\2\1\66\1\67"+
    "\1\70\6\2\1\71\1\2\1\0\3\2\1\72\2\2"+
    "\1\73\1\74\3\2\2\5\1\0\1\75\3\2\1\76"+
    "\1\77\1\100\1\101\1\2\2\0\1\5\1\102\1\103"+
    "\1\104\1\2\1\5\1\2\1\105";

  private static int [] zzUnpackAction() {
    int [] result = new int[147];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\63\0\146\0\231\0\314\0\377\0\u0132\0\u0165"+
    "\0\u0198\0\u01cb\0\u01fe\0\u0231\0\u0264\0\u0297\0\377\0\377"+
    "\0\377\0\u02ca\0\u02fd\0\u0330\0\u0363\0\u0396\0\u03c9\0\u03fc"+
    "\0\u042f\0\u0462\0\u0495\0\u04c8\0\u04fb\0\u052e\0\377\0\377"+
    "\0\377\0\377\0\377\0\377\0\377\0\377\0\377\0\u0561"+
    "\0\377\0\377\0\377\0\377\0\u0594\0\u05c7\0\u05fa\0\377"+
    "\0\u062d\0\377\0\377\0\u0660\0\u0693\0\u06c6\0\u06f9\0\u072c"+
    "\0\u075f\0\u0792\0\u07c5\0\377\0\377\0\u07f8\0\u082b\0\u085e"+
    "\0\u0891\0\u08c4\0\u0132\0\u0132\0\u08f7\0\u092a\0\u095d\0\u0132"+
    "\0\u0132\0\u0990\0\u09c3\0\u09f6\0\u0a29\0\u0a5c\0\u0a8f\0\u0ac2"+
    "\0\377\0\377\0\377\0\377\0\377\0\377\0\377\0\377"+
    "\0\377\0\377\0\377\0\u075f\0\377\0\u0af5\0\u0b28\0\377"+
    "\0\u0b5b\0\u0b8e\0\u0bc1\0\u0bf4\0\u0132\0\u0c27\0\u0132\0\u0132"+
    "\0\u0132\0\u0c5a\0\u0c8d\0\u0cc0\0\u0cf3\0\u0d26\0\u0d59\0\u0132"+
    "\0\u0d8c\0\u0dbf\0\u0df2\0\u0e25\0\u0e58\0\u0e8b\0\u0ebe\0\u0ef1"+
    "\0\u0132\0\u0132\0\u0f24\0\u0f57\0\u0f8a\0\u0fbd\0\u0ff0\0\u1023"+
    "\0\u0132\0\u1056\0\u1089\0\u10bc\0\u0132\0\u0132\0\u0132\0\u0132"+
    "\0\u10ef\0\u0ff0\0\u1122\0\u1023\0\u0132\0\u0132\0\u0132\0\u1155"+
    "\0\u1122\0\u1188\0\u0132";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[147];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\6\1\7\1\10\1\11\1\12\3\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\7\1\26\1\27\1\30\1\31\1\7\1\32\1\33"+
    "\1\7\1\34\1\35\1\36\2\7\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51"+
    "\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\6"+
    "\5\61\1\6\1\62\5\61\1\63\45\61\1\64\5\65"+
    "\2\6\6\65\1\63\44\65\1\66\13\67\1\70\47\67"+
    "\63\6\64\0\3\7\12\0\22\7\25\0\2\10\1\71"+
    "\62\0\1\71\62\0\1\72\63\0\3\13\63\0\1\73"+
    "\63\0\1\74\63\0\1\75\51\0\3\7\12\0\1\7"+
    "\1\76\20\7\24\0\3\7\12\0\2\7\1\77\17\7"+
    "\24\0\3\7\12\0\7\7\1\100\3\7\1\101\6\7"+
    "\24\0\3\7\12\0\7\7\1\102\12\7\24\0\3\7"+
    "\12\0\6\7\1\103\13\7\24\0\3\7\12\0\1\7"+
    "\1\104\20\7\24\0\3\7\12\0\6\7\1\105\3\7"+
    "\1\106\7\7\24\0\3\7\12\0\11\7\1\107\10\7"+
    "\24\0\3\7\12\0\7\7\1\110\7\7\1\111\2\7"+
    "\24\0\3\7\12\0\6\7\1\112\13\7\24\0\3\7"+
    "\12\0\1\7\1\113\7\7\1\114\10\7\24\0\3\7"+
    "\12\0\7\7\1\115\12\7\24\0\3\7\12\0\3\7"+
    "\1\116\2\7\1\117\7\7\1\120\3\7\74\0\1\121"+
    "\23\0\1\122\43\0\1\123\16\0\1\124\44\0\1\125"+
    "\15\0\1\126\50\0\5\61\2\0\5\61\1\0\45\61"+
    "\15\0\1\127\2\0\1\130\5\0\1\62\5\0\1\131"+
    "\26\0\1\132\5\65\2\0\6\65\1\0\44\65\7\0"+
    "\1\62\6\0\1\133\1\0\1\130\5\0\1\62\5\0"+
    "\1\131\26\0\1\132\13\67\1\0\47\67\13\0\1\63"+
    "\51\0\2\134\63\0\1\135\56\0\5\136\1\137\1\140"+
    "\2\136\1\141\51\136\1\0\3\7\12\0\2\7\1\142"+
    "\17\7\24\0\3\7\12\0\14\7\1\143\1\144\4\7"+
    "\24\0\3\7\12\0\5\7\1\145\14\7\24\0\3\7"+
    "\12\0\20\7\1\146\1\7\24\0\3\7\12\0\5\7"+
    "\1\147\14\7\24\0\3\7\12\0\15\7\1\150\4\7"+
    "\24\0\3\7\12\0\13\7\1\151\6\7\24\0\3\7"+
    "\12\0\12\7\1\152\7\7\24\0\3\7\12\0\21\7"+
    "\1\153\24\0\3\7\12\0\16\7\1\154\3\7\24\0"+
    "\3\7\12\0\2\7\1\155\17\7\24\0\3\7\12\0"+
    "\15\7\1\156\4\7\24\0\3\7\12\0\13\7\1\157"+
    "\6\7\24\0\3\7\12\0\1\7\1\160\20\7\24\0"+
    "\3\7\12\0\7\7\1\161\12\7\23\0\5\136\1\137"+
    "\1\140\54\136\6\0\1\140\54\0\5\136\1\137\1\140"+
    "\2\136\1\162\1\141\50\136\1\0\3\7\12\0\3\7"+
    "\1\163\16\7\24\0\3\7\12\0\2\7\1\164\17\7"+
    "\24\0\3\7\12\0\16\7\1\165\3\7\24\0\3\7"+
    "\12\0\2\7\1\166\17\7\24\0\3\7\12\0\13\7"+
    "\1\167\6\7\24\0\3\7\12\0\3\7\1\170\16\7"+
    "\24\0\3\7\12\0\2\7\1\171\17\7\24\0\3\7"+
    "\12\0\7\7\1\172\12\7\24\0\3\7\12\0\12\7"+
    "\1\173\7\7\24\0\3\7\12\0\20\7\1\174\1\7"+
    "\24\0\3\7\12\0\21\7\1\175\23\0\5\162\1\176"+
    "\1\177\4\162\1\200\47\162\1\0\3\7\12\0\4\7"+
    "\1\201\15\7\24\0\3\7\12\0\3\7\1\202\16\7"+
    "\24\0\3\7\12\0\1\7\1\203\20\7\24\0\3\7"+
    "\12\0\12\7\1\204\7\7\24\0\3\7\12\0\2\7"+
    "\1\205\17\7\24\0\3\7\12\0\13\7\1\206\6\7"+
    "\24\0\3\7\12\0\13\7\1\207\6\7\24\0\3\7"+
    "\12\0\2\7\1\210\17\7\24\0\3\7\12\0\15\7"+
    "\1\211\4\7\23\0\6\212\1\177\4\212\1\213\62\212"+
    "\1\213\47\212\5\162\1\176\1\177\3\162\1\200\1\214"+
    "\47\162\1\0\3\7\12\0\15\7\1\215\4\7\24\0"+
    "\3\7\12\0\7\7\1\216\12\7\24\0\3\7\12\0"+
    "\17\7\1\217\2\7\24\0\3\7\12\0\12\7\1\220"+
    "\7\7\23\0\12\212\1\213\1\221\47\212\1\0\3\7"+
    "\12\0\6\7\1\222\13\7\24\0\3\7\12\0\7\7"+
    "\1\223\12\7\23\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4539];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\5\0\1\11\10\1\3\11\15\1\11\11\1\1\4\11"+
    "\3\1\1\11\1\1\2\11\5\1\1\0\1\1\1\0"+
    "\2\11\23\1\13\11\1\1\1\11\1\0\1\1\1\11"+
    "\1\0\20\1\1\0\15\1\1\0\11\1\2\0\10\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[147];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

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



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 134) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 67: 
          { return symbol(RETURN);
          }
        case 70: break;
        case 34: 
          { return symbol(CONCAT);
          }
        case 71: break;
        case 20: 
          { return symbol(MUL);
          }
        case 72: break;
        case 68: 
          { return symbol(ELSEIF);
          }
        case 73: break;
        case 35: 
          { string.setLength(0); yybegin(STRINGBRACKET);
          }
        case 74: break;
        case 4: 
          { return symbol(DOT);
          }
        case 75: break;
        case 49: 
          { string.append("\t");
          }
        case 76: break;
        case 46: 
          { return symbol(NOTEQ);
          }
        case 77: break;
        case 43: 
          { return symbol(BRIGHT);
          }
        case 78: break;
        case 26: 
          { return symbol(MORE);
          }
        case 79: break;
        case 27: 
          { return symbol(LESS);
          }
        case 80: break;
        case 17: 
          { return symbol(COMMA);
          }
        case 81: break;
        case 59: 
          { return symbol(TRUE);
          }
        case 82: break;
        case 16: 
          { return symbol(SEMICOLON);
          }
        case 83: break;
        case 65: 
          { return symbol(FALSE);
          }
        case 84: break;
        case 12: 
          { return symbol(LPAREN);
          }
        case 85: break;
        case 58: 
          { return symbol(ELSE);
          }
        case 86: break;
        case 50: 
          { string.append("\\");
          }
        case 87: break;
        case 19: 
          { return symbol(ADD);
          }
        case 88: break;
        case 69: 
          { return symbol(FUNCTION);
          }
        case 89: break;
        case 53: 
          { return symbol(END);
          }
        case 90: break;
        case 28: 
          { return symbol(BXOR);
          }
        case 91: break;
        case 42: 
          { return symbol(MOREEQ);
          }
        case 92: break;
        case 60: 
          { return symbol(THEN);
          }
        case 93: break;
        case 48: 
          { string.append("\r");
          }
        case 94: break;
        case 15: 
          { return symbol(RBRACE);
          }
        case 95: break;
        case 37: 
          { return symbol(DO);
          }
        case 96: break;
        case 55: 
          { return symbol(NOT);
          }
        case 97: break;
        case 54: 
          { return symbol(AND);
          }
        case 98: break;
        case 21: 
          { return symbol(DIV);
          }
        case 99: break;
        case 40: 
          { return symbol(IF);
          }
        case 100: break;
        case 47: 
          { string.append("\"");
          }
        case 101: break;
        case 22: 
          { return symbol(POW);
          }
        case 102: break;
        case 8: 
          { return symbol(ASSIGNMENT);
          }
        case 103: break;
        case 23: 
          { return symbol(MOD);
          }
        case 104: break;
        case 51: 
          { string.append("\'");
          }
        case 105: break;
        case 39: 
          { return symbol(IN);
          }
        case 106: break;
        case 38: 
          { return symbol(OR);
          }
        case 107: break;
        case 6: 
          { return symbol(SUB);
          }
        case 108: break;
        case 1: 
          { throw new RuntimeException("Illegal character <" + Pattern.quote(yytext()) + ">");
          }
        case 109: break;
        case 10: 
          { string.setLength(0); yybegin(STRINGDOUBLE);
          }
        case 110: break;
        case 7: 
          { return symbol(LBRACKET);
          }
        case 111: break;
        case 66: 
          { return symbol(REPEAT);
          }
        case 112: break;
        case 52: 
          { return symbol(PARAMS);
          }
        case 113: break;
        case 31: 
          { string.append("\n");
          }
        case 114: break;
        case 13: 
          { return symbol(RPAREN);
          }
        case 115: break;
        case 30: 
          { string.append(yytext());
          }
        case 116: break;
        case 2: 
          { return symbol(NAME, yytext());
          }
        case 117: break;
        case 45: 
          { return symbol(BLEFT);
          }
        case 118: break;
        case 63: 
          { return symbol(LOCAL);
          }
        case 119: break;
        case 3: 
          { if (yytext().matches("\\d+\\.\\d+"))
                                   return symbol(NUMERAL, Double.parseDouble(yytext()));
                                else if (yytext().matches("\\d"))
                                   return symbol(NUMERAL, Long.parseLong(yytext()));
                                else
                                   throw new RuntimeException("Expected Numeral");
          }
        case 120: break;
        case 18: 
          { return symbol(COLON);
          }
        case 121: break;
        case 11: 
          { string.setLength(0); yybegin(STRINGSINGLE);
          }
        case 122: break;
        case 29: 
          { return symbol(LENGTH);
          }
        case 123: break;
        case 24: 
          { return symbol(BAND);
          }
        case 124: break;
        case 32: 
          { yybegin(YYINITIAL); return symbol(LITERAL_STRING, string.toString());
          }
        case 125: break;
        case 61: 
          { return symbol(BREAK);
          }
        case 126: break;
        case 57: 
          { return symbol(FOR);
          }
        case 127: break;
        case 62: 
          { return symbol(WHILE);
          }
        case 128: break;
        case 36: 
          { return symbol(EQUAL);
          }
        case 129: break;
        case 56: 
          { return symbol(NIL);
          }
        case 130: break;
        case 64: 
          { return symbol(UNTIL);
          }
        case 131: break;
        case 14: 
          { return symbol(LBRACE);
          }
        case 132: break;
        case 9: 
          { return symbol(RBRACKET);
          }
        case 133: break;
        case 44: 
          { return symbol(LESSEQ);
          }
        case 134: break;
        case 41: 
          { return symbol(FDIV);
          }
        case 135: break;
        case 25: 
          { return symbol(BOR);
          }
        case 136: break;
        case 5: 
          { 
          }
        case 137: break;
        case 33: 
          { string.append("");
          }
        case 138: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     return new java_cup.runtime.Symbol(Token.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface Token
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  private String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = Token.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This main method is the debugging routine for the scanner.
   * It prints debugging information about each returned token to
   * System.out until the end of file is reached, or an error occured.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Lexer <inputfile>");
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        Lexer scanner = null;
        try {
          scanner = new Lexer( new java.io.FileReader(argv[i]) );
          while ( !scanner.zzAtEOF ) scanner.debug_next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
