# 1. 电商管理后台 API 接口文档

## 1.1. API V1 接口说明

- 接口基准地址：`http://127.0.0.1:8888/api/private/v1/`
- 服务端已开启 CORS 跨域支持
- API V1 认证统一使用 Token 认证
- 需要授权的 API ，必须在请求头中使用 `Authorization` 字段提供 `token` 令牌
- 使用 HTTP Status Code 标识状态
- 数据返回格式统一使用 JSON

### 1.1.1. 支持的请求方法

- GET（SELECT）：从服务器取出资源（一项或多项）。
- POST（CREATE）：在服务器新建一个资源。
- PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
- PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
- DELETE（DELETE）：从服务器删除资源。
- HEAD：获取资源的元数据。
- OPTIONS：获取信息，关于资源的哪些属性是客户端可以改变的。

### 1.1.2. 通用返回状态说明

| *状态码* | *含义*                | *说明*                                              |
| -------- | --------------------- | --------------------------------------------------- |
| 200      | OK                    | 请求成功                                            |
| 201      | CREATED               | 创建成功                                            |
| 204      | DELETED               | 删除成功                                            |
| 400      | BAD REQUEST           | 请求的地址不存在或者包含不支持的参数                |
| 401      | UNAUTHORIZED          | 未授权                                              |
| 403      | FORBIDDEN             | 被禁止访问                                          |
| 404      | NOT FOUND             | 请求的资源不存在                                    |
| 422      | Unprocesable entity   | [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误 |
| 500      | INTERNAL SERVER ERROR | 内部错误                                            |
|          |                       |                                                     |

------

## 1.2. 登录

### 1.2.1. 登录验证接口

- 请求路径：/login
- 请求方法：post
- 请求参数

| 参数名   | 参数说明 | 备注     |
| -------- | -------- | -------- |
| username | 用户名   | 不能为空 |
| password | 密码     | 不能为空 |

- 响应参数

| 参数名   | 参数说明    | 备注            |
| -------- | ----------- | --------------- |
| id       | 用户 ID     |                 |
| username | 用户名      |                 |
| mobile   | 手机号      |                 |
| token    | 令牌        | 基于 jwt 的令牌 |

- 响应数据

```json
{
    "data": {
        "username": "admin",
        "mobile": "123",
        "email": "123@qq.com",
        "token": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjUwMCwicmlkIjowLCJpYXQiOjE1MTI1NDQyOTksImV4cCI6MTUxMjYzMDY5OX0.eGrsrvwHm-tPsO9r_pxHIQ5i5L1kX9RX444uwnRGaIM"
    },
    "meta": {
        "msg": "登录成功",
        "status": 200
    }
}
```
##1.2.2注册
- 请求路径：/register
- 请求方法：post
- 请求参数

| 参数名   | 参数说明 | 备注     |
| -------- | -------- | -------- |
| username | 用户名   | 不能为空 |
| password | 密码     | 不能为空 |
-备注：注册只需账号密码，详细信息在editAccount中设定
- 响应数据
```
{
    "data": {
        "username": "admin",
        "mobile": "123",
        "email": "123@qq.com",
        "token": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjUwMCwicmlkIjowLCJpYXQiOjE1MTI1NDQyOTksImV4cCI6MTUxMjYzMDY5OX0.eGrsrvwHm-tPsO9r_pxHIQ5i5L1kX9RX444uwnRGaIM"
    },
    "meta": {
        "msg": "注册成功",
        "status": 200
    }
}
{
    "data":null，
    "meta": {
        "msg": "账号已存在，注册失败",
        "status": 422
    }
}
```  
##1.2.3验证username是否存在
- 请求路径：/usernameIsExist
- 请求方法：get
- 请求参数

| 参数名   | 参数说明 | 备注     |
| -------- | -------- | -------- |
| username | 用户名   | 不能为空 |
- 响应数据
{
    "data":null，
    "meta": {
        "msg": "账号已存在",
        "status": 422
    }
}
{
    "data":null，
    "meta": {
        "msg": "账号可行",
        "status": 200
    }
}
## 1.3. 用户管理

##1.3.1展现买家信息
-请求路径：account/viewAccount
-请求方法:get
-请求参数：无
-响应数据
```json
{
  "data": {
    "username": "admin",
    "password": "123456",
    "address": "xxxx",
  },
  "meta": {
    "msg": "查询成功",
    "status": 200
  }
}
### 1.3.2. 修改用户（管理员）状态

- 请求路径：acount/editSupplier
- 请求方法：put
- 请求参数

| 参数名 | 参数说明 | 备注                                        |
| ------ | -------- | ------------------------------------------- |
| uId    | 用户 ID  | 不能为空`携带在url中`                       |

- 响应数据

```json
{
  "data": {
    "username": "admin",
    "mobile": "123456",
    "email": "bb@itcast.com",
    "mg_state": 0
  },
  "meta": {
    "msg": "设置状态成功",
    "status": 200
  }
}
```
##1.3.3修改买家信息
--备注：通过点击编辑后进行买家信息的密码地址的修改，而后再点击保存提交整个表单，发送到后端一个account型的json数据,然后无论有无修改皆进行set操作,避免修改信息分开操作

-请求路径：account/editAccount
-请求方法:put
-请求参数：account
-响应数据
```json
{
  "data": null,
  "meta": {
    "msg": "查询成功",
    "status": 200
  }
}
## 1.6. 商品分类管理

### 1.6.1. 商品分类数据列表

- 请求路径：categories
- 请求方法：get
- 请求参数

| 参数名   | 参数说明           | 备注                                                         |
| -------- | ------------------ | ------------------------------------------------------------ |
| pagenum  | 当前页码值         | 【可选参数】如果不传递，则默认获取所有分类                   |
| pagesize | 每页显示多少条数据 | 【可选参数】如果不传递，则默认获取所有分类                   |

- 响应参数

| 参数名    | 参数说明     | 备注 |
| --------- | ------------ | ---- |
| total     | 结果数量     |      |
| list      | category数组     |      |

- 响应数据

```json
{
    "status": 200,
    "msg": "查询成功",
    "data": {
        "total": 8,
        "list": [
            {
                "categoryId": "tet",
                "name": "123",
                "description": "234"
            },
            {
                "categoryId": "TIGERS",
                "name": "Tigers",
                "description": "6666666"
            },
            {
                "categoryId": "TTTT",
                "name": "T",
                "description": "11"
            }
        ],
        "pageNum": 2,
        "pageSize": 5,
        "size": 3,
        "startRow": 6,
        "endRow": 8,
        "pages": 2,
        "prePage": 1,
        "nextPage": 0,
        "isFirstPage": false,
        "isLastPage": true,
        "hasPreviousPage": true,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2,
        "firstPage": 1,
        "lastPage": 2
    }
}

```

### 1.6.2. 添加分类

- 请求路径：categories
- 请求方法：post
- 请求参数

| 参数名    | 参数说明  | 备注                                                        |
| --------- | --------- | ----------------------------------------------------------- |
| cat_pid   | 分类父 ID | 不能为空，如果要添加1级分类，则父分类Id应该设置为  `0`      |
| cat_name  | 分类名称  | 不能为空                                                    |
| cat_level | 分类层级  | 不能为空，`0`表示一级分类；`1`表示二级分类；`2`表示三级分类 |

- 响应数据

```json
{
    "data": {
        "cat_id": 62,
        "cat_name": "相框",
        "cat_pid": "1",
        "cat_level": "1"
    },
    "meta": {
        "msg": "创建成功",
        "status": 201
    }
}
```

### 1.6.3. 根据 id 查询Product

- 请求路径：categories/:id
- 请求方法：get
- 请求参数

| 参数名 | 参数说明 | 备注                  |
| ------ | -------- | --------------------- |
| :id    | 分类 ID  | 不能为空`携带在url中` |

- 响应数据

```
{
    "data": {
        "cat_id": 3,
        "cat_name": "厨卫电器",
        "cat_pid": 0,
        "cat_level": 0
    },
    "meta": {
        "msg": "获取成功",
        "status": 200
    }
}
```

### 1.6.4. 编辑提交分类

- 请求路径：categories/:id
- 请求方法：put
- 请求参数

| 参数名   | 参数说明 | 备注                             |
| -------- | -------- | -------------------------------- |
| :id      | 分类 ID  | 不能为空`携带在url中`            |
| cat_name | 分类名称 | 不能为空【此参数，放到请求体中】 |

- 响应数据

```
{
    "data": {
        "cat_id": 22,
        "cat_name": "自拍杆",
        "cat_pid": 7,
        "cat_level": 2
    },
    "meta": {
        "msg": "更新成功",
        "status": 200
    }
}
```

### 1.6.5. 删除分类

- 请求路径：categories/:id
- 请求方法：delete
- 请求参数

| 参数名 | 参数说明 | 备注                  |
| ------ | -------- | --------------------- |
| :id    | 分类 ID  | 不能为空`携带在url中` |

- 响应数据

```
{
    "data": null,
    "meta": {
        "msg": "删除成功",
        "status": 200
    }
}
```

## 1.9. 图片上传

- 请求路径：upload
- 请求方法：post
- 请求参数

| 参数名 | 参数说明 | 备注 |
| ------ | -------- | ---- |
| file   | 上传文件 |      |

- 响应数据

```
{
    "data": {
        "tmp_path": "tmp_uploads/ccfc5179a914e94506bcbb7377e8985f.png",
        "url": "http://127.0.0.1:8888tmp_uploads/ccfc5179a914e94506bcbb7377e8985f.png"
    },
    "meta": {
        "msg": "上传成功",
        "status": 200
    }
}
```

## 1.10. 订单管理

### 1.10.1. 订单数据列表

- 请求路径：/orders
- 请求方法：get
- 请求参数

| 参数名               | 参数说明        | 备注     |
| -------------------- | --------------- | -------- |
| query                | 查询参数        | 可以为空 |   
| pagenum              | 当前页码        | 不能为空 |
| pagesize             | 每页显示条数    | 不能为空 |
备注：暂且未设置查询参数
queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 10
      }
