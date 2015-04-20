package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class ReadCommand extends ServerCommand
{

	@Override
	public void run() throws IOException, ServerException
	{
		try
		{
			String fileName = StreamUtil.readLine(this.inputStream);
			byte[] data = null;
			String dataSize = "0";
			
			try
			{
				data = FileUtil.readData(fileName);
				dataSize = String.valueOf(data.length);
				this.sendOK();
				StreamUtil.writeLine(dataSize, outputStream);
				StreamUtil.writeData(data, outputStream);
			}
			catch (ServerException e)
			{
				System.out.print("GOT HERE");
				String message = "ERROR: ServerException occured when reading file. " + 
								 "No File Found " + fileName;
				this.sendError(message);
				data = null;
			}
		}
		catch (IOException e)
		{
			this.sendError("ERROR: IOExcetiption occured when reading file");
		}
		catch (Exception e)
		{
			this.sendError("ERROR: exception occured when reading file:\n"
						   + e.getMessage());
		}
	}

}
