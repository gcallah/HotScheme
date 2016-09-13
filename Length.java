
/**
* functor to call length on a list
*/
class Length extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).Length());
    }


    public final String Display()
    {
        return "(length ls)\n"
            + "returns: the number of items in ls";
    }
}
