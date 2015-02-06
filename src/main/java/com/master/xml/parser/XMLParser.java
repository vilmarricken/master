package com.master.xml.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.master.exception.MasterException;

public class XMLParser {
	
	private static final XMLParser PARSER = new XMLParser();
	
	private  Map<Class<?>, ParserObject> parsers = new HashMap<Class<?>, ParserObject>();

	private XMLParser() {
	}
	
	public static <T> Object parser(String xml, Class<T> target) throws MasterException{
		return parser(new StringReader(xml), target);
	}
	
	public static <T> Object parser(Reader xml, Class<T> target) throws MasterException{
		SAXBuilder builder = new SAXBuilder();
        try {
			Document jdom = builder.build(xml);
			Element element = jdom.getRootElement();
			System.out.println(element.getName());
		} catch (JDOMException | IOException e) {
			throw new MasterException(e);
		}
        return null;
	}

}
