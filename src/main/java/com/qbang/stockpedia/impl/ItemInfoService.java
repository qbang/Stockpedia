package com.qbang.stockpedia.impl;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.http.client.utils.URIBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service("ItemInfoService")
public class ItemInfoService {
	private static HashSet<String> codeSet = new HashSet<>();
	private static HashMap<String, Integer> infoMap = new HashMap<>();
	private final String codeURL = "https://www.ktb.co.kr/trading/popup/itemPop.jspx";
	private String infoURL = "http://asp1.krx.co.kr/servlet/krx.asp.XMLSise?code=";
	
	// 컨테이너가 빈 객체 생성할 때 종목코드를 가져와서 static에 등록 -> 추후 타임 스케줄러 이용
	private ItemInfoService() throws IOException {
		codeSet = getItemCode();
	}
	
	// KTB투자증권에서 제공하는 종목코드조회에서 종목코드가져오기
	public HashSet<String> getItemCode() throws IOException {
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
	
	// 종목코드로 한국거래소에 주식정보요청 
	public String getItemInfo() throws IOException, URISyntaxException{
		String line = null;
		StringBuffer buffer = new StringBuffer();
		
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http");
		builder.setHost("asp1.krx.co.kr");
		builder.setPath("/servlet/krx.asp.XMLSise");
		long time1 = System.currentTimeMillis();
		for(String key : codeSet) {
			try {
				builder.addParameter("code", key);
				URL url = builder.build().toURL();
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(5000);
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				while ((line = in.readLine()) != null) {
					buffer.append(line);
				}
				in.close();
			}catch(Exception e) {
				
			}
		}
		long time2 = System.currentTimeMillis();
		System.out.println("ttttttttttTT"+(time2-time1));
		return buffer.toString();
	}
	
	// String 정보를 json으로 파싱
	public HashMap<String, Integer> parseItemInfo(String xmlString) {
		JSONArray jsonArray = XML.toJSONObject(xmlString).getJSONArray("stockprice");
		
		for(int i=0; i<jsonArray.length(); i++) {
			JSONObject originStockInfo = (JSONObject) jsonArray.get(i);
			JSONObject detailStockInfo = originStockInfo.getJSONObject("TBL_StockInfo");
			
			if(!detailStockInfo.getString("CurJuka").equals("")) {
				String jongName = detailStockInfo.getString("JongName");
				String curJuka = detailStockInfo.getString("CurJuka");
			
				System.out.println("이얏호 "+jongName+": "+curJuka);
				infoMap.put(jongName, Integer.parseInt(curJuka));
			}
		}
		return infoMap;
	}
}
