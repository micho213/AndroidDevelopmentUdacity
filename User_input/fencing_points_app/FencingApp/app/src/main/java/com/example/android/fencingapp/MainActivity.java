package com.example.android.fencingapp;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    // code for timer used from tutorial  https://www.c-sharpcorner.com/article/creating-stop-watch-android-application-tutorial/
    TextView timer;
    Button start, pause, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timeDisplay);
        start = findViewById(R.id.startButton);
        pause = findViewById(R.id.stopButton);
        reset = findViewById(R.id.clearButton);
        handler = new Handler();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                reset.setEnabled(false);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
            }
        });
    }
    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            timer.setText("" + Minutes + ":"  + String.format("%02d", Seconds));
            handler.postDelayed(this, 0);
        }
    };

    int teamAScore = 0;
    int teamBScore = 0;
    int numberOfRedCardsA = 0;
    int numberOfYellowCardsA = 0;
    int numberOfRedCardsB = 0;
    int numberOfYellowCardsB = 0;

    public void addPointTeamA(View View) {
        teamAScore += 1;
        displayPointsTeamA(teamAScore);
    }

    public void addPointTeamB(View View) {
        teamBScore += 1;
        displayPointsTeamB(teamBScore);
    }

    public void CorrectThePointsTeamA(View View) {
        // points are often evaluated on the fly and correcting the score is important when decision is changed
        teamAScore -= 1;
        // prevents it from going negative
        if (teamAScore < 0) {
            teamAScore += 1;
        }
        displayPointsTeamA(teamAScore);
    }
    public void CorrectThePointsTeamB(View View) {
        // points are often evaluated on the fly and correcting the score is important when decision is changed
        teamBScore -= 1;
        // prevents it from going negative
        if (teamBScore < 0) {
            teamBScore += 1;
        }
        displayPointsTeamB(teamBScore);
    }

    public void doubleHit(View View) {
        teamAScore += 1;
        teamBScore += 1;
        displayPointsTeamA(teamAScore);
        displayPointsTeamB(teamBScore);
    }

    public void clearStats(View View) {
        // resets scores
        teamAScore = 0;
        teamBScore = 0;
        displayPointsTeamA(0);
        displayPointsTeamB(0);

        // resets cards
        numberOfRedCardsA = 0;
        numberOfYellowCardsA = 0;
        numberOfRedCardsB = 0;
        numberOfYellowCardsB = 0;
        // 100 is used to change to "", theres never going to be 100 cards...
        displayRedCardTeamA(100);
        displayRedCardTeamB(100);
        displayYellowCardTeamA(100);
        displayYellowCardTeamB(100);

        MillisecondTime = 0L;
        StartTime = 0L;
        TimeBuff = 0L;
        UpdateTime = 0L;
        Seconds = 0;
        Minutes = 0;
        MilliSeconds = 0;
        timer.setText("00:00");
    }

    public void displayPointsTeamA(int score) {
        TextView scoreView = findViewById(R.id.teamADisplay);
        scoreView.setText(String.valueOf(score));
    }

    public void displayPointsTeamB(int score) {
        TextView scoreView = findViewById(R.id.teamBDisplay);
        scoreView.setText(String.valueOf(score));
    }

    // cards
    // Team A
    public void redCardTeamA(View View) {
        numberOfRedCardsA += 1;
        displayRedCardTeamA(numberOfRedCardsA);
    }

    public void displayRedCardTeamA(int numberOfRedCardsA) {
        Button card = findViewById(R.id.redCardTeamA);
        if (numberOfRedCardsA == 100) {
            card.setText(String.valueOf(""));
        } else {
            card.setText(String.valueOf(numberOfRedCardsA));
        }
    }

    public void yellowCardTeamA(View View) {
        numberOfYellowCardsA += 1;
        displayYellowCardTeamA(numberOfYellowCardsA);
    }

    public void displayYellowCardTeamA(int numberOfYellowCardsA) {
        Button card = findViewById(R.id.yellowCardTeamA);
        if (numberOfYellowCardsA == 100) {
            card.setText(String.valueOf(""));
        } else {
            card.setText(String.valueOf(numberOfYellowCardsA));
        }
    }
    // TeamB
    public void redCardTeamB(View View) {
        numberOfRedCardsB += 1;
        displayRedCardTeamB(numberOfRedCardsB);
    }

    public void displayRedCardTeamB(int numberOfRedCardsB) {
        Button card = findViewById(R.id.redCardTeamB);
        if (numberOfRedCardsB == 100) {
            card.setText(String.valueOf(""));
        } else {
            card.setText(String.valueOf(numberOfRedCardsB));
        }
    }

    public void yellowCardTeamB(View View) {
        numberOfYellowCardsB += 1;
        displayYellowCardTeamB(numberOfYellowCardsB);
    }

    public void displayYellowCardTeamB(int numberOfYellowCardsB) {
        Button card = findViewById(R.id.yellowCardTeamB);
        if (numberOfYellowCardsB == 100) {
            card.setText(String.valueOf(""));
        } else {
            card.setText(String.valueOf(numberOfYellowCardsB));
        }
    }
}