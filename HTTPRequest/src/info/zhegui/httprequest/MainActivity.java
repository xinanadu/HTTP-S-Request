package info.zhegui.httprequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_http_get).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						http();
					}
				});

		findViewById(R.id.btn_https_get).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						https();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void http() {
		new Thread() {
			public void run() {
				String result = Utils.httpGet(MainActivity.this,
						"http://m.baidu.com");
			}
		}.start();
	}
	
	private void https() {
		new Thread() {
			public void run() {
				String result = Utils.httpsGet(MainActivity.this,
						"https://api.i952169.com.cn:8065/BKingAPI.ashx?__from=android&module=Public&action=SendAuthCode&type=0&PhoneNo=15507591767");
			}
		}.start();
	}
}
