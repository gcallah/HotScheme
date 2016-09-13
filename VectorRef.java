
/**
* functor to call vector-ref on an object
*/
class VectorRef extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return (args.first()).VectorRef(
            (int)((args.second()).IntVal()));
    }


    public final String Display()
    {
        return "(vector-ref vector n)\n"
            + "returns: Nth element of vector\n";
    }
}
