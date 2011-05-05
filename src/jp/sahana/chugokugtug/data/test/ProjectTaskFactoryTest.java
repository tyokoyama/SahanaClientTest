package jp.sahana.chugokugtug.data.test;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.data.ProjectTask;
import jp.sahana.chugokugtug.data.ProjectTaskList;
import jp.sahana.chugokugtug.data.ProjectTaskListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import junit.framework.TestCase;

public class ProjectTaskFactoryTest extends TestCase {

	public void testCreate() {
		SahanaHttpClient client = new SahanaHttpClient();
		int iRet = client.get("http://sahana.jp/eden/project/project/3/task.xml");
		assertEquals(iRet, HttpStatus.SC_OK);
		
		ProjectTaskListFactory factory = new ProjectTaskListFactory();
		ProjectTaskList tasklist = (ProjectTaskList)factory.create(client.getResponse());
		
		assertEquals(tasklist.getArray().size(), 2);
		
		ProjectTask task = tasklist.getArray().get(0);
		assertEquals(task.getCreateDate(), "2011-05-02T02:44:52Z");
		assertEquals(task.getModifyDate(), "2011-05-02T02:44:52Z");
		assertEquals(task.getCreatedBy(), "tksyokoyama@gmail.com");
		assertEquals(task.getModifiedBy(), "tksyokoyama@gmail.com");
		assertEquals(task.getUrl(), "http://sahana.jp:80/eden/project/project/3/task/1");
		assertEquals(task.isUrgent(), false);
		assertEquals(task.getPriority(), 1);
		assertEquals(task.getPriorityName(), "通常");
		assertEquals(task.getSubject(), "タスク一覧表示作成");
		assertEquals(task.getDescription(), "タスク一覧表示機能を作る");
		assertEquals(task.getStatus(), 1);
		assertEquals(task.getStatusName(), "新規登録");
	}

}
