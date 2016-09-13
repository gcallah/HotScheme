
/**
* functor to call vectorp on an object
*/
class Vectorp extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Vectorp());
    }


    public final String Display()
    {
        return "(vector? obj)\n"
            + "returns: true if obj is a vector\n"
            + "; false otherwise";
    }
}
