@echo off
echo Cleaning up old data...
rmdir /s /q C:\MariaDB\data
del C:\MariaDB\bin\ib*

echo Re-initializing data...
cd /d C:\MariaDB\bin
mysql_install_db.exe --datadir=C:\MariaDB\data

echo Starting Server...
mysqld.exe --datadir=C:\MariaDB\data --console
pause
