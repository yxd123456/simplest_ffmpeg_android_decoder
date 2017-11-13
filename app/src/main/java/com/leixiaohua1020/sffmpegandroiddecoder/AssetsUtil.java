package com.leixiaohua1020.sffmpegandroiddecoder;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asus on 2017/2/16.
 */

/**
 * http://www.open-open.com/lib/view/open1474425946759.html
 */
public class AssetsUtil {

    public static final String DIR1 = Environment.getExternalStorageDirectory().toString()+"/MP4";

    public static void copy(Context ctx){
        File file1 = new File(DIR1);
        if(!file1.exists()){
            file1.mkdir();
        }

        AssetManager manager = ctx.getAssets();
        try {
            String[] files = manager.list("files");
            for (int i = 0; i < files.length; i++) {
                InputStream is = manager.open("files/"+files[i]);
                FileOutputStream fos = new FileOutputStream(DIR1+"/"+files[i]);
                byte[] bytes = new byte[1024];
                int read = 0;
                while ((read = is.read(bytes))!=-1){
                    fos.write(bytes, 0, read);
                }
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
