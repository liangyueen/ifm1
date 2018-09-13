set SRC=%~dp0yyconfig

:: NChome中resource文件夹的位置 
set DEST=D:\NCC\mwq0828v1\hotwebs\nccloud\WEB-INF\extend\yyconfig

echo 当前bat文件路径：%~dp0
 
XCOPY %SRC% %DEST% /E /I /Y