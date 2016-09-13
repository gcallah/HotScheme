
/**
* functor to call stringp on a obj
*/
class Stringp extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Stringp());
    }


    public String Display()
    {
        return "(string? obj)\n"
            + "returns: true if obj is a string\n"
            + "; false otherwise";
    }
}
