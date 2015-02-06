package com.master.xml.parser;

import com.master.bovespa.entity.User;
import com.master.exception.MasterException;

public class XMLParserTest {

	public XMLParserTest() {
	}
	
	public static void main(String[] args) throws MasterException {
		XMLParser.parser("<user name=\"vilmar\"/>", User.class);
	}

}
