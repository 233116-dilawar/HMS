@echo off
echo =================================
echo  STARTING DATABASE SERVER...
echo =================================
echo LEAVE THIS WINDOW OPEN!
cd /d C:\MariaDB\bin
mysqld.exe --datadir=C:\MariaDB\data --console
pause
