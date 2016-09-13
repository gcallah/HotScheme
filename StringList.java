
/**
* functor to call string->list on a obj
*/
class StringList extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return (args.first()).string_list();
    }


    public String Display()
    {
        return "(string->list? str)\n"
            + "returns: a list made up of the chars in str\n";
    }
}
