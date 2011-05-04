package jp.sahana.chugokugtug.test;

import jp.sahana.chugokugtug.SettingActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import jp.sahana.chugokugtug.R;

public class SettingActivityTest extends ActivityInstrumentationTestCase2<SettingActivity> {
	private EditText editUrl;
	private Button btnCommit;
	
	public SettingActivityTest() {
		super("jp.sahana.chugokugtug", SettingActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		editUrl = (EditText)getActivity().findViewById(R.id.editurl);
		btnCommit = (Button)getActivity().findViewById(R.id.btncommit);
	}
	
	public void testCommitClick() {
		// URL未入力時のテスト
		getActivity().runOnUiThread(new Runnable() {	
			@Override
			public void run() {
				editUrl.setText("");
				btnCommit.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		assertTrue(editUrl.getError().toString().length() > 0);
		
		// スキーマが無い場合のテスト
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				editUrl.setText("japan.sahanafoundation.org");
				btnCommit.performClick();				
			}
		});
		getInstrumentation().waitForIdleSync();
		assertTrue(editUrl.getError().toString().length() > 0);
		
		// 正しい接続先のテスト
		final String strUrl = "http://japan.sahanafoundation.org";
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				editUrl.setText(strUrl);
				btnCommit.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		
		// 正しい設定であれば、Activityがfinish()される。
		assertTrue(getActivity().isFinishing());
	}
}
