
/**
* Memv -- functor to recursively call eqv on a list.
*/
class Memv extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return memv_aux(args.first(), args.second());
    }

/**
memv_aux() is protected, rather than private, so that other statements
(specifically 'case') that need to search a list can call it.
*/
    protected static final SchemeObject memv_aux(SchemeObject item,
            SchemeObject list)
         throws SchemeException
    {
        if((list.first()).Eqv(item))
            return list;
        else if((list.restl()).Nullp())
            return SchemeObject.False;
        else
            return memv_aux(item, list.restl());
    }


    public String Display()
    {
        return "(memvaux obj ls)\n"
            + "returns: the portion of ls starting at obj, "
            + "if obj is eqv? to any member of ls\n"
            + "; false otherwise";
    }
}
