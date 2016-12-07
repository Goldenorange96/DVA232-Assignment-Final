package goldenorange.dva232_assignment1;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Integer result = 0, number1 = -1 , number2 = -1;
    boolean firstInput = false, cleared = false;
    String currentOperator = null;

    private OnClickListener buttonListener = new OnClickListener()
    {
            public void onClick(View v) {
                Button pressedButton = (Button) v;
                String buttonText = pressedButton.getText().toString();
                TextView textField = (TextView) findViewById(R.id.number_textView);
                String currentText = textField.getText().toString();
                Context context = getApplicationContext();

                if(currentText.equals("") && !buttonText.matches("\\d+(?:\\.\\d+)?"))
                {
                    currentText = currentText.concat("");
                    textField.setText(currentText);
                }

                else if(buttonText.matches("\\d+(?:\\.\\d+)?")) //if the button pressed is a digit then just add it the text window.
                {
                    if(firstInput)
                    {
                        textField.setText("");
                        currentText = textField.getText().toString();
                        currentText = currentText.concat(buttonText);
                        textField.setText(currentText);
                        firstInput = false;
                    }
                    else
                    {
                        currentText = currentText.concat(buttonText);
                        textField.setText(currentText);
                    }
                }

                else if(!buttonText.matches("\\d+(?:\\.\\d+)?"))
                {
                    if(currentOperator == null && !buttonText.equals("="))
                    {
                        currentOperator = buttonText;
                        if (number1 == -1) //If the first number is not set then add everything in the textfield to number1.
                        {
                            number1 = Integer.parseInt(currentText);
                            firstInput = false;
                        }
                        currentText = currentText.concat(buttonText);
                        textField.setText(currentText);
                    }

                    else if(buttonText.equals("=") && number1 != -1)
                    {
                        if(currentText.substring(currentText.indexOf(currentOperator) + 1).matches("\\d+(?:\\.\\d+)?"))
                        {
                            String substr = currentText.substring(currentText.indexOf(currentOperator) + 1);
                            number2 = Integer.parseInt(substr);
                            result = calculate(currentOperator);
                            currentText = result.toString();
                            textField.setText(currentText);
                            number1 = -1;
                            number2 = -1;
                            currentOperator = null;
                            firstInput = true;
                        }
                        else
                        {
                            currentText = currentText.concat("");
                            textField.setText(currentText);
                        }
                    }

                    else if(!buttonText.equals("=") && number1 != -1)
                    {
                        if(currentText.substring(currentText.indexOf(currentOperator) + 1).matches("\\d+(?:\\.\\d+)?"))
                        {
                            String substr = currentText.substring(currentText.indexOf(currentOperator) + 1);
                            number2 = Integer.parseInt(substr);
                            number1 = calculate(currentOperator);
                            currentText = number1.toString();
                            currentOperator = buttonText;
                            currentText = currentText.concat(buttonText);
                            textField.setText(currentText);
                        }

                        else
                        {
                            currentText = currentText.concat("");
                            textField.setText(currentText);
                        }
                    }

                    else
                    {
                        currentText = currentText.concat("");
                        textField.setText(currentText);
                    }
                }
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn0 = (Button)findViewById(R.id.btn_0);
        Button btn1 = (Button)findViewById(R.id.btn_1);
        Button btn2 = (Button)findViewById(R.id.btn_2);
        Button btn3 = (Button)findViewById(R.id.btn_3);
        Button btn4 = (Button)findViewById(R.id.btn_4);
        Button btn5 = (Button)findViewById(R.id.btn_5);
        Button btn6 = (Button)findViewById(R.id.btn_6);
        Button btn7 = (Button)findViewById(R.id.btn_7);
        Button btn8 = (Button)findViewById(R.id.btn_8);
        Button btn9 = (Button)findViewById(R.id.btn_9);
        Button btn_equal = (Button)findViewById(R.id.btn_equal);
        Button btn_add = (Button)findViewById(R.id.btn_add);
        Button btn_sub = (Button)findViewById(R.id.btn_sub);
        Button btn_multi = (Button)findViewById(R.id.btn_multi);
        Button btn_div = (Button)findViewById(R.id.btn_div);

        btn0.setOnClickListener(buttonListener);
        btn1.setOnClickListener(buttonListener);
        btn2.setOnClickListener(buttonListener);
        btn3.setOnClickListener(buttonListener);
        btn4.setOnClickListener(buttonListener);
        btn5.setOnClickListener(buttonListener);
        btn6.setOnClickListener(buttonListener);
        btn7.setOnClickListener(buttonListener);
        btn8.setOnClickListener(buttonListener);
        btn9.setOnClickListener(buttonListener);
        btn_equal.setOnClickListener(buttonListener);
        btn_add.setOnClickListener(buttonListener);
        btn_sub.setOnClickListener(buttonListener);
        btn_multi.setOnClickListener(buttonListener);
        btn_div.setOnClickListener(buttonListener);

    }


    //Calculate function that makes the code more readable.
    public int calculate(String type)
    {
        switch (type)
        {
            case "+": //if type is one, then perform addition
                return number1+number2;

            case "-": //if type is two, then perform subtraction
                return number1-number2;

            case "x": //if type is three, then perform multiplication
                return number1*number2;

            case "/": //if type is four, then perform division
                if(number2 == 0)
                {
                    Context context = getApplicationContext();
                    Toast newToast = Toast.makeText(context, "Cannot divide by zero", Toast.LENGTH_SHORT);
                    newToast.show();
                    return 0;
                }

                else
                {
                    return number1/number2;
                }

            default:
                return 0;
        }
    }
}
