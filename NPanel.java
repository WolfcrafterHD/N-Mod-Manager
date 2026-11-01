/*
    Lupinus
    orig. 01.11.2026
    This is a Child Class inheriting from javax.swing.JPanel.
    It adds some necessary Functions and such.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
public class NPanel extends JPanel {
    /***Attributes***/
    private BufferedImage _background; public void SetBackgroundImage(BufferedImage pImage) {_background = pImage;}
    private final String _name; public String GetName() {return _name;}

    /***Constructor***/
    NPanel(String pName, Dimension pDimension, NFrame pParentWindow) {
        this._name = pName;
        this.setPreferredSize(pDimension);
        pParentWindow.add(this, _name);
    }

    /***Overrides***/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(_background, 0, 0, getWidth(), getHeight(), this);
    }
}
