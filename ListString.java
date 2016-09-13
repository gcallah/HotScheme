
/**
* functor to call list->string on a obj
*/
class ListString extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).ListString());
    }


    public String Display()
    {
        return "(list->string? ls)\n"
            + "returns: a string made up of the chars in ls\n";
    }
}
