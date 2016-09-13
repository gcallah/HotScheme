

// (do ((i 10 (- i 1)) (a 1 (* a i))) ((eqv? i 0) a))
class Do extends LexicalScopeForm
{
    protected Do(LispTerminal lisp_term, Environment penv)
        throws SchemeException
    {
        super(lisp_term, penv, LispInterpreter.DO_NAME);

        SchemeObject VarValUpdate = SchemeObject.make(lisp_term, START, local_env);
        formals   = list_of_nth_items(SchemeObject.False, VarValUpdate, 0);
        init_vals = list_of_nth_items(SchemeObject.False, VarValUpdate, 1);
        local_env.AddList(formals);

        updates   = list_of_nth_items(SchemeObject.False, VarValUpdate, 2);

        SchemeObject TestRes = SchemeObject.make(lisp_term, START, local_env);
        test = TestRes.first();
        res  = SchemeObject.cons(Begin, TestRes.restl());

        exps = SchemeObject.cons(Begin,
                SchemeObject.make(lisp_term, LIST_REST, local_env));
    }


    public final SchemeObject Eval(Environment env)
        throws SchemeException
    {
        SchemeObject evaled = eval_vals(False, init_vals);
        local_env.AssignList(formals, evaled);

        while((test.Eval(local_env)).Nullp())
        {
            if(!exps.Nullp()) exps.Eval(local_env);

            evaled = eval_vals(False, updates);
            local_env.AssignList(formals, evaled);
        }
        return res.Eval(local_env);
    }


    private final SchemeObject eval_vals(SchemeObject evaled, SchemeObject raw)
        throws SchemeException
    {
        if(raw.Nullp())
            return evaled;
        else
        {
            if(evaled.Nullp())
                return eval_vals(
                    SchemeObject.cons((raw.first()).Eval(local_env),
                            False),
                        raw.restl());
            else
                return eval_vals(
                    my_append(evaled, (raw.first()).Eval(local_env)),
                        raw.restl());
        }
    }


    private SchemeObject formals;
    private SchemeObject init_vals;
    private SchemeObject updates;
    private SchemeObject test;
    private SchemeObject res;
    private SchemeObject exps;
}
