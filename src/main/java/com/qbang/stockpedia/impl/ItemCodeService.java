package com.qbang.stockpedia.impl;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service("ItemCodeService")
public class ItemCodeService {
	public HashMap<String, String> getItemCode() throws IOException {
		HashMap<String, String> codeMap = new HashMap<>(); 
		String URL = "https://www.ktb.co.kr/trading/popup/itemPop.jspx";
		Document doc = Jsoup.connect(URL).get();
		Elements elem = doc.select("select[name=\"StockS\"]");
		Iterator<Element> iter1 = elem.select("option").iterator();
		while(iter1.hasNext()) {
			Element iter2 = iter1.next();
			String code = iter2.attr("value");
			String name = iter2.text();
			codeMap.put(code, name);
		}
		return codeMap;
	}
}
