@echo off
set unidade=j:
set usuario=%username%
set arquivo=marcos
C:
cd \Users\"%username%"\Desktop
if exist c:\users\"%username%"\desktop\BAT_TOOLS.lnk (del BAT_TOOLS.lnk)
j:
if not exist C:\Users\"%username%"\desktop\bat_tools.lnk (xcopy \\mvrec_suporte\BAT_MARCOS\SERVER\bat_tools.lnk C:\Users\"%username%"\desktop)
if not exist j: (net use j: \\Mvrec_suporte\bat_marcos)
cls

IF EXIST "C:\Users\%username%\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup\BAT_TOOLS.lnk" (Echo "<"%usuario%" | %computername% | BAT INSTALADO NO INICIALIZAR | %date% %time% >">>%unidade%\Data\LOG\LOG_USUARIOS_FUNCAO.%arquivo%
	del "C:\Users\%username%\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup\BAT_TOOLS.lnk"
	Echo " <"%usuario%" | %computername% | COMANDO PARA APAGAR DO INICIALIZAR | %date% %time% >">>%unidade%\Data\LOG\LOG_USUARIOS_FUNCAO.%arquivo%)

EXIT