package com.dataBinder;

import com.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
        /*binder.registerCustomEditor(Person.class,  new PersonPropertyEditor());
        binder.registerCustomEditor(Company.class,  new CompanyPropertyEditor());*/
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/test")
    public ResponseEntity<String> show( @RequestParam(value = "Person", required = false) Person person,
                                        @RequestParam(value = "Company", required = false) Company company)
    {
        return new ResponseEntity<String>( person.getName()+"-"+company.getName() , HttpStatus.OK );
    }
}
