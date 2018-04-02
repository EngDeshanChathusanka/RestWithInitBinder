package com.dataBinder;

//import com.fasterxml.jackson.databind.ObjectMapper;

import com.JsonData.Request;
import com.data.CompanyPropertyEditor;
import com.data.Person;
import com.data.PersonPropertyEditor;
import com.data.PropertyEditorFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by deshan on 3/12/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailControllerFactoryTest
{
    @Spy
    private PersonPropertyEditor personPropertyEditor;

    @Spy
    private CompanyPropertyEditor companyPropertyEditor;

    @Spy @InjectMocks
    private PropertyEditorFactory propertyEditorFactory;

    @InjectMocks
    private DetailController detailController;

    private MockMvc mockMvc;

    @Before
    public void setup(){

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(detailController).build();

    }

    @Test
    public void testGet() throws Exception {

        ResultActions resultActions=this.mockMvc.perform(get("/api/test")
                .param("Company", "ABC-100")
                .param("Person", "Deshan-25"));

        resultActions.andExpect( status().isOk() );
    }

    @Test
    public void testPost() throws Exception {

        Request request = new Request();
        request.setBank("Sampath");
        request.setBranch("Borella");
        ResultActions resultActions = this.mockMvc.perform(post("/api/test")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
