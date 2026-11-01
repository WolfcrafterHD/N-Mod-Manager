import java.awt.*;

/*
    Lupinus
    orig. 01.11.2026
    This Script Manages and initializes everything concerning java.swing
    Frames and Windows.
 */
public class Display {
    /***Attributes***/
    public static Display INSTANCE;

    private NFrame _frameMain; public NFrame GetFrameMain() {return _frameMain;}
    private Dimension _frameAndPanelSize;

    private NPanel _panelMain; public NPanel GetPanelMain() {return _panelMain;}
    private NPanel _panelSettings; public NPanel GetPanelSettings() {return _panelSettings;}

    /***Constructor***/
    Display() {
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    /***Public***/
    public void Instantiate() {
        _frameAndPanelSize = new Dimension(1280, 720);
        _frameMain = new NFrame("N Mod Manager for Linux", _frameAndPanelSize);

        _panelMain = new NPanel("PanelMain", _frameAndPanelSize, _frameMain);
        _panelSettings = new NPanel("PanelSettings", _frameAndPanelSize, _frameMain);
    }

    public void MainFrameStartup() {
        SwitchPanel(_frameMain, _panelMain);
        _frameMain.setVisible(true);
    }

    public void SwitchPanel(NFrame pFrame, NPanel pPanel) {
        pFrame.GetCardLayout().show(pFrame.getContentPane(), pPanel.GetName());
        pPanel.setVisible(true);
        _frameMain.repaint();
    }
}
