@ECHO OFF
MODE con cols=150 lines=500
::wide screen
COLOR 03
TITLE POM.XML DEPENDENCY EXTRACTOR
:: This batch extract direct dependencies in a pom.xml file chosen by user

SETLOCAL ENABLEDELAYEDEXPANSION
SET pomDir=NULL
SET javaDir=C:\devs\devPackage\git-scm-resource\

ECHO    ============================
ECHO             VARIABLES
ECHO    ============================
ECHO:
TIMEOUT>NUL 1
ECHO pomDir = %pomDir%
ECHO javaDir = %javaDir%
TIMEOUT>NUL 1

ECHO:
< nul (set/p z=Please wait)
for /l %%A in (1,1,3) do (
    <nul (set/p z=.)
    TIMEOUT>NUL 1
)
ECHO:

:: Section 1: List the available Maven Projects on %javaDir% directory.
ECHO:
ECHO    ============================
ECHO         LIST MAVEN PROJECTS
ECHO       IN SPECIFIED DIRECTORY
ECHO    ============================
ECHO:
:: Create temp file
COPY NUL %USERPROFILE%\Desktop\mavenProjects.txt
ECHO:
:: If necessary switch disk before listing folder
:: CD /D C:
:: Now move to the folder
CD %javaDir%
:: And redirect listing output to a tmp text file
DIR >%USERPROFILE%\Desktop\mavenProjects.txt

SET /A c=0
SET /A ans=0
:: Adjust the 'skip' value so it doesn't list ".something" directories...
:: We should test lines value of mavenProjects.txt and skip everything except maven projects
FOR /F "tokens=1,2,3,4,5 skip=10" %%G IN (%USERPROFILE%\Desktop\mavenProjects.txt)  DO ( 
    
    :: %%J = octets means we are at the end of "dir" command output
    IF NOT %%J == octets (
        ECHO %%J
        SET /P "ans=        Voulez-vous analyser ce projet ? (ENTER 'y' to select one) "  
        SET /A "c+=1"
        IF !ans! == y (
            SET pomDir=%%J
            GOTO :nextstep
        ) 
    ) ELSE (
        GOTO :nextstep
    )
    
)
:nextstep
ECHO:
ECHO:
ECHO c = %c%, ans = %ans%

:: Section 2: Launch command.
ECHO:
ECHO    ============================
ECHO       LAUNCHING EXTRACTION
ECHO         ON CHOSEN PROJECT
ECHO    ============================
ECHO:

IF !pomDir! == NULL (
    ECHO OUPS! DID YOU REALLY CHOOSE ONE..?
    GOTO :stop
) ELSE (
    COPY NUL %USERPROFILE%\Desktop\pomDD.txt
    ECHO You chose the project named "%pomDir%"
    CD %pomDir%
	ECHO:
	< nul (set/p z=Please wait)
	for /l %%A in (1,1,3) do (
		<nul (set/p z=.)
		TIMEOUT>NUL 1
	)
	ECHO:
    CMD /C mvn dependency:list >%USERPROFILE%\Desktop\pomDD.txt
    for /l %%A in (1,1,2) do (
        <nul (set/p z=.)
        >nul timeout 1
    )
    ECHO Done.
)
COPY NUL %USERPROFILE%\Desktop\pomDD2.txt
:: Get the info needed
FOR /F "tokens=1,2,3" %%A IN (%USERPROFILE%\Desktop\pomDD.txt)  DO (  
    ECHO %%B>>%USERPROFILE%\Desktop\pomDD2.txt
)
COPY NUL %USERPROFILE%\Desktop\pomDD3.csv
ECHO groupID;artifactID;version;scope>>%USERPROFILE%\Desktop\pomDD-%pomDir%.csv
:: Parse the info
FOR /F "tokens=* delims=:" %%A IN (%USERPROFILE%\Desktop\pomDD2.txt)  DO (
    ECHO %%A parsed...
    FOR /F "tokens=1,2,3,4,5 delims=:" %%B IN ("%%A") DO (
        SET cd=%%C
        IF !cd! == %javaDir%%pomDir% (
            ECHO WRONG LINE
        ) ELSE (
            ECHO %%B;%%C;%%E;%%F>>%USERPROFILE%\Desktop\pomDD-%pomDir%.csv
        )
    )
    ECHO DONE
)

:stop
::DEL %USERPROFILE%\Desktop\mavenProjects.txt
::DEL %USERPROFILE%\Desktop\pomDD.txt
::DEL %USERPROFILE%\Desktop\pomDD2.txt
ENDLOCAL
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