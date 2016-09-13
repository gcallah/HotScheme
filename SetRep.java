

class SetRep extends SyntacticalForm
{
    protected SetRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return env.Set((args.first()).Write(), (args.second()).Eval(env));
    }
}