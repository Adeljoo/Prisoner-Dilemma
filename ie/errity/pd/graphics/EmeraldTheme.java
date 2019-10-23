/*******************************************************************************
 * Copyright (c) 2018Copyright (C)  Ameneh Deljoo based on the Code by Andrew Errity
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *******************************************************************************/
package ie.errity.pd.graphics;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

/**
 * This class implements a custom 'green' look and feel to replace the default
 * Java metal look and feel.
 * @author	Andrew Errity 99086921
 */
public class EmeraldTheme extends DefaultMetalTheme
{
	/**
	 *Returns the name of the theme. 
	 *@return	the theme's name
	 */
    public String getName() { return "Emerald"; }
  
    //green shades
    private final ColorUIResource c1 = new ColorUIResource(102, 102, 153);
    private final ColorUIResource c2 = new ColorUIResource(153,153, 204);
    private final ColorUIResource c3 = new ColorUIResource(204, 204, 255); 
    
    private final ColorUIResource c4 = new ColorUIResource(102, 102, 102);
    private final ColorUIResource c5 = new ColorUIResource(153, 153, 153);
    private final ColorUIResource c6 = new ColorUIResource(204, 204, 204);
    
    //Functions overridden from DefaultMetalTheme class
    protected ColorUIResource getPrimary1() { return c1; }  
    protected ColorUIResource getPrimary2() { return c2; } 
    protected ColorUIResource getPrimary3() { return c3; } 
    
    protected ColorUIResource getSecondary1() { return c4; }
    protected ColorUIResource getSecondary2() { return c5; }
    protected ColorUIResource getSecondary3() { return c6; }
    
    
}
