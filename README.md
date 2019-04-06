# SchoolService(校园维修)
	##### 	小程序针主要服务于对校园设施的维修工作，在准备条件不完善的校园中，设施的维修工作通常要经过很多的程序才可以维修，本小程序主旨为了方便校园设施的维修申报，减少中间程序，以更快更准确的方式，让用户和维修员取得交互，完成校园设施的维修；

#### 1.功能分析图

​	![功能图](D:\github文档\SchoolService\support_image\schoolService.png)

#### 2.开发工具

- Eclipse2017
- Mysql5.7
- Tomcat7.x
- Maven
- Redis-cluster(登录验证，第三方登录态缓存等)
- 云服务器(阿里云)

#### 3.数据库设计

#####用户信息表


![用户信息表](D:\github文档\SchoolService\support_image\user.png)

##### 管理员信息表

![](D:\github文档\SchoolService\support_image\manager.png))


##### 维护信息表	 

#####        ![维护信息表](D:\github文档\SchoolService\support_image\maintable.png) 	

 ##### 分类信息表![分类信息表](D:\github文档\SchoolService\support_image\incontentclassifly.png)		

##### 内容表

​	![内容表](D:\github文档\SchoolService\support_image\content.png)

##### 建议类型表

![分类类型表](D:\github文档\SchoolService\support_image\sugsort.png)

##### 建议内容表

##### 	![建议内容表](D:\github文档\SchoolService\support_image\sugtable.png)

#### 4.技术框架与系统架构(SSM  + SOA)

![](D:\github文档\SchoolService\support_image\dubbo.png)	

分布式架构：多个子系统相互协作才能完成业务流程。系统之间需要进行通信。

集群：同一个工程部署到多台服务器上。

分布式架构：

把系统按照模块拆分成多个子系统。

优点：

1、把模块拆分，使用接口通信，降低模块之间的耦合度。

2、把项目拆分成若干个子项目，不同的团队负责不同的子项目。

3、增加功能时只需要再增加一个子项目，调用其他系统的接口就可以。

4、可以灵活的进行分布式部署。

##### 至于为什莫一个节点可以完成，却用分布式，哈哈，只是为了练习为下一个项目做个铺垫

#### 5.项目中间件

- redis
- zookeeper

#### 6.项目进展

- 后台已经发布<http://112.74.166.230:8888/api/content/getImage>一个获取首页轮播图的接口

- 小程序名为“校园修一修”，正在待完善中

  ​

