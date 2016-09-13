
/**
*/
class Caadr extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args,
        Environment env)
	    throws SchemeException
    {
        SchemeObject ls = args.first();

        return ((ls.restl()).first()).first();
    }


    public final String Display()
    {
        return "(caadr ls)\nreturns: (car (car (cdr ls)))";
    }
}
