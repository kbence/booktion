package com.booktion.client.connector.test;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.ClientFactory;
import com.booktion.log.LoggerTestCase;
import com.booktion.thrift.BooktionService;
import com.booktion.thrift.Message;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BooktionConnectorTest extends LoggerTestCase
{
    private final String TEST_HOSTNAME = "hostname";
    private final int TEST_PORT = 1234;

    private BooktionService.Client clientMock;
    private ClientFactory clientFactoryMock;
    private BooktionConnector connector;

    @Before
    public void setUp() throws TException
    {
        clientMock = mock(BooktionService.Client.class);
        clientFactoryMock = mock(ClientFactory.class);

        when(clientFactoryMock.createClient(TEST_HOSTNAME, TEST_PORT)).thenReturn(clientMock);

        connector = new BooktionConnector();
        connector.setProtocolFactory(clientFactoryMock);
    }

    @Test
    public void connectCreatesTheClient() throws TException
    {
        // Arrange, Act
        connector.connect(TEST_HOSTNAME, TEST_PORT);

        // Assert
        verify(clientFactoryMock).createClient(TEST_HOSTNAME, TEST_PORT);
    }

    @Test
    public void pingSendsAMessageAndLogsAnswer() throws TException
    {
        // Arrange
        connector.connect(TEST_HOSTNAME, TEST_PORT);
        when(clientMock.echo(any(Message.class))).thenReturn(new Message("Message to be logged"));

        // Act
        connector.ping();

        // Assert
        verify(clientMock).echo(new Message("PING"));
        assertLog("Should log answer", "SERVER: Message to be logged");
    }
}
