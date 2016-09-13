
/**
*/
class Caar extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env) throws SchemeException
    {
        SchemeObject ls = args.first();

        return (ls.first()).first();
    }


    public final String Display()
    {
        return "(caar ls)\nreturns: (car (car ls))";
    }
}
