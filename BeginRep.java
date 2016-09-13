

/**
BeginRep implements the Scheme 'begin' syntax, which implements sequential execution.
(begin exp1 exp2 ...)
returns: the value of the last expression.
*/
class BeginRep extends SequencingFamily
{
    protected BeginRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
// the cast to int is safe -- the begin block
//  would have to be over 2 billion items long for trouble to occur!
//  believe me, if someone tries to create a 2 billion item list, they'll have worse troubles
//  than this value wrapping!
        return execute_sequence(args, (int)(args.Length()), env);
    }
}
