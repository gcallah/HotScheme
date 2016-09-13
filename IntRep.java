

class IntRep extends Rational
{
    IntRep(long l)    { super((double)l); val = l; }


    public boolean Integerp()
    {
        return true;
    }


	public boolean Eqv(SchemeObject obj)
	{
        boolean b = false;

	    if(!(obj.Integerp())) return super.Eqv(obj);
	    else
	    {
	        try
	        {
	            b = (val == obj.IntVal());
	        }
            catch(SchemeException excp)
            {
// should never get this!
                LispInterpreter.trace_print("Internal error in eqv?");
            }
            return b;
	    }
	}


    public String Write()
    {
        return(Long.toString(val));
    }


    public long IntVal() throws SchemeException
    {
        return val;
    }


    public double FloatVal() throws SchemeException
    {
        return real;
    }


    private long val;
}

