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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.util.AnswerHelper;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link AnswerFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class AnswerFragment extends Fragment {
	private static WeakReference<ArrayList<MathExpression>> param;

	private ArrayList<HashMap<String, ?>> items;
	private MathExpression currentExpression;
	private HashMap<String, Object> currentItem;

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
		items = new ArrayList<HashMap<String,?>>();
		if (param != null) {
			ArrayList<MathExpression> expressions = param.get();
			for(MathExpression expr : expressions)
			{
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("Ojbect", expr);
				item.put("ListItemQuestion", expr.toString());
				item.put("ListItemAnswer", "NONONO");
				items.add(item);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_answer, container, false);
		{
			final ListView listView = (ListView)rootView.findViewById(R.id.listQuestions);
			listView.setAdapter(new SimpleAdapter(this.getActivity(), items, R.layout.listview_answer, 
					new String[]{"ListItemQuestion", "ListItemAnswer"}, 
					new int[]{R.id.ListItemQuestion, R.id.ListItemAnswer}));
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					ListView listView = (ListView)parent;
					HashMap<String, Object> map = (HashMap<String, Object>) listView.getItemAtPosition(position);
					MathExpression expression = (MathExpression) map.get("Object");
					currentExpression = expression;
					currentItem = map;
					((Button)rootView.findViewById(R.id.buttonCheck)).setEnabled(true);
					((EditText)rootView.findViewById(R.id.editAnswer)).setText("");
				}
			});
			final Button buttonCheck = (Button)rootView.findViewById(R.id.buttonCheck);
			buttonCheck.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String answer = ((EditText)rootView.findViewById(R.id.editAnswer)).getText().toString();
					boolean right;
					try {
						right = AnswerHelper.checkAnswer(AnswerHelper.parseSingleNumber(answer), currentExpression.eval());
					} catch (Exception e) {
						currentItem.put("ListItemAnswer", "Wrong");
						return;
					}
					if(right)
						currentItem.put("ListItemAnswer", answer);
					else
						currentItem.put("ListItemAnswer", "Wrong");
					((SimpleAdapter)listView.getAdapter()).notifyDataSetChanged();
				}
			});
			buttonCheck.setEnabled(false);
			final EditText editText = (EditText)rootView.findViewById(R.id.editAnswer);
			//TODO:
		}
		return rootView;
	}
}
