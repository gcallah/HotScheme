
/**
* functor reverse a list
*/
class Reverse extends BuiltIn
{

    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
	    return revaux(args.first(), SchemeObject.False);
    }


    private static SchemeObject revaux(SchemeObject old_list,
            SchemeObject new_list)
        throws SchemeException
    {
    	if(old_list.Nullp())
// we've reached the end; return new_list
    		return new_list;
    	else
    		return revaux(old_list.restl(),
    		    SchemeObject.cons(old_list.first(), new_list));
    }


    public String Display()
    {
        return "(reverse ls)\n"
            + "returns: a copy of ls in reverse order";
    }
}
