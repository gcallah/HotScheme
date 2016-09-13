
/**
Member -- functor to recursively call equal on a list
*/
class Member extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return member_aux(args.first(), args.second());
    }


    private static SchemeObject member_aux(SchemeObject item,
            SchemeObject list)
         throws SchemeException
    {
        if((list.first()).Equal(item))
            return list;
        else if((list.restl()).Nullp())
            return SchemeObject.False;
        else
            return member_aux(item, list.restl());
    }


    public String Display()
    {
        return "(member obj ls)\n"
            + "returns: the portion of ls starting at obj, "
            + "if obj is equal? to any member of ls\n"
            + "; false otherwise";
    }
}
