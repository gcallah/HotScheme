
import java.applet.Applet;


public class LispInterpreter
{
    public LispInterpreter(LispTerminal term, Applet my_applet)
        throws SchemeException
    {
        MyApplet = my_applet;
        MyTerm   = term;

        try
		{
	        load_global_env(term);
			if(!(term instanceof GUILispTerminal))
			{
		        read_eval_print_loop(term, global_env);
			}
		}
        catch(SchemeException scheme_exception)
		{
			throw scheme_exception;
		}
    }

    private void load_global_env(LispTerminal term)
		throws SchemeException
    {
        try
        {
// the global environment itself is made available as a Scheme object:
        	global_env.Intern(GLOBAL_ENV_NAME, global_env);

// as is the applet and context
            global_env.Intern(APPLET_NAME,
                (MyApplet == null) ?
                SchemeObject.False
                : SchemeObject.make(MyApplet));
            global_env.Intern(APPLET_CONTEXT_NAME,
                (MyApplet == null) ?
                SchemeObject.False
                : SchemeObject.make(MyApplet.getAppletContext()));

//  boolean constants:
        	global_env.Intern(T_NAME,        SchemeObject.True);
        	global_env.Intern(F_NAME,        SchemeObject.False);

// arithmetic:
        	global_env.Intern(PLUS_NAME,     new Plus());
        	global_env.Intern(MINUS_NAME,    new Minus());
        	global_env.Intern(MULT_NAME,     new Mult());
        	global_env.Intern(DIV_NAME,      new Div());
        	global_env.Intern(GT_NAME,       new GreaterThan());
        	global_env.Intern(LT_NAME,       new LessThan());

// predicates:
        	global_env.Intern(EQ_NAME,       new Eq());
        	global_env.Intern(EQV_NAME,      new Eqv());
        	global_env.Intern(EQUAL_NAME,    new Equal());
        	global_env.Intern(NULLP_NAME,    new Nullp());
        	global_env.Intern(SYMBOLP_NAME,  new Symbolp());
        	global_env.Intern(CHARP_NAME,    new Charp());
        	global_env.Intern(STRINGP_NAME,  new Stringp());
        	global_env.Intern(NUMBERP_NAME,  new Numberp());
        	global_env.Intern(INTEGERP_NAME, new Integerp());
        	global_env.Intern(RATIONALP_NAME,new Rationalp());
        	global_env.Intern(LISTP_NAME,    new Listp());
        	global_env.Intern(PROCEDUREP_NAME,
        	    new Procedurep());
        	global_env.Intern(VECTORP_NAME,  new Vectorp());

// list operations:
        	global_env.Intern(APPEND_NAME,   new Append());
        	global_env.Intern(CONS_NAME,     new Cons());
        	global_env.Intern(LENGTH_NAME,   new Length());
        	global_env.Intern(MAP_NAME,      new Map());
        	global_env.Intern(MEMBER_NAME,   new Member());
        	global_env.Intern(MEMQ_NAME,     new Memq());
        	global_env.Intern(MEMV_NAME,     new Memv());
// the reverse functor is made global for use around the system:
        	global_env.Intern(REVERSE_NAME,  reverse = new Reverse());
        	global_env.Intern(FIRST_NAME,
        	    global_env.Intern(CAR_NAME,  First.car));
        	global_env.Intern(REST_NAME, global_env.Intern(CDR_NAME,
        	                                 Rest.cdr));
        	global_env.Intern(CAAR_NAME,     new Caar());
        	global_env.Intern(CADR_NAME, global_env.Intern(SECOND_NAME,
        	                                 new Second()));
        	global_env.Intern(CDAR_NAME,     new Cdar());
        	global_env.Intern(CDDR_NAME,     new Cddr());
        	global_env.Intern(CAAAR_NAME,    new Caaar());
        	global_env.Intern(CADAR_NAME,    new Cadar());
        	global_env.Intern(CAADR_NAME,    new Caadr());
        	global_env.Intern(CADDR_NAME,    new Caddr());
        	global_env.Intern(CDAAR_NAME,    new Cdaar());
        	global_env.Intern(CDADR_NAME,    new Cdadr());
        	global_env.Intern(CDDAR_NAME,    new Cddar());
        	global_env.Intern(CDDDR_NAME,    new Cdddr());
        	global_env.Intern(LISTREF_NAME,  new ListRef());

// type converters:
            global_env.Intern(LIST_STRING_NAME,  new ListString());
            global_env.Intern(STRING_LIST_NAME,  new StringList());

// string operations:
            global_env.Intern(SUBSTRING_NAME,    new Substring());
            global_env.Intern(STRINGAPPEND_NAME, new StringAppend());
            global_env.Intern(STRINGREF_NAME,    new StringRef());
            global_env.Intern(STRINGSET_NAME,    new StringSet());

// vector operations:
            global_env.Intern(VECTORLENGTH_NAME, new VectorLength());
            global_env.Intern(VECTORREF_NAME,    new VectorRef());
            global_env.Intern(VECTORSET_NAME,    new VectorSet());

// io:
            global_env.Intern(LOAD_NAME,         new Load());
            global_env.Intern(TERM_NAME,         term);

// java objects:
            global_env.Intern(FRAME_NAME,     new FrameRep());
            global_env.Intern(BUTTON_NAME,    new ButtonRep());
            global_env.Intern(LABEL_NAME,     new LabelRep());
            global_env.Intern(LAYOUT_NAME,    new LayoutRep());
            global_env.Intern(TEXTFIELD_NAME, new TextFieldRep());
            global_env.Intern(URL_NAME,       new URLRep());
        }
        catch(SchemeException scheme_exception)
        {
            term.println(PROG_NAME + " error loading global env: "
                + scheme_exception.getMessage());
        }
        catch(NullPointerException np_exception)
        {
            term.println(PROG_NAME
                + " internal error: catching null pointer");
            System.exit(1);
        }

        if(trace_on) global_env.Display();
    }


