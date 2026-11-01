/*
    Lupinus
    orig. 01.11.2026
    This is a Child Class inheriting from java.awt.event.*;
    It adds some necessary Functions and such.
 */
import java.awt.event.*;
public class NWindowAdapter extends WindowAdapter {
    /***Public***/
    public void windowClosing(WindowEvent event) {
        System.exit(0);
    }
}
