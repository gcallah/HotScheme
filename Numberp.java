
/**
* functor to call numberp on an object
*/
class Numberp extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Numberp());
    }


    public String Display()
    {
        return "(number? obj)\n"
            + "returns: true if obj is a number\n"
            + "; false otherwise";
    }
}
