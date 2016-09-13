import java.awt.*;

public class BorderPanel extends Panel 
{
	Color m_BorderColor;
	int m_nTop, m_nLeft, m_nBottom, m_nRight;
	Insets m_borderDimensions;


	public BorderPanel(Component borderMe, Color oBorderColor, 
			int nTop, int nLeft, int nBottom, int nRight)	
	{ 
		m_borderDimensions = new Insets(nTop, nLeft, nBottom, nRight);
		m_BorderColor = oBorderColor;
		setLayout(new BorderLayout());
		add("Center", borderMe);
	}


	public void paint(Graphics g) 
	{
		Dimension mySize   = getSize();
		g.setColor(m_BorderColor);

		// Top Inset area
		g.fillRect(0,
		           0, 
				   mySize.width,  
				   m_borderDimensions.top);

		// Left Inset area
		g.fillRect(0, 
		           0, 
				   m_borderDimensions.left, 
				   mySize.height);

		// Right Inset area
		g.fillRect(mySize.width - m_borderDimensions.right, 
		           0, 
		           m_borderDimensions.right, 
				   mySize.height);

		// Bottom Inset area
		g.fillRect(0, 
		           mySize.height - m_borderDimensions.bottom, 
				   mySize.width,
				   mySize.height);
	}


	public Insets getInsets() 
	{ 
		return m_borderDimensions;
	}
}
