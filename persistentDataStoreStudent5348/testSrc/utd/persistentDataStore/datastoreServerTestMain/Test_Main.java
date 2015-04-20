package utd.persistentDataStore.datastoreServerTestMain;

import utd.persistentDataStore.datastoreServer.DatastoreClientTestCase;

public class Test_Main {

	public static void main(String[] args)
	{
		DatastoreClientTestCase test = new DatastoreClientTestCase();
		
		try {
			test.testWrite();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
