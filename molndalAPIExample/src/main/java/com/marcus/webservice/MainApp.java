package com.marcus.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Models.Author;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class MainApp extends Application {

    private ListView<Books> bookListView;
    private ObservableList<Books> bookObservableList;
    private ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper för JSON-hantering

    @Override
    public void start(Stage primaryStage) {
        bookObservableList = FXCollections.observableArrayList();
        bookListView = new ListView<>(bookObservableList);

        TextField titleInput = new TextField();
        titleInput.setPromptText("Titel");

        TextField isbnInput = new TextField();
        isbnInput.setPromptText("ISBN");

        TextField authorNameInput = new TextField();
        authorNameInput.setPromptText("Författarnamn");

        Button addButton = new Button("Lägg till bok");
        addButton.setOnAction(event -> addBook(titleInput.getText(), isbnInput.getText(), authorNameInput.getText()));

        Button deleteButton = new Button("Ta bort vald bok");
        deleteButton.setOnAction(event -> {
            Books selectedBook = bookListView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                deleteBook(selectedBook);
            } else {
                showError("Ingen bok vald");
            }
        });

        VBox layout = new VBox(10, bookListView, titleInput, isbnInput, authorNameInput, addButton, deleteButton);
        Scene scene = new Scene(layout, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bokservice");
        primaryStage.show();

        fetchBooks();
    }

    private void fetchBooks() {
        String uri = "http://localhost:8080/books";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> {
            if (res.statusCode() == 200) {
                try {
                    Books[] books = objectMapper.readValue(res.body(), Books[].class);
                    Platform.runLater(() -> bookObservableList.setAll(Arrays.asList(books)));
                } catch (JsonProcessingException e) {
                    Platform.runLater(() -> showError("Fel vid parsing av bokdata: " + e.getMessage()));
                }
            } else {
                Platform.runLater(() -> showError("Kunde inte hämta böcker"));
            }
        }).exceptionally(ex -> {
            Platform.runLater(() -> showError("Fel vid hämtning av böcker: " + ex.getMessage()));
            return null;
        });
    }

    private void addBook(String title, String isbn, String authorName) {
        String uri = "http://localhost:8080/books";

        // Skapa en Author-objekt och sätt namn
        Author author = new Author();
        author.setName(authorName);  // Sätt författarnamn här

        // Skapa en Books-objekt och sätt titel, ISBN och författare
        Books newBook = new Books();
        newBook.setTitle(title);
        newBook.setISBN(isbn);
        newBook.setAuthor(author);  // Länka Author-objektet till boken

        try {
            // Konvertera Books-objektet till JSON
            String json = objectMapper.writeValueAsString(newBook);

            // Skicka POST-begäran till servern
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            // Hantera svaret asynkront
            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            response.thenAccept(res -> {
                Platform.runLater(() -> {
                    if (res.statusCode() == 200 || res.statusCode() == 201) {
                        fetchBooks(); // Hämta uppdaterad lista efter tillägg
                    } else {
                        showError("Kunde inte lägga till bok: " + res.body());
                    }
                });
            }).exceptionally(ex -> {
                Platform.runLater(() -> showError("Fel vid tillägg: " + ex.getMessage()));
                return null;
            });
        } catch (JsonProcessingException e) {
            showError("Fel vid konvertering till JSON: " + e.getMessage());
        }
    }


    private void deleteBook(Books book) {
        String uri = "http://localhost:8080/books/" + book.getId();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .DELETE()
                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> {
            Platform.runLater(() -> {
                if (res.statusCode() == 200) {
                    fetchBooks(); // Hämta uppdaterad lista efter borttagning
                } else {
                    showError("Kunde inte ta bort bok: " + res.body());
                }
            });
        }).exceptionally(ex -> {
            Platform.runLater(() -> showError("Fel vid radering: " + ex.getMessage()));
            return null;
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fel");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}