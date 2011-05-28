package jp.sahana.chugokugtug.data.test;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.data.RequestItem;
import jp.sahana.chugokugtug.data.RequestItemList;
import jp.sahana.chugokugtug.data.RequestItemListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class RequestItemListFactoryTest extends TestCase {
	// ユーザ名とパスワードを載せるわけはいかないので、適当なものにしています。（テストはこのままだと失敗します。）
	private static final String USERNAME = "youraddress";
	private static final String PASSWORD = "yourpassword";

	public void testCreate() {
		SahanaHttpClient client = new SahanaHttpClient(USERNAME, PASSWORD);
		int iRet = client.get("http://japan.sahanafoundation.org/eden/req/req/1/req_item.xml");
		assertEquals(HttpStatus.SC_OK, iRet);
		
		RequestItemListFactory factory = new RequestItemListFactory();
		RequestItemList list = (RequestItemList)factory.create(client.getResponse());
		RequestItem item = list.getArray().get(0);
		
		assertEquals("2011-04-14T15:50:39Z", item.getCreateDate());
		assertEquals("2011-05-06T02:57:27Z", item.getModifyDate());
		assertEquals("emikok@jp.ibm.com", item.getCreatedBy());
		assertEquals("fuga35@gmail.com", item.getModifiedBy());
		assertEquals("http://japan.sahanafoundation.org/eden/req/req/1/req_item/1", item.getUrl());
		assertEquals(5.0, item.getQuantity());
		assertEquals(10.0, item.getQuantitycommit());
		assertEquals(5.0, item.getQuantitytransit());
		assertEquals(5.0, item.getQuantityfulfil());
		assertEquals("a", item.getComments());
		assertEquals("Tent (piece)", item.getItemId());
		assertEquals("piece", item.getItemPackId());
	}

}
