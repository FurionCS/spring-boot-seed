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
- 提供 jwt token 认证 可配置过期时间，不过滤接口，

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
	  - jwt
	       - AuthTokenDetails
	       - JsonWebTokenUtility(jwt工具类)
  -  config
	  -  CorsConfig (跨域处理)
	  -  DruidConfig
- test
  - resource
     - generator.template(模板文件)
   
### 问题
#### 1：devtools 和通用mapper
在使用 DevTools 时，通用Mapper经常会出现 class x.x.A cannot be cast to x.x.A。

同一个类如果使用了不同的类加载器，就会产生这样的错误，所以解决方案就是让通用Mapper和实体类使用相同的类加载器即可。

DevTools 默认会对 IDE 中引入的所有项目使用 restart 类加载器，对于引入的 jar 包使用 base 类加载器，因此只要保证通用Mapper的jar包使用 restart 类加载器即可。

在 src/main/resources 中创建 META-INF 目录，在此目录下添加 spring-devtools.properties 配置，内容如下：

restart.include.mapper=/mapper-[\\w-\\.]+jar
restart.include.pagehelper=/pagehelper-[\\w-\\.]+jar
使用这个配置后，就会使用 restart 类加载加载 include 进去的 jar 包


#### 2： devtools 和swagger 
出现问题，和低版本swagger会出错，升级swagger版本就好