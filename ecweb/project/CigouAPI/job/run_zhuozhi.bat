
rem set JAVA_HOME="C:\Program Files\Java\jdk1.7.0_79\"

rem set JAVA=%JAVA_HOME%/bin/java

rem SET PATH=%JAVA_HOME%/bin;%PATH%
SET LIB=G:\jobs\lib

rem set CLASSPATH=%CLASSPATH%:%LIB%/apache-commons/

set APACHE_LIB=%LIB%\apache-commons\
set JAKARTA_LIB=%LIB%\jakarta-commons\
set JERSEY_LIB=%LIB%\jersey-1.9\
set JSON_LIB=%LIB%\json\
set APP_LIB=%LIB%\app\
set ALL_LIB=.\all_lib


rem %JAVA% -cp "%ALL_LIB%\*"  com.echolab.common.batch.MainBatch cfg/zhuozhi.xml ZZOrderBatch

java -cp "%ALL_LIB%\*"  com.echolab.common.batch.MainBatch cfg/zhuozhi.xml ZZOrderBatch




