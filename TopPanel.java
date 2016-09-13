import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.util.Vector;
import java.util.Enumeration;


public class TopPanel extends Panel
{
    Label           m_EvalLabel, m_ResultLabel;
    DisplayTerminal hist_view;
    EvalTerminal    edit_view;
    DisplayTerminal rslt_view;
    Panel           m_panRight, m_panLeft;
    BorderPanel     m_progTermBorder;
    Button          m_butEval;
    Button          m_butClearEval;
    Button          m_butClearRslt;
    Button          m_butClearHist;
    TraceButton     m_butTraceSwitch;

    LispInterpreter m_LispInterpreter;
    GUILispTerminal m_GUILispTerm;
    Applet          m_MyApplet;


    TopPanel(Applet my_applet)
    {
        m_MyApplet = my_applet;
        init();
    }


    public void init()
    {
        setBackground(java.awt.Color.pink);

        edit_view    = new EvalTerminal();
        hist_view    = new DisplayTerminal();
        rslt_view    = new DisplayTerminal();

        Panel leftPanel  = getLeftPanel();
        Panel rightPanel = getRightPanel();


        GridBagLayout      gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbl);

        gbc.fill       = GridBagConstraints.BOTH;
        gbc.anchor     = GridBagConstraints.NORTHWEST;
        gbc.weightx    = 1.0;
        gbc.weighty    = 1.0;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbl.setConstraints(leftPanel, gbc);
        add(leftPanel);

        gbl.setConstraints(rightPanel, gbc);
        add(rightPanel);

        m_GUILispTerm = new GUILispTerminal();
        try
        {
            m_LispInterpreter = new LispInterpreter(m_GUILispTerm,
                m_MyApplet);
        }
        catch (SchemeException e)
        {
            System.out.print(e.getMessage());
        }

        edit_view.requestFocus();
    }


    public Panel getLeftPanel()
    {
        Panel panel = new Panel();

        GridBagLayout      gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);

        Panel m_butPanel = new Panel();
        m_butPanel.add(m_butEval = new Button("Evaluate Code"));
        m_butPanel.add(m_butTraceSwitch = new TraceButton
            ("Trace On", "Trace Off"));
        m_butPanel.add(m_butClearEval = new Button("Clear Code"));
        m_butPanel.add(m_butClearRslt = new Button("Clear Results"));
        m_butPanel.add(m_butClearHist = new Button("Clear History"));

        m_EvalLabel   = new Label("Enter Your Scheme Code in the Window Below:",Label.LEFT);
        m_ResultLabel = new Label("View Results Below:", Label.LEFT);


        gbc.fill    = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(1,2,0,2);


        gbl.setConstraints(m_EvalLabel, gbc);
        panel.add(m_EvalLabel);

        gbc.insets = new Insets(0,2,1,2);
        gbc.fill    = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.0;
        //gbc.gridx = 0;
        //gbc.gridy = 1;

        gbl.setConstraints(m_EvalLabel, gbc);
        panel.add(m_EvalLabel);

        gbc.insets = new Insets(0,2,1,2);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbl.setConstraints(edit_view, gbc);
        panel.add(edit_view);

        gbc.insets = new Insets(0,2,1,2);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbl.setConstraints(m_butPanel, gbc);
        panel.add(m_butPanel);

        gbc.insets = new Insets(0,2,0,2);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbl.setConstraints(m_ResultLabel, gbc);
        panel.add(m_ResultLabel);

        gbc.insets = new Insets(0,2,1,2);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbl.setConstraints(rslt_view, gbc);
        panel.add(rslt_view);

        return panel;
    }


    public Panel getRightPanel()
    {
        Panel panel = new Panel();

        GridBagLayout      gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);

        gbc.fill      = GridBagConstraints.BOTH;
        gbc.anchor    = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets    = new Insets(0,0,0,0);
        gbc.weightx   = 1.0;
        gbc.weighty   = 0.0;


        Label histLabel = new Label("Session History Appears Below:",Label.LEFT);

        gbl.setConstraints(histLabel, gbc);
        panel.add(histLabel);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        gbc.weighty = 1.0;
        gbl.setConstraints(hist_view, gbc);
        panel.add(hist_view);

        return panel;
    }



    public boolean action(Event evt, Object obj)
    {
        if (evt.target == m_butEval)
        {
            String szInput = edit_view.getText();
            m_GUILispTerm.setInput(szInput);

            boolean bStatus =
                m_LispInterpreter.read_eval_print_loop(m_GUILispTerm);

            Vector vOutput = m_GUILispTerm.getOutput();

            String szTemp;
            for (Enumeration e = vOutput.elements(); e.hasMoreElements();)
            {
                szTemp = (String)(e.nextElement());
                System.out.println(szTemp);
                rslt_view.append(szTemp);
            }
            if (bStatus)
            {
                hist_view.append(szInput.concat("\n"));
                edit_view.cmndOK(szInput);
            }
        }
        else if(evt.target == m_butClearEval)
        {
            edit_view.setText("");
        }
        else if(evt.target == m_butClearRslt)
        {
            rslt_view.setText("");
        }
        else if(evt.target == m_butClearHist)
        {
            hist_view.setText("");
        }
        else if(evt.target == m_butTraceSwitch)
        {
            LispInterpreter.setTraceState(m_butTraceSwitch.isStateOn());
        }
        edit_view.requestFocus();
        return false;
    }
}


