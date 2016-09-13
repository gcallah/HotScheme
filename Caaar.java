
/**
*/
class Caaar extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject ls = args.first();

        return ((ls.first()).first()).first();
    }


    public final String Display()
    {
        return "(caaar ls)\n"
            + "returns: (car (car (car ls)))";
    }
}
