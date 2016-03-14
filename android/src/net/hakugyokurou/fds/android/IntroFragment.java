package net.hakugyokurou.fds.android;

import android.app.Fragment;
import android.app.FragmentManager;
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
			final Button buttonLoad = (Button)rootView.findViewById(R.id.buttonLoad);
			buttonLoad.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonLoad.setEnabled(false);
				}
			});
			final Button buttonGen = (Button)rootView.findViewById(R.id.buttonGen);
			buttonGen.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					GeneratorFragment fragment = GeneratorFragment.newInstance("", "");
					FragmentManager fm = getFragmentManager();
					fm.beginTransaction().replace(IntroFragment.this.getId(), fragment).commit();
				}
			});
			final Button buttonParse = (Button)rootView.findViewById(R.id.buttonParse);
			buttonParse.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonParse.setEnabled(false);
				}
			});
		}
		return rootView;
	}
}