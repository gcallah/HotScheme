
/**
* Memq -- functor to recursively call eq on a list
*/
class Memq extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return memq_aux(args.first(), args.second());
    }


    private static SchemeObject memq_aux(SchemeObject item,
                SchemeObject list)
         throws SchemeException
    {
        if((list.first()).Eq(item))
            return list;
        else if((list.restl()).Nullp())
            return SchemeObject.False;
        else
            return memq_aux(item, list.restl());
    }


    public String Display()
    {
        return "(memqaux obj ls)\n"
            + "returns: the portion of ls starting at obj, "
            + "if obj is eq? to any member of ls\n"
            + "; false otherwise";
    }
}
