
/**
* functor to call substring on a obj
*/
class Substring extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        return SchemeObject.make(
            ((args.first()).StringVal()).substring(
                (int)(args.second()).IntVal(),
                (int)(args.third()).IntVal()));
    }


    public String Display()
    {
        return "(substring string start end)\n"
            + "returns: the portion of string from start to end\n";
    }
}
