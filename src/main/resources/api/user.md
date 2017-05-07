#### 用户相关 api 文档


----

##### 登录

```text
url: /api/user/login
method: POST
param: mobile(手机号), password(密码)
example: /api/user/login?mobile=xxxx&password=xxxx
return:
```
> 成功
```json

{
  "code": 0,
  "data": {
    "uid": 55,
    "token": "75b2e801f3be48d1a4a61700fdd516b8"
  },
  "msg": "success"
}
```
> 失败

```json
{
  "code": 1,
  "data": null,
  "msg": "0"
}
```
> 补充
```text
继续延伸上个版本的逻辑，如果登录失败，并且 msg=0表示密码错误， msg=1表示手机号未注册
```