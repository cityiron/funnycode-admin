# 一、接口文档

## 创建场馆

POST http://localhost:8899/sport/place/add
Content-Type: application/json

```json
{
  "code": "TEST01",
  "name": "测试场馆01",
  "type": "ymq",
  "phone": "18889896666",
  "open": true,
  "startTime": 7,
  "endTime": 24
}
```

```json
{
  "code": 200,
  "message": "成功",
  "success": true,
  "data": {
    "records": [
      {
        "id": 1,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:46:14.000+00:00",
        "modifyTime": "2024-06-28T06:46:14.000+00:00",
        "code": "TEST01",
        "name": "测试场馆01",
        "position": null,
        "type": "ymq",
        "planPic": null,
        "brief": null,
        "facsDict": null,
        "phone": "18889896666",
        "tel": null,
        "open": true,
        "maxDay": 20180808,
        "startTime": "7",
        "endTime": "24"
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1,
    "pages": 1
  }
}
```

## 根据ID查询场馆

GET http://localhost:8899/sport/place/queryById?id=1

```json
{
  "code": 200,
  "message": "成功",
  "success": true,
  "data": {
    "id": 1,
    "deleted": false,
    "creator": "System",
    "modifier": "System",
    "createTime": "2024-06-28T06:46:14.000+00:00",
    "modifyTime": "2024-06-28T06:46:14.000+00:00",
    "code": "TEST01",
    "name": "测试场馆01",
    "position": null,
    "type": "ymq",
    "planPic": null,
    "brief": null,
    "facsDict": null,
    "phone": "18889896666",
    "tel": null,
    "open": true,
    "maxDay": 20180808,
    "startTime": "7",
    "endTime": "24"
  }
}
```

## 根据Code查询场馆

GET http://localhost:8899/sport/place/queryByCode?code=TEST01

```json
{
  "code": 200,
  "message": "成功",
  "success": true,
  "data": {
    "id": 1,
    "deleted": false,
    "creator": "System",
    "modifier": "System",
    "createTime": "2024-06-28T06:46:14.000+00:00",
    "modifyTime": "2024-06-28T06:46:14.000+00:00",
    "code": "TEST01",
    "name": "测试场馆01",
    "position": null,
    "type": "ymq",
    "planPic": null,
    "brief": null,
    "facsDict": null,
    "phone": "18889896666",
    "tel": null,
    "open": true,
    "maxDay": 20180808,
    "startTime": "7",
    "endTime": "24"
  }
}
```

## 批量添加场地

POST http://localhost:8899/sport/court/addBatch
Content-Type: application/json

```json
[
  {
    "placeId": 1,
    "name": "1号",
    "type": 1,
    "sort": 1,
    "open": true
  },
  {
    "placeId": 1,
    "name": "2号",
    "type": 1,
    "sort": 2,
    "open": true
  },
  {
    "placeId": 1,
    "name": "3号",
    "type": 1,
    "sort": 3,
    "open": true
  },
  {
    "placeId": 1,
    "name": "4号",
    "type": 1,
    "sort": 4,
    "open": true
  },
  {
    "placeId": 1,
    "name": "5号",
    "type": 1,
    "sort": 5,
    "open": true
  },
  {
    "placeId": 1,
    "name": "6号",
    "type": 1,
    "sort": 6,
    "open": true
  },
  {
    "placeId": 1,
    "name": "7号",
    "type": 1,
    "sort": 7,
    "open": true
  },
  {
    "placeId": 1,
    "name": "8号",
    "type": 1,
    "sort": 8,
    "open": true
  },
  {
    "placeId": 1,
    "name": "9号",
    "type": 2,
    "sort": 9,
    "open": true
  },
  {
    "placeId": 1,
    "name": "10号",
    "type": 2,
    "sort": 10,
    "open": false
  }
]
```

```json
{
  "code": 200,
  "message": "成功",
  "success": true,
  "data": true
}
```
## 查询场地

GET http://localhost:8899/sport/court/list

```json
{
  "code": 200,
  "message": "成功",
  "success": true,
  "data": {
    "records": [
      {
        "id": 11,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "1号",
        "type": 1,
        "open": true,
        "sort": 1
      },
      {
        "id": 12,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "2号",
        "type": 1,
        "open": true,
        "sort": 2
      },
      {
        "id": 13,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "3号",
        "type": 1,
        "open": true,
        "sort": 3
      },
      {
        "id": 14,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "4号",
        "type": 1,
        "open": true,
        "sort": 4
      },
      {
        "id": 15,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "5号",
        "type": 1,
        "open": true,
        "sort": 5
      },
      {
        "id": 16,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "6号",
        "type": 1,
        "open": true,
        "sort": 6
      },
      {
        "id": 17,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "7号",
        "type": 1,
        "open": true,
        "sort": 7
      },
      {
        "id": 18,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "8号",
        "type": 1,
        "open": true,
        "sort": 8
      },
      {
        "id": 19,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "9号",
        "type": 2,
        "open": true,
        "sort": 9
      },
      {
        "id": 20,
        "deleted": false,
        "creator": "System",
        "modifier": "System",
        "createTime": "2024-06-28T06:48:10.000+00:00",
        "modifyTime": "2024-06-28T06:48:10.000+00:00",
        "placeId": 1,
        "name": "10号",
        "type": 2,
        "open": false,
        "sort": 10
      }
    ],
    "total": 10,
    "size": 10,
    "current": 1,
    "pages": 1
  }
}
```

## 根据场馆编码查询场地

GET http://localhost:8899/sport/court/queryByPlaceCode?code=TEST01

```json
{
  "code": 200,
  "message": "成功",
  "success": true,
  "data": [
    {
      "id": 11,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "1号",
      "type": 1,
      "open": true,
      "sort": 1
    },
    {
      "id": 12,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "2号",
      "type": 1,
      "open": true,
      "sort": 2
    },
    {
      "id": 13,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "3号",
      "type": 1,
      "open": true,
      "sort": 3
    },
    {
      "id": 14,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "4号",
      "type": 1,
      "open": true,
      "sort": 4
    },
    {
      "id": 15,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "5号",
      "type": 1,
      "open": true,
      "sort": 5
    },
    {
      "id": 16,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "6号",
      "type": 1,
      "open": true,
      "sort": 6
    },
    {
      "id": 17,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "7号",
      "type": 1,
      "open": true,
      "sort": 7
    },
    {
      "id": 18,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "8号",
      "type": 1,
      "open": true,
      "sort": 8
    },
    {
      "id": 19,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "9号",
      "type": 2,
      "open": true,
      "sort": 9
    },
    {
      "id": 20,
      "deleted": false,
      "creator": "System",
      "modifier": "System",
      "createTime": "2024-06-28T06:48:10.000+00:00",
      "modifyTime": "2024-06-28T06:48:10.000+00:00",
      "placeId": 1,
      "name": "10号",
      "type": 2,
      "open": false,
      "sort": 10
    }
  ]
}
```