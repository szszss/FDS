package net.hakugyokurou.fds.android;

import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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

	private ArrayList<HashMap<String, String>> items;

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
		items = new ArrayList<HashMap<String,String>>();
		if (param != null) {
			ArrayList<MathExpression> expressions = param.get();
			for(MathExpression expr : expressions)
			{
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("ListItemQuestion", expr.toString());
				item.put("ListItemAnswer", "");
				items.add(item);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_generator, container, false);
		{
			final ListView listView = (ListView)rootView.findViewById(R.id.listQuestions);
			listView.setAdapter(new SimpleAdapter(this.getActivity(), items, R.layout.listview_answer, 
					new String[]{"ListItemQuestion", "ListItemAnswer"}, 
					new int[]{R.id.ListItemQuestion, R.id.ListItemAnswer}));
			final Button buttonCheck = (Button)rootView.findViewById(R.id.buttonCheck);
			buttonCheck.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonCheck.setEnabled(false);
				}
			});
			final EditText editText = (EditText)rootView.findViewById(R.id.editAnswer);
			//TODO:
		}
		return rootView;
	}
}
