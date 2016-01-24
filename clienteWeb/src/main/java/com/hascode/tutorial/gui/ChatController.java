package com.hascode.tutorial.gui;

import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.json.Json;
import javax.json.JsonObject;


public class ChatController implements Initializable {
	@FXML
	private MenuItem exitItem;

	@FXML
	private ChoiceBox<String> roomSelection;

	@FXML
	private Button connectButton;

	@FXML
	private TextField userNameTextfield;

	@FXML
	private TextField messageTextField;

	@FXML
	private Button chatButton;

	@FXML
	private MenuItem aboutMenuItem;

	@FXML
	private ListView<String> chatListView;

	private final ChatModel model = new ChatModel();

	private ChatClientEndpoint clientEndPoint;

	@Override
	public void initialize(final URL url, final ResourceBundle bundle) {
		exitItem.setOnAction(e -> Platform.exit());
		roomSelection.setItems(FXCollections.observableArrayList("arduino", "java", "groovy", "scala"));
		roomSelection.getSelectionModel().select(1);
		model.userName.bindBidirectional(userNameTextfield.textProperty());
		model.roomName.bind(roomSelection.getSelectionModel().selectedItemProperty());
		model.readyToChat.bind(model.userName.isNotEmpty().and(roomSelection.selectionModelProperty().isNotNull()));
		chatButton.disableProperty().bind(model.connected.not());
		messageTextField.disableProperty().bind(model.connected.not());
		messageTextField.textProperty().bindBidirectional(model.currentMessage);
		connectButton.disableProperty().bind(model.readyToChat.not());
		chatListView.setItems(model.chatHistory);
		messageTextField.setOnAction(event -> {
			handleSendMessage();
		});
		chatButton.setOnAction(evt -> {
			handleSendMessage();
		});
		connectButton.setOnAction(evt -> {
			try {
				clientEndPoint = new ChatClientEndpoint(new URI("ws://quevedo2dam.azurewebsites.net/chat/" + model.roomName.get()));
				clientEndPoint.addMessageHandler(responseString -> {
					Platform.runLater(() -> {
						model.chatHistory.add(jsonMessageToString(responseString, model.roomName.get()));
					});
				});
				model.connected.set(true);
			} catch (Exception e) {
				showDialog("Error: " + e.getMessage());
			}

		});
		aboutMenuItem.setOnAction(event -> {
			showDialog("Example websocket chat bot written in JavaFX.\n\n Please feel free to visit my blog at www.hascode.com for the full tutorial!\n\n2014 Micha Kops");
		});
	}

	private void handleSendMessage() {
		clientEndPoint.sendMessage(stringToJsonMessage(model.userName.get(), model.currentMessage.get()));
		model.currentMessage.set("");
		messageTextField.requestFocus();
	}

	private void showDialog(final String message) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		VBox box = new VBox();
		box.getChildren().addAll(new Label(message));
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(5));
		dialogStage.setScene(new Scene(box));
		dialogStage.show();
	}

	private static String stringToJsonMessage(final String user, final String message) {
		return Json.createObjectBuilder().add("sender", user).add("message", message).build().toString();
	}

	private static String jsonMessageToString(final String response, final String roomName) {
//		JsonObject root = Json.createReader(new StringReader(response)).readObject();
//		String message = root.getString("message");
//		String sender = root.getString("sender");
//		String received = root.getString("received");
//		return String.format("%s@%s: %s [%s]", sender, roomName, message, received);
return response;
	}

}
