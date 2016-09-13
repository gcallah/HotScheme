
/**
* functor to cons two objects
*/
class Cons extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        long len;

        if((len = args.Length()) > 2)
            throw new SchemeException(SchemeException.TOO_MANY_ARGS);

        return SchemeObject.cons(args.first(), args.second());
    }


    public final String Display()
    {
        return "(cons obj1 obj2)\n"
            + "returns: a pair with a car of obj1 and a cdr of obj2";
    }
}
