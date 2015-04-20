package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import utd.persistentDataStore.utils.ServerException;

public class InvalidCommand extends ServerCommand
{

	@Override
	public void run() throws IOException, ServerException
	{
		this.sendError("ERROR: Invlid Command Name");
	}

}
