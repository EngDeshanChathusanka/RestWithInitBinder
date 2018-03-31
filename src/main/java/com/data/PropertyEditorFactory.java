package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created by deshan on 3/30/2018.
 */
@Component
public class PropertyEditorFactory
{
    @Autowired
    private PersonPropertyEditor personPropertyEditor;

    @Autowired
    private CompanyPropertyEditor companyPropertyEditor;

    public PropertyEditorSupport getPropertyEditor( Class clazz )
    {
        if( clazz == Person.class )
        {
            return personPropertyEditor;
        }
        else if( clazz == Company.class )
        {
            return companyPropertyEditor;
        }

        return null;
    }
}

