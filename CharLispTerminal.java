import java.io.*;
import java.util.Stack;


public class CharLispTerminal extends LispTerminal
{
    public CharLispTerminal(InputStream in, PrintStream out)
    {
        input  = new PushbackInputStream(in);
        output = out;
    }


    public void my_print(Object obj)
    {
        output.print(obj); output.flush();
    }


    public void my_println(Object obj)
    {
        output.println(obj);
    }


    public int my_read()
    {
        int c = 0;

        try
        {
            c = input.read();
        }
        catch(java.io.IOException e)
        {
            ;
        }
        return c;
    }


    public void my_unread(int c)
    {
        try
        {
            input.unread(c);
        }
        catch(java.io.IOException e)
        {
            ;
        }
    }


    public boolean my_eof()
    {
        return false;
    }


    private PushbackInputStream input;
    private PrintStream         output;
}
