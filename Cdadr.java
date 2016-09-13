
/**
*/
class Cdadr extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject ls = args.first();

        return ((ls.restl()).first()).restl();
    }


    public final String Display()
    {
        return "(cdadr ls)\nreturns: (cdr (car (cdr ls)))";
    }
}
