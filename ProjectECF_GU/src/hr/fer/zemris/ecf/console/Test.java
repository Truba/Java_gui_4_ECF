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
//                console.write(new Job("D:\\Documents\\SVEN\\FER\\Programiranje\\ECF_1.3\\debug\\examples\\GAonemax\\VS\\Debug\\gaonemax.exe", "lib\\log1.txt", 
//                		"lib\\parameters.xml"));
//                console.write("D:\\Documents\\SVEN\\FER\\Programiranje\\ECF_1.3\\debug\\examples\\GAonemax\\VS\\Debug\\gaonemax.exe", "lib\\params.txt");
                
//                console.write(new Job("C:\\Temp\\GAOneMax.exe", "lib\\log1.txt", "lib\\parameters.xml"));
//                console.write("C:\\Temp\\GAOneMax.exe", "lib\\parameters.xml");
//                ProcessBuilder pb = new ProcessBuilder("cmd.exe /c C:\\Temp\\GAOneMax.exe -gui -pardump out.txt");
//                try {
//					pb.start();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
                console.write("C:\\Temp\\ECF_1.3.2\\ECF_1.3.2\\examples\\GAonemax\\VS\\Debug\\GAOneMax.exe", "parameters.txt");
                
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