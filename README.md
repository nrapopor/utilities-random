# utilities-random

## Synopsis

This project implements simple random value generation functions.

## Description

The functions are provided for use inside the Parasoft SOATest/Virtualize environments. The functions will support direct invocation
as a Parasoft ExtensionTool and directly, inside an XSL Transform, for example.

### Usage Details

* The following functions are available as ExtensionTool Scripts.
  - `getRandomAlphanumeric(Object, ExtensionToolContext)`
  - `getRandomName()`
  - `getRandomName(Object, ExtensionToolContext)`
  - `getRandomNumber(Object, ExtensionToolContext)`
  - `getRandomString(Object, ExtensionToolContext)`

  The first argument to these functions (with the exception of `getRandomName(...)`) is expected to be a string representing an integer
  that will be used to determine the length of the returned value. For example passing a `10` to `getRandomNumber` as input will generate
  a number with 10 digits.

  For `getRandomName(Object, ExtensionToolContext)` the expected argument is a comma delimited String containing 2 numbers (i.e. __"3,5"__).
  These numbers represent the number of syllables in the First and Last names (respectively). Any of the numbers can be skipped (i.e. __",5"__ or __"5,"__),
  The missing syllable count will be replaced by current default syllable count.

  NOTE: To use this in a virtual asset, extract something (the xpath is irrelevant) from the input, enable "allow modification" and override
  whatever was exported with the appropriate length or syllable count values the add the output pointing to one of the generator functions above.

* The following functions are available as standalone scripts.
 - `getRandomAlphanumeric(String)`
 - `getRandomName()`
 - `getRandomName(int[])`
 - `getRandomName(String)`
 - `getRandomName(String[])`
 - `getRandomNumber(String)`
 - `getRandomString(String)`

 The argument to these versions of the functions is the same as described above. The getRandomName(String[]) and getRandomName(int[]) functions
 take an array of 2 `String` or `int` values that represent the syllable counts for first and last names.

   For example to use in an XSL transformation script
   ````XML
   <xsl:stylesheet version="2.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:saxon="http://saxon.sf.net/"
		xmlns:xslt="http://xml.apache.org/xslt"
		xmlns:random="http://com.parasoft.sa.ctp" xmlns:java="http://xml.apache.org/xslt/java"
		exclude-result-prefixes="saxon random java" extension-element-prefixes="saxon random java">
		<xsl:output method="xml" encoding="UTF-8" indent="yes" xslt:indent-amount="4" omit-xml-declaration="yes"/>
		<xsl:template match="/">
			<xsl:element name="root">
				<xsl:element name="TwelveDigitRandomNumber">
					<xsl:attribute name="value">
					<xsl:value-of select="random:RandomUtils.getRandomNumber(string(12))" /></xsl:attribute>
				</xsl:element>
				<xsl:element name="TwelveDigitString">
					<xsl:attribute name="value">
					<xsl:value-of select="random:RandomUtils.getRandomString(string(12))" /></xsl:attribute>
				</xsl:element>
				<xsl:element name="TwelveDigitRandomAplphaNum">
					<xsl:attribute name="value">
					<xsl:value-of select="random:RandomUtils.getRandomAlphanumeric(string(12))" /></xsl:attribute>
				</xsl:element>
				<xsl:element name="RandomName">
					<xsl:attribute name="type"><xsl:value-of select="string('default')" /></xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="random:RandomUtils.getRandomName()" /></xsl:attribute>
				</xsl:element>
				<xsl:element name="RandomName">
					<xsl:attribute name="type" ><xsl:value-of select="string('3,5')" /></xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="random:RandomUtils.getRandomName('3,5')" /></xsl:attribute>
				</xsl:element>
			</xsl:element>
		</xsl:template>
   </xsl:stylesheet>
   ````
   This stylesheet would create the output that would look simialr to this:
   ````XML
	<root>
	    <TwelveDigitRandomNumber value="901460693668"/>
	    <TwelveDigitString value="fiUQiDGVMCWI"/>
	    <TwelveDigitRandomAplphaNum value="KO6sxfXiNb1K"/>
	    <RandomName type="default" value="Babutus Luesanoth"/>
	    <RandomName type="3,5" value="Daguslus Giltenthidethevar"/>
	</root>
   ````

* To use please add the jars found in the distribution zip to the ***Menu->Parasoft->Preferences->System Settings***.
The utilities-random-\<VERSION\>.jar and all the jars in the `lib` directory of the distribution archive.

