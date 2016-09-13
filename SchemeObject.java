import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Canvas;


/**
* SchemeObject is the parent of all Scheme data types, as well as
*  being an abstract factory that creates instances of it's children
*/
public class SchemeObject
{
// constructors:
    protected SchemeObject()
    {
    }


// these are a family of abstract factory pseudo-constructors,
//   all named make:

    public static final SchemeObject make(Applet a)
    {
        return new AppletRep(a);
    }


    public static final SchemeObject make(AppletContext a)
    {
        return new AppletContextRep(a);
    }


    public static final SchemeObject make(char c)
    {
        return new CharRep(c);
    }


    public static final SchemeObject make(long val)
    {
        return new IntRep(val);
    }


    public static final SchemeObject make(double val)
    {
        return new Rational(val);
    }


    public static final SchemeObject make(String s)
    {
        return new StringRep(s);
    }


    public static final SchemeObject make(boolean b)
    {
        if(b) return True;
        else  return False;
    }


    public static final SchemeObject make(LispTerminal lisp_term, int context, Environment lenv)
        throws SchemeException
    {
        int    c = 0;

        SchemeToken token = lisp_term.getToken();

        switch(context)
        {
            case START:
                switch(token.getType())
                {
                    case SchemeToken.QUOTE_MACRO:
						return new List(Quote,
						    new List(SchemeObject.make(lisp_term, START, lenv), False)
							);

                    case SchemeToken.OPEN_PAREN:
                        SchemeToken next_token = lisp_term.getToken();

                        if(next_token.getType() == SchemeToken.CLOSE_PAREN)
                            return False;
                        else if((next_token.getText()).equals(
                            LispInterpreter.LAMBDA_NAME))
                        {
                            SchemeObject obj = new Lambda(lisp_term, lenv);

                            if((lisp_term.getToken()).getType() != SchemeToken.CLOSE_PAREN)
                                throw new SchemeException(SchemeException.TOO_MANY_ARGS);

                            return obj;
                        }
                        else if((next_token.getText()).equals(LispInterpreter.DO_NAME))
                        {
                            SchemeObject obj = new Do(lisp_term, lenv);

                            return obj;
                        }
                        else if((next_token.getText()).equals(LispInterpreter.LET_NAME))
                        {
                            SchemeObject obj = new Let(lisp_term, lenv);

                            return obj;
                        }
                        else if((next_token.getText()).equals(LispInterpreter.LETREC_NAME))
                        {
                            SchemeObject obj = new LetRec(lisp_term, lenv);

                            return obj;
                        }
                        else
                        {
                            lisp_term.pushToken(next_token);  // put token back!
                            return new List(lisp_term, lenv);
                        }

                    case SchemeToken.VECTOR_OPEN:
                        return new VectorRep(lisp_term, lenv);

                    case SchemeToken.CHAR:
                        return new CharRep((token.getText()).charAt(0));

                    case SchemeToken.INT:
                        return new IntRep(Long.parseLong(token.getText()));

                    case SchemeToken.RATIONAL:
                        return new Rational(
                            Double.valueOf(token.getText()).doubleValue());

                    case SchemeToken.STRING:
                        return new StringRep(token.getText());

                    case SchemeToken.SYMBOL:
// special forms:
                        String s = token.getText();

                        if(s.equals(LispInterpreter.QUOTE_NAME))
                            return Quote;
                        if(s.equals(LispInterpreter.QUIT_NAME))
                            throw new QuitException();
                        else if(s.equals(LispInterpreter.DEFINE_NAME))
                            return Define;
                        else if(s.equals(LispInterpreter.BEGIN_NAME))
                            return Begin;
                        else if(s.equals(LispInterpreter.IF_NAME))
                            return If;
                        else if(s.equals(LispInterpreter.CASE_NAME))
                            return Case;
                        else if(s.equals(LispInterpreter.COND_NAME))
                            return Cond;
                        else if(s.equals(LispInterpreter.AND_NAME))
                            return And;
                        else if(s.equals(LispInterpreter.OR_NAME))
                            return Or;
                        else if(s.equals(LispInterpreter.SEND_MESSAGE_NAME))
                            return SendMessage;
                        else if(s.equals(LispInterpreter.SET_NAME))
                            return Set;
                        else if(s.equals(LispInterpreter.TRACE_ON_NAME))
                            return TraceOn;
                        else if(s.equals(LispInterpreter.TRACE_OFF_NAME))
                            return TraceOff;

// constant objects:
                        else if(s.equals(LispInterpreter.F_NAME))
                            return False;
                        else if(s.equals(LispInterpreter.T_NAME))
                            return True;

// otherwise, we have the general symbol case:
                        else
                            return new SymbolRep(token.getText());

                    case SchemeToken.CLOSE_PAREN:
                        throw new UnmatchedParenExcep(
                            SchemeException.UNMATCHED_PAREN);

                    default:
                        throw new SchemeException(
                            SchemeException.UNEXPECTED_TOKEN);
                }
            case LIST_REST:
                if(token.getType() == SchemeToken.CLOSE_PAREN)
                    return False;
    			else
	    		{
                    lisp_term.pushToken(token);

			    	return new List(lisp_term, lenv);
    			}
        }
        return null;
    }


// another "semi-constructor" -- builds a list for others
    public static final SchemeObject cons(SchemeObject car, SchemeObject cdr)
        throws SchemeException
    {
        return new List(car, cdr);
    }


// another "semi-constructor" that builds a lambda from args already read in:
    public static final SchemeObject Lambda(SchemeObject formals,
                                    SchemeObject func_body, Environment lenv)
        throws SchemeException
    {
        return new Lambda(formals, func_body, lenv);
    }


