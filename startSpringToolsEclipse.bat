set WORKSPACE_HOME="C:\AjayKapoorDesktop\ERICSSON\ECLIPSE-SDK\SPRINGTOOLS"
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_191"
set JAVA_BIN="C:\Program Files\Java\jdk1.8.0_191\bin"
set PYTHONPATH="C:\Python27"
set ECLIPSE_HOME="C:\Users\ajayk\eclipse\java-2018-09"

REM Eclipse runtime options
REM http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fmisc%2Fruntime-options.html

start eclipse.exe -clean -data %WORKSPACE_HOME% -vm %JAVA_HOME%\bin\javaw
REM start eclipse.exe -clean -showLocation -data %WORKSPACE_HOME% -vm %JAVA_HOME%\bin\javaw -vmargs -Xms1024m -Xmx2048m -XX:MaxPermSize=512m -XX:+UseParallelGC