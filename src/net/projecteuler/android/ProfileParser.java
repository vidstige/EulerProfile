package net.projecteuler.android;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProfileParser extends DefaultHandler {
	
	private StringBuffer sb = new StringBuffer();
	
	private String solved = "0";
	
	public ProfileParser()
	{
	}
	
	public String getSolved()
	{
		return solved;
	}
	
	@Override
	public void startElement(String uri, String name, String qName, Attributes atts) throws SAXException
	{	
	}

	@Override
	public void endElement(String uri, String name, String qName)
	{
		if (name.equals("solved"))
		{
			solved = sb.toString();
		}
		
		sb = new StringBuffer();
	}
	
	@Override
	public void characters(char characters[], int start, int length)
	{
		sb.append(characters, start, length);
	}

	public Profile getProfile() {
		return new Profile(Integer.parseInt(solved));
	}
}
