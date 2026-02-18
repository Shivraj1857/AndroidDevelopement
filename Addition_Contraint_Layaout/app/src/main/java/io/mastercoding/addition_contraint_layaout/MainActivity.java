package io.mastercoding.addition_contraint_layaout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTv, solutionTv;

    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    private MaterialButton buttonPlus, buttonDivide, buttonEquals, buttonAC, buttonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View root = findViewById(R.id.main);
        if (root != null) {
            ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        resultTv   = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        button0 = bind(R.id.button_0);
        button1 = bind(R.id.button_1);
        button2 = bind(R.id.button_2);
        button3 = bind(R.id.button_3);
        button4 = bind(R.id.button_4);
        button5 = bind(R.id.button_5);
        button6 = bind(R.id.button_6);
        button7 = bind(R.id.button_7);
        button8 = bind(R.id.button_8);
        button9 = bind(R.id.button_9);

        buttonPlus   = bind(R.id.button_plus);
        buttonDivide = bind(R.id.button_divide);
        buttonEquals = bind(R.id.button_equals);
        buttonAC     = bind(R.id.button_AC);
        buttonC      = bind(R.id.button_C);

        resultTv.setText("0");
        solutionTv.setText("");
    }

    /** Helper: sets click listener and returns the button */
    private MaterialButton bind(int id) {
        MaterialButton btn = findViewById(id);
        if (btn != null) btn.setOnClickListener(this);
        return btn;
    }

    @Override
    public void onClick(View v) {
        if (!(v instanceof MaterialButton)) return;

        MaterialButton btn = (MaterialButton) v;
        String t = btn.getText().toString();           // The label on the button
        String expr = solutionTv.getText().toString(); // Current expression

        switch (t) {
            case "AC":
                solutionTv.setText("");
                resultTv.setText("0");
                return;

            case "C":
                if (!expr.isEmpty()) {
                    expr = expr.substring(0, expr.length() - 1); // backspace safely
                }
                solutionTv.setText(expr);
                return;

            case "=":
                String res = evaluate(expr); // Evaluate only + and /
                resultTv.setText(res);
                return;

            default:
                // Allow only digits 0–9 and +, /
                if (isDigit(t)) {
                    expr = expr + t;
                } else if (isOperator(t)) { // + or /
                    // Do not start with operator
                    if (expr.isEmpty()) return;
                    // Replace last operator if user presses operator twice
                    if (endsWithOperator(expr)) {
                        expr = expr.substring(0, expr.length() - 1);
                    }
                    expr = expr + t;
                } else {
                    // Ignore any other input (not expected in this UI)
                    return;
                }
                solutionTv.setText(expr);
        }
    }

    // ----------------- Helpers -----------------

    private boolean isDigit(String s) {
        return s.length() == 1 && "0123456789".contains(s);
    }

    private boolean isOperator(String s) {
        return "+/".contains(s) && s.length() == 1;
    }

    private boolean endsWithOperator(String s) {
        if (s.isEmpty()) return false;
        char c = s.charAt(s.length() - 1);
        return c == '+' || c == '/';
    }

    private String sanitize(String expr) {
        // Remove trailing operators like "12+" or "10/"
        while (!expr.isEmpty() && endsWithOperator(expr)) {
            expr = expr.substring(0, expr.length() - 1);
        }
        return expr;
    }

    // --------------- Core evaluation: + and / only ---------------


    private String evaluate(String expr) {
        try {
            expr = sanitize(expr);
            if (expr.isEmpty()) return "0";

            // Split by '+'; each part may be a/b/c...
            String[] addParts = expr.split("\\+");
            double sum = 0.0;

            for (String part : addParts) {
                part = part.trim();
                if (part.isEmpty()) continue;
                double term = evalDivisionChain(part); // handle "a/b/c" = ((a/b)/c)
                sum += term;
            }

            // Pretty formatting: 12.0 -> "12"
            if (Math.floor(sum) == sum) {
                return String.valueOf((long) sum);
            } else {
                return String.valueOf(sum);
            }

        } catch (ArithmeticException ae) {
            // Specifically catch divide by zero
            return "Err";
        } catch (Exception e) {
            // Any other parse/format error
            return "Err";
        }
    }


    private double evalDivisionChain(String s) {
        String[] parts = s.split("/");
        if (parts.length == 0) throw new IllegalArgumentException("Empty term");

        double value = parseNumber(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            double denom = parseNumber(parts[i]);
            try {
                if (denom == 0.0) {
                    throw new ArithmeticException("Division by zero");
                }
                value = value / denom;
            } catch (ArithmeticException e) {
                // Re-throw to be handled in evaluate()
                throw e;
            }
        }
        return value;
    }

    private double parseNumber(String token) {
        token = token.trim();
        if (token.isEmpty()) throw new IllegalArgumentException("Empty number");
        // Only digits are allowed in this UI, so Double.parseDouble is safe
        return Double.parseDouble(token);
    }
}
