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

public class FileMerger {
	private String input_path_1;
	private String input_path_2;
	private String what_OS;
	
	//universal path setter 
    private String settingPath(String p_File){
        String result="";
        
        if(what_OS == "windows"){
            for(int i = 0; i < p_File.length(); i++){
            	result = result + p_File.charAt(i);
                if(p_File.charAt(i)=='\\'){
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
    
    //chooses file to be merged
    public void chooseFiles(String file_1, String file_2){
        input_path_1 = settingPath(file_1);
        input_path_2 = settingPath(file_2);
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
    
    //merges files into one
    public void mergeFiles(String output) throws IOException {
        //read first file
        byte[] input_file_1 = readFileBytes(input_path_1);
        int file_len_1 = input_file_1.length;
        
        //read second file
        byte[] input_file_2 = readFileBytes(input_path_2);
        int file_len_2 = input_file_2.length;
        
        byte[] out_file = new byte[file_len_1 + file_len_2];
        int counter_1 = 0;
        int counter_2 = 0;
        
        for(int i=0; i < (file_len_1 + file_len_2); i++){
            if(i%2 == 0){
                out_file[i] = input_file_1[counter_1];
                counter_1++;
            }
            else{
                out_file[i] = input_file_2[counter_2];
                counter_2++;
            }
        }
        String path = settingPath(output);
        
        writeFileBytes(out_file,path);
    }
    
    //constructor
    FileMerger(){
        SystemDetector detector = new SystemDetector();
        if(detector.isWindows()){
            System.out.println("MS Windows system detected");
            what_OS = "windows";
        }
        else if(detector.isUnix()){
            System.out.println("Linux system detected");
            what_OS = "linux";
        }
        else if(detector.isMacOS()){
            System.out.println("MacOS system detected");
            what_OS = "mac";
        }
        else{
            System.out.println("Detection error");
        }
    }
    
}