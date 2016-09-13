

class LetRec extends LetFamily
{
    protected LetRec(LispTerminal lisp_term, Environment penv)
        throws SchemeException
    {
        super(lisp_term, penv, LispInterpreter.LETREC_NAME);

        SchemeObject args =
            SchemeObject.make(lisp_term, LIST_REST, local_env);

        lambda_exp   =
            SchemeObject.Lambda(
                list_of_nth_items(SchemeObject.False, args.first(), 0),
                args.second(),
                local_env);
        actuals = list_of_nth_items(SchemeObject.False, args.first(), 1);
    }
}
