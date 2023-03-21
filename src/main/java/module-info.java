module com.javarush.cryptanalyzer.maisuradze {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.javarush.cryptanalyzer.maisuradze to javafx.fxml;
    exports com.javarush.cryptanalyzer.maisuradze;
}