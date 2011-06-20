package net.projecteuler.android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity implements ProfileListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String profile = preferences.getString("profile", null);

        profile = "vidstige";
        if (profile == null)
        {
        	setContentView(R.layout.noprofile);
        }
        else
        {
        	setContentView(R.layout.profile);
        	new LoadProfileTask(this).execute(profile);
        }
    }

	@Override
	public void onProfile(Profile p) {
		ImageView image = (ImageView)findViewById(R.id.image);
		image.setImageResource(R.drawable.cube);
		
		TextView solved = (TextView)findViewById(R.id.solved);
		solved.setText(p.getSolved());
	}
}