    public boolean read_eval_print_loop(LispTerminal term)
    {
        return read_eval_print_loop(term, global_env);
    }

    
    public static boolean read_eval_print_loop(LispTerminal term, Environment env)
    {
        while(true)
        {
			if(!(term instanceof BufferedLispTerminal)) term.print(PROG_NAME + " >> ");

            try
            {
                term.println(((SchemeObject.make(term, SchemeObject.START, env)).Eval(env)).Display());
            }
            catch(QuitException quit_msg)
            {
                term.println(quit_msg.Print());
                System.exit(0);
            }
            catch(SpecialFormException special)
            {
    			if(term instanceof GUILispTerminal) return true;
            }
            catch(SchemeException scheme_exception)
            {
                term.println(PROG_NAME + " error: "
                    + scheme_exception.Print());
                if(term instanceof GUILispTerminal) return false;
            }
            catch(NullPointerException np_exception)
            {
                term.println(PROG_NAME
                    + " internal error: catching null pointer");
                if(term instanceof GUILispTerminal) return false;
            }
			if(term.eof()) break;
        }
        return true;
    }


    public static Environment getGlobalEnv() { return global_env; }

    
    public static void trace_print(String s)
    {
        if(trace_on)
            MyTerm.println(s);
    }


    public static final String PROG_NAME           = "HotScheme";
    public static final String TOP_LEVEL_DEFS      = "top-level-defs";

// globals in HotScheme:
    public static final String GLOBAL_ENV_NAME     = "global-env";
    public static final String APPLET_NAME         = "applet";
    public static final String APPLET_CONTEXT_NAME = "applet-context";

// core forms:
// (these are the forms that are handled directly by the interpreter,
//   rather than through the function application mechanism)
    public static final String DEFINE_NAME       = "define";
    public static final String LAMBDA_NAME       = "lambda";
    public static final String QUIT_NAME         = "quit";
    public static final String QUOTE_NAME        = "quote";
    public static final String IF_NAME           = "if";
    public static final String AND_NAME          = "and";
    public static final String OR_NAME           = "or";
    public static final String SEND_MESSAGE_NAME = "send-message";
    public static final String SET_NAME          = "set!";
    public static final String TRACE_ON_NAME     = "trace-on";
    public static final String TRACE_OFF_NAME    = "trace-off";
    public static final String LET_NAME          = "let";
    public static final String LETREC_NAME       = "letrec";
    public static final String DO_NAME           = "do";
    public static final String BEGIN_NAME        = "begin";
    public static final String ELSE_NAME         = "else";
    public static final String CASE_NAME         = "case";
    public static final String COND_NAME         = "cond";

// the empty list:
    public static final String EMPTY_LIST_NAME = "()";

// booleans:
    public static final String T_NAME          = "#t";
    public static final String F_NAME          = "#f";

// predicates:
    public static final String CHARP_NAME      = "char?";
    public static final String EQ_NAME         = "eq?";
    public static final String EQV_NAME        = "eqv?";
    public static final String EQUAL_NAME      = "equal?";
    public static final String NULLP_NAME      = "null?";
    public static final String PROCEDUREP_NAME = "procedure?";
    public static final String LISTP_NAME      = "list?";
    public static final String NUMBERP_NAME    = "number?";
    public static final String INTEGERP_NAME   = "integer?";
    public static final String RATIONALP_NAME  = "rational?";
    public static final String SYMBOLP_NAME    = "symbol?";
    public static final String STRINGP_NAME    = "string?";
    public static final String VECTORP_NAME    = "vector?";

// arithmetic functions:
    public static final String PLUS_NAME       = "+";
    public static final String MINUS_NAME      = "-";
    public static final String MULT_NAME       = "*";
    public static final String DIV_NAME        = "/";
    public static final String GT_NAME         = ">";
    public static final String LT_NAME         = "<";

// list functions:
// hey, I like the common lisp names, what can I say!
    public static final String FIRST_NAME      = "first";
    public static final String SECOND_NAME     = "second";
    public static final String REST_NAME       = "rest";

