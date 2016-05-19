Fuzzer
=======
Requirements to create, build and run the Fuzzer and the generated programs
---------------------------------------------------------------------------
* Java 8
* Python 2.7
* Git
* Gradle
* CMake 2.6 or newer
* LunarG Vulkan Loader
* A Vulkan ICD

Supported Systems
-----------
These are the operating systems that the Fuzzer tool has been tested on.

* OS: Linux (Ubuntu), Windows 10

How to build the Fuzzer
-----------------------
Follow these instructions while being in the root of the cloned repository:

### Linux
* ./gradlew jar

### Windows
* gradlew jar

How to use the Fuzzer
---------------------
You can then run the Fuzzer from the build directory in the following way:

* cd build/libs
* java -jar Fuzzer-1.0-SNAPSHOT.jar -library vulkan -output /path/to/output/folder -samples number-of-programs-to-generate

You can optionally supply the -compute parameter to generate only calls to the compute api

or to minimise a program

* java -jar Fuzzer-1.0-SNAPSHOT.jar -library vulkan -input /path/to/metaFile.meta -id id-of-code

The id represents the //ID: id value of the comment that identifies a code section

You can get help about how to use the program by typing java -jar Fuzzer-1.0-SNAPSHOT.jar


How to build and run the generated programs
-------------------------------------------
The tests will be generated in the specified folder along with the Test Runner.

### Linux
While in the folder of the generated programs execute the following commands:

* mkdir build
* cmake -DCMAKE_BUILD_TYPE=(Debug or Release) ..
* make -j8
* python TestRunner.py

The Test Runner will run all of the generated programs.
It will then output either success or failure depending whether or not the
program crash, failed an assertion or exited successfully. A brief summary will
be shown in the end summarising the results of the test. The output of the
individual programs is saved in a file called results.txt

### Windows
While in the folder of the generated programs do the following:

Before generating the visual studio project from CMake, modify the CMakeLists.txt
file by setting the VULKAN_SDK_ROOT variable to the path of the LunarG SDK root.
Make sure you generate the 64 bit binaries of the programs.

Once you have generated the project open it through Visual Studio.

* Select the Debug configuration
* Select Build Solution from the Build section of the menu bar
* python TestRunner.py

It might be required to switch to the Release configuration of the project
due to incompatibilities of the installed Vulkan loader library with the latest
version of Visual Studio (2015).

The Finite State Machine that generates code
-----------
The FSM diagram that generates code can be found in the diagrams folder.
The diagram describes the graphics finite state machine.