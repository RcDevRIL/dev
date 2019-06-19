@ECHO OFF
MODE con cols=130 lines=500
::wide screen
COLOR 03
TITLE AVD_LAUNCHER
:: This batch launch the AVD emulator chosen by user

SET exeDirPath=%USERPROFILE%\AppData\Local\Android\Sdk\emulator\
SET exeName=emulator.exe
SET cmdArg=-avd
SET avdDirPath=%USERPROFILE%\.android\avd\
SET avdName=NULL
ECHO    ============================
ECHO             VARIABLES
ECHO    ============================
ECHO:
TIMEOUT>NUL 1
ECHO exeDirPath = %exeDirPath%
ECHO exeName = %exeName%
ECHO cmdArg = %cmdArg%
ECHO avdDirPath = %avdDirPath%
ECHO avdName = %avdName%
TIMEOUT>NUL 1
ECHO:

< nul (set/p z=Please wait)
for /l %%A in (1,1,2) do (
    <nul (set/p z=.)
    TIMEOUT>NUL 1
)
ECHO:

:: Section 1: List the available Android Virtual Devices built on %USERPROFILE% directory.
ECHO:
ECHO    ============================
ECHO             LIST AVDs
ECHO    ============================
ECHO:
:: Create temp file
COPY NUL %USERPROFILE%\Desktop\avd.txt
ECHO avdDirPath = %avdDirPath%
:: If necessary switch disk before listing android folder
CD /D C:
:: Now move to the folder
CD %avdDirPath%
:: And redirect listing output to a tmp text file
DIR >%USERPROFILE%\Desktop\avd.txt

SET /A c=0
SET /A ans=0
FOR /F "tokens=1,2,3,4,5 skip=7" %%G IN (%USERPROFILE%\Desktop\avd.txt)  DO (
    ECHO %%J
    
    SET /P "ans=        Voulez-vous lancer cet AVD ? (ENTER 'y' to select one) "  
    SET /A "c+=1"
    SETLOCAL ENABLEDELAYEDEXPANSION
    IF !ans! == y (
        SET avdName=%%J
        GOTO :nextstep
    )  
)
:nextstep
DEL %USERPROFILE%\Desktop\avd.txt
ECHO:
ECHO:
ECHO c = %c%, ans = %ans%
:: Section 2: Launch command.
ECHO:
ECHO    ============================
ECHO       LAUNCHING EMULATOR.EXE
ECHO          WITH CHOSEN AVD
ECHO    ============================
ECHO:

IF !avdName! == NULL (
    ECHO OUPS! DID YOU REALLY CHOOSE ONE..?
) ELSE (
    ECHO You chose the AVD named "%avdName:~0,-4%"
    START %exeDirPath%%exeName% %cmdArg% %avdName:~0,-4%
    for /l %%A in (1,1,2) do (
        <nul (set/p z=.)
        >nul timeout 1
    )
    ECHO Done.
)



PAUSE>NUL
EXIT /B 0
::Code|Couleur
::0	  |Noir
::1   |Bleu foncé
::2   |Vert
::3   |Bleu-Gris
::4   |Marron
::5   |Pourpre
::6   |Kaki
::7   |Gris Clair
::8   |Gris
::9   |Bleu Clair
::A   |Vert clair
::B   |Cyan
::C   |Rouge
::D   |Rose
::E   |Jaune
::F   |Blanc

::https://www.robvanderwoude.com/files/tee_nt.txt


::SET strlength=0
::ECHO %avdName%> tmp.txt
::FOR %%? IN (tmp.txt) DO ( SET /A strlength=%%~z? - 2 )
::DEL tmp.txt
::ECHO %strlength%
::SET /A strlength-=4
::ECHO %strlength%
