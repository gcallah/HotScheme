
/**
* functor to call plus on a list of numbers
*/
class Plus extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make((args.first()).FloatVal()
            + ((args.Length() > 1) ? plus_aux(args.restl()) : 0));
    }


    private static double plus_aux(SchemeObject ls)
        throws SchemeException
    {
        return (ls.first()).FloatVal()
            + ((ls.Length() > 1) ? plus_aux(ls.restl()) : 0);
    }


    public String Display()
    {
    	return "(+ num1 num2 ...):\nreturns the result of adding num1 ... numN\n";
    }
}

