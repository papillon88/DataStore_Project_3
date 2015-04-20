package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import java.util.List;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DirectoryCommand extends ServerCommand
{

	@Override
	public void run() throws IOException, ServerException
	{
		try
		{
			List<String> files = FileUtil.directory();
			String numFiles = String.valueOf(files.size());
			this.sendOK();
			StreamUtil.writeLine(numFiles, outputStream);
			for (int i = 0; i < files.size(); i++)
				StreamUtil.writeLine(files.get(i), outputStream);
		}
		catch (ServerException e)
		{
			this.sendError("ERROR: ServerException occured when executing directory command");
		}
		catch (IOException e)
		{
			this.sendError("ERROR: IOExcetiption occured when executing directory command");
		}
		catch (Exception e)
		{
			this.sendError("ERROR: exception occured when executing directory command:\n"
						   + e.getMessage());
		}
	}

}
