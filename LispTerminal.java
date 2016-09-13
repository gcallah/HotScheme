import java.io.*;
import java.util.Stack;


abstract public class LispTerminal extends HotSchemeInternalRep
{
    public LispTerminal()
	{
	}

    
    public LispTerminal(LispTerminal in, LispTerminal out)
	{
	    in_term  = in;
	    out_term = out;
	}


    public final void print(Object obj)
    {
        if(out_term != null) out_term.print(obj);
        else                 my_print(obj);
    }
    
    
    abstract public void my_print(Object obj);


    public final void print(int i)     { print(new String((new Integer(i)).toString())); }

    public final void print(long l)    { print(new String((new Long(l)).toString())); }


    public final void println(Object obj)
    {
        if(out_term != null) out_term.println(obj);
        else                 my_println(obj);
    }
    
    abstract public void my_println(Object obj);


    public int read()
    {
        if(in_term != null) return in_term.read();
        else                return my_read();
    }

    abstract public int my_read();


    public void unread(int c)
    {
        if(in_term != null) in_term.unread(c);
        else                my_unread(c);
    }
    
    abstract public void my_unread(int c);

    public boolean eof()
    {
        if(in_term != null) return in_term.eof();
        else                return my_eof();
    }

    abstract public boolean my_eof();


    public SchemeToken getToken() throws SchemeException
    {
        if(token_stack.empty())
            return new SchemeToken(this);
        else
            return (SchemeToken)token_stack.pop();
    }


    public void pushToken(SchemeToken token)
    {
        token_stack.push(token);
    }


// a place for clients to push tokens back to:
    private Stack        token_stack = new Stack();
    private LispTerminal in_term  = null;
    private LispTerminal out_term = null;
}
