# Product Management API Documentation

## Tổng quan
API quản lý sản phẩm với các chức năng CRUD cơ bản, sử dụng Spring Boot và MySQL.
Thêm quản lý Loại sản phẩm (Category) với quan hệ 1-nhiều tới Product.

## Cấu hình Database
- Database: MySQL
- Bảng: `products`
- Các thuộc tính:
  - `id`: Primary key (auto-increment)
  - `name`: Tên sản phẩm (required)
  - `desc`: Mô tả sản phẩm
  - `price`: Giá sản phẩm (required, precision 10,2)
  - `created_at`: Thời gian tạo (auto-generated)
  - `updated_at`: Thời gian cập nhật (auto-generated)

## Base URL
```
http://localhost:8080/api/products
http://localhost:8080/api/categories
```

## API Endpoints

### 1. Lấy danh sách tất cả sản phẩm
```
GET /api/products
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "iPhone 15",
    "desc": "Latest iPhone model",
    "price": 999.99,
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 2. Lấy sản phẩm theo ID
```
GET /api/products/{id}
```

**Parameters:**
- `id`: ID của sản phẩm

**Response:**
```json
{
  "id": 1,
  "name": "iPhone 15",
  "desc": "Latest iPhone model",
  "price": 999.99,
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### 3. Tạo sản phẩm mới
```
POST /api/products
```

**Request Body:**
```json
{
  "name": "Samsung Galaxy S24",
  "desc": "Latest Samsung flagship",
  "price": 899.99
}
```

**Response:** 201 Created
```json
{
  "id": 2,
  "name": "Samsung Galaxy S24",
  "desc": "Latest Samsung flagship",
  "price": 899.99,
  "createdAt": "2024-01-01T11:00:00",
  "updatedAt": "2024-01-01T11:00:00"
}
```

### 4. Cập nhật sản phẩm
```
PUT /api/products/{id}
```

**Parameters:**
- `id`: ID của sản phẩm cần cập nhật

**Request Body:**
```json
{
  "name": "iPhone 15 Pro",
  "desc": "Latest iPhone Pro model",
  "price": 1199.99
}
```

**Response:** 200 OK
```json
{
  "id": 1,
  "name": "iPhone 15 Pro",
  "desc": "Latest iPhone Pro model",
  "price": 1199.99,
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T12:00:00"
}
```

### 5. Xóa sản phẩm
```
DELETE /api/products/{id}
```

**Parameters:**
- `id`: ID của sản phẩm cần xóa

**Response:** 204 No Content

### 6. Tìm kiếm sản phẩm theo tên
```
GET /api/products/search?name={name}
```

**Parameters:**
- `name`: Tên sản phẩm cần tìm (case-insensitive)

**Response:**
```json
[
  {
    "id": 1,
    "name": "iPhone 15",
    "desc": "Latest iPhone model",
    "price": 999.99,
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 7. Lấy sản phẩm theo khoảng giá
```
GET /api/products/price-range?minPrice={minPrice}&maxPrice={maxPrice}
```

**Parameters:**
- `minPrice`: Giá tối thiểu
- `maxPrice`: Giá tối đa

**Response:**
```json
[
  {
    "id": 1,
    "name": "iPhone 15",
    "desc": "Latest iPhone model",
    "price": 999.99,
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

## Category Endpoints

### 1. Lấy tất cả category
```
GET /api/categories
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Điện thoại",
    "desc": "Các sản phẩm điện thoại",
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
]
```

### 2. Lấy category theo ID
```
GET /api/categories/{id}
```

### 3. Tạo category
```
POST /api/categories
```
**Request Body:**
```json
{
  "name": "Laptop",
  "desc": "Các loại laptop"
}
```

### 4. Cập nhật category
```
PUT /api/categories/{id}
```

### 5. Xóa category
```
DELETE /api/categories/{id}
```

### 6. Tìm kiếm category theo tên
```
GET /api/categories/search?name={name}
```

## Liên kết Product với Category
- Khi tạo/cập nhật `Product`, có thể truyền đối tượng `category` với `id` hợp lệ:
```json
{
  "name": "iPhone 15",
  "price": 999.99,
  "category": { "id": 1 }
}
```

## Swagger UI
Truy cập Swagger UI để xem và test API:
```
http://localhost:8080/swagger-ui.html
```

## Cài đặt và chạy ứng dụng

### 1. Cài đặt MySQL
- Tạo database `product_db`
- Cập nhật thông tin kết nối trong `application.properties` nếu cần

### 2. Chạy ứng dụng
```bash
cd th3
./mvnw spring-boot:run
```

### 3. Test API
- Sử dụng Postman, curl, hoặc Swagger UI
- Base URL: `http://localhost:8080`

## Ví dụ sử dụng với curl

### Tạo sản phẩm mới
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "MacBook Pro",
    "desc": "Apple laptop",
    "price": 1999.99
  }'
```

### Lấy danh sách sản phẩm
```bash
curl -X GET http://localhost:8080/api/products
```

### Cập nhật sản phẩm
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "MacBook Pro M3",
    "desc": "Apple laptop with M3 chip",
    "price": 2199.99
  }'
```

### Xóa sản phẩm
```bash
curl -X DELETE http://localhost:8080/api/products/1
```


