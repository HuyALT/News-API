# Website xem tin tức

Dự án đơn giản nhằm mục đích học tập và tìm hiểu về microservice và Spring Framework.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

# Mục lục
- [Cơ sở dữ liệu](#Cơ-sở-dữ-liệu)
- [Tổng quan về hệ thống Back-end](#Tổng-quan-về-hệ-thống-Back-end)
- [Tổng quan về giao diện](#Tổng-quan-về-giao-diện)
- [Cách khởi chạy back-end bằng Docker](#Cách-khởi-chạy-back-end-bằng-Docker)

## Cơ sở dữ liệu

Cơ sở dữ liệu được thiết kế đơn giản hóa như sau:

![ERD](image/News-ERD.png)

Với khá ít bảng, việc phân chia cơ sở dữ liệu là không cần thiết. Tuy nhiên, để nghiên cứu về kiến trúc microservice, nó được phân chia như sau:

![ERD Split](image/News-ERD-split.png)

## Tổng quan về hệ thống Back-end
Cách hoạt động back-end được mô tả trong hình sau
![ERD Split](image/BackendLogic.png)

## Tổng quan về giao diện
Được viết bằng Angular. Phần này không được chi tiết lắm nhưng về cơ bản các chức năng vẫn hoạt động bình thường
### Trang Chủ
![ERD Split](image/front-end-1.png)
### Chi tiết tin tức
![ERD Split](image/front-end-2.png)
### Giao diện quản lý
![ERD Split](image/front-end-3.png)

## Cách khởi chạy back-end bằng Docker

#### Docker compose v1
Cập nhật các biến môi trường trong file service.env sau đó chạy.
````cmd
docker-compose --env-file service.env up -d
````
#### Docker compose v2
Thay thế biến môi trường trực tiếp trong file docker-compose.yml chạy
````cmd
docker compose up -d
````
