package com.myproject.aem.core.servlets;

import org.apache.http.entity.ContentType;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({OpenIdUtil.class})
public class CustomerFormServletTest {

    private CustomerFormServlet customerFormServletSpy;
    @InjectMocks
    CustomerFormServlet customerFormServlet;

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private SlingHttpServletResponse response1;


    MockSlingHttpServletResponse response;
    @Mock
    Resource formBuilderResource;

    Map<String,String> stateMap = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        customerFormServlet = new CustomerFormServlet();
        customerFormServletSpy = spy(customerFormServlet);

        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(OpenIdUtil.class);
        when(request.getContentType()).thenReturn(ContentType.APPLICATION_JSON.getMimeType());
        when(request.getMethod()).thenReturn("POST");
        request = mock(SlingHttpServletRequest.class);
        response1 = mock(SlingHttpServletResponse.class);
        response = new MockSlingHttpServletResponse();
        customerFormServlet =mock(CustomerFormServlet.class);
        when(request.getRequestPathInfo()).thenReturn(requestPathInfo);
    }

    @Test
    public void doPost_test() throws Exception {

        //Sudo unit test for the implementation
        customerFormServlet.doPost(request, response);
    }


}
