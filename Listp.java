
/**
* functor to call listp on an obj
*/
class Listp extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Listp());
    }


    public String Display()
    {
        return "(list? obj)\n"
            + "returns: true if obj is a list\n"
            + "; false otherwise";
    }
}
