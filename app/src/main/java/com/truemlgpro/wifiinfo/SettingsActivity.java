package com.truemlgpro.wifiinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import me.anwarshahriar.calligrapher.Calligrapher;

public class SettingsActivity extends AppCompatActivity
{

	public static final String KEY_PREF_SWITCH = "theme_switch";
	public static final String KEY_PREF_AMOLED_CHECK = "amoled_theme_checkbox";
	public static final String KEY_PREF_APP_FONT = "app_font_list";
	public static final String KEY_PREF_CARD_FREQ = "card_update_freq";
	public static final String KEY_PREF_BOOT_SWITCH = "boot_switch";
	public static final String KEY_PREF_NTFC_SWITCH = "notification_switch";
	public static final String KEY_PREF_CLR_CHECK = "colorize_ntfc_checkbox";
	public static final String KEY_PREF_VIS_SIG_STRG_CHECK = "visualize_signal_strength_ntfc_checkbox";
	public static final String KEY_PREF_STRT_STOP_SRVC_CHECK = "start_stop_service_screen_state_ntfc_checkbox";
	public static final String KEY_PREF_NTFC_FREQ = "notification_update_freq";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Boolean keyTheme = new SharedPreferencesManager(getApplicationContext()).retrieveBoolean(SettingsActivity.KEY_PREF_SWITCH, MainActivity.darkMode);
		Boolean keyAmoledTheme = new SharedPreferencesManager(getApplicationContext()).retrieveBoolean(SettingsActivity.KEY_PREF_AMOLED_CHECK, MainActivity.amoledMode);

		if (keyTheme) {
			setTheme(R.style.DarkTheme);
		}

		if (keyAmoledTheme) {
			if (keyTheme) {
				setTheme(R.style.AmoledDarkTheme);
			}
		}

		if (!keyTheme) {
			setTheme(R.style.LightTheme);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		
		Calligrapher calligrapher = new Calligrapher(this);
		String font = new SharedPreferencesManager(getApplicationContext()).retrieveString(SettingsActivity.KEY_PREF_APP_FONT, MainActivity.appFont);
		calligrapher.setFont(this, font, true);

		setSupportActionBar(toolbar);
		final ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setElevation(20);

		toolbar.setNavigationOnClickListener(v -> {
			// Back button pressed
			restartActivity();
		});
		
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.content_frame, new SettingsFragment())
			.commit();
	}
	
	private void restartActivity() {
		finish();
		overridePendingTransition(0, 0);
		Intent mainActivityIntent = new Intent(SettingsActivity.this, MainActivity.class);
		startActivity(mainActivityIntent);
		overridePendingTransition(0, 0);
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		restartActivity();
	}
}
