package com.bu.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.annotation.SuppressLint;

import android.widget.Button;
import android.widget.EditText;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.parser.ParseException;
import com.ezylang.evalex.data.EvaluationValue;




public class MainActivity extends AppCompatActivity {
    EditText display;
    Button btn1;

    boolean sonuclandirildi=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(v -> {
            if (getString(R.string.tap_here).equals(display.getText().toString())) {
                display.setText("");
            }
        });
    }

    public void btngoster(View view) {
        if(sonuclandirildi){
            display.setText("");
            sonuclandirildi=false;
        }
        switch (view.getId()) {
            case R.id.btnsil:
                display.setText("");
                break;
            case R.id.btnparan:
                addBrackets();
                break;

            case R.id.btnesit:

                EvaluationValue result = null;
                try {
                    Expression expression = new Expression(display.getText().toString());
                    result = expression.evaluate();
                    display.setText(result.getNumberValue().toString());
                } catch (EvaluationException | ParseException e) {
                    display.setText("Error");
                }
                break;

            case R.id.btnyuzde:
                updateDisplay("%");
                break;
            case R.id.btnbolme:
                updateDisplay("÷");
                break;
            case R.id.btncarpi:
                updateDisplay("×");
                break;
            case R.id.btnarti:
                updateDisplay("+");
                break;
            case R.id.btneksi:
                updateDisplay("-");
                break;
            case R.id.btn9:
                updateDisplay("9");
                break;
            case R.id.btn8:
                updateDisplay("8");
                break;
            case R.id.btn7:
                updateDisplay("7");
                break;
            case R.id.btn6:
                updateDisplay("6");
                break;
            case R.id.btn5:
                updateDisplay("5");
                break;
            case R.id.btn4:
                updateDisplay("4");
                break;
            case R.id.btn3:
                updateDisplay("3");
                break;
            case R.id.btn2:
                updateDisplay("2");
                break;
            case R.id.btn1:
                updateDisplay("1");
                break;
            case R.id.btn0:
                updateDisplay("0");
                break;
            case R.id.btnnokta:
                updateDisplay(".");
                break;


        }


    }






    private void addBrackets() {
        String textDisplay = display.getText().toString();
        int cusorPos = display.getSelectionStart();
        int countBrackets = 0 ;
        for ( int i=0 ; i < textDisplay.length(); i++) {
            if(textDisplay.substring(i,i+1).equalsIgnoreCase("(")) countBrackets++;
            if(textDisplay.substring(i,i+1).equalsIgnoreCase(")")) countBrackets--;
        }
        String lastCharOfTextDisplay = textDisplay.substring(textDisplay.length()-1);
        if(countBrackets==0|| lastCharOfTextDisplay.equals("(")) updateDisplay("(");
        else if (countBrackets>0 && !lastCharOfTextDisplay.equals(")")) updateDisplay(")");


    }











    @SuppressLint("ResourceType")
    private void updateDisplay(String addCharToDisplay) {
        int cursorPos = display.getSelectionStart();
        if (getString(R.id.tap_here).equals(display.getText().toString())) {
            display.setText(addCharToDisplay);
        } else {
            String oldDisplay = display.getText().toString();
            String leftsideofDisplay = oldDisplay.substring(0, cursorPos);
            String rightsideofDisplay = oldDisplay.substring(cursorPos);
            String newtext = leftsideofDisplay + addCharToDisplay + rightsideofDisplay;
            display.setText(newtext);

        }

        display.setSelection(cursorPos + 1);
    }




}
