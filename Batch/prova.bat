mysql -h 127.0.0.1 --user=root --password=root -D tasckhdb < "C:\Users\Marcello\Desktop\Marcello Eclipse EE\TasckhProgect\Batch"
ping 127.0.0.1 -n 2 > nul
@echo off
for /f "delims=" %%a in (C:\Users\Marcello\Desktop\Marcello Eclipse EE\TasckhProgect\Batch\provolinos.txt) do (
	rmdir /s /q "C:\Users\Marcello\Desktop\Marcello Eclipse EE\TasckhProgect\Batch\%%a\"
	mkdir "%%a"
	mysql -h 127.0.0.1 --user=root --password=root -D tasckhdb -t --execute="SELECT * FROM user WHERE user.country=(SELECT idcountry FROM country WHERE countryname='%%a')" > "C:\Users\Marcello\Desktop\Marcello Eclipse EE\TasckhProgect\Batch\%%a\%%a.txt"
)

del /Q C:\Users\Marcello\Desktop\Marcello Eclipse EE\TasckhProgect\Batch\provolinos.txt
pause