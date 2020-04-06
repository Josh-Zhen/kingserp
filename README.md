# ERP购销項目
 * Author: Joshua
 * Email：by.Moonlit@gmail.com
 
## 項目描述：
- 版本信息：

|框架|版本|
|:----    |:---|
|Spring-Cloud |Hoxton.SR1  |
|Spring-Boot |2.2.4.RELEASE  |
|Eureka |2.2.1.RELEASE  |
|Gateway |2.2.1.RELEASE  |
|Hystrix |2.2.1.RELEASE  |
|Turbine |2.2.1.RELEASE  |
- 數據庫：Mysql、Redis

#### 一、模块包括：
- 后台管理者模块（成員管理、角色管理、權限目錄管理、日志查詢）
- 客戶與商品管理
- 銷售管理
- 采購管理
- 資金統計

#### 内網項目Swagger測試地址
http://localhost:8805/swagger-ui.html
#### 接口説明文檔 
https://www.showdoc.cc/Moonlit?page_id=4190089381350025  密碼:qwe123qwe

## 備注：
#### 關於權限分配
- super_admin（超级管理员）：擁有所有權
- admin（普通管理員）：僅擁有操作成員、角色、權限分配、系統操作日志查詢權限
- user（操作用戶）：僅擁有商品、銷售、客戶、采購權限、資金統計等權限