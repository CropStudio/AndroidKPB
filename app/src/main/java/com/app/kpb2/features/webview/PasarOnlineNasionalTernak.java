package com.app.kpb2.features.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.features.petani.MenuUtama;

public class PasarOnlineNasionalTernak extends AppCompatActivity implements AdvancedWebView.Listener{
    String TEST_PAGE_URL = "https://pip7.cloud-host.id/";
    private AdvancedWebView mWebView;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasar_online_nasional_ternak);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Ubah kebutuhan saprotan");
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        pDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        //pDialog.setCancelable(false);

        mWebView = findViewById(R.id.webview);
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
        Toast.makeText(PasarOnlineNasionalTernak.this, "onPageError(errorCode = "+errorCode+",  description = "+description+",  failingUrl = "+failingUrl+")", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(PasarOnlineNasionalTernak.this, "onExternalPageRequest(url = "+url+")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        Intent a = new Intent(PasarOnlineNasionalTernak.this, MenuUtama.class);
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
}
