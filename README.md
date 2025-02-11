# Website xem tin tức

Dự án đơn giản nhằm mục đích học tập và tìm hiểu về microservice và Spring Framework.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

## Cơ sở dữ liệu

Cơ sở dữ liệu được thiết kế đơn giản hóa như sau:

![ERD](image/News-ERD.png)

Với khá ít bảng, việc phân chia cơ sở dữ liệu là không cần thiết. Tuy nhiên, để nghiên cứu về kiến trúc microservice, nó được phân chia như sau:

![ERD Split](image/News-ERD-split.png)

## Cách khởi chạy trên Docker

#### Docker compose v1
Cập nhật các biến môi trường trong file service.env sau đó chạy.
````md
```
docker-compose --env-file service.env up -d
```
````
#### Docker compose v2
Thay thế biến môi trường trực tiếp trong file docker-compose.yml chạy
````md
```
docker compose up -d
```
````
