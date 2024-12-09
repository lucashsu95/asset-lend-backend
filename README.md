# asset lend backend

## How to Start

```bash
docker-compose up -d
./gradlew clean build
./gradlew bootRun
```

## Development

- java11
- spring boot
- mariadb: 10.4.32
- domain:`http://127.0.0.1:8000/api`

## API

| 1 | `/api/auth/login` | POST | 登入 |
| --- | --- | --- | --- |
| 2 | `/api/auth/logout` | POST | 登出 |
| 3 | `/api/auth/signup` | POST | 註冊 |
| 4 | `/api/users` | GET | 獲得所有使用者 |
| 5 | `/api/user` | POST | 新增使用者 |
| 6 | `/api/user/{user_id}` | PUT | 編輯使用者 |
| 7 | `/api/user/{user_id}` | DELETE | 刪除使用者 |
| 8 | `/api/asset` | GET | 獲得所有器材 |
| 9 | `/api/asset` | POST | 新增器材 |
| 10 | `/api/asset/{asset_id}` | PUT | 編輯器材 |
| 11 | `/api/asset/{asset_id}` | DELETE | 刪除器材 |
| 12 | `/api/lends` | GET | 獲得所有借用記錄 |
| 13 | `/api/lends/{user_id}`  | POST | 使用者借用 |
| 14 | `/api/lends/{lend_id}` | DELETE | 刪除借用記錄 |
| 15 | `/api/lends/{lend_id}/return` | POST | 還借用器材 |
| 16 | `/api/lend_assets/{lend_asset_id}/return`  | POST | 還器材 |

```json
{
    "lend_assets":[
        ["id1","amount1"],
        ["id2","amount2"],
    ]
}
```

## Feature

- 管理員
    - 登入
        - google
        - 限制只能用`.ntub.edu.tw`帳號
    - 登出
    - 借用管理 CURD
    - 器材管理 CURD
    - 人員管理 CURD
- 使用者
    - 購物車
        - 新增進購物車
        - 刪除購物車裡的器具
        - 借用器具
    - 還器具
- 註冊

## Database

- users
    - id
    - nickname
    - email
    - password
    - role
    - access_token
    - updated_at
    - created_at
    - deleted_at
- assets
    - id
    - name
    - img
    - amount
    - updated_at
    - created_at
    - deleted_at
- lends
    - id
    - user_id
    - lend_date
    - updated_at
    - created_at
    - deleted_at
- lend_assets
    - id
    - lend_id
    - asset_id
    - lend_amount
    - return_date
    - updated_at
    - created_at