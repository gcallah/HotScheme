
/**
* functor to call rest on a list
*/
class Rest extends BuiltIn
{
    public static final Rest cdr = new Rest();


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return (args.first()).restl();
    }


    public final String Display()
    {
        return "(cdr ls)\n"
            + "returns: a copy of ls with the first item removed";
    }
}
