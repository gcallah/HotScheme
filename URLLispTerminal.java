import java.net.URL;
import java.net.MalformedURLException;
import java.io.*;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
import java.io.IOException;


class URLLispTerminal extends BufferedLispTerminal
{
	private boolean debug = false;

    public URLLispTerminal(String url, LispTerminal out)
        throws MalformedURLException, IOException
    {
        super(null, out);

//setBuffer("(car '(a b c))");
//setBuffer("(define fact2 (lambda (n) (do ((i n (- i 1)) (a 1 (* a i))) ((eqv? i 0) a))))");
        BufferedReader in =
            new BufferedReader(new InputStreamReader((new URL(url)).openStream()));

        StringBuffer input = new StringBuffer("");
        String       s     = "";

        while((s = in.readLine()) != null)
        {
            input.append(s);
        }
        setBuffer(input.toString());
        in.close();
/*
*/
    }


// these last two should never be called!
//  exception handling should be put in
    public final void my_print(Object obj)    { }
    public final void my_println(Object obj)  { }
}
