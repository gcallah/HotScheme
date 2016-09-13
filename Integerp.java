
/**
* functor to call integerp on an object
*/
class Integerp extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Integerp());
    }


    public final String Display()
    {
        return "(integer? obj)\n"
            + "returns: true if obj is an integer\n"
            + "; false otherwise";
    }
}
