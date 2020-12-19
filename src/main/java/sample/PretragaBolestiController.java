package main.java.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.sample.covidportal.model.Bolest;
import main.java.sample.covidportal.model.Osoba;
import main.java.sample.covidportal.model.Simptom;
import main.java.sample.covidportal.model.Virus;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class PretragaBolestiController implements Initializable {
    private static ObservableList<Bolest> observableListaBolesti;

    @FXML
    private TableView tablicaBolesti ;
    @FXML
    private TableColumn<Bolest, String> nazivStupac;
    @FXML
    private TableColumn<Set<Simptom>, String> simptomiStupac;
    @FXML
    private TableColumn<Long, String> idStupac;

    @FXML
    private TableColumn<String, String> opisStupac;

    @FXML
    private TextField unosNazivaBolesti;

    // 1. zadatak

    public void pretraga() throws IOException {
        String uneseniNazivBolesti = unosNazivaBolesti.getText().toLowerCase();

        Optional<List<Bolest>> filtriranaBolest = Optional.ofNullable(
                Main.bolesti
                .stream()
                .filter(z -> (!(z instanceof Virus)) && z.getNaziv().toLowerCase().contains(uneseniNazivBolesti))
                .collect(Collectors.toList())
        );
//        System.out.println(filtriranaBolest.get(0).getNaziv());

        if(filtriranaBolest.isPresent()) {
//            nazivStupac.setCellValueFactory(new PropertyValueFactory<Bolest, String>("naziv"));
//            simptomiStupac.setCellValueFactory(new PropertyValueFactory<Set<Simptom>, String>("simptomi"));

            tablicaBolesti.getItems().setAll(filtriranaBolest.get());
        }


//        tablicaZupanija.setItems(FXCollections.observableArrayList(filtriraneZupanije));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableListaBolesti = FXCollections.observableArrayList();
        observableListaBolesti.addAll(Main.bolesti.stream().filter(z -> (!(z instanceof Virus))).collect(Collectors.toList()));

        nazivStupac.setCellValueFactory(new PropertyValueFactory<Bolest, String>("naziv"));
        simptomiStupac.setCellValueFactory(new PropertyValueFactory<Set<Simptom>, String>("simptomi"));
        idStupac.setCellValueFactory(new PropertyValueFactory<Long, String>("id"));
        opisStupac.setCellValueFactory(new PropertyValueFactory<String, String>("opis"));

        tablicaBolesti.setItems(observableListaBolesti);

        // 3. zadatak


        tablicaBolesti.setRowFactory( t -> {
            TableRow<Bolest> red = new TableRow<>();
            // dodajem callback funkciju na event listener - ala JavaScript ista logika samo brojimo klikove sa getClickCount() metodom umjesto nekakvom statičkom varijablom
            red.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!red.isEmpty())) {

                    // znam da je ruzan workaround preko globalne varijable , no ne stignem napraviti konstruktor niti metodu za prikazivanje preko kontrolera
//                        Main.odabranaOsoba = red.getItem();

                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("dodavanjeBolesti.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load(), 800, 500);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                        Stage stg = new Stage(); // Ovo je koristimo ako zelimo otvoriti u novom prozoru

                    Stage stg = Main.getMainStage(); // Ovo koristimo ako zelimo otvoriti u istom prozoru

                    stg.setScene(scene);
                    stg.show();

                    DodavanjeNoveBolestiController controller = loader.getController();
                    controller.nadopuniBolest(red.getItem());

//                        Parent pretragaOsobaFrame = null;
//                        try {
//                            pretragaOsobaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaOsobe.fxml"));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }



//                        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 550, 380);
//                        Main.getMainStage().setScene(pretragaOsobaScene);
                }
            });
            return red ;
        });

    }

    public static ObservableList<Bolest> getObservableListaBolesti() {
        return observableListaBolesti;
    }

    public static void setObservableListaBolesti(ObservableList<Bolest> observableListaBolesti) {
        PretragaBolestiController.observableListaBolesti = observableListaBolesti;
    }
}
