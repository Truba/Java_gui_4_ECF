package hr.fer.zemris.ecf.console;

import java.io.FileNotFoundException;

public class Test {

        /**
         * @param args
         * @throws FileNotFoundException 
         */
        public static void main(String[] args) {
                DetectOS os = new DetectOS();
                int osName = os.getOS_asInt();
                System.out.println(osName);
                
                ITalk console = os.getOS_console();
                
                
                
                
                
                //console.write("lib","c.exe>in.txt");
                //console.write("C:", "C:/Users/Vlaho/Desktop/mythsim-3.1.1", "java -jar mythsim-3.1.1.jar");
                //console.write("C:/output", "c.exe>lib/in.txt");
                console.write("lib", "c>lib/in.txt");
                
//                File f = new File("lib/in.txt");
//                while (!f.exists()) {
//                        //wait
//                }
//                
//                if(f.exists()) {
//                        Scanner s = null;
//                        try {
//                                s = new Scanner(f);
//                        } catch (FileNotFoundException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                        }
//                        while(s.hasNextLine()) 
//                        	System.out.println(s.nextLine());
//                        
//                }
                
                
        }

}