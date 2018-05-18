package com.example.mushfiq.igarden;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private Date LastUpdated;
    Boolean DataUpdated = false;
    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tempValue = findViewById(R.id.TempValue);
        final TextView moistValue = findViewById(R.id.MoistValue);
        final TextView humidityValue = findViewById(R.id.HumidityValue);
        final TextView suggestionBox = findViewById(R.id.suggestion);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference QueryRef = database.getReference("sadiq/sensors");
        final DatabaseReference UpdateRef = database.getReference("sadiq/command");

        final String Message1="Suggested Temperature :  25\u2103";
        final String Message2="Temperature Level is Good";
        final String Message3="Suggested Moisture :  70%";
        final String Message4="Moisture Level is Good";


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(10000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Date Now = Calendar.getInstance().getTime();
                                if(!DataUpdated);
                                else if(Now.getTime() - LastUpdated.getTime() < 5*60*1000);
                                else{
                                    String Suggestion="           Connection Lost\n   Please check your device";
                                    suggestionBox.setText(Suggestion);
                                    suggestionBox.setTextColor(Color.RED);
                                    suggestionBox.setTextSize(25);
                                }
                            }
                        });
                    } catch (Exception e) {}
                }
            }
        }).start();

        QueryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QueryData data = dataSnapshot.getValue(QueryData.class);
                tempValue.setText(data.getTemp() + "\u2103");
                moistValue.setText(data.getMoist() + "%");
                humidityValue.setText(data.getHumidity() + "%");
                LastUpdated = Calendar.getInstance().getTime();
                DataUpdated = true;


                String Suggestion="";
                if(data.getTemp()< 25) Suggestion+=Message1; else Suggestion+=Message2;
                Suggestion+="\n ";
                if(data.getMoist()<70) Suggestion+=Message3; else Suggestion+=Message4;
                suggestionBox.setText(Suggestion);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final TextView TempControlLabel = findViewById(R.id.LabelControlTemp);
        final TextView TempPromptLabel = findViewById(R.id.PromptControlTemp);
        final TextView TempLabel = findViewById(R.id.SeekValueTemp);
        final SeekBar TempSeek = findViewById(R.id.TempSlider);
        final Switch TempSwitch = findViewById(R.id.SwitchModeTemp);

        TempSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                final TextView TempLabel = findViewById(R.id.SeekValueTemp);
                if(!TempSwitch.isChecked()) TempLabel.setText(String.valueOf(progress) + " mins");
                else TempLabel.setText(String.valueOf(progress) + "\u2103");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        TempSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    TempPromptLabel.setText("Switch bulb until");
                    TempLabel.setText("0 \u2103");

                }
                else {
                    TempPromptLabel.setText("Switch bulb on for");
                    TempLabel.setText("0 mins");
                }
                TempSeek.setProgress(0);
            }
        });


        final TextView WaterControlLabel = findViewById(R.id.LabelControlWater);
        final TextView WaterPromptLabel = findViewById(R.id.PromptControlWater);
        final TextView WaterLabel = findViewById(R.id.SeekValueWater);
        final SeekBar WaterSeek = findViewById(R.id.WaterSlider);
        final Switch WaterSwitch = findViewById(R.id.SwitchModeWater);

        WaterSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                final TextView WaterLabel = findViewById(R.id.SeekValueWater);
                if(!WaterSwitch.isChecked()) WaterLabel.setText(String.valueOf(progress) + " secs");
                else WaterLabel.setText(String.valueOf(progress) + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        WaterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    WaterPromptLabel.setText("Switch water until");
                    WaterLabel.setText("0 %");
                }
                else {
                    WaterPromptLabel.setText("Switch water on for");
                    WaterLabel.setText("0 secs");
                }
                WaterSeek.setProgress(0);
            }
        });

        final Button PostButton = findViewById(R.id.POST);
        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempVal = TempSeek.getProgress();
                int moistVal = WaterSeek.getProgress();
                int tempMode = TempSwitch.isChecked() ? 1 : 0;
                int waterMode = WaterSwitch.isChecked() ? 1 : 0;

                UpdateRef.child("lightMode").setValue(tempMode);
                UpdateRef.child("lightValue").setValue(tempVal);
                UpdateRef.child("waterMode").setValue(waterMode);
                UpdateRef.child("waterValue").setValue(moistVal);
            }
        });
    }
}
