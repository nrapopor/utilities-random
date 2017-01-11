@setlocal  ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
@set GROUP=%1
@set ARTIFACT=%2
@set VERSION=%3
@set FILE=%4
@set PACKAGING=jar
@set CLASSIFIER=
@if "%5" == "sources"       set CLASSIFIER=-Dclassifier=%5
@if "%5" == "javadoc"       set CLASSIFIER=-Dclassifier=%5
@if "%5" == "tests"         set CLASSIFIER=-Dclassifier=%5
@if "%5" == "tests-javadoc" set CLASSIFIER=-Dclassifier=%5
@if "%5" == "pom"           set PACKAGING=%5
@if "%6" == "pom"           set PACKAGING=%6

@call mvn deploy:deploy-file -Durl=%REPO_URL% ^
    -DrepositoryId=%REPO_ID% ^
    -DgroupId=%GROUP% ^
    -DartifactId=%ARTIFACT% ^
    -Dversion=%VERSION% ^
    -Dpackaging=%PACKAGING% ^
    -Dfile=%FILE% ^
    %CLASSIFIER% ^
    -DgeneratePom=true
@endlocal