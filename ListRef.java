
/**
* functor to call list-ref on an obj
*/
class ListRef extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return
            (args.first()).ListRef(
                (int)(args.second()).IntVal()
            );
    }


    public String Display()
    {
        return "(list-ref list n)\n"
            + "returns: Nth item of list";
    }
}
