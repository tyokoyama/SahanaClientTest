package jp.sahana.chugokugtug.web.test;

import org.apache.http.HttpStatus;

import android.util.Log;

import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class SahanaHttpClientTest extends TestCase {
	// ユーザ名とパスワードを載せるわけはいかないので、適当なものにしています。（テストはこのままだと失敗します。）
	private static final String USERNAME = "youraddress";
	private static final String PASSWORD = "yourpassword";
	
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
		
		SahanaHttpClient client2 = new SahanaHttpClient(USERNAME, PASSWORD);
		
		iRet = client2.get("http://japan.sahanafoundation.org/eden/req/req_item");
		assertEquals(iRet, HttpStatus.SC_OK);
		
		assertNotNull(client2.getResponse());
		
		response = client.getResponse();
		Log.d("TEST", response);
		assertTrue(response.length() > 0);
	}

	public void testPutData() {
		SahanaHttpClient client = new SahanaHttpClient(USERNAME, PASSWORD);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='utf-8'?>");
		sb.append("<s3xml>");
		sb.append("<resource name=\"req_req_item\">");
		sb.append("<data field=\"req_id\" value=\"2\" />");
		sb.append("<data field=\"req_item_id\" value=\"2\" />");
		sb.append("<data field=\"req_item_pack_id\" value=\"2\" />");
		sb.append("<data field=\"quantity\" value=\"10\" />");
		sb.append("<data field=\"comments\" value=\"Sahanadroid test\"/>");
		sb.append("</resource>");
		sb.append("</s3xml>");

		int iRet = client.put("http://japan.sahanafoundation.org/eden/req/req_item/create.xml", sb.toString());		
		
		assertEquals(iRet, HttpStatus.SC_OK);
		
		assertEquals("{\"status\": \"success\", \"statuscode\": \"200\"}", client.getResponse());
		Log.d("TEST", client.getResponse());
	}
}
