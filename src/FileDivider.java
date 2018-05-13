/*
************************************************************************
Copyright 2018 Krzysztof Stê¿a³a

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
************************************************************************
*/

/**
 * 
 * @author filesmuggler
 *
 */

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


//FileDivider provides unencrypted file dividing
public class FileDivider {
	
	private String input_path;
	private String what_OS;
	
	//constructor
	FileDivider(){
		SystemDetector sys_detect = new SystemDetector();
		
		if(sys_detect.isWindows()) {
			System.out.println("MS Windows system detected");
            what_OS = "windows";
		}
		else if(sys_detect.isUnix()){
            System.out.println("Linux system detected");
            what_OS = "linux";
        }
        else if(sys_detect.isMacOS()){
            System.out.println("MacOS system detected");
            what_OS = "mac";
        }
        else{
            System.out.println("Detection error");
        }
	}
	
	//universal path setter 
    private String settingPath(String p_file){
        String result="";
        
        if(what_OS == "windows"){
            for(int i = 0; i < p_file.length(); i++){
            	result = result + p_file.charAt(i);
                if(p_file.charAt(i)=='\\'){
                	result = result + '\\';
                }
            }
        }
        else if(what_OS == "linux"){
            System.out.println("Linux not supported yet.");
        }
        else if(what_OS == "mac"){
            System.out.println("Mac not supported yet.");
        }
        return result;
    }
	
    //chooses file to be divided
    public void chooseFile(String file){
        input_path = settingPath(file);
    }
    
    //reads file byte-by-byte
    private byte[] readFileBytes(String p_path) throws IOException{
        Path path = Paths.get(p_path);
        return Files.readAllBytes(path);
    }
    
    //writes new file byte-by-byte
    private void writeFileBytes(byte[] contents, String p_path) throws IOException{
        Path path = Paths.get(p_path);
        Files.write(path, contents);
    }
    
    //divides given file into 2 separate parts 
    public void divideFile(String output_path_1 ,String output_path_2) throws IOException{
        byte[] input_file = readFileBytes(input_path);
        int file_len = input_file.length;
        byte[] output_1 = new byte[file_len/2+1];
        byte[] output_2 = new byte[file_len/2];
        int counter_1 = 0;
        int counter_2 = 0;
        for(int i=0; i < input_file.length; i++){
            if(i%2==0){
                output_1[counter_1] = input_file[i];
                counter_1++;
            }
            else{
                output_2[counter_2] = input_file[i]; 
                counter_2++;
            }
        }
        
        String path_1="";
        String path_2="";
       
        path_1 = settingPath(output_path_1);
      
        path_2 = settingPath(output_path_2);
           
        writeFileBytes(output_1,path_1);
        writeFileBytes(output_2,path_2);
        
    }
}