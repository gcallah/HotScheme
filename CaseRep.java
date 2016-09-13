

/**
CaseRep implements the Scheme 'case' syntax.
(case exp0 clause1 clause2 ...)<br>
Each clause except the last must have the form: ((key ...) exp1 exp2 ...).
The last clause may have the form: (else exp1 exp2 ...).
@return
exp0 is evaluated, and the result is tested against the keys of each clause in succesion.
If there are any matches, each of the expressions associated with the clause is evaluated,
and the value of the last is returned.
If there are no matches, the value of the optional else clause is returned, if present,
otherwise false is returned.
*/
class CaseRep extends SequencingFamily
{
    protected CaseRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return case_aux((args.first()).Eval(env), args.restl(), env);
    }

    private final SchemeObject case_aux(SchemeObject exp0, SchemeObject args, Environment env)
        throws SchemeException
    {
        if(args.Nullp()) return False;
        else
        {
            SchemeObject this_clause = args.first();
            SchemeObject key_or_else = this_clause.first();
            SchemeObject exps        = this_clause.restl();
            if((key_or_else.Write()).equals(LispInterpreter.ELSE_NAME))
            {
                return execute_sequence(exps, (int)(exps.Length()), env);
            }
            else
            {
                if(!(Memv.memv_aux(exp0, key_or_else)).Nullp())
                    return execute_sequence(exps, (int)(exps.Length()), env);
                else
                    return case_aux(exp0, args.restl(), env);
            }
        }
    }
}
