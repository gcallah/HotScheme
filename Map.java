class Map extends BuiltIn
{
    public SchemeObject Apply(SchemeObject args, Environment env)
        throws SchemeException
    {
        SchemeObject f    = args.first();
        SchemeObject ls   = args.second();
        SchemeObject more =
            (args.Length() > 2) ?
                (args.restl()).restl()
                : null;
        return map_aux(f, ls, more, env);
    }


    private SchemeObject map_aux(SchemeObject f,
        SchemeObject ls, SchemeObject more, Environment env)
        throws SchemeException
    {
        if(more == null) return map_aux1(f, ls, env);
        else             return map_aux_more(f, ls, more, env);
    }


    private SchemeObject map_aux1(SchemeObject f,
        SchemeObject ls, Environment env)
        throws SchemeException
    {
        if(ls.Nullp()) return SchemeObject.False;
        else
            return SchemeObject.cons(
                f.Apply(SchemeObject.cons(ls.first(), SchemeObject.False),
                    env),
                map_aux1(f, ls.restl(), env));
    }


    private SchemeObject map_aux_more(SchemeObject f,
        SchemeObject ls, SchemeObject more, Environment env)
        throws SchemeException
    {
        if(ls.Nullp()) return SchemeObject.False;
        else
            return SchemeObject.cons(
                    f.Apply(
                        SchemeObject.cons(
                            ls.first(),
                            map_aux(First.car, more, null, env)
                        ),
                        env
                    ),
                    map_aux_more(f, ls.restl(),
                        map_aux(Rest.cdr, more, null, env),
                        env)
                );
    }


    public String Display()
    {
        return "(map procedure list1 list2 ...)\n"
            + "returns: list of results\n";
    }
}

