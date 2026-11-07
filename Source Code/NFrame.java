/*
    Lupinus
    orig. 01.11.2026
    This is a Child Class inheriting from javax.swing.JFrame.
    It adds some necessary Functions and such.
 */
import javax.swing.*;
import java.awt.*;
public class NFrame extends JFrame {
    /***Attributes***/
    private NWindowAdapter _nWindowAdapter; public NWindowAdapter GetNWindowAdapter() {return _nWindowAdapter;}
    private CardLayout _cardLayout; public CardLayout GetCardLayout() {return _cardLayout;}

    /***Constructor***/
    NFrame(String pName, Dimension pDimension) {
        this.setName(pName);
        this.setTitle(pName);
        this.setSize(pDimension);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this._nWindowAdapter = new NWindowAdapter();
        this.addWindowListener(_nWindowAdapter);
        this._cardLayout = new CardLayout();
        this.setLayout(_cardLayout);
    }
}
