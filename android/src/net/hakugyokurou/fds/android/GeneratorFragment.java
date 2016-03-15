package net.hakugyokurou.fds.android;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import net.hakugyokurou.fds.generator.MathExpressionGenerator;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link GeneratorFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link GeneratorFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class GeneratorFragment extends Fragment {
	
	public static GeneratorFragment newInstance(String param1, String param2) {
		GeneratorFragment fragment = new GeneratorFragment();
		return fragment;
	}

	public GeneratorFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_generator, container, false);
		{
			final Button buttonLoad = (Button)rootView.findViewById(R.id.buttonGenerate);
			buttonLoad.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					AnswerFragment fragment = AnswerFragment.newInstance(MathExpressionGenerator.generateEasy(5));
					FragmentManager fm = getFragmentManager();
					fm.beginTransaction().replace(GeneratorFragment.this.getId(), fragment).commit();
				}
			});
		}
		return rootView;
	}
}
