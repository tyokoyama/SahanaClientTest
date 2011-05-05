package jp.sahana.chugokugtug.web.test;

import org.apache.http.HttpStatus;

import android.util.Log;

import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class SahanaHttpClientTest extends TestCase {

	public void testGetData() {
		SahanaHttpClient client = new SahanaHttpClient();
		
		int iRet = client.get("http://sahana.jp/eden/vol/project.xml");
		assertEquals(iRet, HttpStatus.SC_OK);
		
		assertNotNull(client.getResponse());
		
		String response = client.getResponse();
		Log.d("TEST", response);
		assertTrue(response.length() > 0);
		
		// スキーマ指定が抜けている設定時のテスト
		iRet = client.get("japan.sahanafoundation.org/eden/vol/project.xml");
		assertEquals(iRet, -1);
		
		// 接続先設定に/を含めてしまって//になった時のテスト
		iRet = client.get("http://japan.sahanafoundation.org//eden/vol/project.xml");
		assertEquals(iRet, HttpStatus.SC_OK);
		
		response = client.getResponse();
		Log.d("TEST", response);
		assertTrue(response.length() > 0);
		
		// 接続先がそもそも存在しない場合のテスト
		iRet = client.get("http://ore.no.testserver.com/eden/vol/project.xml");
		assertEquals(iRet, -1);
		
	}

}
