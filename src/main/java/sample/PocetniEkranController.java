package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PocetniEkranController implements Initializable {

//    @FXML
//    private TextField usernameTextField;
//    @FXML
//    private TextField passwordField;
//
//    public void login() throws IOException {
//        String username = usernameTextField.getText();
//        String password = passwordField.getText();
//        System.out.println(Main.osobe.get(1).getIme());
//
//
//
//        if("pero".equals(username) && "pp".equals(password)) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Prijava u aplikaciju");
//            alert.setHeaderText("Uspješna prijava");
//            alert.setContentText("Čestitamo, uspješno ste se prijavili u aplikaciju!");
//            alert.showAndWait();
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaZupanija.fxml"));
//            Scene pretragaZupanijaScene = new Scene(root, 550, 380);
//            Main.getMainStage().setScene(pretragaZupanijaScene);
////            Stage stage = new Stage();
////            stage.setTitle("Ekran aplikacije");
////            stage.setScene(new Scene(root, 600, 400));
////            stage.show();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Prijava u aplikaciju");
//            alert.setHeaderText("Nespješna prijava");
//            alert.setContentText("Unijeli ste neispravno korisničko ime ili lozinku!");
//            alert.showAndWait();
//        }
//    }

    public void prikaziEkranZaPretraguZupanija () throws IOException {
        Parent pretragaZupanijaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaZupanija.fxml"));
        Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 550, 380);
        Main.getMainStage().setScene(pretragaZupanijaScene);
    }
    public void prikaziEkranZaPretraguSimptoma () throws IOException {
        Parent pretragaSimptomaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaSimptoma.fxml"));
        Scene pretragaSimptomaScene = new Scene(pretragaSimptomaFrame, 550, 380);
        Main.getMainStage().setScene(pretragaSimptomaScene);
    }
    public void prikaziEkranZaPretraguBolesti () throws IOException {
        Parent pretragaBolestiFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaBolesti.fxml"));
        Scene pretragaBolestiScene = new Scene(pretragaBolestiFrame, 550, 380);
        Main.getMainStage().setScene(pretragaBolestiScene);
    }
    public void prikaziEkranZaPretraguVirusa () throws IOException {
        Parent pretragaVirusaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaVirusi.fxml"));
        Scene pretragaVirusaScene = new Scene(pretragaVirusaFrame, 550, 380);
        Main.getMainStage().setScene(pretragaVirusaScene);
    }
    public void prikaziEkranZaPretraguOsoba () throws IOException {
        Parent pretragaOsobaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaOsoba.fxml"));
        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 550, 380);
        Main.getMainStage().setScene(pretragaOsobaScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
