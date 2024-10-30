BookserviceAS
BookserviceAS är ett skolprojekt där vi bygger ett CRUD API för hantering av böcker med hjälp av Spring Boot, Spring Data JPA och MySQL. Projektet kan köras både lokalt med H2-databasen och på AWS med MySQL som databas. CI/CD hanteras med GitHub Actions och AWS CodePipeline för automatisk byggning och deployment.

Funktioner
CRUD-funktionalitet för böcker:
Skapa, uppdatera, hämta och ta bort böcker.
API-endpoints
Böcker:
GET /books: Hämta alla böcker.
GET /books/{id}: Hämta en specifik bok med ID.
POST /books: Skapa en ny bok.
PUT /books/{id}: Uppdatera en befintlig bok.
DELETE /books/{id}: Ta bort en bok.
Miljöer
Lokal miljö (H2-databas)
För lokalt utvecklingsarbete använder vi en in-memory H2-databas. Den lokala miljön är konfigurerad att köra på port 5000. Denna miljö är perfekt för snabb utveckling och testning utan behov av en extern databas.

H2-databas:
Databasen skapas automatiskt vid applikationens start.
Alla data finns i minnet och försvinner när applikationen stängs av.
Konfiguration för lokal miljö finns i application-local.properties:

# Local H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA and Hibernate Settings for H2
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

# Logging Levels
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql=TRACE

# Server Port
server.port=5000
AWS-miljö (Elastic Beanstalk och RDS MySQL)
För produktion och mer omfattande testning använder vi AWS Elastic Beanstalk för att deploya applikationen och AWS RDS (MySQL) som databas. Denna miljö kräver manuell deployment av databasen via AWS, och applikationen kopplar till MySQL via miljövariabler.

Environment URL:
Applikationen är tillgänglig via:
http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books
Konfiguration för AWS-miljö finns i application-aws.properties:

# AWS MySQL Database Configuration
spring.datasource.url=jdbc:mysql://webservice-db.chcwmoqw0h8e.eu-north-1.rds.amazonaws.com:3306/webservice?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=

# JDBC Driver
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA and Hibernate Settings for MySQL
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# HikariCP Settings (Connection Pool)
spring.datasource.hikari.connection-timeout=60000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.maximum-pool-size=10
Teknologier
Java 21 med Spring Boot
Spring Data JPA för databasinteraktion
MySQL och H2 som databaser
Maven för byggsystem
JUnit och Mockito för tester
AWS Elastic Beanstalk för deployment
GitHub Actions och AWS CodePipeline för CI/CD
Installation och användning
1. Klona projektet
   För att klona projektet från GitHub, kör följande kommando i terminalen:

bash
git clone https://github.com/Guppie88/BookserviceAS.git
2. Bygg projektet
   Navigera till projektmappen BookserviceAS och bygg projektet genom att köra följande kommando:

bash
mvn clean install
3. Kör applikationen lokalt
   För att köra applikationen lokalt med H2-databasen, använd följande kommando:

bash
mvn spring-boot:run
Applikationen kommer att vara tillgänglig på http://localhost:5000.

4. Deployment till AWS
   För att deploya projektet på AWS Elastic Beanstalk, följ dessa steg:

Skapa en MySQL-databas på AWS RDS och uppdatera applikationens application-aws.properties med databasuppgifterna.
Deploya applikationen på AWS Elastic Beanstalk via AWS CLI eller AWS CodePipeline.
Testning
Projektet använder JUnit och Mockito för enhetstester. Du kan köra testerna lokalt med följande kommando:

bash
mvn test
CI/CD
GitHub Actions (Build och Test)
Projektet använder GitHub Actions för att automatiskt bygga och testa applikationen. Vid varje push eller pull request till huvudgrenen körs följande process:

Byggprocess: Koden byggs med Maven genom att köra mvn clean package.
Tester: Enhetstester körs automatiskt med JUnit.
Artifact: Byggda JAR-filer arkiveras och används senare för deployment.
AWS CodePipeline (Deployment)
För att hantera deployment-delen av CI/CD-processen används AWS CodePipeline. Processen är som följer:

Source: När en ny version pushas till GitHub triggas en pipeline.
Build: GitHub Actions bygger applikationen och skapar en JAR-fil som laddas upp till en S3-bucket.
Deploy: AWS CodePipeline tar JAR-filen från S3 och deployar den till Elastic Beanstalk.
Detaljerad beskrivning av pipelineprocessen
GitHub Actions Trigger: Varje gång en ändring pushas till GitHub på grenen main triggas GitHub Actions. Detta bygger koden, kör tester och laddar upp den byggda JAR-filen som en artefakt.

Ladda upp till S3: Efter att JAR-filen är byggd, laddas den upp till en S3-bucket. Detta möjliggör att AWS Elastic Beanstalk kan hämta och deploya applikationen.

Elastic Beanstalk Deployment: AWS CodePipeline hämtar JAR-filen från S3-bucketen och skapar en ny applikationsversion i Elastic Beanstalk. Denna version uppdateras sedan i den specifika Elastic Beanstalk-miljön som kör applikationen live.

Verifiering och feedback: Efter att deployment-processen är slutförd, är applikationen tillgänglig online. Eventuella fel under processen loggas och kan felsökas via AWS och GitHub Actions-loggar.

Författare
Andrea Sveinsdottir
GitHub-profil: Guppie88
ppppp