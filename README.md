ToDoList
====

## Description
ToDoListの管理を行うwebアプリケーション  
#### backend
* Web FrameworkとしてSpring Bootを使用
* データベースとしてMySQLを使用
#### frontend
* デザインテンプレートとしてBoot Strapを使用
* DOM管理やajaxにjQueryを使用
## Requirement
* Java(1.8.0_91)
* MySQL(8.0.16)

## Usage
#### MySQLのセットアップ
* mysql> CREATE USER test IDENTIFIED BY 'testtest';
* mysql> CREATE DATABASE todo_test;
* mysql> GRANT ALL  ON todo_test.* TO test;

todo_test.dumpを使用する場合(password: testtest)  
* mysql -u test -p -D todo_test < todo_test.dump  

#### ToDoListの起動
* cd demo
* ./gradlew bootRun
## Install
* git clone git@github.com:m-book/todoList.git












