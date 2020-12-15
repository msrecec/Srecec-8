package main.java.sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.sample.covidportal.model.Bolest;
import main.java.sample.covidportal.model.Simptom;
import main.java.sample.covidportal.model.Virus;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PretragaVirusiController {
    @FXML
    private TableView tablicaVirusa ;
    @FXML
    private TableColumn<Bolest, String> nazivStupac;
    @FXML
    private TableColumn<Set<Simptom>, String> simptomiStupac;
    @FXML
    private TextField unosNazivaVirusa;

    public void pretraga() throws IOException {
        String uneseniNazivVirusa = unosNazivaVirusa.getText().toLowerCase();

        Optional<List<Bolest>> filtriranaBolest = Optional.ofNullable(Main.bolesti.stream().filter(z -> ((z instanceof Virus)) && z.getNaziv().toLowerCase().contains(uneseniNazivVirusa)).collect(Collectors.toList()));
//        System.out.println(filtriranaBolest.get(0).getNaziv());

        if(filtriranaBolest.isPresent()) {
            nazivStupac.setCellValueFactory(new PropertyValueFactory<Bolest, String>("naziv"));
            simptomiStupac.setCellValueFactory(new PropertyValueFactory<Set<Simptom>, String>("simptomi"));

            tablicaVirusa.getItems().setAll(filtriranaBolest.get());
        }


//        tablicaZupanija.setItems(FXCollections.observableArrayList(filtriraneZupanije));
    }
}
