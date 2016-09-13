
/**
* functor to call divide a list of numbers
*/
class Div extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
    	if(args.Nullp()) throw new SchemeException(SchemeException.TOO_FEW_ARGS);
    	else
    	{
    		if(args.restl().Nullp())
    		{
    			return SchemeObject.make(1 / args.first().FloatVal());
    		}
    		else
    		{
    			return(SchemeObject.make(div_aux(args.first().FloatVal(),
    			    args.restl())));
    		}
    	}
    }


    private static final double div_aux(double this_number,
            SchemeObject more_numbers)
        throws SchemeException
    {
    	if(more_numbers.Nullp()) return(this_number);
    	else
    	{
    		return(div_aux(this_number / more_numbers.first().FloatVal(),
    		    more_numbers.restl()));
    	}
    }



    public final String Display()
    {
        return "(/ num1 num2 num3 ...)\n"
            + "returns: with one arg, the reciprocal of num1\n"
            + "with > 1 arg, the results of dividing num1 by "
            + "the product of the remaining arguments.";
    }
}

