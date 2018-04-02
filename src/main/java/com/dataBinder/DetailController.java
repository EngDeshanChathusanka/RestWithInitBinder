package com.dataBinder;

import com.JsonData.Request;
import com.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
        /*binder.registerCustomEditor(Person.class,  new PersonPropertyEditor());
        binder.registerCustomEditor(Company.class,  new CompanyPropertyEditor());*/
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/test")
    public ResponseEntity<String> show( @RequestParam(value = "Person", required = false) Person person,
                                        @RequestParam(value = "Company", required = false) Company company)
    {
        return new ResponseEntity<String>( person.getName()+"-"+company.getName() , HttpStatus.OK );
    }

    @RequestMapping(method = RequestMethod.POST, value = "api/test")
    public ResponseEntity<String> personPOST(@RequestBody Request requestBody) {
        return new ResponseEntity<String>(requestBody.getBank() + "-" + requestBody.getBranch(), HttpStatus.OK);
    }
}
