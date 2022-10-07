package com.github.itaru2622.example.testfileobserver;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.FileObserver;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.File;

public class MainActivity extends AppCompatActivity
{

    private TextView console = null;
    private MyFileObserver fileObserver = null;
    private static final File CAMERA_FOLDER = new File(""+android.os.Environment.getExternalStorageDirectory().toString() +"/DCIM/Camera");
    private static final int mask = FileObserver.ALL_EVENTS;
    private static final int mask4write = FileObserver.ALL_EVENTS - FileObserver.ACCESS - FileObserver.CLOSE_NOWRITE - FileObserver.OPEN; // ALL but not for READ(ACCESS|OPEN|CLOSE_NOWRITE)


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        console = findViewById(R.id.Console);
        console.setMovementMethod(new ScrollingMovementMethod()); // enable scrolling

        console.append( String.format("FileObserver\n  Folder:" + CAMERA_FOLDER.getAbsolutePath() + "\n  mask: 0x%08X\n", mask) );

        fileObserver = new MyFileObserver(CAMERA_FOLDER, mask).setConf("console", console);
//      fileObserver = new MyFileObserver(CAMERA_FOLDER, mask4write).setConf("console", console);

/*
        fileObserver = new FileObserver(CAMERA_FOLDER, mask) {
            @Override
            public void onEvent(int i, @Nullable String str)
            {
                console.append("FileObserver  int:" + i + "  string: " + str + "\n");
                Log.e("FileObserver", "int:" +i + " string:" + str);
            }
        };
*/
    }

    public void onClickStartBtn(View view)
    {
        Log.e("StartBtn", "clicked");
        console.append("startBtn Clicked\n");

        fileObserver.startWatching();

    }
    public void onClickStopBtn(View view)
    {
        Log.e("StopBtn", "clicked");
        console.append("stopBtn Clicked\n");
        fileObserver.stopWatching();
    }
    public void onClickClearBtn(View view)
    {
        Log.e("ClearBtn", "clicked");
        console.setText("");
        fileObserver.stopWatching();
    }

}
