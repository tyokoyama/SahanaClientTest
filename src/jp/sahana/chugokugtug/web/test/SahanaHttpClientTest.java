package jp.sahana.chugokugtug.web.test;

import org.apache.http.HttpStatus;

import android.util.Log;

import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class SahanaHttpClientTest extends TestCase {

	public void testGetData() {
		SahanaHttpClient client = new SahanaHttpClient();
		
		int iRet = client.getData("http://sahana.jp/eden/vol/project.xml");
		assertEquals(iRet, HttpStatus.SC_OK);
		
		assertNotNull(client.getResponse());
		
		String response = client.getResponse();
		Log.d("TEST", response);
	}

}
