
/**
* functor to call charp on a obj
*/
class Charp extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Charp());
    }


    public final String Display()
    {
        return "(char? obj)\n"
            + "returns: true if obj is a character\n"
            + "; false otherwise";
    }
}
