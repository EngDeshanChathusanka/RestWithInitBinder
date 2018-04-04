package com.dataBinder;

import com.JsonData.Request;
import com.data.Bank;
import com.data.CompanyPropertyEditor;
import com.data.PersonPropertyEditor;
import com.data.PropertyEditorFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by deshan on 4/4/2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Bank.class)
@SuppressStaticInitializationFor("com.data.Bank")
public class DetailControllerTestWithPowerMock {
    private PersonPropertyEditor personPropertyEditor;

    private CompanyPropertyEditor companyPropertyEditor;

    @InjectMocks
    private PropertyEditorFactory propertyEditorFactory;

    @InjectMocks
    private DetailController detailController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        personPropertyEditor = Mockito.spy(new PersonPropertyEditor());
        companyPropertyEditor = Mockito.spy(new CompanyPropertyEditor());
        propertyEditorFactory = Mockito.spy(PropertyEditorFactory.class);
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(detailController).build();

    }

    @Test
    public void testGet() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(get("/api/test")
                .param("Company", "ABC-100")
                .param("Person", "Deshan-25"));

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {

        PowerMockito.mockStatic(Bank.class);
        Bank bank = new Bank(".........Mock Bank..........");
        when(Bank.getInstance()).thenReturn(bank);

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
