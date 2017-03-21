package at.sw2017.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class calculator extends AppCompatActivity implements View.OnClickListener {

    TextView numberView;
    int firstNumber;
    State state = State.INIT;

    enum  State { ADD, SUB, MUL, DIV, INIT, NUM}

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        switch (view.getId()) {
            case R.id.buttonAdd:
                clearNumberView();
                state = State.ADD;
                break;
            case R.id.buttonDiv:
                clearNumberView();
                state = State.DIV;
                break;
            case R.id.buttonMul:
                clearNumberView();
                state = State.MUL;
                break;
            case R.id.buttonSub:
                clearNumberView();
                state = State.SUB;
                break;
            case R.id.buttonEqual:
                calculateResult();
                state = State.INIT;
                break;
            case R.id.buttonClear:
                clearTextView();
                break;
            default:
                String recentNumber = numberView.getText().toString();
                if (state == State.INIT) {
                    recentNumber = "";

                    state = State.NUM;
                }
                recentNumber += clickedButton.getText().toString();
                numberView.setText(recentNumber);
        }
    }

    private  void calculateResult(){
        int secondNumber = 0;

       String tempString = numberView.getText().toString();

        if(!tempString.equals("")){
            secondNumber = Integer.valueOf(tempString);
        }
        int result;
        switch (state){
            case ADD:
                result = Calculations.doAddition(firstNumber,secondNumber);
            break;
            case SUB:
                result = Calculations.doSubtraction(firstNumber,secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber,secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber,secondNumber);
                break;
            default:
                result = secondNumber;
        }
        numberView.setText(Integer.toString(result));
    }

    public void setUpNumberButtonListener() {
        for (int i = 0; i <= 9; i++) {
            String buttonName = "button" + i;
            int id = getResources().getIdentifier(buttonName, "id",
                    R.class.getPackage().getName());
            Button button = (Button) findViewById(id);
            button.setOnClickListener(this);
        }
    }
    private  void clearTextView(){
        numberView.setText("0");
        firstNumber = 0;
        state = State.INIT;
    }

    private  void clearNumberView(){
        String tempString = numberView.getText().toString();
        if(!tempString.equals(("")))
            firstNumber = Integer.valueOf(tempString);
        numberView.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        Button buttonMultiply = (Button) findViewById(R.id.buttonMul);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonDivide = (Button) findViewById(R.id.buttonDiv);
        Button buttonComma = (Button) findViewById(R.id.buttonClear);
        Button buttonSubtract = (Button) findViewById(R.id.buttonSub);
        Button buttonC = (Button) findViewById(R.id.buttonClear);
        Button buttonResult = (Button) findViewById(R.id.buttonEqual);
        Button buttonZero = (Button) findViewById(R.id.button0);
        numberView = (TextView) findViewById(R.id.textView);

        buttonAdd.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonComma.setOnClickListener(this);
        buttonResult.setOnClickListener(this);
        buttonZero.setOnClickListener(this);
        setUpNumberButtonListener();
    }
}
