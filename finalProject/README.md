# Final project
## Technologies
* Java version: 8.0_60
* MySql version: 5.5
* Apache commons version: 2.7.0
* JUnit version: 4.12
* Log4j version: 1.2.17
* Mockito version: 1.10.19
* JSTL version: 1.2
* Servlet API version: 4.0.1
## Система Доставка Груза
Предоставление услуг по получению и
доставке груза. На сайте содержится информация о тарифах и направления
доставки. Незарегистрированному пользователю доступен просмотр сайта и
калькулятор услуг. Зарегистрированный пользователь у себя в Кабинете
может создать Заявку на получение багажа и адрес доставки. Заявка
содержит информацию о типе багажа, вес и дату получения. После
получения заявки Система формирует счет. Пользователь может оплатить его
в своем кабинете.
## Instructions to install the project
Clone the project from Github repository, use command:
git clone https://github.com/MadYeti/JavaExternalClass/tree/finalProjectServlets/finalProject
## Instructions to run the project
1. Install database.
2. Go to Tomcat root folder /bin and run startup.bin to run Tomcat
3. Build and deploy the project with maven mvn tomcat7:deploy
4. Go to url localhost:8080/MainPage