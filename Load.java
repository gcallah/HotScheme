
import java.net.MalformedURLException;
import java.io.IOException;


/**
* functor to call load
*/
class Load extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        try
        {
            LispInterpreter.read_eval_print_loop(
                new URLLispTerminal(
                    args.first().StringVal(),
                    env.getTerm()),
                env);
        }
        catch(MalformedURLException e1)
        {
            return SchemeObject.False;
        }
        catch(IOException e2)
        {
            return SchemeObject.False;
        }
        
        return SchemeObject.True;
    }


    public String Display()
    {
        return "(load filename)\n"
            + "returns: true if the file was loaded, otherwise false\n";
    }
}