class TraceButton extends Button
{
    boolean mTextStateOn  = false;
    String  mButtonTextOn;
    String  mButtonTextOff;

    public TraceButton(String szButtonTextOn, String szButtonTextOff)
    {
        super(szButtonTextOn);
        mButtonTextOn  = szButtonTextOn;
        mButtonTextOff = szButtonTextOff;
    }


    public boolean action(Event evt, Object obj)
    {
        System.out.println("TraceButton::Action");
        mTextStateOn = !mTextStateOn;
        setLabel(mTextStateOn ? mButtonTextOff : mButtonTextOn);
        return false;
    }


    public boolean isStateOn()
    {
        return mTextStateOn;
    }

}

// Quick fix to get give flow layout a size for this
// panel so it takes up a full column
class SizablePanel extends Panel
{
    int m_nWidth, m_nHeight;
    SizablePanel(int nWidth, int nHeight)
    {
        super();
        m_nWidth = nWidth;
        m_nHeight = nHeight;
    }
}


class TextAreaTerminal extends TextArea
{

    static final char CTRL_H     = (char)8;
    static final char UNDERSCORE = '_';
    static final char NEWLINE    = '\n';
    static final int  TOPMARGIN  = 10;
    static final int  BUFFER_CHAR_WIDTH = 80; // the width of the buffer in
                                              // characters.

    static final int  BUFFER_CHAR_HEIGHT = 25; // the height of the buffer in
                                               // characters.
    int m_hVerticalPosition   = 12;
    int m_hHorizontalPosition = 0;
    int m_charWidth;
    int m_charHeight;
    int m_bufferPixelWidth;
    int m_bufferPixelHeight;
    int m_nCurCanvasLine = 0;
    ScreenBuffer m_screenBuffer;


    public TextAreaTerminal()
    {
    }


    public void init()
    {
        m_screenBuffer      = new ScreenBuffer(25, 80);
        setFont(new Font("Courier", Font.PLAIN, 14));
        Graphics g          = getGraphics();
        FontMetrics oCurFontMetrics = g.getFontMetrics(g.getFont());
        m_charWidth         = oCurFontMetrics.charWidth('W');
        m_charHeight        = oCurFontMetrics.getHeight();
        setBackground(java.awt.Color.white);
        m_bufferPixelWidth  = m_charWidth  * BUFFER_CHAR_WIDTH;
        m_bufferPixelHeight = m_charHeight * BUFFER_CHAR_HEIGHT;
    }


    public void print(char cToPrint)
    {
        if (cToPrint == CTRL_H)
        {
            m_screenBuffer.backspace();
        }
        else if (cToPrint == NEWLINE)
        {
            m_screenBuffer.newLine();
        }
        else
        {
            m_screenBuffer.addChar(cToPrint);
        }
        repaint();
    }


    public void print(String szToPrint)
    {
        char[] acToPrint = szToPrint.toCharArray();
        for (int i = 0; i < acToPrint.length; i++)
        {
            print(acToPrint[i]);
        }
        repaint();
    }


