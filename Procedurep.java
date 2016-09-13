
/**
* functor to call procedurep on an object
*/
class Procedurep extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Procedurep());
    }


    public String Display()
    {
        return "(procedure? obj)\n"
            + "returns: true if obj is a procedure\n"
            + "; false otherwise";
    }
}
