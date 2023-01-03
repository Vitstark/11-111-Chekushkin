module com.example.client {
	requires javafx.controls;
	requires javafx.fxml;

	requires com.almasb.fxgl.all;

	opens com.example.client to javafx.fxml;
	exports com.example.client;
}