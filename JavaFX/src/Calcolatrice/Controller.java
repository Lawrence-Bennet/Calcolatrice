package Calcolatrice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.DecimalFormat;

public class Controller {

    private double n1 = 0;
    private double n2 = 0;
    private double risultato = 0;
    private boolean premutoOperatore = false;
    private char operatore = ' ';
    private int moltiplicatore = 1;
    private boolean virgolaPremuta = false;
    private boolean numeroGiaPremuto = false;

    DecimalFormat df = new DecimalFormat("#.###");

    @FXML
    private Button ugualeButton;

    @FXML
    private Button sottraiButton;

    @FXML
    private Button sommaButton;

    @FXML
    private Button moltiplicaButton;

    @FXML
    private Button dividiButton;

    @FXML
    private Button elevaButton;

    @FXML
    private Button cancellaButton;

    @FXML
    private Button setteButton;

    @FXML
    private Button ottoButton;

    @FXML
    private Button noveButton;

    @FXML
    private Button quattroButton;

    @FXML
    private Button cinqueButton;

    @FXML
    private Button seiButton;

    @FXML
    private Button unoButton;

    @FXML
    private Button dueButton;

    @FXML
    private Button treButton;

    @FXML
    private Button zeroButton;

    @FXML
    private Button virgolaButton;

    @FXML
    private Label risultatoLabel;

    @FXML
    void numTapped(ActionEvent event) {

        Button tf = (Button) event.getSource();
        Integer value = (Integer) tf.getUserData();

        if (virgolaPremuta) {

            if (premutoOperatore){
                n2 *= moltiplicatore;
                n2 += value;
                n2 /= moltiplicatore;
                String numero = df.format(n2).replace(".", ",");
                risultatoLabel.setText(numero);
            } else {
                n1 *= moltiplicatore;
                n1 += value;
                n1 /= moltiplicatore;
                String numero = df.format(n1).replace(".", ",");
                risultatoLabel.setText(numero);
            }
        } else {

            if (numeroGiaPremuto) {
                moltiplicatore = 10;
            }
            if (premutoOperatore) {
                n2 *= moltiplicatore;
                n2 += value;
                String numero = df.format(n2);
                risultatoLabel.setText(numero);
                numeroGiaPremuto = true;
            } else {
                n1 *= moltiplicatore;
                n1 += value;
                String numero = df.format(n1);
                risultatoLabel.setText(numero);
                numeroGiaPremuto = true;
            }
        }
    }

    @FXML
    void operationTapped(ActionEvent event) {


        Button tf = (Button) event.getSource();
        char value = (char)tf.getUserData();
        switch (value) {
            case 'C':
                resetta();
                break;
            case 'x':
                imposta();
                operatore = 'x';
                break;
            case '=':
                if (operatore != ' '){
                    faiOperazione();
                }
                break;
            case '+':
                imposta();
                operatore = '+';
                break;
            case '-':
                imposta();
                operatore = '-';
                break;
            case '/':
                imposta();
                operatore = '/';
                break;
            case '^':
                imposta();
                operatore = '^';
                break;
            case '.':
                moltiplicatore = 10;
                virgolaPremuta = true;
                break;
        }
    }

    @FXML
    void initialize() {

        df.setDecimalSeparatorAlwaysShown(false);
        cancellaButton.setUserData('C');
        noveButton.setUserData(9);
        ugualeButton.setUserData('=');
        zeroButton.setUserData(0);
        unoButton.setUserData(1);
        dueButton.setUserData(2);
        treButton.setUserData(3);
        quattroButton.setUserData(4);
        cinqueButton.setUserData(5);
        seiButton.setUserData(6);
        setteButton.setUserData(7);
        ottoButton.setUserData(8);
        moltiplicaButton.setUserData('x');
        sommaButton.setUserData('+');
        sottraiButton.setUserData('-');
        virgolaButton.setUserData('.');
        dividiButton.setUserData('/');
        elevaButton.setUserData('^');
    }

    void resetta() {
        n1 = 0;
        n2 = 0;
        risultato = 0;
        premutoOperatore = false;
        moltiplicatore = 1;
        operatore = ' ';
        String numero = df.format(n1);
        risultatoLabel.setText(numero);
        virgolaPremuta = false;
    }

    void faiOperazione() {
        switch (operatore){
            case 'x':
                risultato = n1*n2;
                break;
            case '+':
                risultato = n1+n2;
                break;
            case '-':
                risultato = n1-n2;
                break;
            case '/':
                risultato = n1/n2;
                break;
            case '^':
                risultato = Math.pow(n1, n2);
                break;
        }
        n2 = 0;
        n1 = risultato;
        String result = df.format(risultato);
        risultatoLabel.setText(result);
    }

    public void imposta(){
        numeroGiaPremuto = false;
        virgolaPremuta = false;
        premutoOperatore = true;
        moltiplicatore = 1;
    }

}
