package net.projecteuler.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChooseActivity extends Activity implements OnClickListener {
	
	private EditText profileText;
	private SharedPreferences preferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose);
		 
		View okButton = (Button)findViewById(R.id.ok);
		okButton.setOnClickListener(this);
		 
		profileText = (EditText) findViewById(R.id.profileName);

		preferences = getSharedPreferences("settings", MODE_PRIVATE);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		String profileName = preferences.getString("profile", null);
		profileText.setText(profileName);
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		saveState();
	}

	@Override
	public void onClick(View v) {
		saveState();
		Intent intent = new Intent(this, ProfileActivity.class);
		startActivity(intent);
	}
	
	private void saveState()
	{
		Editor editor = preferences.edit();
		editor.putString("profile", profileText.getText().toString());
		editor.commit();
	}
}