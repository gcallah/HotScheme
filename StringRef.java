
/**
* The StringRef functor calls string-ref on a string.
*/
class StringRef extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make(
            ((args.first()).StringVal()).charAt((int)((args.second()).IntVal())));
    }


    public String Display()
    {
        return "(string-ref string n)\n"
            + "returns: the nth character of string\n";
    }
}
