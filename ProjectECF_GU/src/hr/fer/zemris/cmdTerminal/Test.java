package hr.fer.zemris.cmdTerminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

        /**
         * @param args
         * @throws FileNotFoundException 
         */
        public static void main(String[] args) {
                DetectOS os = new DetectOS();
                int osName = os.getOS();
                System.out.println(osName);
                
                ITalk cmd = null;
                if(osName == DetectOS.WINDOWS){
                        cmd = new CmdTalk();
                }
                if(osName == DetectOS.LINUX){
                        cmd = new TerminalTalk();
                }
                
                //cmd.write("lib","c.exe>in.txt");
                //cmd.write("C:", "C:/Users/Vlaho/Desktop/mythsim-3.1.1", "java -jar mythsim-3.1.1.jar");
                //cmd.write("C:/output", "c.exe>lib/in.txt");
                cmd.write("lib", "c>lib/in.txt");
                
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