package com.dataBinder;

import com.JsonData.Request;
import com.data.Bank;
import com.data.Company;
import com.data.CompanyPropertyEditor;
import com.data.Person;
import com.data.PersonPropertyEditor;
import com.data.PropertyEditorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deshan on 3/9/2018.
 */

@RestController
public class DetailController
{
    @Autowired
    PropertyEditorFactory propertyEditorFactory;

    @InitBinder
    public void initBinderAll(WebDataBinder binder) {
        binder.registerCustomEditor(Person.class,  propertyEditorFactory.getPropertyEditor( Person.class ));
        binder.registerCustomEditor(Company.class,  propertyEditorFactory.getPropertyEditor( Company.class ));
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/test")
    public ResponseEntity<String> personGET(@RequestParam(value = "Person", required = false) Person person,
                                            @RequestParam(value = "Company", required = false) Company company)
    {
        return new ResponseEntity<String>( person.getName()+"-"+company.getName() , HttpStatus.OK );
    }

    @RequestMapping(method = RequestMethod.POST, value = "api/test")
    public ResponseEntity<String> personPOST(@RequestBody Request requestBody) {
        return new ResponseEntity<String>(requestBody.getBank() + "-" + requestBody.getBranch() + Bank.getInstance().getGreeting(), HttpStatus.OK);
    }
}
