package com.qbang.stockpedia.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service("RequestStockService")
public class RequestStockService {
	private final String codeURL = "https://www.ktb.co.kr/trading/popup/itemPop.jspx";
	private final String infoURL = "http://asp1.krx.co.kr/servlet/krx.asp.XMLSise?code=";
	
	private JSONArray stockInfoArr = new JSONArray();
	
	// KTB투자증권에서 제공하는 종목코드조회에서 종목코드가져오기
	public HashSet<String> getItemCode() throws IOException {
		HashSet<String> codeSet = new HashSet<String>();
		Document doc = Jsoup.connect(codeURL).get();
		Elements elem = doc.select("select[name=\"StockS\"]");
		Iterator<Element> iter1 = elem.select("option").iterator();
		while(iter1.hasNext()) {
			Element iter2 = iter1.next();
			String code = iter2.attr("value");
			codeSet.add(code);
		}
		return codeSet;
	}
	
	// 종목코드로 한국거래소에 주식정보요청 template 생성
	public JSONArray getItemInfo(HashSet<String> codeSet){
		for(String key : codeSet) {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setReadTimeout(30000);
			RestTemplate template = new RestTemplate(factory);
			getResponseBody(template, key);
		}
		return stockInfoArr;
	}
	
	// template 받아서 요청 후 실패하면 다시 호출
	private void getResponseBody(RestTemplate template, String key) {
		try {
			String body = template.getForObject(infoURL+key, String.class);
			JSONObject object = XML.toJSONObject(body);
			stockInfoArr.put(object);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}

