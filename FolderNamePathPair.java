/*
    Lupinus
    orig. 01.11.2026
    This is a helper class holding each the Name and Path for a Folder.
 */
public class FolderNamePathPair {
    /***Attributes***/
    private String _name; public String GetName() {return _name;}
    private String _path; public String GetPath() {return _path;}

    /***Constructor***/
    FolderNamePathPair(String pName, String pPath) {
        _name = pName;
        _path = pPath;
    }

    /***Overrides***/
    //This Override returns the Name of the Object instead of the address name
    @Override
    public String toString() {
       return _name;
    }
}
