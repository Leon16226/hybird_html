package com.rxjava.administrator.hybird;

import android.app.Activity;
import android.support.constraint.Guideline;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * Created by Administrator on 2019/2/14.
 */

public class ClikListenerControl {

   Activity activity;
    private Button javaToJsDefault;
    private Button javaToJsSpec;
    private BridgeWebView webView;
    private Guideline guideline;



    public ClikListenerControl(Activity activity) {
        this.activity = activity;
        javaToJsDefault = (Button) activity.findViewById(R.id.java_to_js_default);
        javaToJsSpec = (Button) activity.findViewById(R.id.java_to_js_spec);
        webView = (BridgeWebView) activity.findViewById(R.id.webView);
        guideline = (Guideline) activity.findViewById(R.id.guideline);
    }

    public void onclick(View v){
        switch (v.getId()) {
            case R.id.java_to_js_default:
                //默认接收
                webView.send("发送数据给js默认接收", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        //处理js回传的数据
                        Toast.makeText(activity, data, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.java_to_js_spec:
                //指定接收参数 functionInJs
                webView.callHandler("functionInJs", "发送数据给js指定接收", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        //处理js回传的数据
                        Toast.makeText(activity, data, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.webView:
                break;
            case R.id.guideline:
                break;
        }
    }



}
