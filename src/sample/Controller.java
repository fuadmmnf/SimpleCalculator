package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller
{
    private static double number=0;
    private String operator=" ";
    private boolean point=false;
    private int piCount=0;
   // private static double operand=0;
    private static boolean flag=false;
    private static boolean mathError=false;
    @FXML
    private Label output;
    @FXML
    public void NumberPress(ActionEvent event)
    {
        if(!mathError) {
            if (flag == true) {
                flag = false;
                output.setText("0");
                number = 0;
            }

            String value = ((Button) event.getSource()).getText();
            if (value.equals("Pi") && point == false) {
                piCount++;
                if (piCount < 2) {
                    output.setText(output.getText() + 3.1416);
                 /*  if((output.getText()).length()>1)
                   {
                       output.setText(""+Double.parseDouble(output.getText())*3.1416);
                   }
                   else
                   {

                   }
                   */
                }
            } else if (value.equals("/b")) {
                if ((output.getText()).length() > 1)
                    output.setText((output.getText()).substring(0, (output.getText()).length() - 1));
            } else {
                if (!value.equals("Pi") && !(value.equals(".") && point)) output.setText(output.getText() + value);
                if (value.equals(".") && point == false) point = true;
            }
        }
    }
    public void OperatorPress(ActionEvent event)
    {
        point=false;
        piCount=0;
        String value =((Button)event.getSource()).getText();
        if(!value.equals("CE") && mathError) return;
        if(value.equals("CE"))
        {
            mathError=false;
            number=0.0;
            output.setText("0");
            operator="";
            flag=false;
            return;
        }
        else if(value.equals("="))
        {
            flag=true;
            switch (operator)
            {
                case "+": number+= Double.parseDouble(output.getText());
                    break;
                case "-": number-= Double.parseDouble(output.getText());
                    break;
                case "*": number*= Double.parseDouble(output.getText());
                    break;
                case "/":
                    if(Double.parseDouble(output.getText())==0)
                    {
                        output.setText("Math Error");
                        mathError=true;
                        return;
                    }
                    else number/=Double.parseDouble(output.getText());
                    break;

            }
            output.setText(""+number);
        }
        else
        {
            flag=false;
           number= Double.parseDouble(output.getText());
           output.setText("0");
           operator= ((Button)event.getSource()).getText();
        }
    }
}
