package com.example.app4g.webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.petani.MenuUtama;

import im.delight.android.webview.AdvancedWebView;

public class Kios extends AppCompatActivity implements AdvancedWebView.Listener {

    String TEST_PAGE_URL = "http://kios.kartupetaniberjaya.com/";
    private AdvancedWebView mWebView;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kios);

        requestAppPermissions();

        pDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        //pDialog.setCancelable(false);

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.setGeolocationEnabled(false);
        mWebView.setMixedContentAllowed(true);
        mWebView.setCookiesEnabled(true);
        mWebView.setThirdPartyCookiesEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                pDialog.setMessage("Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(pDialog!=null){
                    pDialog.dismiss();
                }
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //Toast.makeText(WebViews.this, title, Toast.LENGTH_SHORT).show();
                // hideDialog();
            }

        });

        mWebView.addHttpHeader("X-Requested-With", "");
        mWebView.loadUrl(TEST_PAGE_URL);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        pDialog.setMessage("Please wait...");
        pDialog.show();
        mWebView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(String url) {
        pDialog.dismiss();
        mWebView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Toast.makeText(Kios.this, "onPageError(errorCode = "+errorCode+",  description = "+description+",  failingUrl = "+failingUrl+")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        //Toast.makeText(WebViews.this, "onDownloadRequested(url = "+url+",  suggestedFilename = "+suggestedFilename+",  mimeType = "+mimeType+",  contentLength = "+contentLength+",  contentDisposition = "+contentDisposition+",  userAgent = "+userAgent+")", Toast.LENGTH_LONG).show();

        if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
            // download successfully handled
            //AdvancedWebView.handleDownload(this, url, suggestedFilename);
            Toast.makeText(getApplicationContext(), "Berhasil didownload", Toast.LENGTH_LONG).show();
        }
        else {
            // download couldn't be handled because user has disabled download manager app on the device
        }
    }

    @Override
    public void onExternalPageRequest(String url) {
        Toast.makeText(Kios.this, "onExternalPageRequest(url = "+url+")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        Intent a = new Intent(Kios.this, MenuUtama.class);
        startActivity(a);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1000); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }
}
