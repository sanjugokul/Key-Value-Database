
package key;

import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Key {

    public void write() throws IOException{
         Scanner s=new Scanner(System.in);
        String value,key,keyname=null;
        System.out.println("Enter the key value");
        key=s.nextLine();
        System.out.println("Enter the value: ");
        value=s.nextLine();
        
        File file=new File("C:\\Users\\91807\\Desktop\\Data.txt");
        file.createNewFile();
        File file2=new File("C:\\Users\\91807\\Desktop\\keylist.txt");
        file2.createNewFile();
      
      //Check the entered key is already in the database
      JSONParser parser=new JSONParser();
      try{
          
         JSONArray array=(JSONArray)parser.parse(new FileReader(file2));
         for(Object obj:array){
             JSONObject jsonobject=(JSONObject)obj;
             keyname=(String)jsonobject.get("Keylist");
         } 
      }catch(Exception e){
          e.printStackTrace();
      }
        
      if(key!=keyname){
          
      JSONObject jsonobject1=new JSONObject();
      jsonobject1.put(key, value); 
      
      JSONObject jsonobjet2=new JSONObject();
      jsonobjet2.put("Keylist", key);
      
      try{
          FileWriter writer1=new FileWriter(file,true);
          writer1.write(jsonobject1.toJSONString());
          writer1.flush();
          
          FileWriter writer2=new FileWriter(file2,true);
          writer2.write(jsonobjet2.toJSONString());
          writer2.flush();
      }catch(Exception e){
          e.printStackTrace();
      }
      }
      
      else{
          System.out.println("The key value is already in the keylist");
      }
    }
    
    public void read(){
        Scanner s=new Scanner(System.in);
        String key;
        System.out.println("Enter the key value for read");
        key=s.nextLine();
        
         File file=new File("C:\\Users\\91807\\Desktop\\Data.txt");
         JSONParser parser=new JSONParser();
      try{
          Object obj=parser.parse(new FileReader(file));
          JSONObject object=(JSONObject)obj;
          String name=(String)object.get(key);
          System.out.println(name);
      }catch(Exception e){
          e.printStackTrace();
      }
    }
    
    public void delete() {
        File file=new File("C:\\Users\\91807\\Desktop\\Data.txt");
        JSONParser parser=new JSONParser();
        try{
            Object obj=parser.parse(new FileReader(file));
            JSONArray array=(JSONArray)obj;
            FileWriter writer=new FileWriter(file);
            for(int index=0;index<array.size();++index){
                JSONObject jsonobject=(JSONObject)array.get(index);
                jsonobject.remove("2");
                writer.write(jsonobject.toJSONString());
                writer.flush();
                if(index==array.size()-1)
                    writer.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException{
        Scanner s=new Scanner(System.in);
        int choice;
        boolean continu=true;
        Key k=new Key();
        
        System.out.println("1.Write 2.Read 3.Delete 4.End");
        while(continu){
            System.out.println("Enter the operation");
            choice=s.nextInt();
            switch(choice){
                case 1:
                    k.write();
                    break;
                case 2:
                    k.read();
                    break;
                case 3:
                    k.delete();
                    break;
                case 4:
                    continu=false;
                    break;
                    
            }
        }   
    }    
}
