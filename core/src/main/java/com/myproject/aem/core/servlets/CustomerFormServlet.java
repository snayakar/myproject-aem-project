package com.myproject.aem.core.servlets;

import com.myproject.aem.core.services.CustomerFormServices;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service= Servlet.class,

        property={

                Constants.SERVICE_DESCRIPTION + "=Customer Form Servlet",

                "sling.servlet.methods=" + HttpConstants.METHOD_POST,

                "sling.servlet.paths="+ "/bin/myproject/components",

        })
public class CustomerFormServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private transient CustomerFormServices customerFormServices;



    @Override

    protected void doPost(final SlingHttpServletRequest req,
                          final SlingHttpServletResponse resp) throws ServletException, IOException {


        try {



            String formName= req.getParameter("formName");
            String formEmail= req.getParameter("formEmail");
            String formId= req.getParameter("formId");



            Message message = new MimeMessage(getSession());
            String JsonData=null;
            String requestUrl="";

            //Save the Form data in to Map Object and pass the Map Object to CustomerFormService class make the request to Mock API.

            Map<String,Object> formData = extractFormFields(req);
            Map<String, Object> dataMap=new HashMap<String, Object>();

            //prepare the data map

            resp=customerFormServices.updateData(formData, dataMap);

            //based on the response - Redirect the page to display Thank you page or Error page

            resp.getWriter().
                    write(jacksonMapper.convertObjectToJsonString(
                            postBackResponse, false,
                            jacksonMapper.createSimpleFilter(CBusCoreConsts.JCR_FILTER)));

        }
        catch (ApiException e) {
            LOGGER.error("Error Response code: {}, message: {}", e.getErrorCode(),
                    jacksonMapper.convertObjectToJsonString(errorResponse, false, null));
        }

    }

    private Map<String,Object> extractFormFields(SlingHttpServletRequest request) throws IOException{
        return jacksonMapper.convertJsonToObject(request.getInputStream(), Map.class);
    }


}
