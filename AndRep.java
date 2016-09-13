

class AndRep extends SyntacticalForm
{
    protected AndRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        if(((args.first()).Eval(env)).Nullp())
            return False;
        else
            return and_aux(args.restl(), env);
    }

    private final SchemeObject and_aux(SchemeObject args, Environment env)
        throws SchemeException
    {
        if(args.Nullp())
            return True;
        else if(((args.first()).Eval(env)).Nullp())
            return False;
        else
            return and_aux(args.restl(), env);
    }
}
