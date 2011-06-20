package net.projecteuler.android;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import android.os.AsyncTask;

public class LoadProfileTask extends AsyncTask<String, Void, Profile> {

	private static SAXParser xmlParser;
	private ProfileListener listener;

	public LoadProfileTask(ProfileListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	protected Profile doInBackground(String... profileNames) {
		for (String profileName : profileNames)
		{
			ProfileParser parser = new ProfileParser();
			try {
				URI uri = new URI("http://projecteuler.net/profile/"+profileName+".xml");
				BufferedHttpEntity entity = fetch(uri);
				
				getParser().parse(entity.getContent(), parser);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return parser.getProfile();
		}
		return null;
	}
	
	protected void onPostExecute(Profile result) {
		listener.onProfile(result);
    }
	
	private BufferedHttpEntity fetch(URI uri) throws ClientProtocolException, IOException
	{
		HttpGet httpRequest = new HttpGet(uri);		
		httpRequest.addHeader("Accept-Encoding", "gzip");
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httpRequest);
		HttpEntity entity = response.getEntity();
		return new BufferedHttpEntity(entity);
	}
	
	private static SAXParser getParser() throws ParserConfigurationException, SAXException {
		if (xmlParser == null)
		{
			SAXParserFactory spf = SAXParserFactory.newInstance();
			xmlParser = spf.newSAXParser();
		}
		return xmlParser;
	}
}
