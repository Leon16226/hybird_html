package com.rxjava.administrator.hybird;

import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

public class Hybird_activity extends AppCompatActivity implements View.OnClickListener {

    @butterknife.BindView(R.id.java_to_js_default)
    Button javaToJsDefault;
    @butterknife.BindView(R.id.java_to_js_spec)
    Button javaToJsSpec;
    @butterknife.BindView(R.id.webView)
    BridgeWebView webView;
    @butterknife.BindView(R.id.guideline)
    Guideline guideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybird_activity);
        butterknife.ButterKnife.bind(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        setWebView();
    }

    private void setWebView() {
        // webView.loadUrl("http://139.196.35.30:8080/OkHttpTest/apppackage/test.html");//加载url
        // webView.loadUrl("content://com.ansen.webview/sdcard/test.html"); //加载sd卡文件

        webView.getSettings().setJavaScriptEnabled(true);//添加js支持
        webView.loadUrl("file:///android_asset/hybird.html");//加载asset文件夹下html
        //跳转页面不用外置游览器
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        //默认接收
        webView.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String msg = "默认接收到js的数据：" + data;
                Toast.makeText(Hybird_activity.this, msg, Toast.LENGTH_LONG).show();

                function.onCallBack("java默认接收完毕，并回传数据给js"); //回传数据给js
            }
        });
        //指定接收 submitFromWeb 与js保持一致
        webView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String msg = "指定接收到js的数据：" + data;
                Toast.makeText(Hybird_activity.this,msg, Toast.LENGTH_LONG).show();

                function.onCallBack("java指定接收完毕，并回传数据给js"); //回传数据给js
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @butterknife.OnClick({R.id.java_to_js_default, R.id.java_to_js_spec, R.id.webView, R.id.guideline})
    public void onViewClicked(View view) {
        //为了解耦交给控制器
        ClikListenerControl clikListenerControl = new ClikListenerControl(this);
        clikListenerControl.onclick(view);
    }
}