* To use the methods inside a script in a Parasoft's extension tool.
 1. The extension tool can pass the expected length of the random value to the script.

	For example:

  ```groovy
  	import com.parasoft.api.*;
  	import com.parasoft.sa.ctp.*;

	public String GenerateRandomNumberAndName(Object o, ExtensionToolContext ctx) {
		String fmt = "<root><randomNumber length=\"%1$s\">%2$s</randomNumber><name>%3$s</name></root>"
		String result = RandomUtils.getRandomNumber(o.toString());
		String name = RandomUtils.getRandomName();
		return String.format(fmt,o.toString,result,name);
	}
  ```
	Attach an XML Data Bank to the output of the tool to preserve the values for reuse.

 2. Add an **XML Data Bank** output to this ExtensionTool
    * Extract individual elements from the **result** XML into the **XML Data Bank**.
    * They are now ready to be used in the response message.

### Default settings

There are default configuration files used for the first and last name generation that can be overwritten at Eclipse/Server launch time.
To override the files packaged with the tool by default add the files to the class path **before** the utilities-random-\<VERSION\>.jar

This is also possible to override the various settings on the fly as part of the script. Be aware that the overrides have a **global**
(meaning server wide impact).

Here is the configuration file **utilities-random.properties** that can be overridden on the classpath just make sure its to add it to the class path **before** the
utilities-random-\<VERSION\>.jar

```properties
	error-allowed-fmt=Expecting "%1$s" part that allows last character of prefix to be a %2$s, but there is none.\
	 You should add one, or remove requirements that cannot be fulfilled..\
	 the syllable used was : "%3$s", which means there should be a part available, that has \"%4$s\" requirement or\
	 no requirements for previous syllables at all.
	error-expected-fmt=Expecting "%1$s" part starting with %2$s, but there is none. You should add one, or remove requirement for one..
	error-file-load=%1$s: Error initializing %2$s from file '%3$s'. Error message: %4$s
	error-file-read=%1$s: %2$s '%3$s' could not be read : caught %4$s Error
	error-mallformed-len=%1$s: Malformed or Missing %2$s assuming {%3$s}. Passed string was '%4$s'
	error-min-syllables=compose(int syls) can't have less than 1 syllable
	error-missing-middle-fmt=You are trying to create a name with more than 2 parts, which requires middle parts, of\
	 which you have none in the file '%1$s'. You should add some. Every word, which doesn't have + or - for a prefix\
	 is counted as a middle part.
	error-file-name=File name cannot be null or empty
	error-missing-prefix-fmt=You have no prefixes, to start creating a name, in the file '%1$s'. Add some using "-"\
	 before the value, to identify it as a prefix for a name. (example: -asd)
	error-missing-suffix-fmt=You have no suffixes, to end creating a name, in the file '%1$s'. Add some, using "+"\
	 before the value , to identify it as a suffix for a name. (example: +asd)
	first-names-syllables-key=com.parasoft.sa.ctp.firstname.syllables
	first-names-default=/NameGeneratorSyllableFirst.txt
	last-names-syllables-key=com.parasoft.sa.ctp.lastname.syllables
	last-names-default=/NameGeneratorSyllableLast.txt
	length-default=5
	log-expectation-state=Expecting starting {} found syllable {} (prefix ends on {} and syllable {} hate this
	name-generator-syllable-count-key=com.parasoft.sa.ctp.syllable.count
	syllable-count-default=3
	syllables-allow-repeat-default=false
	syllables-allow-repeat-key=com.parasoft.sa.ctp.syllable.repeat
	syllables-file-key=com.parasoft.sa.ctp.syllable.file
	syllables-file-default=/NameGeneratorSyllable.txt
	consonants=bcdfghjklmnpqrstvwxyz
	vowels=aeiou
```
Here are the examples of the possible override parameters (for a complete list review the javadoc for the application)

```properties
	#Using the NameGenerator standalone:
	-Dcom.parasoft.sa.ctp.syllable.file=<name of the Syllable file on the classpath>
	#Using the NameGenerator as part of the RandomUtils
	-Dcom.parasoft.sa.ctp.firstname.syllables=<name of the Syllable file on the classpath>
	-Dcom.parasoft.sa.ctp.lastname.syllables=<name of the Syllable file on the classpath>
	-Dcom.parasoft.sa.ctp.syllable.count=<number of syllables per name part>
	 #defaults to 3 (prefix/middle/suffix) used for both first and last names when explicit values are not provided
	-Dcom.parasoft.sa.ctp.syllable.repeat=(false|true)
	 #this setting prevents duplication of middle syllable. defaults to false names look nicer this way

```
 Default File Name                  |Description
