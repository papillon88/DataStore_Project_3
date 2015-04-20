package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class WriteCommand extends ServerCommand
{

	@Override
	public void run() throws IOException, ServerException
	{
		try
		{
			String fileName = StreamUtil.readLine(this.inputStream);
			String dataSizeString = StreamUtil.readLine(this.inputStream);
			int dataSize = Integer.parseInt(dataSizeString);
			byte[] data = StreamUtil.readData(dataSize, inputStream);
			FileUtil.writeData(fileName, data);
			this.sendOK();
		}
		catch (IOException e)
		{
			this.sendError("ERROR: IOExcetiption occured when writing to file");
		}
		catch (Exception e)
		{
			this.sendError("ERROR: exception occured when writing to file:\n"
						   + e.getMessage());
		}
	}

}
