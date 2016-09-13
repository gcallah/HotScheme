

/**
SymbolRep is the representation of the Scheme character type.
At present it implements the Scheme commands <code>symbol?</code>,
<code>equal?</code>,
and <code>eval</code>.
@see TRep
*/
class SymbolRep extends TRep
{
    SymbolRep(StringBuffer s)
    {
        name      = s.toString();
    }

    SymbolRep(String s)
    {
        name = s;
    }


    public String Write()
    {
        return(name);
    }


    public boolean Symbolp()
    {
        return true;
    }


	public boolean Equal(SchemeObject obj)
	{
	    if(!(obj.Symbolp())) return false;
	    else                 return name.equals(obj.Write());
	}


    public SchemeObject Eval(Environment env)
        throws SchemeException
    {
        return env.Eval(Write());
    }


    private String name;
}
