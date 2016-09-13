
/**
* functor to call eq on an obj
*/
class Eq extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Eq(args.second()));
    }


    public final String Display()
    {
        return "(eq? obj1 obj2)\n"
            + "returns: true if obj1 and obj2 are the same object in memory\n"
            + "; false otherwise";
    }
}
