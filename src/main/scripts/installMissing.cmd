@setlocal
@call setenv.cmd
::##################################################################################
::@call mvnInstallFile <groupId> <artifactId> <version> file [classifier] [packaging]
::##################################################################################
@call mvnInstallFile com.parasoft  parasoft-api  9.10.0 ..\lib\com.parasoft.api.jar
@endlocal