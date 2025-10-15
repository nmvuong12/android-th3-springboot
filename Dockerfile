# Sử dụng một image Java nhẹ làm nền
FROM openjdk:21-slim

# Đặt tên cho file JAR (bạn có thể thay đổi cho phù hợp với tên file của mình)
ARG JAR_FILE=target/*.jar

# Tạo một thư mục làm việc bên trong container
WORKDIR /app

# Copy file JAR đã được build từ máy của bạn vào container
COPY ${JAR_FILE} app.jar

# Mở cổng 8080 để bên ngoài có thể truy cập vào ứng dụng
EXPOSE 8080

# Lệnh để chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]