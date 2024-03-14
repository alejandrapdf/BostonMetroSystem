~~~~To run the code using the main method in IntelliJ 
1) Extract zip file "CS308-Boston.zip"
2) Open project in IntelliJ (File->Open->select "CS308-Boston" folder)
3) Load Maven build script (bottom right of screen)
4) When prompted to by IntelliJ, choose to trust the project
5) Assign SDK to the project (File->Project Structure->Project->SDK)
6) Run "MGraphDriver" (main) class

~~~~To run the jar files in IntelliJ
1) Download a JFX SDK of your choice (we used openjfx https://openjfx.io/)
2) extract to a location of your choice
3) add the location of the lib forlder as a 'VM option' in IntelliJ run/debug configurations
		(eg. --module-path "${PATH_TO_FX}" --add-modules javafx.controls,javafx.fxml 
		where "${PATH_TO_FX}" is the path to the lib folder of the JFX SDK)
4) Run either of the jar files in the project 
		CS308-Boston\out\artifacts\CS308_Boston_jar\CS308-Boston.jar 
		or
		CS308-Boston\target\CS308-Boston-1.0-SNAPSHOT.jar		
