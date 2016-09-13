

class Let extends LetFamily
{
    protected Let(LispTerminal lisp_term, Environment penv)
        throws SchemeException
    {
        super(lisp_term, penv, LispInterpreter.LET_NAME);

        SchemeObject args =
            SchemeObject.make(lisp_term, LIST_REST, penv);

        SchemeObject let_body = args.second();
        let_body.SetEnv(penv, local_env);

        lambda_exp   =
            SchemeObject.Lambda(
                list_of_nth_items(SchemeObject.False, args.first(), 0),
                let_body,
                local_env);
        actuals = list_of_nth_items(SchemeObject.False, args.first(), 1);
    }
}
