package edu.washington.bbarron.lifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView loseText;
    private int p1Life;
    private int p2Life;
    private int p3Life;
    private int p4Life;
    private boolean p1Lost;
    private boolean p2Lost;
    private boolean p3Lost;
    private boolean p4Lost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1Life = 20;
        p2Life = 20;
        p3Life = 20;
        p4Life = 20;

        p1Lost = false;
        p2Lost = false;
        p3Lost = false;
        p4Lost = false;

        loseText = (TextView) findViewById(R.id.loseText);
        ViewGroup main = (ViewGroup) findViewById(R.id.main_layout);
        for (int i = 0; i < main.getChildCount(); i++) {
            LinearLayout player = (LinearLayout) main.getChildAt(i);
            TextView text = (TextView) player.getChildAt(0);
            text.setText("Player " + (i + 1) + " life total: 20");
        }
    }

    public void onClick(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        TextView playerLife = (TextView) parent.getChildAt(0);
        Button button = (Button) v;
        int change = 0;

        if (button.getText().equals("+")) {
            change = 1;
        } else if (button.getText().equals("+5")) {
            change = 5;
        } else if (button.getText().equals("-")) {
            change = -1;
        } else if (button.getText().equals("-5")) {
            change = -5;
        }

        if (parent.getTag().toString().equals("player1")) {
            p1Life += change;
            playerLife.setText("Player 1 life total: " + p1Life);
        } else if (parent.getTag().toString().equals("player2")) {
            p2Life += change;
            playerLife.setText("Player 2 life total: " + p2Life);
        } else if (parent.getTag().toString().equals("player3")) {
            p3Life += change;
            playerLife.setText("Player 3 life total: " + p3Life);
        } else if (parent.getTag().toString().equals("player4")) {
            p4Life += change;
            playerLife.setText("Player 4 life total: " + p4Life);
        }

        if (p1Life <= 0 || p2Life <= 0 || p3Life <= 0 || p4Life <= 0) {
            if (p1Life <= 0 && !p1Lost) {
                loseText.setText("Player 1 LOSES!");
                timedPost();
                p1Lost = true;
            }
            if (p2Life <= 0 && !p2Lost) {
                loseText.setText("Player 2 LOSES!");
                timedPost();
                p2Lost = true;
            }
            if (p3Life <= 0 && !p3Lost) {
                loseText.setText("Player 3 LOSES!");
                timedPost();
                p3Lost = true;
            }
            if (p4Life <= 0 && !p4Lost) {
                loseText.setText("Player 4 LOSES!");
                timedPost();
                p4Lost = true;
            }
        }
    }

    public void timedPost() {
        loseText.setVisibility(View.VISIBLE);
        loseText.postDelayed(new Runnable() {
            @Override
            public void run() {
                loseText.setVisibility(View.INVISIBLE);
            }
        }, 5000);
    }
}