    public boolean Eq(SchemeObject obj)
	{
        return obj == this;
	}


	public boolean Eqv(SchemeObject obj)
	{
	    return Eq(obj);
	}


	public boolean Equal(SchemeObject obj)
	{
	    return Eqv(obj);
	}


    public boolean Not()
    {
        return false;
    }


    public boolean Nullp()
    {
        return false;
    }


    public boolean Atomp()
    {
        return false;
    }


    public boolean Charp()
    {
        return false;
    }


    public boolean Symbolp()
    {
        return false;
    }


    public boolean Stringp()
    {
        return false;
    }


    public boolean Integerp()
    {
        return false;
    }


    public boolean Rationalp()
    {
        return false;
    }


    public boolean Numberp()
    {
        return false;
    }


    public boolean Listp()
    {
        return false;
    }


    public boolean Procedurep()
    {
        return false;
    }


    public boolean Vectorp()
    {
        return false;
    }


    public char CharVal() throws SchemeException
    {
        throw new SchemeException(SchemeException.NOT_A_CHAR + " in CharVal");
    }


    public String StringVal() throws SchemeException
    {
        throw new SchemeException(SchemeException.NOT_A_STRING + " in StringVal");
    }


    public long IntVal() throws SchemeException
    {
        throw new SchemeException(SchemeException.NOT_A_NUM + " in IntVal");
    }


    public double FloatVal() throws SchemeException
    {
        throw new SchemeException(SchemeException.NOT_A_NUM + " in FloatVal");
    }


// type conversions:
    public String ListString()
        throws SchemeException
    {
        throw new NotAListExcp(" in ListString of: ", this);
    }


    public SchemeObject string_list()
        throws SchemeException
    {
        throw new SchemeException(
            SchemeException.NOT_A_STRING
            + " in string_list of: ", this);
    }


// vector operations:
    public void VectorSet(int n, SchemeObject obj)
        throws SchemeException
    {
        throw new SchemeException(
            SchemeException.NOT_A_VECTOR
            + " in vector-set! of: ", this);
    }


    public SchemeObject VectorRef(int n)
        throws SchemeException
    {
        throw new SchemeException(
            SchemeException.NOT_A_VECTOR
            + " in vector-ref of: ", this);
    }


    public int VectorLength()
        throws SchemeException
    {
        throw new SchemeException(
            SchemeException.NOT_A_VECTOR
            + " in vector-length of: ", this);
    }


// list operations:
    public SchemeObject first() throws SchemeException
    {
        throw new NotAListExcp(" in car of: ", this);
    }


    public SchemeObject second() throws SchemeException
    {
        throw new NotAListExcp(" in cadr of: ", this);
    }


