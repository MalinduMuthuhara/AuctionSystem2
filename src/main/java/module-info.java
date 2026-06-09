module edu.ijse.fx.auctionsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ijse.fx.auctionsystem to javafx.fxml;
    exports edu.ijse.fx.auctionsystem;
}