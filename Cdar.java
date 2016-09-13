
/**
*/
class Cdar extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject ls = args.first();

        return (ls.first()).restl();
    }


    public final String Display()
    {
        return "(cdar ls)\nreturns: (cdr (car ls))";
    }
}
