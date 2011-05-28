package jp.sahana.chugokugtug.put.test;

import jp.sahana.chugokugtug.put.PutRequestItem;
import jp.sahana.chugokugtug.put.PutRequestItemFactory;
import junit.framework.TestCase;

public class PutRequestItemFactoryTest extends TestCase {
	private static final String ANSWER = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><s3xml><resource name=\"req_req_item\"><data field=\"req_id\" value=\"2\" /><data field=\"req_item_id\" value=\"2\" /><data field=\"req_item_pack_id\" value=\"2\" /><data field=\"quantity\" value=\"10\" /><data field=\"comments\" value=\"Sahanadroidのテスト\" /></resource></s3xml>";
	public void testCreateXml() {
		PutRequestItem item = new PutRequestItem();
		
		item.setReqId(2);
		item.setReqItemId(2);
		item.setReqitemPackId(2);
		item.setQuantity(10);
		item.setComments("Sahanadroidのテスト");
		
		PutRequestItemFactory factory = new PutRequestItemFactory(item);
		String str = factory.createXml();
		
		assertTrue(str.length() > 0);
		assertEquals(ANSWER, str);
	}
}
