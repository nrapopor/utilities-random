# Required for maven 2
export  M3=/usr/local/lib/apache-maven-3.3.9
export  M3_HOME=$M3
export  JAVA_HOME=/usr/local/java/jdk1.8.0_111
export  PATH=$M2/bin:$JAVA_HOME/bin:$PATH
export  MVN_OPTS=-ea
# required for the deploy-file goal
export  REPO_URL=http://
export  REPO_ID=
export  ARGS=$*
# echo $ARGS