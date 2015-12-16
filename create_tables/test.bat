@echo off
set ORACLE_SID=XE
cd %~dp0
sqlplus test/test@xe @create_tables
/