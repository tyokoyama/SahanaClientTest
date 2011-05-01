package jp.sahana.chugokugtug.data.test;

import java.util.ArrayList;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.data.Project;
import jp.sahana.chugokugtug.data.ProjectList;
import jp.sahana.chugokugtug.data.ProjectListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class ProjectListFactoryTest extends TestCase {

	public void testCreate() {
		SahanaHttpClient client = new SahanaHttpClient();
		int iRet = client.getData("http://sahana.jp/eden/vol/project.xml");
		
		assertEquals(iRet, HttpStatus.SC_OK);
		
		ProjectListFactory factory = new ProjectListFactory();
		ProjectList list = (ProjectList)factory.create(client.getResponse());
		ArrayList<Project> prjList = list.getArray();
		assertEquals(prjList.size(), 2);
		
		Project prj = prjList.get(0);
		assertEquals(prj.getStatus(), 1);
		assertEquals(prj.getStatusName(), "アクティブ");
		assertEquals(prj.getUrl(), "http://sahana.jp:80/eden/project/project/1");
		assertEquals(prj.getCreateBy(), "nanbuwks+sahana@nanbu.com");
		assertEquals(prj.getModifiedBy(), "nanbuwks+sahana@nanbu.com");
		assertEquals(prj.getCreateDate(), "2011-03-12T15:08:46Z");
		assertEquals(prj.getModifyDate(), "2011-03-12T15:08:46Z");
		assertEquals(prj.getName(), "臨時災害FMラジオ放送局：岩手県奥州市:77.8MHz");
	}

}
