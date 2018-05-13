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

public class SystemDetector {

	private static String what_OS = null;
	
	public static String getOsName() {
		if(what_OS == null) { 
            what_OS = System.getProperty("os.name"); 
        }
        return what_OS;
	}
	
	//checks if OS is MS windows
	public static boolean isWindows(){
        return getOsName().startsWith("Windows");
    }
	
	//checks if OS is Unix
	public static boolean isUnix(){
        return getOsName().startsWith("Unix");
    }
	
	//checks if OS is Mac OS
	public static boolean isMacOS(){
        return getOsName().startsWith("mac");
    }
	
}
