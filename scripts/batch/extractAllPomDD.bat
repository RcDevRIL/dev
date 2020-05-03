@ECHO OFF
MODE con cols=150 lines=500
::wide screen
COLOR 03
TITLE POM.XML DEPENDENCY EXTRACTOR
:: This batch extract direct dependencies in all maven projects found on directory javaDir

SETLOCAL ENABLEDELAYEDEXPANSION
SET pomDir=NULL
:: Set javaDir manually so it refers to your local Java directory
SET javaDir=C:\devs\devPackage\git-scm-resource

ECHO    ============================
ECHO             VARIABLES
ECHO    ============================
ECHO:
TIMEOUT>NUL 1
ECHO pomDir = %pomDir%
ECHO javaDir = %javaDir%
ECHO:
ECHO Creating temp files..
ECHO:
COPY NUL %USERPROFILE%\Desktop\pomDDglobal.csv
ECHO projectName;groupID;artifactID;version;scope>%USERPROFILE%\Desktop\pomDDglobal.csv
ECHO:
COPY NUL %USERPROFILE%\Desktop\mavenProjects.txt
ECHO:
COPY NUL %USERPROFILE%\Desktop\pomDD.txt
ECHO:
COPY NUL %USERPROFILE%\Desktop\pomDD2.txt
ECHO:
ECHO Done.
:: Section 1: List the available Maven Projects on %javaDir% directory.
ECHO:
ECHO:
ECHO    ============================
ECHO         LIST MAVEN PROJECTS
ECHO       IN SPECIFIED DIRECTORY
ECHO    ============================
ECHO:
ECHO:
CD %javaDir%
:: And redirect listing output to a tmp text file
DIR >%USERPROFILE%\Desktop\mavenProjects.txt

SET /A c=0
:: Adjust the 'skip' value so it doesn't list ".something" directories...
:: We should test lines value of mavenProjects.txt and skip everything except maven projects
FOR /F "tokens=1,2,3,4,5 skip=10" %%G IN (%USERPROFILE%\Desktop\mavenProjects.txt)  DO ( 
    
    :: %%J = octets means we are at the end of "dir" command output
    IF NOT %%J == octets (
        ::ECHO %%J
        SET pomDir=%%J
		::ECHO pomDir = !pomDir!
        SET /A "c+=1"
		CALL :LMC
    ) ELSE (
		ECHO:
		ECHO:
		ECHO    ============================
		ECHO       	EXTRACTION COMPLETED 
		ECHO            SUCCESSFULLY
		ECHO    ============================
		ECHO:
		ECHO:
		ECHO Exiting program...
		DEL %USERPROFILE%\Desktop\mavenProjects.txt
		DEL %USERPROFILE%\Desktop\pomDD.txt
		DEL %USERPROFILE%\Desktop\pomDD2.txt
		ENDLOCAL
		PAUSE>NUL
		EXIT /B 0
    )
    
)

:LMC
:: Section 2: Launch Maven Command.
ECHO:
ECHO:
ECHO count = %c%, pomDir = !pomDir!
ECHO:
ECHO    ============================
ECHO       LAUNCHING EXTRACTION
ECHO         ON CHOSEN PROJECT
ECHO    ============================
ECHO:
ECHO:

IF !pomDir! == NULL (
	ECHO Exiting program...
	DEL %USERPROFILE%\Desktop\mavenProjects.txt
	DEL %USERPROFILE%\Desktop\pomDD.txt
	DEL %USERPROFILE%\Desktop\pomDD2.txt
	ENDLOCAL
	PAUSE>NUL
	EXIT /B 0
) ELSE (
    ECHO You chose the project named "%pomDir%"
    CD %pomDir%
    CMD /C mvn dependency:list -DexcludeTransitive=true >%USERPROFILE%\Desktop\pomDD.txt
)
ECHO:
COPY NUL %USERPROFILE%\Desktop\pomDD2.txt
ECHO:
:: Get the info needed
FOR /F "tokens=1,2,3" %%A IN (%USERPROFILE%\Desktop\pomDD.txt)  DO (  
    ECHO %%B>>%USERPROFILE%\Desktop\pomDD2.txt
)
:: Parse the info
FOR /F "tokens=* delims=:" %%A IN (%USERPROFILE%\Desktop\pomDD2.txt)  DO (
    ECHO %%A parsed...
    FOR /F "tokens=1,2,3,4,5 delims=:" %%B IN ("%%A") DO (
        SET cd=%%C
		ECHO cd = !cd!
        IF !cd! == %javaDir%\%pomDir% (
            ECHO WRONG LINE
        ) ELSE (
            ECHO !pomDir!;%%B;%%C;%%E;%%F>>%USERPROFILE%\Desktop\pomDDglobal.csv
        )
    )
    ECHO DONE
)
CD %javaDir%
ECHO:
ECHO:
ECHO    ============================
ECHO       	  EXTRACTION DONE
ECHO    ============================
ECHO:
ECHO: