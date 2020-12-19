package main.java.sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.sample.covidportal.enumeracija.VrijednostSimptoma;
import main.java.sample.covidportal.iznimke.BolestIstihSimptoma;
import main.java.sample.covidportal.model.Bolest;
import main.java.sample.covidportal.model.Simptom;
import main.java.sample.covidportal.model.Virus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class DodavanjeNoveBolestiController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(DodavanjeNoveBolestiController.class);

    @FXML
    private TextField nazivBolesti;

    @FXML
    private TextField simptomi;

    @FXML
    private TextField opis;

    // 1. zadatak

    public void dodajNovuBolest() {
        File unosBolesti = new File("dat/bolesti.txt");
        try (
                FileWriter filewriter = new FileWriter(unosBolesti, true);
                BufferedWriter writer = new BufferedWriter(filewriter);
        ) {
            String nazivBolestiText = nazivBolesti.getText();
            String simptomiText = simptomi.getText();
            String opisText = opis.getText();

            // 2. zadatak

            nazivBolesti.setStyle("-fx-text-box-border: grey;-fx-focus-color: grey;");
            simptomi.setStyle("-fx-text-box-border: grey;-fx-focus-color: grey;");
            opis.setStyle("-fx-text-box-border: grey;-fx-focus-color: grey;");

            if(nazivBolestiText.isEmpty()) {
                nazivBolesti.setStyle("-fx-text-box-border: red;-fx-focus-color: red;");
                throw new Exception("Prazan string");
            }
            if(simptomiText.isEmpty()) {
                simptomi.setStyle("-fx-text-box-border: red;-fx-focus-color: red;");
                throw new Exception("Prazan string");
            }
            if(opisText.isEmpty()) {
                opis.setStyle("-fx-text-box-border: red;-fx-focus-color: red;");
                throw new Exception("Prazan string");
            }

            Set<Simptom> odabraniSimptomi = new HashSet<>();

            Arrays.stream(simptomiText.split(",")).forEach(el -> {

                // Iteracija simptoma po indeksu

                int element = Integer.parseInt(el);

                Simptom simptom;

                Iterator<Simptom> iteratorSimptoma = Main.simptomi.iterator();
                Simptom pronadeniOdabraniSimptom = null;

                for (int k = 0; k < Main.simptomi.size() && iteratorSimptoma.hasNext(); ++k) {
                    simptom = iteratorSimptoma.next();
                    if (simptom.getId() == (element)) {
                        pronadeniOdabraniSimptom = simptom;
                        odabraniSimptomi.add(pronadeniOdabraniSimptom);
                    }
                }


            } );

            // Provjera duplikata unosa Simptoma

            if (Main.bolesti.size() > 0) {

                Main.provjeraBolestiIstihSimptoma(Main.bolesti, odabraniSimptomi);

            }

    //        Bolest novaBolest = new Bolest((long) (Main.bolesti.stream().map(b -> !(b instanceof Virus)).collect(Collectors.toList()).size() + 1), nazivBolestiText, odabraniSimptomi);

            Bolest novaBolest = new Bolest(Long.parseLong("1"+((Integer.valueOf((int) Main.bolesti.stream().filter(b -> !(b instanceof Virus)).count() + 1)).toString())), nazivBolestiText, odabraniSimptomi, opisText);

            // Provjera da li je unos bolest ili virus i unos u polje bolesti

            if (Main.bolesti == null) {
                Main.bolesti = new HashSet<>();
            }
            Main.bolesti.add(novaBolest);

            PretragaBolestiController.setObservableListaBolesti(FXCollections.observableArrayList());

            PretragaBolestiController.getObservableListaBolesti().addAll(Main.bolesti.stream().filter(z -> (!(z instanceof Virus))).collect(Collectors.toList()));


            writer.write(novaBolest.getId().toString()+"\n");
            writer.write(novaBolest.getNaziv()+"\n");
            writer.write(String.join(",", novaBolest
                    .getSimptomi()
                    .stream()
                    .map(simptom -> simptom.getId().toString())
                    .collect(Collectors.toList())) +"\n");
            writer.write(novaBolest.getOpis()+"\n");

            System.out.println(String.join(",", novaBolest
                    .getSimptomi()
                    .stream()
                    .map(simptom -> simptom.getId().toString())
                    .collect(Collectors.toList())));

            logger.info("Unesena je bolest: " + novaBolest.getNaziv());

            nazivBolestiText = null;
            simptomiText = null;

            PocetniEkranController.uspjesanUnos();

        } catch (IOException e) {
            PocetniEkranController.neuspjesanUnos(e.getMessage());
            logger.error("Ne mogu pronaci datoteku.", e);
        } catch (BolestIstihSimptoma ex) {
            logger.error("Bolest istih simptoma greska: ", ex);
            PocetniEkranController.neuspjesanUnos(ex.getMessage());
        } catch (NumberFormatException exc) {
            logger.error("Ne mogu pretvoriti vrijednost: ", exc);
            PocetniEkranController.neuspjesanUnos(exc.getMessage());
        } catch (Exception exce) {
            logger.error("Unio sam prazan string: ", exce);
            PocetniEkranController.neuspjesanUnos(exce.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //opis.setStyle("-fx-text-box-border: default;-fx-focus-color: default;");
    }

    // 3. zadatak

    public void nadopuniBolest(Bolest item) {
        nazivBolesti.setText(item.getNaziv());
        simptomi.setText(String.join(",", item
                .getSimptomi()
                .stream()
                .map(simptom -> simptom.getId().toString())
                .collect(Collectors.toList())));
        opis.setText(item.getOpis());




    }
}
