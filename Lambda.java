

class Lambda extends FuncRep
{
    Lambda(LispTerminal lisp_term, Environment penv)
        throws SchemeException
    {
        local_env = new Environment(penv, "lambda");

        formals = SchemeObject.make(lisp_term, START, local_env);

        local_env.AddList(formals);
        func_body = SchemeObject.make(lisp_term, START, local_env);
    }


    Lambda(SchemeObject frmls, SchemeObject func, Environment lenv)
        throws SchemeException
    {
        local_env = lenv;

        local_env.AddList(formals = frmls);

        func_body = func;
    }


    public SchemeObject Apply(SchemeObject actuals, Environment env)
        throws SchemeException
    {
        local_env.PushVars(formals, actuals);

        SchemeObject ret = func_body.Eval(local_env);

        local_env.PopVars();

        return ret;
    }


    public void SetEnv(Environment old_env, Environment new_env)
        throws SchemeException
    {
        if(local_env.GetParentEnv() == old_env) local_env.SetParentEnv(new_env);
    }


// private data:
    private Environment  local_env  = null;

    private SchemeObject formals    = null;
    private SchemeObject func_body  = null;
}