:----------------------------------	|:---
/NameGeneratorSyllable.txt			| Default file for standalone NameGenerator invocations
/NameGeneratorSyllableFirst.txt		| Default file for RandomUtils NameGenerator invocation used for first names
/NameGeneratorSyllableLast.txt		| Default file for RandomUtils NameGenerator invocation used for last names

* Note the leading slash in the names, it is important, otherwise the the file would be looked for in the folder structure corresponding to the class' package.

## Name Generator Details

###Short Description:

The NameGenerator class (and related classes) generates random names from syllables, and provides programmer a simple way to set a group of rules for generator
to avoid unpronounceable and bizarre names.

### Details

#### Syllable file:

 1. Will be searched for in the classpath
 2. Can be passed into the constructor
 3. Can be passed in the system property `com.parasoft.sa.ctp.syllable.file`
 4. Name will be defaulted to `NameGeneratorSyllable.txt` if not passed through the constructor and the system property is not set

##### Syllable file Requirements/Format:

 1. All syllables are separated by line break.
 2. Syllable should not contain or start with whitespace, as this character is ignored and only first part of the syllable is read.
 3. ***+*** and ***-*** characters are used to set rules, and using them in other way, may result in unpredictable results.
 4. Empty lines are ignored. Also lines that start with #

##### Syllable Classification:

 1. Name is usually composed from 3 different class of syllables, which include **prefix**, **middle** part and **suffix**.
 1. To declare syllable as a **prefix** in the file, insert **"-"** as a first character of the line.
 1. To declare syllable as a **suffix** in the file, insert **"+"** as a first character of the line.
 1. Everything else is read as a **middle** part.

##### Number Of Syllables:
 Names may have any positive number of syllables.
 1. In case of 1 syllable, name will be chosen from amongst the **prefixes**.
 2. In case of 2 syllables, name will be composed from **prefix** and **suffix**.
 3. In case of 3+ syllables, name will begin with **prefix**, is filled with **middle** parts and ended with **suffix**.

##### Assigning Rules:
 I included a way to set 4 kind of rules for every syllable.

 To add rules to the syllables, write them right after the syllable and **SEPARATE WITH WHITESPACE**. (example: "aad +v -c").
 The order of rules is not important.

##### Rules:

 1. __+v__ means that next syllable must definitely start with a vowel.
 2. __+c__ means that next syllable must definitely start with a consonant.
 3. __-v__ means that this syllable can only be added to another syllable, that ends with a vowel.
 4. __-c__ means that this syllable can only be added to another syllable, that ends with a consonant.

###### Example:
 "aad +v -c" means that "aad" can only be after consonant and next syllable must start with vowel.
 Beware of creating logical mistakes, like providing only syllables ending with consonants, but expecting only vowels, which will be detected and
 `NameGeneratorException` will be thrown.

##### To Start:

 Create a new NameGenerator object, provide the syllable file (through one of the methods describe above), and create names using `compose()` method.

##### The RandomUtils implementation
There are 2 `NameGenerator` objects wrapped in the utility (see above for mechanisms of overriding default Syllable sets).

1.	NameGenerator for first names
2.	NameGenerator for last names

Well that's it basically, also the default generation is using 3 syllables, but that can be overwritten by passing the comma delimited list of syllable counts


#### Modifications

##### Nick Rapoport:

 1. Replaced the 'vocal' with 'vowel'. As vowels what was obviously was meant here: __(a, e, i, o, u, y)__.
 2. Switched to English only (for now) ( umlauts are a PIA to deal with in XML ) .
 3. Switched to loading the files from classpath and provided a default syllable file name to use
 4. added an environment variable to override the default syllable file name
 5. Cleaned-up the javadoc comments for readability and added descriptions of new functionality
 6. Re-factored the code for better encapsulation and readability
 7. Added logging support for running in the Parasoft's Virtualize server context
 8. Added jUnit tests to validate the functionality
 9. Added a property to allow/deny repeated middle syllables

#### Authors:
 Joonas Vali, August 2009.
 (Modifications) Nick Rapoport, December 2016



## How to build