    public static final String CAR_NAME        = "car";
    public static final String CDR_NAME        = "cdr";
    public static final String CAAR_NAME       = "caar";
    public static final String CADR_NAME       = "cadr";
    public static final String CDAR_NAME       = "cdar";
    public static final String CDDR_NAME       = "cddr";
    public static final String CAAAR_NAME      = "caaar";
    public static final String CAADR_NAME      = "caadr";
    public static final String CADAR_NAME      = "cadar";
    public static final String CADDR_NAME      = "caddr";
    public static final String CDAAR_NAME      = "cdaar";
    public static final String CDADR_NAME      = "cdadr";
    public static final String CDDAR_NAME      = "cddar";
    public static final String CDDDR_NAME      = "cdddr";
    public static final String LISTREF_NAME    = "list-ref";
    public static final String APPEND_NAME     = "append";
    public static final String CONS_NAME       = "cons";
    public static final String LENGTH_NAME     = "length";
    public static final String MAP_NAME        = "map";
    public static final String MEMBER_NAME     = "member";
    public static final String MEMQ_NAME       = "memq";
    public static final String MEMV_NAME       = "memv";
    public static final String REVERSE_NAME    = "reverse";

// type converters:
    public static final String LIST_STRING_NAME  = "list->string";
    public static final String STRING_LIST_NAME  = "string->list";

// string functions:
    public static final String SUBSTRING_NAME    = "substring";
    public static final String STRINGAPPEND_NAME = "string-append";
    public static final String STRINGREF_NAME    = "string-ref";
    public static final String STRINGSET_NAME    = "string-set!";

// vector functions:
    public static final String VECTORLENGTH_NAME = "vector-length";
    public static final String VECTORREF_NAME    = "vector-ref";
    public static final String VECTORSET_NAME    = "vector-set!";

// io
    public static final String LOAD_NAME         = "load";
    public static final String TERM_NAME         = "curr-term";

// some java objects:
    public static final String FRAME_NAME        = "Frame";
    public static final String BUTTON_NAME       = "Button";
    public static final String LABEL_NAME        = "Label";
    public static final String LAYOUT_NAME       = "LayoutManager";
    public static final String TEXTFIELD_NAME    = "TextField";
    public static final String URL_NAME          = "URL";

    private static Environment global_env = new Environment(null, "global");

// some functors made global for use around the system:
    public static Reverse reverse;

    private static boolean      trace_on = false;
    public static final boolean getTraceState() { return trace_on; }
    public static final void    setTraceState(boolean state) { trace_on = state; trace_print("trace_on = " + trace_on); }

    private Applet MyApplet;

    private static LispTerminal MyTerm;
}


