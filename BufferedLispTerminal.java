import java.io.*;
import java.util.Stack;


abstract public class BufferedLispTerminal extends LispTerminal
{
    public BufferedLispTerminal()
	{
	    super();
        CommandBuf = new StringBuffer("");
	}

    
    public BufferedLispTerminal(LispTerminal in, LispTerminal out)
	{
	    super(in, out);
        CommandBuf = new StringBuffer("");
	}


    public int my_read()
    {
		if(pos < CommandBuf.length())
			return((int)CommandBuf.charAt(pos++));
		else
		    return(0);
    }


    public void my_unread(int c)
    {
		if(pos > 0) pos--;
    }


    public boolean my_eof()
    {
        if(pos < CommandBuf.length())
        {
// if everything else is spaces, return true, else false
            for(int i = pos; i < CommandBuf.length(); i++)
            {
                if(!Character.isWhitespace(CommandBuf.charAt(i)))
                    return false;
            }
            CommandBuf.setLength(pos);  // we won't look at those spaces again
        }
        return true;
    }


    protected void setBuffer(String s)
    {
        pos = 0;
        CommandBuf.setLength(0);
        CommandBuf.append(s);
    }

    
    private StringBuffer CommandBuf;
  	private int pos = 0;
}
