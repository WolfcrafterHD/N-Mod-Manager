/*
    Lupinus
    orig 01.11.2026
    This file starts up everything, like absolutely everything.
 */
public class Main {
    /***Entry***/
    public static void main(String[] args) {
        new Display();
        new GUI();
        new Control();

        Display.INSTANCE.Instantiate();
        GUI.INSTANCE.Instantiate();
        Display.INSTANCE.MainFrameStartup();

        Control.INSTANCE.FileLoadingProtocol();
    }
}
