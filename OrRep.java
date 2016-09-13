

class OrRep extends SyntacticalForm
{
    protected OrRep(String s) { super(s); }

    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        if(!((args.first()).Eval(env)).Nullp())
            return True;
        else
            return or_aux(args.restl(), env);
    }

    private final SchemeObject or_aux(SchemeObject args, Environment env)
        throws SchemeException
    {
        if(args.Nullp())
            return False;
        else if(!((args.first()).Eval(env)).Nullp())
            return True;
        else
            return or_aux(args.restl(), env);
    }
}
