# Setting Keycloak server
```bash
    # Mongo DB
    docker run -d --name keycloak 
    -p 8180:8080 
    -e KEYCLOAK_ADMIN=admin 
    -e KEYCLOAK_ADMIN_PASSWORD=admin 
    quay.io/keycloak/keycloak:25.0.0 start-dev
```
# Setting Web-app

- Tạo mới dự án react với vite
```bash
    # Với **React + JavaScript**:
    npm create vite@latest my-app -- --template react
```

```bash
    # Với **React + TypeScript**:
    npm create vite@latest my-app -- --template react-ts
```
- Cài đặt dependency

```bash
    # React router
    npm i react-router-dom
    # Add prettier
    npm install -D prettier
```

- Setting prettier + Eslint

    * **ESLint**: kiểm tra và enforce coding convention (tên biến, import thừa, lỗi JS/TS, …).
    * **Prettier**: format code nhất quán (dấu `;`, dấu nháy, spacing, wrap dòng…).  

    * ESLint sẽ lo phần **chất lượng code**.
    * Prettier sẽ lo phần **style & format**.
    * Dùng plugin `eslint-config-prettier` để tắt rule ESLint trùng với Prettier (tránh conflict).
    

```bash
    npm install -D 
    eslint 
    prettier 
    eslint-config-prettier 
    eslint-plugin-prettier
```

### 1. `.eslintrc.json` (hoặc `.eslintrc.js`)

```json
{
  "env": {
    "browser": true,
    "es2021": true
  },
  "extends": [
    "eslint:recommended",
    "plugin:react/recommended", 
    "plugin:prettier/recommended" 
  ],
  "plugins": ["react", "prettier"],
  "rules": {
    "prettier/prettier": "error"
  }
}
```

👉 Giải thích:

* `"plugin:prettier/recommended"` = tự động bật `eslint-config-prettier` và plugin Prettier.
* `"prettier/prettier": "error"` = nếu code không format đúng Prettier → ESLint báo lỗi.

---

### 2. `.prettierrc`

```json
{
  "semi": true,
  "singleQuote": true,
  "tabWidth": 2,
  "trailingComma": "es5",
  "printWidth": 80
}
```

* `"semi": true` → luôn thêm `;` ở cuối dòng.
* `"singleQuote": true` → dùng `'` thay vì `"`.
* `"tabWidth": 2` → mỗi tab = 2 spaces.
* `"trailingComma": "es5"` → thêm dấu `,` cuối object/array (ES5 cho phép).
* `"printWidth": 80` → wrap dòng khi >80 ký tự.

---

### 3. `.prettierignore`

```
node_modules
dist
build
```

👉 Loại trừ thư mục không cần format.

---

### 4. Thêm script trong `package.json`

```json
"scripts": {
  "lint": "eslint . --ext .js,.jsx,.ts,.tsx",
  "format": "prettier --write ."
}
```

---

✅ Sau khi setup xong:

* `npm run lint` → kiểm tra code với ESLint (bao gồm rule Prettier).
* `npm run format` → format toàn bộ code với Prettier.

# Setting profile-service
- Install mongo db 
```bash
    # Mongo DB
    docker run -d --name mongodb
    -p 27017:27017 
    -e MONGO_INITDB_ROOT_USERNAME=admin 
    -e MONGO_INITDB_ROOT_PASSWORD=admin mongo:latest
```