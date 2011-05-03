package jp.sahana.chugokugtug.util;

import android.test.ActivityInstrumentationTestCase2;
import jp.sahana.chugokugtug.MainActivity;

public class AppSettingTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private AppSetting setting;
	
	public AppSettingTest() {
		super("jp.sahana.chugokugtug", MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setting = new AppSetting(getActivity());
	}
	
	public void testCommit() {
		final String siteURL = "http://japan.sahanafoundation.org/eden/";
		final String userName = "username";
		final String password = "password";
		
		setting.setSiteURL(siteURL);
		setting.setUserName(userName);
		setting.setPassword(password);
		
		setting.commit();
		
		AppSetting setting2 = new AppSetting(getActivity());
		assertEquals(setting.getSiteURL(), setting2.getSiteURL());
		assertEquals(setting.getUserName(), setting2.getUserName());
		assertEquals(setting2.getPassword(), setting2.getPassword());
	}
}
