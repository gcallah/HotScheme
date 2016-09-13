
/**
*/
class Cdaar extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject ls = args.first();

        return ((args.first()).first()).restl();
    }


    public final String Display()
    {
        return "(cdaar ls)\nreturns: (cdr (car (car ls)))";
    }
}
