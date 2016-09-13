
/**
*/
class Cadar extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args,
        Environment env) throws SchemeException
    {
        SchemeObject ls = args.first();

        return ((ls.first()).restl()).first();
    }


    public final String Display()
    {
        return "(cadar ls)\nreturns: (car (cdr (car ls)))";
    }
}
