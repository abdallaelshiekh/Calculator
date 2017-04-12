package com.abdalla.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etResult;
    private EditText etNewNumber;
    private TextView tvOperation;

    // variables will hold operands and type of operation
    private Double operand1 = null;
    private String pendingOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNewNumber = (EditText)findViewById(R.id.newNumber);
        etResult = (EditText)findViewById(R.id.result);
        tvOperation = (TextView)findViewById(R.id.textView);

        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        Button buttonDot = (Button)findViewById(R.id.dotButton);



        Button buttonEquals = (Button)findViewById(R.id.equalButton);
        Button buttonMinus = (Button)findViewById(R.id.minusButton);
        Button buttonPlus = (Button)findViewById(R.id.plusButton);
        Button buttonDivide = (Button)findViewById(R.id.divideButton);
        Button buttonMultiply = (Button)findViewById(R.id.multiplyButton);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button)v;
                etNewNumber.append(button.getText().toString());

            }
        };
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button)v;
                String op = button.getText().toString();
                String value = etNewNumber.getText().toString();
                try {
                    Double vDouble = Double.valueOf(value);
                    performOperation(vDouble,op);
                }catch (NumberFormatException ex){
                    etNewNumber.setText("");
                }
                pendingOperation = op;
                tvOperation.setText(pendingOperation);
            }
        };

        button0.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        buttonDot.setOnClickListener(onClickListener);



        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);

    }

    private void performOperation(Double value, String op) {
       if(null == operand1){
           operand1 = value;
       }else {
           if (pendingOperation.equals("=")){
               pendingOperation = op;
           }
           switch (pendingOperation){
               case "=":
                   operand1 = value;
                   break;
               case "/":
                   Log.d("MainActivity",value.toString());
                   if(value == 0){
                       operand1 = 0.0;
                   }else {
                       operand1 /= value;
                   }
                   break;
               case "*":
                   operand1 *= value;
                   break;
               case "-":
                   operand1 -= value;
                   break;
               case "+":
                   operand1 += value;
                   break;
           }
       }
       etResult.setText(operand1.toString());
        etNewNumber.setText("");
    }



}
