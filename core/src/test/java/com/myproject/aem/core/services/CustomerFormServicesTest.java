package com.myproject.aem.core.services;

import junitx.util.PrivateAccessor;
import org.apache.http.entity.ContentType;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CustomerFormServices.class})
public class CustomerFormServicesTest {

    @InjectMocks
    CustomerFormServices customerFormServices;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateData_test() throws Throwable {

        //Sudo unit test for the implementation
        Map<String, Object> formData = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        ApiWebApplicationV1Client client = mock(ApiWebApplicationV1Client.class);
        PowerMockito.whenNew(ApiWebApplicationV1Client.class).withNoArguments().thenReturn(client);



        Assert.assertNotNull(customerFormServices.updateData(formData, data));

    }




}
