package main.java.sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.sample.covidportal.model.Simptom;
import main.java.sample.covidportal.model.Zupanija;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PretragaSimptomaController {
    @FXML
    private TableView tablicaSimptoma ;
    @FXML
    private TableColumn<Simptom, String> nazivStupac;
    @FXML
    private TableColumn<Simptom, String> vrijednostStupac;
    @FXML
    private TextField unosNazivaSimptoma;

    public void pretraga() throws IOException {
        String uneseniNazivSimptoma = unosNazivaSimptoma.getText().toLowerCase();

        Optional<List<Simptom>> filtriraniSimptom = Optional.ofNullable(Main.simptomi.stream().filter(z -> z.getNaziv().toLowerCase().contains(uneseniNazivSimptoma)).collect(Collectors.toList()));
//        System.out.println(filtriraniSimptom.get(0).getNaziv());

        if(filtriraniSimptom.isPresent()) {
            nazivStupac.setCellValueFactory(new PropertyValueFactory<Simptom, String>("naziv"));
            vrijednostStupac.setCellValueFactory(new PropertyValueFactory<Simptom, String>("vrijednost"));

            tablicaSimptoma.getItems().setAll(filtriraniSimptom.get());
        }

//        tablicaZupanija.setItems(FXCollections.observableArrayList(filtriraneZupanije));
    }
}
