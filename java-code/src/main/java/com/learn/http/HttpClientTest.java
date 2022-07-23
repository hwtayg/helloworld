package com.learn.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class HttpClientTest {

    public static void main(String[] args) throws Exception {


        List<String> words = new ArrayList<>();
        words.add("a");
        words.add("ab");
        Function.identity();
        Collections.sort(words, comparing(String::length));
        HttpClientBuilder builder = HttpClients.custom();
//        SSLContext sslContext = getIgnoreStrategy();
        SSLContext sslContext = getIgnoreTrustManager();
        builder.setSSLContext(sslContext);
        CloseableHttpClient client = builder.build();
        HttpGet get = new HttpGet("https://expired.badssl.com/");
//        HttpGet get = new HttpGet("https://baidu.com");
        try (CloseableHttpResponse resp = client.execute(get)) {
            System.out.println(resp.getStatusLine());
        }
    }

    private static SSLContext getIgnoreStrategy() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                return false;
            }
        }).build();
        return sslContext;
    }

    private static SSLContext getIgnoreTrustManager() throws Exception {
        SSLContext sc = SSLContext.getInstance("TLS");
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return;
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
}
