package net.projecteuler.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity implements ProfileListener, OnClickListener {

    private SharedPreferences preferences;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        preferences = getSharedPreferences("settings", MODE_PRIVATE);
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	
    	String profile = preferences.getString("profile", null);
    	if (profile == null || profile.length() == 0)
        {
    		setContentView(R.layout.noprofile);
    		Button b = (Button)findViewById(R.id.select);
    		b.setOnClickListener(this);
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
		image.setImageResource(p.getLevel().getDrawableResource());
		
		TextView solved = (TextView)findViewById(R.id.solved);
		solved.setText(Integer.toString(p.getSolved()));
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ChooseActivity.class);
		startActivity(intent);
	}
}