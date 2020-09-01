package li.raymond.goose;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	long count = 10000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * Increment counter when Goose is clicked.
	 */
	public void incrementCounter(View view) {
		count++;
		TextView counter = (TextView)findViewById(R.id.counter);
		counter.setText(Long.toString(count));
	}

	/**
	 * Go to https://raymond.li when copyright is clicked
	 */
	public void visitSite(View view) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://raymond.li"));
		startActivity(browserIntent);
	}
}