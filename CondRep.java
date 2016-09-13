

/**
CondRep implements the Scheme 'cond' syntax.
(cond clause1 clause2 ...)<br>
Each clause must have one of the following forms:
(test)
(test exp1 exp2 ...)
(We have not yet implemented the form (test => exp).)
The last clause may also have the form: (else exp1 exp2 ...).
@return
The first of the clause forms returns the value of 'test' when 'test' is true.
The second of them returns the last 'exp' when 'test' is true.
*/
class CondRep extends SequencingFamily
{
    protected CondRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        if(args.Nullp()) return False;
        else
        {
            SchemeObject this_clause = args.first();
            SchemeObject test        = this_clause.first();
            SchemeObject exps        = this_clause.restl();
            if((test.Write()).equals(LispInterpreter.ELSE_NAME))
            {
                return execute_sequence(exps, (int)(exps.Length()), env);
            }
            else
            {
                SchemeObject evaled_test = test.Eval(env);
                if(!evaled_test.Nullp())  // test is true
                    if(exps.Nullp())
                        return evaled_test;
                    else
                        return execute_sequence(exps, (int)(exps.Length()), env);
                else
                    return Apply(args.restl(), env);
            }
        }
    }
}
