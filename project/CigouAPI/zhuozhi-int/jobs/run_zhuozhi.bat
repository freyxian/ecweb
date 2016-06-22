
set JAVA_HOME=D:\jdk1.7.0_79

set JAVA=%JAVA_HOME%/bin/java

SET PATH=%JAVA_HOME%/bin;%PATH%
SET LIB=G:\jobs\lib

rem set CLASSPATH=%CLASSPATH%:%LIB%/apache-commons/

set APACHE_LIB=%LIB%\apache-commons\
set JAKARTA_LIB=%LIB%\jakarta-commons\
set JERSEY_LIB=%LIB%\jersey-1.9\
set JSON_LIB=%LIB%\json\
set APP_LIB=%LIB%\app\
set ALL_LIB=\jobs\all_lib

rem %JAVA% -cp "%LIB%\*" "%APACHE_LIB%\*" "%JAKARTA_LIB%\*" "%JERSEY_LIB%\*" "%JSON_LIB%\*" "%APP_LIB%\*" com.echolab.common.batch.MainBatch cfg/zhuozhi.xml

rem %JAVA% -cp "%LIB%\*" "%APACHE_LIB%\*" "%JAKARTA_LIB%\*" "%JERSEY_LIB%\*" "%JSON_LIB%\*" "%APP_LIB%\*" com.echolab.common.batch.MainBatch cfg/zhuozhi.xml ZZOrderBatch

rem %JAVA% -cp "%ALL_LIB%\*"  -Dlog4j.configuration=log4j.xml com.echolab.common.batch.MainBatch cfg/zhuozhi.xml ZZOrderBatch

%JAVA% -cp "%ALL_LIB%\*"  com.echolab.common.batch.MainBatch cfg/zhuozhi.xml ZZOrderBatch