- 响应数据

```
{
    "data": {
        "total": 1,
        "pagenum": "1",
        "goods": [
            {
                "order_id": 47,
                "user_id": 133,
                "order_price": 322,
                "order_pay": "1",
                "is_send": "是",
                "create_time": 1508331565,
                "consignee_addr": "北京市海淀区苏州街长远天地大厦305室}",
                "pay_status": "1",
                "total_price": "xxx",
            }
        ]
    },
    "meta": {
        "msg": "获取成功",
        "status": 200
    }
}
```
### 1.10.x. 查看订单商品列表

- 请求路径：/OrderInfo
- 请求方法：get
- 请求参数

| 参数名               | 参数说明        | 备注     |
| -------------------- | --------------- | -------- |
| orderId                | 订单号        | 非空 |

- 响应数据

```
{
    "data": {
        "goods": [
            {
                itemId:xxx
                unitprice:xxxx
                quantity:xxxx
            },
            {
                itemId:xxx
                unitprice:xxxx/unitCost
                quantity:xxxx

            }
        ]
    },
    "meta": {
        "msg": "获取成功",
        "status": 200
    }
}
```
### 1.10.2. 修改订单是否发货

- 请求路径：orders/changeStatus
- 请求方法：put
- 请求参数

| 参数名       | 参数说明     | 备注                                       |
| ------------ | ------------ | ------------------------------------------ |
| id           | 订单 ID      | 不能为空                     |
| status      | 订单是否发货 | p:已经发货，n:未发货                       |            |

