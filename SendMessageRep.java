

// for Abstract Objects, this creates the syntax
//  (send-message obj msg arg ...)
class SendMessageRep extends SyntacticalForm
{
    protected SendMessageRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject obj       = (args.first()).Eval(env);
        String msg             = (args.second()).Write();
        SchemeObject real_args = ((args.restl()).restl()).Evargs(env);

        return obj.AcceptMessage(msg, real_args);
    }
}
