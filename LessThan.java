
/**
* functor to call greater than on a list of numbers
*/
class LessThan extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return lt_aux(args.first(), args.restl());
    }


    private final static SchemeObject lt_aux(SchemeObject num1, SchemeObject nums)
        throws SchemeException
    {
        return
            (nums.Nullp()) ? True
                :
                    ((num1.FloatVal() >= (nums.first()).FloatVal()) ?
                    False
                    : lt_aux(nums.first(), nums.restl()));
    }


    public final String Display()
    {
    	return "(< num1 num2 ...):\nreturns the true if num1 ... numN are monotonically increasing, else false\n";
    }
}
