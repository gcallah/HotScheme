
/**
* The StringSet functor calls string-set! on a string.
*/
class StringSet extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        if(args.first() instanceof StringRep)
            return ((StringRep)(args.first())).StringSet(
                (int)((args.second()).IntVal()),
                (args.third()).CharVal());

        throw new SchemeException(SchemeException.NOT_A_STRING + (args.first()).Write());
    }


    public String Display()
    {
        return "(string-set! string n char)\n"
            + "returns: sets the nth character of string to char; returns string\n";
    }
}
