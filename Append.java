
/**
* functor to append to a list
*/
class Append extends BuiltIn
{
// our implementation of append uses the traditional Lisp
//  technique of auxilliary functions.
//  The code here is directly translated from R. Kent Dybvig's
//  Scheme implementation of append in
//  "The Scheme Programming Language."
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return appaux1(SchemeObject.False, args);
    }


    public final String Display()
    {
        return "(append ls1 ls2 ...)\n"
            + "returns: a new list consisting of ls2 ... lsN appended to ls1";
    }


    private static final SchemeObject appaux1(SchemeObject ls, SchemeObject args)
        throws SchemeException
    {
        if(args.Nullp()) return ls;
        else             return appaux2(ls, args);
    }

    private static final SchemeObject appaux2(SchemeObject ls, SchemeObject args)
        throws SchemeException
    {
        if(ls.Nullp())
            return(appaux1(args.first(), args.restl()));
        else
            return(SchemeObject.cons(
                ls.first(),
                appaux2(ls.restl(), args)));
    }

}
