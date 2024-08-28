#!/bin/bash
 
LIB_PATH=./libs
CLASSPATH=.:$LIB_PATH/*:$LIB_PATH/corbairplistener-1.0.1-SNAPSHOT.jar
##JAVA_MAIN_CLASS=com.ericsson.oss.nfe.alarmirp.CorbaIrpListener -Dconfig.properties=$LIB_PATH/dbconfig.properties
 
function stoplistener {
               echo Stopping CorbaIrpListener!
		for pid in `ps -ef | grep "CorbaIrpListener" | grep -v "grep" | awk '{ print $2 }'` ;
		    do
		    	echo -n ".";
		    	# Not calling kill -9: want to give the Shutdown hook a chance 
			kill $pid;
		    done;

		    for i in {1..10}
		    do
		    	if [[ -z `ps -ef | grep "CorbaIrpListener" | grep -v "grep"` ]]
			then
			    echo -n ".";
			    break;
			else
				echo -n ".";
			    sleep 1;
			fi;
			done;
		    if [[ -n `ps -ef | grep "CorbaIrpListener" | grep -v "grep"` ]]
		    then
		    	for pid in `ps -ef | egrep "CorbaIrpListener" | egrep -v "grep |postgres " | awk '{ print $2 }'` ;
			    do
				echo ""
				echo -n "Killing pid: $pid ";
				kill -9 $pid;
			    done;
			    sleep 1;
			    if [[ -n `ps -ef | grep "CorbaIrpListener" | grep -v "grep"` ]]
			    then
			    	echo "Failed to kill all the CorbaIrpListener processes."
					exit 0;
		    	fi
		    fi;
		    echo ""
}
 
 
case "$1" in
    stop)
	stoplistener
    ;;
    start)
	echo starting CorbaIrpListener ....
	LIB_PATH=./libs
	CLASSPATH=.:$LIB_PATH/*:$LIB_PATH/corbairplistener-1.0.1-SNAPSHOT.jar
	JAVA_MAIN_CLASS='com.ericsson.oss.nfe.alarmirp.CorbaIrpListener'
	echo "java -cp ${CLASSPATH} $JAVA_MAIN_CLASS"
	exec java -cp ${CLASSPATH}  -Dappconfig.properties=../nfv_appconfig/appconfig.properties $JAVA_MAIN_CLASS  >/dev/null &
	
    ;;
   status)
	   if [[ -n `ps -ef | grep "CorbaIrpListener" | grep -v "grep"` ]]
           then
	   echo 'CorbaIrpListener is running';
           else
	   echo 'CorbaIrpListener is stopped';

	   fi
    ;;

    *)
        	echo "Usage: $0 {start|stop|status}"
        	exit 1
 
       ;;
esac
exit 0