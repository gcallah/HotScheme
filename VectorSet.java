
/**
* functor to call vectorp on an object
*/
class VectorSet extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        (args.first()).VectorSet(
            (int)((args.second()).IntVal()),
            args.third());

        return SchemeObject.True;
    }


    public final String Display()
    {
        return "(vector-set v n obj)\n"
            + "set the nth element of v to obj\n";
    }
}
