@rem set SPECPATH=C:\work\PJM\hamlog
@set VERSION=2.2.1
@IF /I NOT "%1" == "" @set VERSION=%1
@set JAVA_HOME=C:\tools\Java\jdk1.6.0_25
@echo JAVA_HOME=%JAVA_HOME%
@set MAVEN_OPTS=-ea
@rem set MAVEN_OPTS=-Djava.endorsed.dirs=C:/.m2/repository/javax/xml/parsers/jaxp-api/1.4.2;C:/.m2/repository/com/sun/xml/parsers/jaxp-ri/1.4.2 -ea
@echo MAVEN_OPTS=%MAVEN_OPTS%
@set M2=C:\Tools\apache-maven-%VERSION%
@set M2_HOME=%M2%
@set M3=
@set M3_HOME=
@set PATH=%JAVA_HOME%\bin;%M2%\bin;%PATH%
