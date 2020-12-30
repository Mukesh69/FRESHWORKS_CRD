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
    public void create(String path){
        FileWriter file;
        String key,nv="";
        String[] name_value;
        JSONObject obj = new JSONObject();
        System.out.print("ENTER FILE NAME : ");key = scan.next();
        System.out.println("ENTER KEY AND VALUE (KEY,VALUE).TYPE 'END' TO TERMINATE: ");
        while(true){
            nv = scan.next();
            if(nv.equalsIgnoreCase("end")) break;
            else {
                name_value = nv.split(",", 2);
                try {
                    obj.put(name_value[0], name_value[1]);
                    System.out.println(name_value[0] + "-" + name_value[1]);
                } catch(ArrayIndexOutOfBoundsException e){
                  break;
                } catch (JSONException e) {
                    System.out.print("UNABLE TO LOAD JSON");
                    e.printStackTrace();
                }
            }
        }
        try{
            file = new FileWriter(path+"/"+key+".txt");
            file.write(obj.toString());
            System.out.println("JSON OBJECT : \n"+ obj);
            file.flush();
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
	public static void main(String[] args) throws IOException{
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
