

class TraceOffRep extends SyntacticalForm
{
    protected TraceOffRep() { super(LispInterpreter.TRACE_OFF_NAME); }


    public final SchemeObject Eval(Environment env)
        throws SchemeException
    {
        LispInterpreter.setTraceState(false);
        return False;
    }
}
