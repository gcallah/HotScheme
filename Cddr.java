
/**
*/
class Cddr extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return ((args.first()).restl()).restl();
    }


    public final String Display()
    {
        return "(cddr ls)\nreturns: (cdr (cdr ls))";
    }
}
