package com.example.melobit;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class MusicFragment extends Fragment {

    private ImageView stopOrPause,backSong,forSong;
    private TextView currentTime,totalDuration ;
    private SeekBar playerSeekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    private TextView songTitle ;
    private TextView fullnameArtist ;
    private TextView DLcount ;
    private TextView RelaseDate ;
    private ImageView coverImg;

    private int seekForwardTime = 10 * 1000; // default 10 second
    private int seekBackwardTime = 10 * 1000; // default 10 second

    private String URL ="https://s1.pr3m.ir/Music/1399/4/001/0/Sasy%20-%20Gentleman.mp3";
    private String title ;
    private String date ;
    private String downloadCount ;
    private String picAddress ;
    private String fullName ;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stopOrPause = view.findViewById(R.id.stopOrPauseSong);
        backSong = view.findViewById(R.id.backwardSong);
        forSong = view.findViewById(R.id.forwardSong);
        currentTime = view.findViewById(R.id.currentTime);
        totalDuration = view.findViewById(R.id.totalDurationTime);
        playerSeekbar = view.findViewById(R.id.seekBar_luminosite);
        songTitle =  view.findViewById(R.id.songName);
        fullnameArtist =  view.findViewById(R.id.artistName);
        DLcount =  view.findViewById(R.id.downloadCounts);
        RelaseDate = view.findViewById(R.id.date);
        coverImg =view.findViewById(R.id.artistImage);

        URL = this.getArguments().getString("URL");
        title = this.getArguments().getString("title");
        date = this.getArguments().getString("date");
        downloadCount = this.getArguments().getString("downloadCount");
        picAddress = this.getArguments().getString("picAddress");
        fullName = this.getArguments().getString("fullName");

        songTitle.setText(title);
//      TODO String to Date
//      TODO Lyrics Request
        RelaseDate.setText(date);
        Picasso.get().load(picAddress).into(coverImg);
        DLcount.setText(downloadCount);
        fullnameArtist.setText(fullName);

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
                    stopOrPause.setImageResource(R.drawable.ic_baseline_pause_24);
                    prepareMediaPlayer();
                    mediaPlayer.start();
                    updateSeekBar();
                }
            }
        });

        forSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardSong();
            }
        });

        backSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rewindSong();
            }
        });

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


    public void rewindSong() {
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (currentPosition - seekBackwardTime >= 0) {
                mediaPlayer.seekTo(currentPosition - seekBackwardTime);
            } else {
                mediaPlayer.seekTo(0);
            }
        }
    }
    public void forwardSong() {
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (currentPosition + seekForwardTime <= mediaPlayer.getDuration()) {
                mediaPlayer.seekTo(currentPosition + seekForwardTime);
            } else {
                mediaPlayer.seekTo(mediaPlayer.getDuration());
            }
        }
    }
    private void prepareMediaPlayer() {
        try {
            mediaPlayer.setDataSource(URL);

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
        int seconds = (int)((milliSeconds % (1000 * 60 *60)) % (1000 * 60) / 1000);

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music,container,false);
    }
}