    public SchemeObject third() throws SchemeException
    {
        throw new NotAListExcp(" in third of: ", this);
    }


    public SchemeObject ListRef(int n) throws SchemeException
    {
        throw new NotAListExcp(" in ListRef of: ", this);
    }


    public SchemeObject restl() throws SchemeException
    {
        throw new NotAListExcp(" in cdr of: ", this);
    }


    public SchemeObject set_car(SchemeObject obj) throws SchemeException
    {
        throw new NotAListExcp(" in set_car of: ", this);
    }


    public SchemeObject set_cdr(SchemeObject obj) throws SchemeException
    {
        throw new NotAListExcp(" in set_cdr of: ", this);
    }


// object-oriented stuff:
    public SchemeObject AcceptMessage(String msg, SchemeObject args)
         throws SchemeException
    {
        throw new SchemeException(SchemeException.NOT_AN_OBJ);
    }


// shouldn't this throw something?
    public void SetEnv(Environment old_env, Environment new_env)
        throws SchemeException
    {
    }


    public SchemeObject Eval(Environment env)
        throws SchemeException
    {
        return this;
    }


/**
* an auxilliary function for Eval()
*/
    protected SchemeObject Evargs(Environment env)
        throws SchemeException
    {
    	if(this == False) return False;
    	else
    	{
    		return SchemeObject.cons(first().Eval(env),
    		    restl().Evargs(env));
    	}
    }


    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        throw new SchemeException(SchemeException.NOT_A_FUNC + " in Apply of "
            + Write());
    }


    public String Display()
    {
        return Write();
    }


    public String Write()
    {
        return "SchemeObject";
    }


    protected String aux_write() { return ""; }


    public long Length()  throws SchemeException
    {
        return 0;
    }


// token types
    public static final int ERROR      = 'E';  // didn't assign type!
    public static final int FALSE      = 'F';
    public static final int INT        = 'I';
    public static final int RATIONAL   = 'R';
    public static final int LAMBDA     = 'L';
    public static final int QUIT       = 'X';
    public static final int QUOTE      = 'Q';
    public static final int STRING     = 'S';
    public static final int SYMBOL     = 'Y';
    public static final int TRUE       = 'T';

// state variables:
    public static final int START      = 1;
    public static final int LIST_REST  = 2;
    public static final int DONE       = -1;


    public static final AndRep           And         = new AndRep(LispInterpreter.AND_NAME);
    public static final BeginRep         Begin       = new BeginRep(LispInterpreter.BEGIN_NAME);
    public static final CaseRep          Case        = new CaseRep(LispInterpreter.CASE_NAME);
    public static final CondRep          Cond        = new CondRep(LispInterpreter.COND_NAME);
    public static final DefineRep        Define      = new DefineRep(LispInterpreter.DEFINE_NAME);
    public static final IfRep            If          = new IfRep(LispInterpreter.IF_NAME);
    public static final OrRep            Or          = new OrRep(LispInterpreter.OR_NAME);
    public static final QuoteRep         Quote       = new QuoteRep(LispInterpreter.QUOTE_NAME);
    public static final SendMessageRep   SendMessage = new SendMessageRep(LispInterpreter.SEND_MESSAGE_NAME);
    public static final SetRep           Set         = new SetRep(LispInterpreter.SET_NAME);
    public static final SyntacticalForm  TraceOn     = new TraceOnRep();
    public static final SyntacticalForm  TraceOff    = new TraceOffRep();

    public static final TRep True  = new TRep();
    public static final FRep False = new FRep();
	SchemeObject schemeObject;
	LispTerminal lispTerminal;
}


/**
FRep represents the false object and the null list.
It is instantiated as a singleton.
*/
class FRep extends SchemeObject
{
// selectors:
    public String Write()
    {
        return LispInterpreter.F_NAME;
    }

    protected String aux_write()
    {
        return Write();
    }


    public boolean Not()
    {
        return true;
    }


    public boolean Nullp()
    {
        return true;
    }


    public boolean Atomp()
    {
        return true;
    }


    public long Length()  throws SchemeException
    {
        return 0;
    }
}