    public void print(Object obj)
    {
        String szToPrint = obj.toString();
        char[] acToPrint = obj.toString().toCharArray();
        for (int i = 0; i < acToPrint.length; i++)
        {
            print(acToPrint[i]);
        }
    }


    public void println(Object obj)
    {
        print(obj);
        print("\n");
    }


    public void print(int i)
    {
        print(Integer.toString(i));
    }


    public void unread(int c)
    {

    }


    public int read()
    {
        return 0;
    }


    public void print(long l)
    {
        print(Long.toString(l));
    }


    public boolean keyDown(Event evt, int nKey)
    {
        return false;
    }
}


class DisplayTerminal extends TextAreaTerminal
{
    DisplayTerminal()
    {
        setEditable(false);
        setBackground(java.awt.Color.lightGray);
    }
}


class EvalTerminal extends TextAreaTerminal
{
    EvalTerminal()
    {
        setBackground(java.awt.Color.white);
    }


    public boolean keyDown(Event e, int key)
    {
        if ((e.modifiers & Event.CTRL_MASK) != 0)
        {
            if (e.id == Event.KEY_ACTION)
            {
                switch (key)
                {
                    case Event.UP:
                        if (curr_cmnd > 0)
                        {
                            setText((String)(history.elementAt(--curr_cmnd)));
                        }
                        return true;
                    case Event.DOWN:
                        if (curr_cmnd < (history.size() - 1))
                        {
                            setText((String)(history.elementAt(++curr_cmnd)));
                        }
                        return true;
                }
            }
        }
/*
Brian -- any clue how to make this work?
        else if((e.modifiers == 0) || ((e.modifiers & Event.SHIFT_MASK) != 0))
        {
            if(e.id == Event.KEY_PRESS)
            {
                switch((char)e.key)
                {
// because of the odd relation of the caret position to the length of the string
//  (new lines seem to get counted as two chars), we've just implemented
//  autoindent at the end of the field:
                    case '\n':
                        String s = getText();
                        if(getCaretPosition() >= s.length()) // at end of field
                        {
                            StringBuffer indent = new StringBuffer("\n");
                            char c;
                            for(int i = s.lastIndexOf('\n') + 1;
                                i > 0 && i < s.length() && Character.isWhitespace(c = s.charAt(i));
                                i++)
                                indent.append(c);

                            append(indent.toString());
                            return true;
                        }
                        else return false;
                }
            }
        }
*/
        return (false);
    }


    public void cmndOK(String cmnd)
    {
        history.addElement(cmnd);
        curr_cmnd = history.size();
        setText("");
    }

    private Vector history   = new Vector();
    private int    curr_cmnd = 0;
}


class ScreenBuffer
{
    Vector page;
    int m_nCurCol = 1;
    int m_nCurRow = 1;
    int m_nNumRows;
    int m_nNumCols;
    boolean m_nSingleChar = false;


    public ScreenBuffer(int nNumRows, int nNumCols)
    {
        page = new Vector();

        page.addElement(new StringBuffer());

        m_nNumRows = nNumRows;
        m_nNumCols = nNumCols;
    }


    public void addChar(char ch)
    {
        ((StringBuffer)(page.elementAt(m_nCurRow - 1))).append(ch);

        if (m_nCurCol <= m_nNumCols)
        {
            m_nCurCol++;
        }
        else
        {
            page.addElement(new StringBuffer());
            m_nCurCol = 1;
            m_nCurRow++;
        }
    }


    public void backspace()
    {
        int nLineLength = ((StringBuffer)page.elementAt(m_nCurRow - 1)).length();
        if (nLineLength > 0)
        {
            ((StringBuffer)page.elementAt(m_nCurRow - 1)).setLength(nLineLength - 1);
            m_nCurCol--;
        }
    }


    public void newLine()
    {
        page.addElement(new StringBuffer());
        m_nCurCol = 1;
        m_nCurRow++;
    }


    public Vector getRawBuffer()
    {
        return page;
    }


    public String getLastLine()
    {
        return ((StringBuffer)page.elementAt(m_nCurRow - 2)).toString();
    }


    public void printChars()
    {
        for (Enumeration e = page.elements(); e.hasMoreElements();)
        {
            System.out.println((StringBuffer)e.nextElement());
        }
    }
}

