# BookserviceAS

**BookserviceAS** är ett skolprojekt där vi bygger ett CRUD API för hantering av böcker med hjälp av Spring Boot, Spring Data JPA och MySQL. Projektet deployas både manuellt och automatiskt på AWS via GitHub Actions och AWS CodePipeline.

## Funktioner

- **CRUD-funktionalitet** för böcker:
  - Skapa, uppdatera, hämta och ta bort böcker.

### API-endpoints

- **Böcker**:
  - `GET /books`: Hämta alla böcker.
  - `GET /books/{id}`: Hämta en specifik bok med ID.
  - `POST /books`: Skapa en ny bok.
  - `PUT /books/{id}`: Uppdatera en befintlig bok.
  - `DELETE /books/{id}`: Ta bort en bok.

- **Environment URL**:
  - Applikationen är tillgänglig via:  
    `http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books`

## Teknologier

- **Java 21** med Spring Boot
- **Spring Data JPA** för databasinteraktion
- **MySQL** som databas
- **Maven** för byggsystem
- **JUnit** och **Mockito** för tester
- **AWS Elastic Beanstalk** för deployment
- **GitHub Actions** och **AWS CodePipeline** för CI/CD

## Installation och användning

### 1. Klona projektet
För att klona projektet från GitHub, kör följande kommando i terminalen:
```bash
git clone https://github.com/Guppie88/BookserviceAS.git
2. Bygg projektet
Navigera till projektmappen BookserviceAS och bygg projektet genom att köra följande kommando:

bash
Kopiera kod
mvn clean install
3. Kör applikationen lokalt
För att köra applikationen lokalt, använd kommandot:

bash
Kopiera kod
mvn spring-boot:run
4. Deployment till AWS
Projektet kan deployas på AWS Elastic Beanstalk. Följande instruktioner hjälper dig att koppla applikationen till en MySQL-databas på AWS RDS via miljövariabler.

Testning
Projektet använder JUnit och Mockito för att genomföra enhetstester. Tester körs automatiskt genom GitHub Actions, men du kan också köra dem lokalt med följande kommando:

bash
Kopiera kod
mvn test
CI/CD
GitHub Actions (Build och Test)
Projektet använder GitHub Actions för att bygga och testa applikationen automatiskt. Vid varje push eller pull request till main körs följande process:

Byggprocess: Koden byggs med Maven genom att köra mvn clean package.
Tester: Enhetstester körs automatiskt med JUnit.
Artifact: Byggda JAR-filer arkiveras och används senare för deployment.
AWS CodePipeline (Deployment)
För att hantera deployment-delen av CI/CD-processen används AWS CodePipeline. Processen är som följer:

Source: När en ny version pushas till GitHub via en push eller pull request triggas en pipeline.
Build: GitHub Actions bygger applikationen och skapar en JAR-fil som laddas upp till en S3-bucket.
Deploy: AWS CodePipeline tar den skapade JAR-filen från S3 och deployar den till Elastic Beanstalk. Pipeline består av steg för att skapa en ny applikationsversion i Elastic Beanstalk och sedan uppdatera miljön med den nya versionen.
Detaljerad beskrivning av pipelineprocessen
GitHub Actions Trigger: Varje gång en ändring pushas till GitHub på grenen main triggas GitHub Actions. Detta bygger koden, kör tester och laddar upp den byggda JAR-filen som en artefakt.

Ladda upp till S3: Efter att JAR-filen är byggd, laddas den upp till en S3-bucket. Detta möjliggör att AWS Elastic Beanstalk kan hämta och deploya applikationen.

Elastic Beanstalk Deployment: AWS CodePipeline hämtar JAR-filen från S3-bucketen och skapar en ny applikationsversion i Elastic Beanstalk. Denna version uppdateras sedan i den specifika Elastic Beanstalk-miljön som kör applikationen live.

Verifiering och feedback: Efter att deployment-processen är slutförd, är applikationen tillgänglig online. Eventuella fel under processen loggas och kan felsökas via AWS och GitHub Actions-loggar.

Framtida arbete
Skapa en separat klientapplikation som ansluter till detta API.
Författare
Andrea Sveinsdottir - GitHub-profil:https://github.com/Guppie88/BookserviceAS