- 响应数据

```
{
    "data": null,
    "meta": {
        "msg": "修改成功",
        "status": 200
    }
}
```

### 1.10.5. 查看物流信息
-备注：暂未实现
+ 请求路径：/kuaidi/:id

+ 请求方法：get

+ 供测试的物流单号：1106975712662

+ 响应数据：

  ```json
  {
    "data": [
      {
        "time": "2018-05-10 09:39:00",
        "ftime": "2018-05-10 09:39:00",
        "context": "已签收,感谢使用顺丰,期待再次为您服务",
        "location": ""
      },
      {
        "time": "2018-05-10 08:23:00",
        "ftime": "2018-05-10 08:23:00",
        "context": "[北京市]北京海淀育新小区营业点派件员 顺丰速运 95338正在为您派件",
        "location": ""
      },
      {
        "time": "2018-05-10 07:32:00",
        "ftime": "2018-05-10 07:32:00",
        "context": "快件到达 [北京海淀育新小区营业点]",
        "location": ""
      },
      {
        "time": "2018-05-10 02:03:00",
        "ftime": "2018-05-10 02:03:00",
        "context": "快件在[北京顺义集散中心]已装车,准备发往 [北京海淀育新小区营业点]",
        "location": ""
      },
      {
        "time": "2018-05-09 23:05:00",
        "ftime": "2018-05-09 23:05:00",
        "context": "快件到达 [北京顺义集散中心]",
        "location": ""
      },
      {
        "time": "2018-05-09 21:21:00",
        "ftime": "2018-05-09 21:21:00",
        "context": "快件在[北京宝胜营业点]已装车,准备发往 [北京顺义集散中心]",
        "location": ""
      },
      {
        "time": "2018-05-09 13:07:00",
        "ftime": "2018-05-09 13:07:00",
        "context": "顺丰速运 已收取快件",
        "location": ""
      },
      {
        "time": "2018-05-09 12:25:03",
        "ftime": "2018-05-09 12:25:03",
        "context": "卖家发货",
        "location": ""
      },
      {
        "time": "2018-05-09 12:22:24",
        "ftime": "2018-05-09 12:22:24",
        "context": "您的订单将由HLA（北京海淀区清河中街店）门店安排发货。",
        "location": ""
      },
      {
        "time": "2018-05-08 21:36:04",
        "ftime": "2018-05-08 21:36:04",
        "context": "商品已经下单",
        "location": ""
      }
    ],
    "meta": { "status": 200, "message": "获取物流信息成功！" }
  }
  
  ```

