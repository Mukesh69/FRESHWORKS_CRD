import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

public class CRD {
	
	//To fix the file storage path
	private File dir;
    Scanner scan;
    public CRD(String path){
        dir = new File(path);
        scan = new Scanner(System.in);
        if(!dir.mkdir()){                      
            System.out.println("cannot create datastore");
        }
        else System.out.println("DataStore created at "+path);
    }
    
    //Create a new key value pair file
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String def_path = "C:/Data Store";       
	        String path = "";
	        int opt = 0;                           
	        String key;
	        JSONObject obj = new JSONObject();
	        Scanner scan = new Scanner(System.in);

	        System.out.println("SPECIFY PATH OF DATA STORE.FOR DEFAULT PATH (C:/Data Store) GIVE 'DEFAULT' AS INPUT:");
	        path = scan.next();
	        if(path.equalsIgnoreCase("default")) path = def_path;

	        CRD ds = new CRD(path);

	        while(opt!=4){
	            System.out.println("Select the option for \n1.Create \n2.Read \n3.Delete \n4.Quit");
	            opt = scan.nextInt();
	            switch (opt) {
	                case 1:
	                    ds.create(path);
	                    break;
	                case 2:
	                    System.out.print("Enter the key to read : ");
	                    key = scan.next();
	                    ds.read(path, key);
	                    break;
	                case 3:
	                    System.out.print("Enter the key to delete file: ");
	                    key = scan.next();
	                    ds.delete(path, key);
	                    break;
	                default:
	                    System.out.print("Incorrect Option");
	                    break;
	            }
	        }
	}

}
