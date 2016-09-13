
/**
functor to call equal an arg list
*/
class Equal extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Equal(args.second()));
    }


    public final String Display()
    {
        return "(equal? obj1 obj2)\n"
            + "returns: true if obj1 and obj2 have the "
            + "same structure and contents\n"
            + "; false otherwise";
    }
}
