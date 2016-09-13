
/**
* functor to subtract a list of numbers
*/
class Minus extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
    	if(args.Nullp())
    	    throw new SchemeException(SchemeException.TOO_FEW_ARGS);
    	else
    	{
    		if(args.restl().Nullp())
    		{
    			return SchemeObject.make(
    			    args.first().FloatVal() * (-1));
    		}
    		else
    		{
    			return(SchemeObject.make(
    			    minus_aux(args.first().FloatVal(),
    			        args.restl())));
    		}
    	}
    }


    private static double minus_aux(double this_number,
            SchemeObject more_numbers)
        throws SchemeException
    {
    	if(more_numbers.Nullp()) return(this_number);
    	else
    	{
    		return(minus_aux(this_number - more_numbers.first().FloatVal(),
    		    more_numbers.restl()));
    	}
    }


	public String Display()
	{
		return "(- num1 num2 ...):\nreturns the result of subtracting num2 ... numN from num1\n";
	}
}

