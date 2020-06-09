# ddbs

## 后端

### 数据库

我们首先设计了若干个数据表，且数据表均满足3NF。数据库的设计如下：

* 用户表（users)

|id| username| password | email |
|---|----|----| ----|
|用户id，主键| 用户名，非空| 密码，非空| 用户邮箱，非空|

* 管理员表（admins)

在本次设计中，Admin表和User表具有相同的结构

* 商品表（items）

| id | userId | name | introduction | pictureURL | kind | originalPrice | currentPrice | amount | uploadTime |
| --- | ---| ---| ---| ---| ---| ---| ---| ---| ---| 
| 商品id，主键| 用户id，外键 | 商品名，非空| 介绍，非空 | 图片链接地址 | 商品种类 | 原始价格 | 当前价格，非空 | 数量，非空 | 商品上传时间，非空 | 

*  交易表

交易表中的每个属性值都不能为空

| id | goodId | userId | totalPrice | goodsNumber | address | phoneNumber | orderTime | orderState| 
| --- | ---| ---| ---| ---| ---| ---| ---| ---|
| 交易id，主键| 商品id，外键| 用户id，外键| 订单总价| 订货数量 | 收货地址 | 收货电话 | 生成订单时间 | 订单状态 |

* 商品评价表

待定


#### 数据库分片

#### 分片策略

常见的分片策略包括垂直分片和水平分片，垂直分片主要指将不同的数据表放在不同的数据库中，垂直分片的情况
主要适用于数据表之间逻辑较为独立的情况。水平分片主要指将逻辑上统一的不同数据表划分存储在不同数据库中。
水平分片根据划分策略主要可以分为：

1. 连续分片

连续分片指数据表按照元组中某列元素的取值范围来划分。例如，可以将出生日期大于某个日期的人划分在一个数据库中，
将出生日期小于某个日期的人划分在另外一个数据库中。连续分片的优点在于能够将数据按照取值范围划分在不同的区间中，
从而在查询时效率更高，其存在的问题为，某些库中的数据可能存在数据过热的情况。

2. 随机分片

根据数据库中某些属性的哈希值来进行分片，其优缺点与连续分片正好相反。

#### 优化策略

1. 绑定表

在水平分片过程中，如果存在两个数据表其使用统一策略对某一unique属性进行分片，则这两个表可以互称为binding
table，对于binding table来说，其在数据表连接时只需要进行两次笛卡尔积操作（假设两个表的分片结果分别为
a0,a1,b0,b1，则进行笛卡尔积的操作为a0xb0，a1xb0），而对于非binding table的数据表来说，需要进行四次
笛卡尔积操作(a0xb0, a0xb1, a1xb0, a1xb1）。

2. 广播表

指所有的分片数据源中都存在的表，表结构和表中的数据在每个数据库中均完全一致。适用于数据量不大且需要与海量
数据的表进行关联查询的场景，例如：字典表。


注意到对一般系统来说，商品种类数（因为对同一种商品来说，卖家不同，则其在数据库中的记录也不相同）
会远远多于用户数，因此当系统的规模增大时，商品表更容易成为系统的瓶颈。

## 前端接口

本项目前后端通信的数据格式统一为JSON格式，前端可以通过RESTful格式的API来访问后端资源。
因为后端有些信息需要授权用户才能访问，因此当后端返回登陆要求时，前端需要让用户验证身份并将验证信息提交至后端。
后端之后会返回token，前端需要在以后的请求中将token附在http的请求头中，以便后端验证用户身份。

后端使用了分布式主键的生成算法（snowflake)，因此前端在post数据时并不需要附带id.



### 例子

单表查询：

```
获取所有用户信息
GET http://127.0.0.1:8080/users

获取特定id用户的信息
GET http://127.0.0.1:8080/users/{id}

往数据库中添加一条用户记录
POST http://127.0.0.1:8080/users，其中http的request body为待提交的JSON格式数据

更新一条数据库中的记录

PUT http://127.0.0.1:8080/users/{id}，其中http的request body为待提交的JSON格式数据，该数据既可以为
用户表的全属性（即更新用户所有信息），也可以为用户表的部分属性（即更新用户的部分信息，例如用户名）
```

联合查询：

```
获取特定id用户所发布的所有商品
GET http://127.0.0.1:8080/goods/users/{id}

待补充
```

## Todo:

- [x] 从前端提交的token中提取出用户ID
- [x] 将admin的权限和普通user的权限分离
- [x] 用户的登录方式更改为使用注册的邮箱登录
- [x] 在数据的邮箱列上建索引，加快查询速度
- [x] 使用hash值存储密码
- [x] 实现表的联合查询
- [x] 在数据表中加入时间（time）
- [x] 增加新的数据表