This utility will need to be built using ``maven`` [https://maven.apache.org/](https://maven.apache.org/ "https://maven.apache.org/")
just install java 8 and maven and run

```bash
   maven clean install
```

### Windows

1. Download and install(unzip) Maven I recommend a short path with no spaces in it like ``c:\tools\``
    so the final path to maven would be something like ``c:\tools\apache-maven-X.X.X`` where
    ``X.X.X`` is the maven version
2. Create a ``.m2`` folder under your user id
   on Windows 7+ this means ``C:\Users\<your login id>``
   on older versions ``C:\Documents and Settings\<your login id>``
   - NOTE: This cannot be done graphically in ``explorer`` only on the command line using
 ```cmd
     md .m2
 ```

3. Copy the ``settings.xml`` from maven installation folder like ``c:\tools\apache-maven-3.3.9\conf``

  + NOTE: I recommend against installing into "Program Files" we will be using command line and
        scripting. Spaces in folder/file names and scripts do not mix.


7.	Install java JDK (yes JDK not JRE). Please use the latest java (which at the time of this writing
    is version 8.
    - Download Java JDK from [oracle website](http://www.oracle.com/technetwork/java/javase/downloads/index.html "http://www.oracle.com/technetwork/java/javase/downloads/index.html")
    - run installation
    - Create a link to the ``C:\Program Files\Java`` from a path that does
      not have spaces in it. This will need to be run from an Administrator console
      ``Right-Click`` on the ``Cmd.exe`` link and click *Run As Administrator*
      For example:
 ```cmd
        mklink /J C:\tools\java "C:\Program Files\Java"
        mklink /J C:\tools\java32 "C:\Program Files (x86)\Java"
 ```
8.	We will need both Java and Maven in the `PATH` and `JAVA_HOME` and `M2` and `M2_HOME`
   (also `M3` and `M3_HOME`) set to properly run maven.
   - NOTE: (`M3`/`M3_HOME` may not be needed any more, however this was an issue at one point so I'm keeping them)
   - You can add the following to your system environment variables and `PATH` or create a
     `setenv_m3.cmd` in a convenient location (one that's been added to the system path, for me it's ``c:\work\bin``
     or simply put it into the current folder (where you checked out this project).

 ```cmd
        @set M2=c:\tools\apache-maven-3.3.9
        @set M3=%M2%
        @set M2_HOME=%M2%
        @set M3_HOME=%M2%
        @set JAVA_HOME=C:\tools\java\jdk1.8.0_92
        @set PATH=%M2%\bin;%JAVA_HOME%\bin;%PATH%
        @echo M2=%M2%
        @echo JAVA_HOME=%JAVA_HOME%
 ```


9.	Ok enough foreplay lets get down to cases. In the checked out folder run:
 ```cmd
           setenv_m3.cmd
           mvn clean install
 ```

### Unix or Linux systems

 - NOTE: Order of installs is important *java* then *maven*

1.	Install Oracle JDK. on debian or ubuntu based systems you can use this script:
 ```sh
    #!/bin/bash
    RELEASE=`lsb_release -cs`
    LINE_CNT=`grep  "http://ppa.launchpad.net/webupd8team" /etc/apt/sources.list | wc -l`
    if [[ ! ${LINE_CNT} -gt 1 ]]; then
            sudo echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu ${RELEASE} main" | sudo tee -a /etc/apt/sources.list
            sudo echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu ${RELEASE} main" | sudo tee -a /etc/apt/sources.list
            sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886
    fi
    sudo apt-get update
    sudo apt-get install oracle-java8-installer
 ```
 - if you are not that lucky, go to [oracle website](http://www.oracle.com/technetwork/java/javase/downloads/index.html "http://www.oracle.com/technetwork/java/javase/downloads/index.html")
 and download the package appropriate for your system (`rpm` or `tar.gz`)
  + if `rpm` (put the correct version below instead of ``8u111``  use the latest :
 ```sh
    ## Oracle java 1.8.0_111 ##
    sudo rpm -Uvh jdk-8u111-linux-x64.rpm
    ## java ##
    sudo alternatives --install /usr/bin/java java /usr/java/latest/jre/bin/java 200000
    ## javaws ##
    sudo alternatives --install /usr/bin/javaws javaws /usr/java/latest/jre/bin/javaws 200000

    ## Java Browser (Mozilla) Plugin 32-bit ##
    sudo alternatives --install /usr/lib/mozilla/plugins/libjavaplugin.so libjavaplugin.so /usr/java/latest/jre/lib/i386/libnpjp2.so 200000

    ## Java Browser (Mozilla) Plugin 64-bit ##
    sudo alternatives --install /usr/lib64/mozilla/plugins/libjavaplugin.so libjavaplugin.so.x86_64 /usr/java/latest/jre/lib/amd64/libnpjp2.so 200000

    ## Install javac only if you installed JDK (Java Development Kit) package ##
    sudo alternatives --install /usr/bin/javac javac /usr/java/latest/bin/javac 200000
    sudo alternatives --install /usr/bin/jar jar /usr/java/latest/bin/jar 200000
 ```
  + if `tar.gz` (place the file in your home folder (put the correct version instead of
     ``8u92``  use the latest :
 ```sh
    ## Oracle java 1.8.0_111 ##
    tar -xvf jdk-8u111-linux-x64.tar.gz
 ```
  + If this is your option the JAVA_HOME will be `~/jdk1.8.0_111` (or whatever the current version is)
     add the following lines to your `~/.bashrc` or `~/.profile` (replacing the ``<your user id>`` with your
     actual user id
 ```sh
    export JAVA_HOME=/home/<your user id>/jdk1.8.0_111
    export PATH=${JAVA_HOME}/bin:${PATH}
 ```
  + NOTE: The settings will not take effect until you logout/login again. Do that before doing step 2

2.	Download and install(unzip) Maven or again on debian, ubuntu or debian clones:
 ```sh
    sudo apt-get maven
 ```
  + If downloading and un-zipping manualy
 ```sh
    tar -xvf apache-maven-<version>-bin.tar.gz
 ```
  + If this is your option the `M2` will be ~/apache-maven-\<version\>
    add the following lines to your `~/.bashrc` or `~/.profile` after the java lines (replacing the
     ``<your user id>`` with your actual user id and `<version>` with the actuall maven version
 ```sh
    export M2=/home/<your user id>/apache-maven-<version>
    export M3=${M2}
    export M2_HOME=${M2}
    export M3_HOME=${M2}
    export PATH=${M2}/bin:${PATH}
 ```
   + NOTE: Ignore step 8 and any references to the ``setenv_m3.sh`` script. The settings will
           not take effect until you logout/login again.

8.	We will need both Java and Maven in the PATH and `JAVA_HOME` and `M2` and `M2_HOME`
   (also `M3` and `M3_HOME`) set to properly run maven.
 - NOTE: (`M3`/`M3_HOME` may not be needed, however this was an issue at one point so I'm keeping them)
 - You can add the following to your `.profile` (or `.bashrc`) however I do not like fixing specific versions
     in the initialization. place `setenv_m3.sh` in ``~/bin``
     or simply put it into the current folder (where you checked out this project).

 ```sh
    export M2=/usr/share/maven/
    export M3=${M2}
    export M2_HOME=${M2}
    export M3_HOME=${M2}
    export JAVA_HOME=/usr/lib/jvm/java-8-oracle/
    export PATH=${M2}/bin:${JAVA_HOME}/bin:${PATH}
    echo M2=${M2}
    echo JAVA_HOME=${JAVA_HOME}
 ```

9.	OK enough foreplay, lets get down to cases. In the checked out folder run:
 ```sh
           . ~/bin/setenv_m3.sh
           mvn clean install
 ```

### Build Results

10.	If everything goes well:
 - The `target` folder where the project was checked out will have **utilities-random-\<VERSION\>-bin-dist.*** and **utilities-random-\<VERSION\>.jar** files.
 Pick the `bin` archive that's appropriate to the target `OS` type. and unzip it.

## Notes on this Project
This project was created in late 2016 and all the references to maven and Java versions will
probably get old very quickly

## Author and Legal information

### Author

Nick Rapoport

### Copyright

Copyright&copy;  2016 Nick Rapoport -- All rights reserved (free
for duplication under the AGPLv3)

### License

AGPLv3

#### Original NameGenerator License

* The original license for this code was provided under the GNU general public license (GPL). My changes are under the AGPLv3.
   I'm not a lawyer but I will attempt to clarify my intent.

* With the respect to the `NameGenerator` code and related classes (by functionality) the original GPL will be in effect.
   The rest of the code is mine and is released under AGPLv3

## Based On

#### Projects
- [Apache Commons Lang] (https://commons.apache.org/proper/commons-lang/ "https://commons.apache.org/proper/commons-lang/")
- [A random name generator graciously provided by Joonas Vali in August 2009](http://forum.codecall.net/topic/49665-java-random-name-generator/ "http://forum.codecall.net/topic/49665-java-random-name-generator/")

#### Date
2016-12-25

