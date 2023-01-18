package com.example.melobit;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MusicFragment extends Fragment {

    private ImageView stopOrPause;
    private TextView currentTime,totalDuration ;
    private SeekBar playerSeekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        stopOrPause = view.findViewById(R.id.stopOrPauseSong);
        currentTime = view.findViewById(R.id.currentTime);
        totalDuration = view.findViewById(R.id.totalDurationTime);
        playerSeekbar = view.findViewById(R.id.seekBar_luminosite);
        mediaPlayer = new MediaPlayer();

        playerSeekbar.setMax(100);

        stopOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    stopOrPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    stopOrPause.setImageResource(R.drawable.ic_baseline_pause_24);
                    updateSeekBar();
                }
            }
        });

        prepareMediaPlayer();
        playerSeekbar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                currentTime.setText(milliSecondsToTime(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playerSeekbar.setSecondaryProgress(percent);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playerSeekbar.setProgress(0);
                stopOrPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                currentTime.setText("00:00");
                totalDuration.setText("00:00");
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });
    }


    private void prepareMediaPlayer() {
        try {
            // TODO mediaPlayer.setDataSource("");
            mediaPlayer.prepare();
            totalDuration.setText(milliSecondsToTime(mediaPlayer.getDuration()));
        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            currentTime.setText(milliSecondsToTime(currentDuration));
        }
    };
    private void updateSeekBar(){
        if (mediaPlayer.isPlaying()){
            playerSeekbar.setProgress((int)(((float) mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration())* 100));
            handler.postDelayed(updater,1000);
        }
    }

    private String milliSecondsToTime(long milliSeconds){
        String timerString = "";
        String secondsString;
        int hours = (int)(milliSeconds / (1000 * 60 *60));
        int minutes = (int)(milliSeconds % (1000 * 60 *60)) / (1000 * 60);
        int seconds = (int)((milliSeconds / (1000 * 60 *60)) % (1000 * 60) / 1000);
        
        if (hours > 0){
            timerString = hours + ":";
        }
        if (seconds < 10){
            secondsString = "0" + seconds ;
        } else{
            secondsString = "" + seconds ;
        }
        timerString = timerString + minutes + " : " + secondsString ;
        return timerString ;
    }
}
