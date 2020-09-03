package li.raymond.goose;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	AlertDialog.Builder builder;
	long count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPref = this.getPreferences(Context.MODE_PRIVATE);
		count = sharedPref.getLong(getString(R.string.counter), Long.parseLong(getString(R.string.default_counter)));
		editor = sharedPref.edit();
		setContentView(R.layout.activity_main);
		TextView counter = (TextView) findViewById(R.id.counter);
		counter.setText(Long.toString(count));
		if (sharedPref.getBoolean(getString(R.string.firstRun), true))
			firstRunDialog();
	}

	/**
	 * Increment counter when Goose is clicked.
	 */
	public void incrementCounter(View view) {
		count++;
		TextView counter = (TextView) findViewById(R.id.counter);
		counter.setText(Long.toString(count));
		//if (count % 5 == 0) {
		editor.putLong(getString(R.string.counter), count).apply();
		//}
	}

	/**
	 * Go to https://raymond.li when copyright is clicked
	 */
	public void visitSite(View view) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.site))));
	}

	/**
	 * Show this dialog on first run
	 */
	public void firstRunDialog() {
		AlertDialog alert = new AlertDialog.Builder(this).setMessage(R.string.firstRunMsg)
				.setTitle(R.string.firstRunTitle).setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						editor.putBoolean(getString(R.string.firstRun), false).apply();
					}
				}).create();
		alert.show();
		((TextView) alert.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
	}
}