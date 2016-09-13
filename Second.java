
/**
functor to call second (cadr) on a list
*/
class Second extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return (args.first()).second();
    }


    public String Display()
    {
        return "(cadr ls)\n"
            + "returns: (car (cdr ls))";
    }
}
