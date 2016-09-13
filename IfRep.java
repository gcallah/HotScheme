

/**
IfRep implements the Scheme 'if' syntax.
(if cond exp1 exp2)
returns: exp1 if cond is true, else exp2
*/
class IfRep extends SyntacticalForm
{
    protected IfRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        if(!((args.first()).Eval(env)).Nullp())
            return (args.second()).Eval(env);
        else
            return (args.third()).Eval(env);
    }
}
