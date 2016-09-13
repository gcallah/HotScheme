
/**
* functor to call greater than on a list of numbers
*/
class GreaterThan extends BuiltIn
{
    public final SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return gt_aux(args.first(), args.restl());
    }


    private static final SchemeObject gt_aux(SchemeObject num1, SchemeObject nums)
        throws SchemeException
    {
        return
            (nums.Nullp()) ? True
                :
                    ((num1.FloatVal() <= (nums.first()).FloatVal()) ?
                    False
                    : gt_aux(nums.first(), nums.restl()));
    }


    public final String Display()
    {
    	return "(> num1 num2 ...):\nreturns the true if num1 ... numN are monotonically decreasing, else false\n";
    }
}

