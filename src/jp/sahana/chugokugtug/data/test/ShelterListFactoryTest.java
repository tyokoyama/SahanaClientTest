package jp.sahana.chugokugtug.data.test;

import java.util.ArrayList;

import org.apache.http.HttpStatus;

import android.util.Log;

import jp.sahana.chugokugtug.data.Shelter;
import jp.sahana.chugokugtug.data.ShelterList;
import jp.sahana.chugokugtug.data.ShelterListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class ShelterListFactoryTest extends TestCase {

	public void testCreate() {
		SahanaHttpClient client = new SahanaHttpClient();
		int iRet = client.getData("http://japan.sahanafoundation.org/eden/cr/shelter.xml");
		assertEquals(iRet, HttpStatus.SC_OK);

		ShelterListFactory factory = new ShelterListFactory();
		ShelterList list = (ShelterList)factory.create(client.getResponse());
		ArrayList<Shelter> array = list.getArray();
		assertEquals(array.size(), 10);
		
		Shelter shelter = array.get(0);
		assertEquals(shelter.getCreatedBy(), "nyampire@gmail.com");
		assertEquals(shelter.getModifiedBy(), "nyampire@gmail.com");
		assertEquals(shelter.getCreateDate(), "2011-04-24T15:43:52Z");
		assertEquals(shelter.getModifyDate(), "2011-04-24T15:43:52Z");
		assertEquals(shelter.getName(), "faily tale");
		assertEquals(shelter.getBuilding_name(), "サンモール日泉ビル");
		assertEquals(shelter.getAddress(), "仙台市青葉区一番町２−７−３ \rhttp://blog1.fairy-tale.cc");
		assertEquals(shelter.getL3(), "一番町二丁目");
		assertEquals(shelter.getL2(), "仙台市青葉区");
		assertEquals(shelter.getL1(), "宮城県");
		assertEquals(shelter.getL0(), "Japan");
		assertEquals(shelter.getCapacity(), 20);
		assertEquals(shelter.getSource(), "sinsai.info");
		assertEquals(shelter.getComments(), "仙台のメイド喫茶『fairy tale』は、18日営業再開予定！当面は11時開店、18時閉店。ホットサンド・ホットケーキは17時オーダーストップ、全メニューは17時30分オーダーストップ。18日・19日と土日祝は一人2時間の時間制限。名物メニューのカルボライスやオムライスなどお休みするメニューあり。");
		assertEquals(shelter.getShelter_type_id(), "その他");
		assertEquals(shelter.getShelter_service_id(), "食料配給, 給水");
	}

}
