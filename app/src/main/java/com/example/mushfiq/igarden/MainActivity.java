package com.example.mushfiq.igarden;

import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tempValue = findViewById(R.id.TempValue);
        final TextView moistValue = findViewById(R.id.MoistValue);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference QueryRef = database.getReference("sadiq/sensors");
        final DatabaseReference UpdateRef = database.getReference("sadiq/command");

        QueryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QueryData data = dataSnapshot.getValue(QueryData.class);
                tempValue.setText(data.getTemp() + "\u2103");
                moistValue.setText(data.getMoist() + "%");
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
