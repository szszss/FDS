package net.hakugyokurou.fds.android;

import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import net.hakugyokurou.fds.MathExpression;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link AnswerFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class AnswerFragment extends Fragment {
	private static WeakReference<ArrayList<MathExpression>> param;

	private ArrayList<MathExpression> expressions;

	public static AnswerFragment newInstance(ArrayList<MathExpression> expressions) {
		AnswerFragment fragment = new AnswerFragment();
		param = new WeakReference<ArrayList<MathExpression>>(expressions);
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public AnswerFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (param != null) {
			expressions = param.get();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_generator, container, false);
		{
			final ListView listView = (ListView)rootView.findViewById(R.id.listQuestions);
			//listView.setAdapter(new SimpleAdapter(this, data, resource, from, to));
			final Button buttonLoad = (Button)rootView.findViewById(R.id.buttonGenerate);
			buttonLoad.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonLoad.setEnabled(false);
				}
			});
		}
		return rootView;
	}
}
