package com.example.a3coin_connection_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int gameState[] = {2,2,2,2,2,2,2,2,2};

    int winningPositions [][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        Log.i("info", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellowcoin);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.redcoin);

                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningposition : winningPositions) {

                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2) {

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";
                    }
                   // Toast.makeText(this, winner + " Won the match !", Toast.LENGTH_LONG).show();

                    Button buttonView = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById((R.id.winnerTextView));

                    winnerText.setText(winner + " Won the match ! ");
                    buttonView.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);

                }
            }
        }
    }
    public void playAgain (View view) {

        Button buttonView = (Button) findViewById(R.id.playAgainButton);
        Log.i("info","Button Pressed");
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        buttonView.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        Log.i("grid","Grid layout");

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }
        for (int i = 0; i < gameState.length; i++) {

            gameState [i] = 2;

        }
        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}