/**
*/
class Cddar extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
       SchemeObject ls = args.first();

       return ((ls.first()).restl()).restl();
    }


    public final String Display()
    {
        return "(cddar ls)\nreturns: (cdr (cdr (car ls)))";
    }
}
