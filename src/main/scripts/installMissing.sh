#!/bin/bash
. ./setenv.sh
####################################################################################
# ./mvnInstallFile.sh <groupid> <artifactid> <version> file [clasifier] [packaging]
####################################################################################
./mvnInstallFile.sh com.parasoft  parasoft-api  9.10.0 ..\lib\com.parasoft.api.jar