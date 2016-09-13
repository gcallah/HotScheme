

class Rational extends Complex
{
    Rational(double d)   { real = d; }


    public boolean Rationalp()
    {
        return true;
    }


    public String Write()
    {
        return(Double.toString(real));
    }


    public long IntVal() throws SchemeException
    {
        return (long)real;
    }


    public double FloatVal() throws SchemeException
    {
        return real;
    }
}

