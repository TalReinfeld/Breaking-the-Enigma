import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        UIImp ui = new UIImp();
        ui.run();

//        // Creates an array in which we will store the names of files and directories
//        String[] fileNames;
//
//        // Creates a new File instance by converting the given pathname string
//
//        String folderPath="C:\\Users\\talre\\source\\repos" ;//replace with your own folder path
//
//        File f = new File(folderPath);
//        // Populates the array with names of files and directories
//        fileNames=f.list();
//        assert fileNames != null;
//        Arrays.sort(fileNames);
//
//
//        EngineImp engine=new EngineImp();//enter your implement
//        int i=0;
//        // For each pathname in the pathnames array
//        for (String fileName : fileNames) {
//            try{
//                System.out.println((++i) +" # "+fileName+":");
//
//                engine.readSystemDetailsFile(folderPath+fileName);//enter your implement
//            }
//            catch (Exception e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//        }
    }
}
