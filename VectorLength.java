
/**
* functor to call vector-length on an object
*/
class VectorLength extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).VectorLength());
    }


    public final String Display()
    {
        return "(vector-length vector)\n"
            + "returns: length of vector\n";
    }
}
