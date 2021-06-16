package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private long number1;
    private String operator ="";
    private boolean start= true;

    @FXML
    private Label output;

    @FXML
    private void processNumbers(ActionEvent event){
        if (start){
            output.setText("");
            start=false;
        }
        String value =((Button) event.getSource()).getText();
        output.setText(output.getText()+ value);

    }

    @FXML
    private void processOperators(ActionEvent event){
        if (output.getText().equals("Error")){
            return;
        }
        String value =((Button) event.getSource()).getText();
        if (!value.equals("=")){
            if (!operator.isEmpty()){
                return;
            }
            operator = value;
            number1 = Long.parseLong(output.getText());
            output.setText("");
        }
        else{
            if (operator.isEmpty()){
                return;
            }
            if (output.getText().isEmpty()){
                output.setText("Error");
                operator= "";
                start= true;
                return;
            }
            output.setText(calculate(number1, Long.parseLong(output.getText()), operator));
            operator="";
            start=true;
            return;
        }

    }

    @FXML
    private void clearOutput(ActionEvent event){
        output.setText("0");
        start=true;
        operator="";
    }

    private String calculate(long number1, long number2, String operator){
        switch (operator){
            case "+":
                return String.valueOf(number1+number2);

            case "-":
                return String.valueOf(number1-number2);

            case "/":
                if (number2 == 0){
                    return "Error";
                }

                return String.valueOf(number1/number2);

            case "*":
                return String.valueOf(number1*number2);

            case "%":
                return String.valueOf(number1%number2);

        }
        return "Error";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
