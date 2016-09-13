

class QuoteRep extends SyntacticalForm
{
    protected QuoteRep(String s) { super(s); }


    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return args.first();
    }
}
