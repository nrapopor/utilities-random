@rem set SPECPATH=C:\work\PJM\hamlog
@set VERSION=3.3.9
@IF /I NOT "%1" == "" @set VERSION=%1
@set JAVA_HOME=C:\tools\Java\64\jdk1.8.0_101
@echo JAVA_HOME=%JAVA_HOME%
@set MAVEN_OPTS=-ea
@rem set MAVEN_OPTS=-Djava.endorsed.dirs=C:/.m2/repository/javax/xml/parsers/jaxp-api/1.4.2;C:/.m2/repository/com/sun/xml/parsers/jaxp-ri/1.4.2 -ea
@echo MAVEN_OPTS=%MAVEN_OPTS%
@set M2=
@set M2_HOME=
@set M3=C:\Tools\apache-maven-%VERSION%
@set M3_HOME=%M3%
@set PATH=%JAVA_HOME%\bin;%M3%\bin;%PATH%

