package net.hakugyokurou.fds.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class IntroFragment extends Fragment {

	public IntroFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		{
			final Button button = (Button)rootView.findViewById(R.id.buttonLoad);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					button.setEnabled(false);
				}
			});
		}
		return rootView;
	}
}