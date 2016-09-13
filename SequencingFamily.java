

abstract class SequencingFamily extends SyntacticalForm
{
    protected SequencingFamily(String s) { super(s); }


/**
execute_sequence() is protected, rather than private, so that other statements
that use sequences of expressions can call it for evaluation.
*/
    protected final static SchemeObject execute_sequence(SchemeObject args,
            int len, Environment env)
        throws SchemeException
    {
        switch(len)
        {
            case 0: return False;
            case 1: return (args.first()).Eval(env);
            default:
                (args.first()).Eval(env);
                return execute_sequence(args.restl(), len - 1, env);
        }
    }
}
