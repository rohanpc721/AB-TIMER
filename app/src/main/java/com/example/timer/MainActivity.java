package com.example.timer;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Boolean counterIsAcive = false;
    TextView timerTextView;
    SeekBar timerSeekBar;
    Button goButton;
    Button goButton1;
    CountDownTimer timer;
    MediaPlayer mplayer = null;
    int flag;
    String secondString=null;
    ImageView birdImageView;
    int secondsleft=0;
    int r=0;
    int sum=0;
    int s;
    public void buttonClicked1(View view1) {

        final ImageView eggImageView = findViewById(R.id.eggImageView);
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
       timerTextView.setTextColor(Color.parseColor("black"));
        timerTextView.setText("00:00:30");
        if (s == 1) {
            birdImageView.animate().alpha(0).setDuration(0);
            birdImageView.clearAnimation();
            eggImageView.animate().alpha(1).setDuration(1000);
            timerTextView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(1000);
            mplayer.stop();
            goButton.setText("START");
            counterIsAcive = false;
        }
    }

    public void buttonClicked(View view) {
        timerTextView.setTextColor(Color.parseColor("black"));

        if(s==1){
            timerTextView.setTextColor(Color.parseColor("black"));
            timerTextView.setText("00:00:30");
            timerSeekBar.setProgress(30);
        }
        s=0;
        birdImageView.animate().alpha(0).setDuration(0);
        timerTextView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(1000);
        final ImageView eggImageView = findViewById(R.id.eggImageView);


        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        final Animation animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);


        if (counterIsAcive) {



            timerSeekBar.setEnabled(true);
            timer.cancel();
            eggImageView.animate().alpha(1).setDuration(1000);
            eggImageView.clearAnimation();
            birdImageView.clearAnimation();
            birdImageView.animate().alpha(0).setDuration(100);
            goButton.setText("START");
            counterIsAcive=false;
            if(flag==1) {
                mplayer.stop();
                flag=0;
            }
            timerTextView.setTextColor(Color.parseColor("black"));

        } else{
            birdImageView.animate().alpha(0).setDuration(100);
            eggImageView.animate().alpha(1).setDuration(100);

            birdImageView.clearAnimation();
            eggImageView.startAnimation(animShake);
            counterIsAcive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP");
             sum=timerSeekBar.getProgress();

            timer= new CountDownTimer(sum * 1000, 1000) {


                @Override
                public void onTick(long l) {

                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    timerTextView.setText("TIME UP!!!!");
                    timerTextView.setTextColor(Color.parseColor("red"));
                    timerTextView.animate().scaleX(4.0f).scaleY(4.0f).setDuration(10000);
                    mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    flag=1;
                    eggImageView.clearAnimation();
                    eggImageView.animate().alpha(0).setDuration(100);

                    birdImageView.animate().alpha(1).setDuration(100);
                    birdImageView.startAnimation(animBlink);
                    s=1;
                }
            }.start();
        }

    }
    public void updateTimer(int secondsleft) {


        int p1 = secondsleft % 60;
        int p2 = secondsleft / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        if (p1 <= 9) {
            secondString = "0" + Integer.toString(p1);
        } else {
            secondString = Integer.toString(p1);
        }

        if (p3 <= 9) {
            if (p2 <= 9) {
                timerTextView.setText("0" + Integer.toString(p2) + ":" + "0" + Integer.toString(p3) + ":" + secondString);

            } else {
                timerTextView.setText(Integer.toString(p2) + ":" + "0" + Integer.toString(p3) + ":" + secondString);
            }
        }




        else {
            if(p2<=9){
                timerTextView.setText("0"+Integer.toString(p2) + ":" + Integer.toString(p3) + ":" +  secondString);
            }
            else {
                timerTextView.setText(Integer.toString(p2) + ":" + Integer.toString(p3) + ":" +  secondString);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        birdImageView = findViewById(R.id.birdImageView);
        birdImageView.animate().alpha(0).setDuration(100);
        timerSeekBar=findViewById(R.id.timerSeekBar);
        timerTextView=findViewById(R.id.countdownTextView);
        goButton=findViewById(R.id.goButton);
        goButton1=findViewById(R.id.goButton1);
        goButton1.setText("RESET");
        timerSeekBar.setMax(86400);
        timerSeekBar.setProgress(30);
        timerTextView.setText("00:00:30");
        timerTextView.setTextColor(Color.parseColor("black"));
        timerTextView.setTypeface(null, Typeface.BOLD_ITALIC);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
