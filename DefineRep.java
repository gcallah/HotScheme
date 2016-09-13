

class DefineRep extends SyntacticalForm
{
    protected DefineRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return LispInterpreter.getGlobalEnv().Intern(
            (args.first()).Write(), (args.second()).Eval(env));
    }
}
