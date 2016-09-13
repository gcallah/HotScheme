

class LetFamily extends LexicalScopeForm
{
    public LetFamily(LispTerminal lisp_term, Environment penv, String name)
        throws SchemeException
    {
        super(lisp_term, penv, name);
    }


    public final SchemeObject Eval(Environment env)
        throws SchemeException
    {
        return lambda_exp.Apply(actuals, env);
    }


    protected SchemeObject lambda_exp = null;
    protected SchemeObject actuals    = null;
}
