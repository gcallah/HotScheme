
/**
* functor to call rationalp on an object
*/
class Rationalp extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Rationalp());
    }


    public String Display()
    {
        return "(rational? obj)\n"
            + "returns: true if obj is a rational number\n"
            + "; false otherwise";
    }
}
