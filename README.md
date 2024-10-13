
**BookserviceAS** är ett skolprojekt där vi bygger ett CRUD API för hantering av böcker och författare med hjälp av Spring Boot, Spring Data JPA och MySQL. Projektet deployas både manuellt och automatiskt på AWS via GitHub Actions och AWS CodePipeline.

## Funktioner

- **CRUD-funktionalitet** för böcker och författare:
    - Skapa, uppdatera, hämta och ta bort böcker och författare.
    - Böcker kan kopplas till författare och hanteras dynamiskt via API:et.

### API-endpoints

- **Böcker**:
    - GET /books: Hämta alla böcker.
    - GET /books/{id}: Hämta en specifik bok med ID.
    - POST /books: Skapa en ny bok.
    - PUT /books/{id}: Uppdatera en befintlig bok.
    - DELETE /books/{id}: Ta bort en bok.

- **Författare**:
    - GET /authors: Hämta alla författare.
    - GET /authors/{id}: Hämta en specifik författare med ID.
    - POST /authors: Skapa en ny författare.
    - PUT /authors/{id}: Uppdatera en befintlig författare.
    - DELETE /authors/{id}: Ta bort en författare.

- **Environment URL**:
    - Applikationen är tillgänglig via:  
      http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books

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

bash
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

Koden byggs med Maven.
Enhetstester körs automatiskt.
AWS CodePipeline (Deployment)
AWS CodePipeline används för att hantera deployment-delen av CI/CD-processen. När en ändring pushas till GitHub, byggs applikationen automatiskt och deployas till AWS Elastic Beanstalk via CodePipeline.

Framtida arbete
Skapa en separat klientapplikation som ansluter till detta API.
Utöka funktionaliteten med autentisering och säkerhet (exempelvis JWT).
Eventuell integration med fler AWS-tjänster.
Författare
Andrea Sveinsdottir - GitHub-profil  är det rätt? och skriv in min github profil

ChatGPT sade:
ChatGPT
Här är din uppdaterade README-fil i korrekt markdown-format och med din GitHub-profil inlagd:

markdown
Kopiera kod
# BookserviceAS

**BookserviceAS** är ett skolprojekt där vi bygger ett CRUD API för hantering av böcker och författare med hjälp av Spring Boot, Spring Data JPA och MySQL. Projektet deployas både manuellt och automatiskt på AWS via GitHub Actions och AWS CodePipeline.

## Funktioner

- **CRUD-funktionalitet** för böcker och författare:
    - Skapa, uppdatera, hämta och ta bort böcker och författare.
    - Böcker kan kopplas till författare och hanteras dynamiskt via API:et.

### API-endpoints

- **Böcker**:
    - `GET /books`: Hämta alla böcker.
    - `GET /books/{id}`: Hämta en specifik bok med ID.
    - `POST /books`: Skapa en ny bok.
    - `PUT /books/{id}`: Uppdatera en befintlig bok.
    - `DELETE /books/{id}`: Ta bort en bok.

- **Författare**:
    - `GET /authors`: Hämta alla författare.
    - `GET /authors/{id}`: Hämta en specifik författare med ID.
    - `POST /authors`: Skapa en ny författare.
    - `PUT /authors/{id}`: Uppdatera en befintlig författare.
    - `DELETE /authors/{id}`: Ta bort en författare.

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
mvn clean install

mvn clean install
3. Kör applikationen lokalt
För att köra applikationen lokalt, använd kommandot:

bash
mvn spring-boot:run

mvn spring-boot:run
4. Deployment till AWS
Projektet kan deployas på AWS Elastic Beanstalk. Följande instruktioner hjälper dig att koppla applikationen till en MySQL-databas på AWS RDS via miljövariabler.

Testning
Projektet använder JUnit och Mockito för att genomföra enhetstester. Tester körs automatiskt genom GitHub Actions, men du kan också köra dem lokalt med följande kommando:

bash
mvn test

mvn test
CI/CD
GitHub Actions (Build och Test)
Projektet använder GitHub Actions för att bygga och testa applikationen automatiskt. Vid varje push eller pull request till main körs följande process:

Koden byggs med Maven.
Enhetstester körs automatiskt.
AWS CodePipeline (Deployment)
AWS CodePipeline används för att hantera deployment-delen av CI/CD-processen. När en ändring pushas till GitHub, byggs applikationen automatiskt och deployas till AWS Elastic Beanstalk via CodePipeline.

Framtida arbete
Skapa en separat klientapplikation som ansluter till detta API.
Utöka funktionaliteten med autentisering och säkerhet (exempelvis JWT).
Eventuell integration med fler AWS-tjänster.
Författare
Andrea Sveinsdottir - GitHub-profil