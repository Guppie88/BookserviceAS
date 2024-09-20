Här är ett utkast till din README-fil för projektet **BookserviceAS**:

---

# BookserviceAS

**BookserviceAS** är ett skolprojekt där vi bygger ett CRUD API för hantering av böcker och författare med hjälp av Spring Boot, Spring Data JPA och MySQL. Projektet inkluderar även en klientapplikation och deployas manuellt på AWS, där både kod och databas hanteras.

## Funktioner

- CRUD-funktionalitet för böcker och författare:
    - Skapa, uppdatera och ta bort böcker och författare.
    - Böcker kan kopplas till författare och hanteras dynamiskt.

- Klientapplikation (under utveckling):
    - Användargränssnitt för att interagera med API:et.
    - Möjlighet att lägga till och ta bort böcker samt visa boklistor.

## Teknologier

- **Java 22** med Spring Boot
- **Spring Data JPA** för databasinteraktion
- **MySQL** som databas
- **Maven** för byggsystem
- **JUnit** och **Mockito** för tester
- **AWS Elastic Beanstalk** för deployment
- **GitHub Actions** för CI/CD

## Installation och användning

1. **Klona projektet**:
   ```bash
   git clone https://github.com/ditt-användarnamn/BookserviceAS.git
   ```

2. **Bygg projektet**:
   Gå till projektmappen `BookserviceAS/molndalAPIExample` och kör följande kommando:
   ```bash
   mvn clean install
   ```

3. **Kör applikationen lokalt**:
   ```bash
   mvn spring-boot:run
   ```

4. **Manuell deployment på AWS**:
    - Använd AWS Elastic Beanstalk för att deploya applikationen manuellt.
    - Skapa en MySQL-databas på AWS RDS och koppla den till din applikation via miljövariabler.
    - Se till att API:et och klientapplikationen är korrekt konfigurerade för att kommunicera med databasen och varandra.

## Testning

Projektet använder JUnit och Mockito för enhetstester. Tester körs automatiskt genom GitHub Actions. För att köra testerna lokalt:
```bash
mvn test
```

## Klientapplikation

Klientapplikationen, som är under utveckling, kommer att byggas som en separat modul och möjliggöra användarinteraktion med API:et.

## CI/CD

Vi använder **GitHub Actions** för kontinuerlig integration och testning. Workflow-filen konfigurerar en CI-pipeline som kör tester automatiskt vid varje push eller pull request till huvudgrenen.

## Framtida arbete

- Färdigställande av klientapplikationen.
- Förbättrad säkerhet och autentisering.
- Automatisering av deployment till AWS med CI/CD.

## Författare

- **Andrea Sveinsdottir** - [GitHub-profil](https://github.com/Guppie88)

---

