package utd.persistentDataStore.datastoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DatastoreClientImpl implements DatastoreClient
{
	private static Logger logger = Logger.getLogger(DatastoreClientImpl.class);

	private InetAddress address;
	private int port;

	public DatastoreClientImpl(InetAddress address, int port)
	{		
		this.address = address;
		this.port = port;
	}
	
	Socket openSocket()
	{
		Socket socket;
		
		try
		{
			logger.debug("Opening socket to server");
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket = new Socket();
			socket.connect(saddr);
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to open socket correctly");
			e.printStackTrace();
			return null;
		}
		
		return socket;
	}


	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#write(java.lang.String, byte[])
	 */
	@Override
    public void write(String name, byte data[]) throws ClientException
	{
		logger.debug("Executing Write Operation");
		
		Socket socket;
		InputStream is;
		OutputStream os;
		
		socket = openSocket();
		
		if (socket != null)
		{
			try
			{
				is = socket.getInputStream();
				os = socket.getOutputStream();
			}
			catch (IOException e)
			{
				logger.error("ERROR: Not able to set streams correctly");
				e.printStackTrace();
				return;
			}
		}
		else
			return;
		
		String commandName = "write";
		String dataSize = String.valueOf(data.length);
		
		try
		{
			logger.debug("building and sending request message to the server");
			StreamUtil.writeLine(commandName, os);
			StreamUtil.writeLine(name, os);
			StreamUtil.writeLine(dataSize, os);
			StreamUtil.writeData(data, os);
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to write request to ouput stream");
			e.printStackTrace();
		}
		
		try
		{
			logger.debug("waiting for and processing response message from the server");
			String responseCode = StreamUtil.readLine(is);
			logger.debug("Response Code: " + responseCode);
			
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to read repsonse from input stream");
			e.printStackTrace();
		}
		
		StreamUtil.closeSocket(is);
		logger.debug("Closed the socket connection");
	}
	
	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#read(java.lang.String)
	 */
	@Override
    public byte[] read(String name) throws ClientException
	{
		logger.debug("Executing Read Operation");
		
		Socket socket;
		InputStream is;
		OutputStream os;
		
		socket = openSocket();
		
		if (socket != null)
		{
			try
			{
				is = socket.getInputStream();
				os = socket.getOutputStream();
			}
			catch (IOException e)
			{
				logger.error("ERROR: Not able to set streams correctly");
				e.printStackTrace();
				return null;
			}
		}
		else
			return null;
		
		String commandName = "read";
		
		try
		{
			logger.debug("building and sending request message to the server");
			StreamUtil.writeLine(commandName, os);
			StreamUtil.writeLine(name, os);
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to write request to ouput stream");
			e.printStackTrace();
		}
		
		String dataSizeString;
		int dataSize;
		byte[] data = null;
		
		try
		{
			logger.debug("waiting for and processing response message from the server");
			String responseCode = StreamUtil.readLine(is);
			logger.debug("Response Code: " + responseCode);
			
			if (responseCode.equalsIgnoreCase("ok"))
			{
				dataSizeString = StreamUtil.readLine(is);
				logger.debug("Data Size: " + dataSizeString);
				dataSize = Integer.parseInt(dataSizeString);
				data = StreamUtil.readData(dataSize, is);
			}
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to read repsonse from input stream");
			e.printStackTrace();
		}
				
		try
		{
			is.close();
			logger.debug("Closed the socket connection");
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to close the input stream");
			e.printStackTrace();
		}
		
		if (data == null)
			logger.error("WARNING: returning null byte[]");
		
		return data;
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#delete(java.lang.String)
	 */
	@Override
    public void delete(String name) throws ClientException
	{
		logger.debug("Executing Delete Operation");
		
		Socket socket;
		InputStream is;
		OutputStream os;
		
		socket = openSocket();
		
		if (socket != null)
		{
			try
			{
				is = socket.getInputStream();
				os = socket.getOutputStream();
			}
			catch (IOException e)
			{
				logger.error("ERROR: Not able to set streams correctly");
				e.printStackTrace();
				return;
			}
		}
		else
			return;
		
		String commandName = "delete";
		
		try
		{
			logger.debug("building and sending request message to the server");
			StreamUtil.writeLine(commandName, os);
			StreamUtil.writeLine(name, os);
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to write request to ouput stream");
			e.printStackTrace();
		}
		
		try
		{
			logger.debug("waiting for and processing response message from the server");
			String responseCode = StreamUtil.readLine(is);
			logger.debug("Response Code: " + responseCode);
			
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to read repsonse from input stream");
			e.printStackTrace();
		}
				
		try
		{
			is.close();
			logger.debug("Closed the socket connection");
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to close the input stream");
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#directory()
	 */
	@Override
    public List<String> directory() throws ClientException
	{
		logger.debug("Executing Directory Operation");
		
		Socket socket;
		InputStream is;
		OutputStream os;
		
		socket = openSocket();
		
		if (socket != null)
		{
			try
			{
				is = socket.getInputStream();
				os = socket.getOutputStream();
			}
			catch (IOException e)
			{
				logger.error("ERROR: Not able to set streams correctly");
				e.printStackTrace();
				return null;
			}
		}
		else
			return null;
		
		String commandName = "directory";
		
		try
		{
			logger.debug("building and sending request message to the server");
			StreamUtil.writeLine(commandName, os);
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to write request to ouput stream");
			e.printStackTrace();
		}
		
		String numFilesString;
		int numFiles;
		List<String> files = null;
		
		try
		{
			logger.debug("waiting for and processing response message from the server");
			String responseCode = StreamUtil.readLine(is);
			numFilesString = StreamUtil.readLine(is);
			logger.debug("Response Code: " + responseCode);
			logger.debug("Number of Files: " + numFilesString);
			numFiles = Integer.parseInt(numFilesString);
			files = new ArrayList<String>(numFiles);
			
			for (int i = 0; i < numFiles; i++)
				files.add(StreamUtil.readLine(is));
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to read repsonse from input stream");
			e.printStackTrace();
		}
				
		try
		{
			is.close();
			logger.debug("Closed the socket connection");
		}
		catch (IOException e)
		{
			logger.error("ERROR: Not able to close the input stream");
			e.printStackTrace();
		}
		
		if (files.size() <= 0)
			logger.error("WARNING: returning null List<String>");
		
		return files;
	}

}
