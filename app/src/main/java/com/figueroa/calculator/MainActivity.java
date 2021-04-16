package com.figueroa.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itis.libs.parserng.android.expressParser.MathExpression;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    // Operation to be interpreted
    private String operation;
    private String currentNumber;
    private String ans;

    // TextViews
    private TextView operationText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Some initializations
        operation = "";
        currentNumber = "";
        ans = "";

        // Buttons
        Button ans, c, plus, nine, eight, seven, power, minus,
                six, five, four, sqrt, multiply, three, two, one, module, division,
                zero, dot, equals, e, pi, factorial, openingParenthesis, closingParenthesis,
                sin, cos, tan, ln, log;

        // Obtaining texts from view
        operationText = findViewById(R.id.operation_input);
        resultText = findViewById(R.id.result_output);

        // Obtaining cards from view
        ans = findViewById(R.id.ans_operation);
        c = findViewById(R.id.c_operation);
        plus = findViewById(R.id.plus_operation);
        nine = findViewById(R.id.nine_button);
        eight = findViewById(R.id.eight_button);
        seven = findViewById(R.id.seven_button);
        power = findViewById(R.id.power_operation);
        minus = findViewById(R.id.minus_operation);
        six = findViewById(R.id.six_button);
        five = findViewById(R.id.five_button);
        four = findViewById(R.id.four_button);
        sqrt = findViewById(R.id.sqrt_operation);
        multiply = findViewById(R.id.multiply_operation);
        three = findViewById(R.id.three_button);
        two = findViewById(R.id.two_button);
        one = findViewById(R.id.one_button);
        module = findViewById(R.id.module_operation);
        division = findViewById(R.id.division_operation);
        zero = findViewById(R.id.zero_button);
        dot = findViewById(R.id.dot_button);
        equals = findViewById(R.id.equal_operation);
        e = findViewById(R.id.e_number);
        pi = findViewById(R.id.pi_number);
        factorial = findViewById(R.id.factorial_operation);
        openingParenthesis = findViewById(R.id.opening_parenthesis);
        closingParenthesis = findViewById(R.id.closing_parenthesis);
        sin = findViewById(R.id.sin_operation);
        cos = findViewById(R.id.cos_operation);
        tan = findViewById(R.id.tan_operation);
        ln = findViewById(R.id.ln_operation);
        log = findViewById(R.id.log_operation);

        // Setting the listeners to each card
        ans.setOnClickListener(this);
        ans.setOnLongClickListener(this);
        c.setOnClickListener(this);
        c.setOnLongClickListener(this);
        plus.setOnClickListener(this);
        nine.setOnClickListener(this);
        eight.setOnClickListener(this);
        seven.setOnClickListener(this);
        power.setOnClickListener(this);
        minus.setOnClickListener(this);
        six.setOnClickListener(this);
        five.setOnClickListener(this);
        four.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        multiply.setOnClickListener(this);
        three.setOnClickListener(this);
        two.setOnClickListener(this);
        one.setOnClickListener(this);
        module.setOnClickListener(this);
        division.setOnClickListener(this);
        zero.setOnClickListener(this);
        dot.setOnClickListener(this);
        equals.setOnClickListener(this);
        e.setOnClickListener(this);
        pi.setOnClickListener(this);
        factorial.setOnClickListener(this);
        openingParenthesis.setOnClickListener(this);
        closingParenthesis.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        ln.setOnClickListener(this);
        log.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("result", resultText.getText().toString());
        outState.putString("operation", operationText.getText().toString());
        if (!ans.trim().isEmpty()) outState.putString("ans", ans);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operation = savedInstanceState.getString("operation");

        if (!operation.equals("Type in your operation here!")) operationText.setText(operation);
        else operation = "";

        ans = savedInstanceState.getString("ans", "");
        resultText.setText(savedInstanceState.getString("result", "0.0"));
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // Numbers...
            case R.id.nine_button:
                operation += "9";
                operationText.setText(operation);
                break;
            case R.id.eight_button:
                operation += "8";
                operationText.setText(operation);
                break;
            case R.id.seven_button:
                operation += "7";
                operationText.setText(operation);
                break;
            case R.id.six_button:
                operation += "6";
                operationText.setText(operation);
                break;
            case R.id.five_button:
                operation += "5";
                operationText.setText(operation);
                break;
            case R.id.four_button:
                operation += "4";
                operationText.setText(operation);
                break;
            case R.id.three_button:
                operation += "3";
                operationText.setText(operation);
                break;
            case R.id.two_button:
                operation += "2";
                operationText.setText(operation);
                break;
            case R.id.one_button:
                operation += "1";
                operationText.setText(operation);
                break;
            case R.id.zero_button:
                if (!currentNumber.equals("0")) {
                    operation += "0";
                    operationText.setText(operation);
                }
                break;
            case R.id.dot_button:
                if (!currentNumber.contains(".")) {
                    operation += ".";
                    operationText.setText(operation);
                }
                break;

                // Special numbers...
            case R.id.e_number:
                operation += "e";
                operationText.setText(operation);
                break;
            case R.id.pi_number:
                operation += "π";
                operationText.setText(operation);
                break;

                // Parenthesis
            case R.id.opening_parenthesis:
                operation += "(";
                operationText.setText(operation);
                break;
            case R.id.closing_parenthesis:
                operation += ")";
                operationText.setText(operation);
                break;

                // Factorial...
            case R.id.factorial_operation:
                operation += "!";
                operationText.setText(operation);
                break;

                // Deleting characters one by one...
            case R.id.c_operation:
                if (!operation.isEmpty()) {
                    operation = operation.substring(0, operation.length() - 1);
                    operationText.setText(operation);
                } if (operation.length() == 0) {
                    operationText.setText("Type in your operation here!");
                }
                break;

                // Basic operations...
            case R.id.plus_operation:
                if (!operation.trim().isEmpty() && !operation.trim().endsWith("+")) {
                    operation += " + ";
                    operationText.setText(operation);
                }
                break;
            case R.id.minus_operation:
                if (!operation.trim().isEmpty() && !operation.trim().endsWith("-")) {
                    operation += " - ";
                    operationText.setText(operation);
                }
                break;
            case R.id.division_operation:
                if (!operation.trim().isEmpty() && !operation.trim().endsWith("÷") ) {
                    operation += " ÷ ";
                    operationText.setText(operation);
                }
                break;
            case R.id.multiply_operation:
                if (!operation.trim().isEmpty() && !operation.trim().endsWith("×")) {
                    operation += " × ";
                    operationText.setText(operation);
                }
                break;

                // Not so basic operations
            case R.id.module_operation:
                if (!operation.trim().isEmpty() && !operation.trim().endsWith("%")) {
                    operation += " % ";
                    operationText.setText(operation);
                }
                break;
            case R.id.sqrt_operation:
                if (!operation.trim().endsWith("√")) {
                    operation += "√";
                    operationText.setText(operation);
                }
                break;
            case R.id.power_operation:
                if (!operation.trim().isEmpty() && !operation.trim().endsWith("^")) {
                    operation += "^";
                    operationText.setText(operation);
                }
                break;

                // Ooh boy...
            case R.id.sin_operation:
                operation += "sin(";
                operationText.setText(operation);
                break;
            case R.id.cos_operation:
                operation += "cos(";
                operationText.setText(operation);
                break;
            case R.id.tan_operation:
                operation += "tan(";
                operationText.setText(operation);
                break;
            case R.id.ln_operation:
                operation += "ln(";
                operationText.setText(operation);
                break;
            case R.id.log_operation:
                operation += "log(";
                operationText.setText(operation);
                break;

                // Pushing ANS to use the last value...
            case R.id.ans_operation:
                if (!ans.trim().isEmpty()) {
                    operation += ans;
                    operationText.setText(operation);
                }
                break;
                // Hitting equal to solve the operation...
            case R.id.equal_operation:
                if (!operation.isEmpty()) {
                    try {
                        MathExpression expr = new MathExpression(operation);
                        ans = expr.solve();
                        if (ans.equals("SYNTAX ERROR")) {
                            ans = "";
                            Toast.makeText(getApplicationContext(),
                                    "Oops! Something went wrong... " +
                                            "Check your operation and try again please!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            resultText.setText(ans);
                            showNotification("Result ready!",
                                    "The result was: " + ans, operation);
                        }
                        operation = "";
                    } catch (Exception oops) {
                        Toast.makeText(getApplicationContext(),
                                "Oops! Something went wrong... " +
                                        "Check your operation and try again please!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        }
        if (!operation.isEmpty()) {
            // TODO special operations sin, cos, tan, etc.
            LinkedList<String> numbers = new LinkedList<>(
                    Arrays.asList(operation.split("[+×\\-÷()]")));
            LinkedList<String> toRemove = new LinkedList<>();

            for (String number : numbers) {
                if (number.trim().isEmpty()) {
                    toRemove.add(number);
                }
            }

            if (toRemove.size() > 0) numbers.removeAll(toRemove);
            if (numbers.size() > 0) currentNumber = numbers.get(numbers.size() - 1).trim();
            // Log.w("Funny Valentine", numbers + "\n" + currentNumber);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onLongClick(View v) {
        boolean res = false;
        if (v.getId() == R.id.c_operation) {
            operation = "";
            currentNumber = "";
            operationText.setText("Type in your operation here!");
            resultText.setText("0.0");
            res = true;
        } else if (v.getId() == R.id.ans_operation) {
            AlertDialog alert = createAlert(this, "Value of ANS",
                    !ans.trim().isEmpty() ? "ANS is equal to " + ans :
                            "ANS has nothing stored yet!");
            alert.show();

            res = true;
        }
        return res;
    }

    /**
     *
     *  showNotification
     * @param title String: The title of the notification
     * @param message String: The content of the notification
     * @param operation String: The operation that was performed
     *
     */
    void showNotification(String title, String message, String operation) {
        // Creating the notification manager...
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Checking the Android version in which the app is running...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Creating channel...
            NotificationChannel channel = new NotificationChannel("RESULT_NOTIFICATION",
                    "RESULT_NOTIFICATION_CHANNEL", NotificationManager.IMPORTANCE_DEFAULT);

            // Adding a description...
            channel.setDescription("This is the channel for the notification" +
                    " that will show the result");

            // Creating the channel...
            manager.createNotificationChannel(channel);
        }

        // Building and customising the notification...
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(), "RESULT_NOTIFICATION")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("I processed the following" +
                        "operation: " + operation + " and I got this " + ans + " as a result!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Creating the intent that will thow the notification...
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // ... and adding it to the 'queue'
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0 ,
                new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

        // Notifying the user...
        builder.setContentIntent(pendingIntent);
        manager.notify(0, builder.build());
    }


    /**
     *
     * createAlert
     * @param activity AppCompatActivity: The activity that is currently being displayed
     * @param title String: Title of the alert
     * @param message String: Message of the alert
     * @return AlertDialog
     *
     */
    public AlertDialog createAlert(AppCompatActivity activity, String title, String message) {
        // Creating the builder...
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // Customising the alert...
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok!", null);

        // Creating and returning it...
        return builder.create();
    }
}