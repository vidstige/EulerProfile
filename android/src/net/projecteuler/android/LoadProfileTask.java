package net.projecteuler.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class LoadProfileTask extends AsyncTask<String, Void, Profile> {

	private ProfileListener listener;

	public LoadProfileTask(ProfileListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	protected Profile doInBackground(String... profileNames) {
		for (String profileName : profileNames)
		{
			try {
				URI uri = new URI("http://projecteuler.net/profile/"+profileName+".txt");
				BufferedHttpEntity entity = fetch(uri);
				
				String contents = slurp(entity.getContent());
				String[] parts = contents.split(",");
				
				int solved = Integer.parseInt(parts[3]);
				int level = Integer.parseInt(parts[4]);
				return new Profile(parts[0], parts[1], parts[2], solved, level);
								
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}
	
	protected void onPostExecute(Profile result) {
		listener.onProfile(result);
    }
	
	private BufferedHttpEntity fetch(URI uri) throws ClientProtocolException, IOException
	{
		HttpGet httpRequest = new HttpGet(uri);		
		//httpRequest.addHeader("Accept-Encoding", "gzip");
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httpRequest);
		HttpEntity entity = response.getEntity();
		return new BufferedHttpEntity(entity);
	}
	
	private String slurp(InputStream inputStream) throws IOException
	{
		StringBuilder builder = new StringBuilder();
        char[] buffer = new char[8192];
        Reader reader = new InputStreamReader(inputStream);
        int read;
        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
            builder.append(buffer, 0, read);
        }
        return builder.toString();
	}
}
