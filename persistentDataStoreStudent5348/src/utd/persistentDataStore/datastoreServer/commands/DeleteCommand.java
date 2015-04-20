package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DeleteCommand extends ServerCommand
{
	
	@Override
	public void run() throws IOException, ServerException
	{
		String fileName = "";
		
		try
		{
			fileName = StreamUtil.readLine(this.inputStream);
			FileUtil.deleteData(fileName);
			this.sendOK();
		}
		catch (ServerException e)
		{
			this.sendError("ERROR: IOExcetiption - No File Found " + fileName);
		}
		catch (IOException e)
		{
			this.sendError("ERROR: IOExcetiption occured when deleting file");
		}
		catch (Exception e)
		{
			this.sendError("ERROR: exception occured when deleting file:\n"
						   + e.getMessage());
		}
	}

}
