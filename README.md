<h1 align="center" id="title">Website xem tin tức</h1>
<p id="description" align="center">Dự án đơn giản nhằm mục đích học tập và tìm hiểu về microservice và spring frameword.</p>
<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white" />
</p>
<h2>Cơ sở dữ liệu</h2>
<p>Cơ sở dữ liệu được thiết kế đơn giản hóa như sau</p>
<img src="image/News-ERD.png"/>
<p>Với khá ít bảng việc phân chia cơ sở dữ liệu là không cần thiết, tuy nhiên để nghiên cứu về kiến trúc microservice nó được phân chia như sau</p>
<img src="image/News-ERD-split.png"/>

<h2>Cách khởi chạy trên Docker</h2>
<h4>Docker compose v1</h4>
Cập nhật các biến môi trường trong file service.env sau đó chạy.
```
docker-compose --env-file service.env up -d
```
<h4>Docker compose v2</h4>
Thay thế biến môi trường trực tiếp trong file docker-compose.yml chạy
```
docker compose up -d
```
