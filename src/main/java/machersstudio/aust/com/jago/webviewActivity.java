package machersstudio.aust.com.jago;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class webviewActivity extends AppCompatActivity {



    private WebView wb;
    private ClickTouch clickTouch;
    public Activity activity;
    private ProgressDialog progDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        activity=this;
        progDialog=ProgressDialog.show(activity,"Loading","please wait",true);
        progDialog.setCancelable(true);
        wb=(WebView)findViewById(R.id.webView);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
       wb.getSettings().supportZoom();
        wb.getSettings().getBuiltInZoomControls();
        wb.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                progDialog.show();
                view.loadUrl(url);
                return true;
            }
            public void onPageFinished(WebView view ,final String url){
                progDialog.dismiss();
            }
        });
        wb.loadUrl(Constants.link);
    }
    public void onBackPressed(){
        this.startActivity(new Intent(webviewActivity.this,MainActivity.class));
        finish();
    }
}
