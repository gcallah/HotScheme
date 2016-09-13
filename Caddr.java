
/**
*/
class Caddr extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject ls = args.first();

        return ((ls.restl()).restl()).first();
    }


    public final String Display()
    {
        return "(caddr ls)\nreturns: (car (cdr (cdr ls)))";
    }
}
