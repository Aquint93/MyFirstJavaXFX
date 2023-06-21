package application;
	
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;


public class Main extends Application {
	static File file;
	static TextAnalyzer aq;
	static Map<String, Integer> wordFrequency;
	
	public static void main(String[] args) throws IOException {
		file = new File("raven.txt");
		aq = new TextAnalyzer();
		wordFrequency = aq.usableWords(file);
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ObservableList<String> list = FXCollections.observableArrayList();

		primaryStage.setTitle("Text Analyzer");
		
		Label inputLabel = new Label("Input: ");
		TextField numInput = new TextField();
		Label outputLabel = new Label ("Output: ");
		ListView<String> results = new ListView<>(list);
		Button button = new Button("Generate");

		
		GridPane grid = new GridPane();
		grid.setMinSize(400, 200);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.CENTER);
		
		grid.add(inputLabel, 0, 0);
		grid.add(numInput, 1, 0);
		grid.add(outputLabel, 0, 1);
		grid.add(results, 1, 1);
		grid.add(button, 1, 2);
		
		VBox.setVgrow(results, Priority.ALWAYS);
		VBox.setMargin(results, new Insets(5));
		VBox root = new VBox(grid);
		root.setFillWidth(true);
		
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		button.setOnAction(e -> {
			String num = numInput.getText();
			int n = Integer.parseInt(num);
			
			list.clear();
			
			List<Entry<String, Integer>> topFrequentWords = aq.topFrequentWords(wordFrequency);
			for(int i = 0; i < n && i < topFrequentWords.size(); i++) {
				Entry<String, Integer> entry = topFrequentWords.get(i);
				list.add(entry.getKey()+" = "+entry.getValue());
			}
		});

	}

}
