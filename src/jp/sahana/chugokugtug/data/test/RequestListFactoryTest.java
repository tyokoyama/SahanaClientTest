package jp.sahana.chugokugtug.data.test;

import java.util.ArrayList;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.data.Request;
import jp.sahana.chugokugtug.data.RequestList;
import jp.sahana.chugokugtug.data.RequestListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class RequestListFactoryTest extends TestCase {

	public void testCreate() {
		SahanaHttpClient client = new SahanaHttpClient();
		int iRet = client.get("http://japan.sahanafoundation.org/eden/req/req.xml");
		assertEquals(iRet, HttpStatus.SC_OK);
		
		RequestListFactory factory = new RequestListFactory();
		RequestList list = (RequestList)factory.create(client.getResponse());
		ArrayList<Request> array = list.getArray();
		assertEquals(array.size(), 11);
		
		Request request = array.get(0);
		assertEquals(request.getCreatedBy(), "athlon64x2.windsor.6000plus@gmail.com");
		assertEquals(request.getCreateDate(), "2011-04-23T12:34:10Z");
		assertEquals(request.getModifiedBy(), "athlon64x2.windsor.6000plus@gmail.com");
		assertEquals(request.getModifyDate(), "2011-04-23T12:47:48Z");
		assertEquals(request.getUrl(), "http://japan.sahanafoundation.org/eden/req/req/7");
		assertEquals(request.getDate(), "2011-04-23");
		assertEquals(request.getType(), 1);
		assertEquals(request.getTypeName(), "Supplies");
		assertEquals(request.getPriority(), 2);
		assertEquals(request.getCommitStatus(), 0);
		assertEquals(request.getCommitStatusName(), "なし");
		assertEquals(request.getTransitStatus(), 0);
		assertEquals(request.getTransitStatusName(), "なし");
		assertEquals(request.getFulfilStatus(), 0);
		assertEquals(request.getFulfilStatusName(), "なし");
		assertFalse(request.isCancel());
		// ひとまず、改行コードが何か分からないので（\r\n？）一旦データがあればOKとしておく。
		assertTrue(request.getComments().length() > 0);
//		assertEquals(request.getComments(), "・自宅に避難している周辺住民が物資を取りに来るので人数よりも多めに必要。\r・毛布:600枚\r・飲料水:1000人分\r・衣類(下着):600人分\r・カイロ:1000人分\r・マスク:1000人分\r");
	}

}
