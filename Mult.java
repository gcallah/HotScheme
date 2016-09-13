
/**
* functor to call mult on a list of numbers
*/
class Mult extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make(
            (args.first()).FloatVal()
            * ((args.Length() > 1) ? mult_aux(args.restl()) : 1));
    }


    private static final double mult_aux(SchemeObject ls)
        throws SchemeException
    {
        return
            (ls.first()).FloatVal()
            * ((ls.Length() > 1) ? mult_aux(ls.restl()) : 1);
    }


	public String Display()
	{
		return "(* num1 num2 ...):\nreturns the result of multiplying num1 ... numN\n";
	}
}

