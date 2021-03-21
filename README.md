# Bitter
使用netty做了一个通讯的项目

关于 XML 头声明和standalone 的解释   
  
头申明这种格式:  
<?xml version="1.0" encoding="utf-8" standalone="yes" ?>  
  
standalone表示该xml是不是独立的，如果是yes，则表示这个XML文档时独立的，不能引用外部的DTD规范文件；如果是no，则该XML文档不是独立的，表示可以用外部的DTD规范文档。
