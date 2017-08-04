## spring-boot-seed
> 项目是为了快速开发项目省去每次新项目开始是都要做一系列的前期准备。该项目是建立在[\spring-boot-api-project-seed\](https://github.com/lihengming/spring-boot-api-project-seed) 项目之上的，整合了自己的一些东西。

### 简介
该项目是基于spring boot 的一个种子项目，可以快速构建项目，适用于小型项目的开发，整合了比较常用的一些功能。

### 快速开始

- 修改各个application.properties中数据库连接信息
![Alt text](http://csbucket.oss-cn-shanghai.aliyuncs.com/ReadMe/seed_img_3.png)

- 修改模板生成工具里面数据库连接信息
![Alt text](http://csbucket.oss-cn-shanghai.aliyuncs.com/ReadMe/seed_img_2.png)

- 生成对应表的 mapper,dao,service,web
![Alt text](http://csbucket.oss-cn-shanghai.aliyuncs.com/ReadMe/seed_img_1.png)

- 启动项目开始

### 提供

- 提供了common 模块，包含了统一异常处理，统一请求，统一响应，工具类
- 采用了log4j+mongodb做接口日志存储
- 整合了druid 数据库连接池和监控
- 整合了 PageHelper分页插件
- 提供了请求参数验证切面
。。。

### 项目配置
关于快速生成的名字配置和一些模板配置

- com.company.project (这个名字改成自己的)
  -  appender
  -  aspect
  -  basic
	  - config
		  - MybatisConfigurer
		  - ProjectConstant (配置生成dao,service等名字)
	  - exception
		  - ServiceException
	  - mapper
		  - Mapper
	  - service
		  -   Service (通用service)
		  -  impl
			  - AbstractService  
  -  config
	  -  CorsConfig (跨域处理)
	  -  DruidConfig
- test
  - resource
     - generator.template(模板文件)
   
