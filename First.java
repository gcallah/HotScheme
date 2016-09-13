
/**
* functor to call first on a list
*/

class First extends BuiltIn
{
    public static final First car = new First();


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return (args.first()).first();
    }


    public final String Display()
    {
        return "(car ls)\nreturns: first member of ls";
    }
}
