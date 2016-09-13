
/**
* The StringAppend functor calls string-append on a list of strings.
*/
class StringAppend extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make(append_aux(args));
    }

    private String append_aux(SchemeObject args)
        throws SchemeException
    {
        if(args.Nullp())
            return "";
        else
            return (args.first()).StringVal() + append_aux(args.restl());
    }


    public String Display()
    {
        return "(string-append string ...)\n"
            + "returns: a new string formed by concatenating string ...\n";
    }
}
