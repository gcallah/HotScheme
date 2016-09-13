
public class Scheme
{
    public static void main(String args[])
        throws java.io.IOException
    {
        try
        {
            LispInterpreter.trace_print("creating term");

            LispTerminal lisp_term
                = new CharLispTerminal(System.in, System.out);

            LispInterpreter.trace_print("creating interp");

            LispInterpreter lisp_interp = new LispInterpreter(lisp_term,
                null);
        }
        catch(SchemeException e)
        {
            System.out.println("SchemeException caught");
        }
        catch(Exception e)
        {
            System.out.println("Exception caught" + e.getMessage());
        }
    }
}
