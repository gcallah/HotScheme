
/**
* functor to call stringp on an object
*/
class Symbolp extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Symbolp());
    }


    public String Display()
    {
        return "(symbol? obj)\n"
            + "returns: true if obj is a symbol\n"
            + "; false otherwise";
    }
}
