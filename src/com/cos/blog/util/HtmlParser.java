package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	public static String getContentYoutube(String content) {
		Document doc = Jsoup.parse(content);
		Elements aTags = doc.select("a");
		//System.out.println("aTags:" + aTags);
		// https://youtu.be/TgOu00Mf3kI
		// https://www.youtube.com/watch?v=yqtCGojXEpM
		for (Element aTag : aTags) {
			String href = aTag.attr("href");
			//System.out.println("href:" + href);
			//System.out.println("aTags:" + aTags);
			String youtubeId = null;
			if (href != null) {
				if (href.contains("https://youtu.be")) {
					String[] hrefArr = href.split("/");
					youtubeId = hrefArr[3];
					//System.out.println("youtubeId:" + youtubeId);
				} else if (href.contains("https://www.youtube.com")) {
					String[] hrefArr = href.split("=");
					youtubeId = hrefArr[1];
					System.out.println("youtubeId:" + youtubeId);
				}

				System.out.println("JSOUP 파싱 : Youtube : " + youtubeId);
				String video = "<br/><iframe src='http://www.youtube.com/embed/" + youtubeId
						+ "' width='400px' height='400px' frameborder='0' allowfullscreen></iframe>";
				System.out.println("video : " + video);
				aTag.after(video);
			}
		}
		return doc.toString();
	}

	public static String getContentPreview(String content) {

		Document doc = Jsoup.parse(content);
		Elements pTags = doc.select("p");

		for (Element pTag : pTags) {
			String text = pTag.text();
			if (text.length() > 0) {
				if (text.length() < 11) {
					return pTag.text();
				} else {
					return pTag.text().substring(0, 10) + "...";
				}
			}
		}
		return "내용 없음...";
	}

}
