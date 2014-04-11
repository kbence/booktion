package com.booktion.client.connector.test;

import com.booktion.client.connector.BooktionConnector;
import com.booktion.client.connector.ClientFactory;
import com.booktion.log.util.LoggerTestCase;
import com.booktion.thrift.BooktionService;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BooktionConnectorTest extends LoggerTestCase
{
    private final String TEST_HOSTNAME = "hostname";
    private final int TEST_PORT = 1234;

    private ClientFactory clientFactoryMock;
    private BooktionConnector connector;

    @Before
    public void setUp() throws TException
    {
        BooktionService.Client clientMock = mock(BooktionService.Client.class);
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
}
