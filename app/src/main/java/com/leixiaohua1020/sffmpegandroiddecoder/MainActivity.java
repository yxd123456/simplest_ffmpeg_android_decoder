/**
 * ��򵥵Ļ���FFmpeg����Ƶ������-��׿
 * Simplest FFmpeg Android Decoder
 * 
 * ������ Lei Xiaohua
 * leixiaohua1020@126.com
 * �й���ý��ѧ/���ֵ��Ӽ���
 * Communication University of China / Digital TV Technology
 * http://blog.csdn.net/leixiaohua1020
 * 
 * �������ǰ�׿ƽ̨����򵥵Ļ���FFmpeg����Ƶ�������������Խ��������Ƶ���ݽ����YUV�������ݡ�
 * 
 * This software is the simplest decoder based on FFmpeg in Android. It can decode video stream
 * to raw YUV data.
 * 
 */
package com.leixiaohua1020.sffmpegandroiddecoder;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


	private EditText urlEdittext_input;
	private EditText urlEdittext_output;
	private Button startButton;
	private String inputurl;
	private String outputurl;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		AssetsUtil.copy(MainActivity.this);
        
		startButton = (Button) this.findViewById(R.id.button_start);
		urlEdittext_input= (EditText) this.findViewById(R.id.input_url);
		urlEdittext_output= (EditText) this.findViewById(R.id.output_url);
		
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0){

				String folderurl=Environment.getExternalStorageDirectory().getPath();
				
				String urltext_input=urlEdittext_input.getText().toString();
		        inputurl=folderurl+"/MP4/"+urltext_input;
		        
		        String urltext_output=urlEdittext_output.getText().toString();
		        outputurl=folderurl+"/MP4/"+urltext_output;
		        
		        Log.i("TT",inputurl);
		        Log.i("TT",outputurl);

				final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
				dialog.setMessage("START DECODING...");
				dialog.show();

		    	new Thread(new Runnable() {
					@Override
					public void run() {
						decode(inputurl,outputurl);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								dialog.dismiss();
								Toast.makeText(MainActivity.this, "DECODED...", Toast.LENGTH_SHORT).show();
							}
						});
					}
				}).start();
			}
		});
    }

    
    //JNI
    public native int decode(String inputurl, String outputurl);
    
    static{
    	System.loadLibrary("avutil-54");
    	System.loadLibrary("swresample-1");
    	System.loadLibrary("avcodec-56");
    	System.loadLibrary("avformat-56");
    	System.loadLibrary("swscale-3");
    	System.loadLibrary("postproc-53");
    	System.loadLibrary("avfilter-5");
    	System.loadLibrary("avdevice-56");
    	System.loadLibrary("sffdecoder");
    }
}
