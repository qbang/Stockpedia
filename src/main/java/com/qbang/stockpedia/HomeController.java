package com.qbang.stockpedia;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qbang.stockpedia.impl.ItemCodeService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name="ItemCodeService")
	private ItemCodeService itemCodeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {
		HashMap<String, String> codeMap = itemCodeService.getItemCode();
		model.addAttribute("codeMap", codeMap);
		return "home";
	}
	
}
