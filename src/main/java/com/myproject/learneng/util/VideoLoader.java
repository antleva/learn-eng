package com.myproject.learneng.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class VideoLoader {

    public static byte[] parseVideo(String text, int index) throws IOException {
                String addWord = text.replaceAll("\\s", "%20");
                Document document = Jsoup.connect("https://getyarn.io/yarn-find?text=" + addWord).get();
                Element div = document.getElementsByAttributeValue("class", "pure-gx").first().child(index);
                Element ahref = div.select("div.card.tight.bg-w > a").first();
                String url = ahref.attr("href").substring(11);
                HttpURLConnection inputStream = (HttpURLConnection) new URL("https://y.yarn.co/" + url+ ".mp4").openConnection();
                inputStream.addRequestProperty("User-Agent", "Mozilla/5.0");
                byte [] bytes = inputStream.getInputStream().readAllBytes();
            return bytes;
    }
}
