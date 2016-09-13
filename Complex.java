

/**
* the class which is the base of all numbers
*/
class Complex extends TRep
{
    public boolean Numberp()
    {
        return true;
    }


	public boolean Eqv(SchemeObject obj)
	{
        boolean b = false;

	    if(!(obj.Numberp())) return false;
	    else
	    {
	        try
	        {
	            b = (real == obj.FloatVal()); // add imaginary!
	        }
            catch(SchemeException excp)
            {
// should never get this!
                LispInterpreter.trace_print("Internal error in eqv?");
            }
            return b;
	    }
	}


    protected double real = 0.0;
    protected double imag = 0.0;
}

