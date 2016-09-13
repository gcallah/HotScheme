

class TraceOnRep extends SyntacticalForm
{
    protected TraceOnRep() { super(LispInterpreter.TRACE_ON_NAME); }


    public final SchemeObject Eval(Environment env)
        throws SchemeException
    {
        LispInterpreter.setTraceState(true);
        return False;
    }
}
