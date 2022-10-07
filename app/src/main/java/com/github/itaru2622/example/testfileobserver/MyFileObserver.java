package com.github.itaru2622.example.testfileobserver;

import android.os.Build;
import android.os.FileObserver;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class MyFileObserver extends FileObserver
{
    private Map<String,Object> conf = new HashMap<String,Object>();
    private TextView console = null;

    public MyFileObserver(@NonNull File file)                  { super(file);   }
    public MyFileObserver(@NonNull File file, int mask)        { super(file, mask); }
    public MyFileObserver(@NonNull List<File> files)           { super(files); }
    public MyFileObserver(@NonNull List<File> files, int mask) { super(files, mask); }

    public MyFileObserver setConf(@NonNull String key, Object val)
    {
        switch (key)
        {
            case "console":console = (TextView) val; break;
        }
        conf.put(key, val);
        return this;
    }
    public Object getConf(@NonNull String key) { return conf.get(key);}

    @Override
    public void onEvent(int i, @Nullable String str)
    {
        String hexStr = int2hexString(i);
        String codeStr = code2hexString(i);

        String msg = String.format("int(hex): %s  string: %s (%s)", hexStr, str, codeStr);
        if(str != null && false == str.startsWith(".")) { msg += " ** "; }
        Log.e("MyFileObserver", msg);
        if(console == null) return;

        console.append("MyFileObserver: " + msg +"\n");
    }

    public String int2hexString(int code) { return String.format("0x%08X",code);}

    public String code2hexString(int code)
    {
        String str = "";

        if((code & FileObserver.ACCESS) != 0)        { str += "ACCESS "; }
        if((code & FileObserver.ATTRIB) != 0)        { str += "ATTRIB "; }
        if((code & FileObserver.CLOSE_NOWRITE) != 0) { str += "CLOSE_NOWRITE "; }
        if((code & FileObserver.CLOSE_WRITE) != 0)   { str += "CLOSE_WRITE "; }
        if((code & FileObserver.CREATE) != 0)   { str += "CREATE "; }
        if((code & FileObserver.DELETE) != 0)   { str += "DELETE "; }
        if((code & FileObserver.DELETE_SELF) != 0)   { str += "DELETE_SELF "; }
        if((code & FileObserver.MODIFY) != 0)   { str += "MODIFY "; }
        if((code & FileObserver.MOVED_FROM) != 0)   { str += "MOVED_FROM "; }
        if((code & FileObserver.MOVE_SELF) != 0)   { str += "MOVE_SELF "; }
        if((code & FileObserver.MOVED_TO) != 0)   { str += "MOVED_TO "; }
        if((code & FileObserver.OPEN) != 0)   { str += "OPEN "; }

        return str;
    }
}
