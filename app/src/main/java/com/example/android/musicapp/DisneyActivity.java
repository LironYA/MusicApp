package com.example.android.musicapp;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisneyActivity extends AppCompatActivity {
    //All song and pictures rights are reserved to Disney
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_activity);
        final Button playButton = (Button) findViewById(R.id.play);
        final Button pauseButton = (Button) findViewById(R.id.pause);
        final Button stopButton = (Button) findViewById(R.id.stop);
        final TextView song_text = findViewById(R.id.song_name);
        final ImageView picture = (ImageView) findViewById(R.id.picture);

        final ArrayList<Music> musicLibrary = new ArrayList<Music>();
        musicLibrary.add(new Music("Disney - Moana", "How far I'll go"));
        musicLibrary.add(new Music("Disney - The little mermaid", "Part of your world"));
        musicLibrary.add(new Music("Disney - Lion King", "Circle Of Life"));

        MusicAdapter adapter = new MusicAdapter(this, musicLibrary);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.moana);
        final MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.mermaid);
        final MediaPlayer mediaPlayer3 = MediaPlayer.create(this, R.raw.lionking);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (mediaPlayer1 != null && position == 0) {
                    mediaPlayer1.start();
                    song_text.setText(getResources().getString(R.string.moana));
                    picture.setImageDrawable(getResources().getDrawable(R.drawable.moana));
                    Toast.makeText(DisneyActivity.this, getResources().getString(R.string.moana_playing), Toast.LENGTH_SHORT).show();
                }
                if (mediaPlayer2 != null && position == 1) {
                    mediaPlayer2.start();
                    song_text.setText(getResources().getString(R.string.mermaid));
                    Toast.makeText(DisneyActivity.this, getResources().getString(R.string.mermaid_playing), Toast.LENGTH_SHORT).show();
                    picture.setImageDrawable(getResources().getDrawable(R.drawable.mermaid));
                }
                if (mediaPlayer3 != null && position == 2) {
                    song_text.setText(getResources().getString(R.string.lionking));
                    mediaPlayer3.start();
                    Toast.makeText(DisneyActivity.this, getResources().getString(R.string.lionking_playing), Toast.LENGTH_SHORT).show();
                    picture.setImageDrawable(getResources().getDrawable(R.drawable.thelionking));
                }
                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position == 0) {
                            mediaPlayer1.start();
                            mediaPlayer2.stop();
                            mediaPlayer3.stop();
                        } else if (position == 1) {
                            mediaPlayer1.stop();
                            mediaPlayer2.start();
                            mediaPlayer3.stop();
                        } else if (position == 2) {
                            mediaPlayer1.stop();
                            mediaPlayer2.stop();
                            mediaPlayer3.start();
                        }
                    }
                });
                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position == 0) {
                            mediaPlayer1.pause();
                        } else if (position == 1) {
                            mediaPlayer2.pause();
                        } else if (position == 2) {
                            mediaPlayer3.pause();
                        }
                    }
                });
                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayer1.isPlaying() || mediaPlayer2.isPlaying() || mediaPlayer3.isPlaying()) {
                            mediaPlayer1.stop();
                            mediaPlayer2.stop();
                            mediaPlayer3.stop();
                            DisneyActivity.this.finish();
                            Intent myIntent = new Intent(DisneyActivity.this, DisneyActivity.class);
                            DisneyActivity.this.startActivity(myIntent);
                        } else {
                            DisneyActivity.this.finish();
                        }
                    }
                });
            }
        });
    }
}

