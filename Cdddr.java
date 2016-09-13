
/**
*/
class Cdddr extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject ls = args.first();

        return (((ls.restl()).restl()).restl());
    }


    public final String Display()
    {
        return "(cdddr ls)\nreturns: (cdr (cdr (cdr ls)))";
    }
}
