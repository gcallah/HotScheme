/**
* functor to call null? on an object
*/
class Nullp extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Nullp());
    }


    public String Display()
    {
        return "(null? obj)\n"
            + "returns: true if obj is #f or ()\n"
            + "; false otherwise";
    }
}
