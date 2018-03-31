package com.data;

import java.beans.PropertyEditorSupport;

/**
 * Created by deshan on 3/30/2018.
 */
public class PersonPropertyEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String text) {
        Person person = new Person();
        String[] data=text.split( "-" );
        person.setName(data[0]);
        person.setAge(Integer.parseInt( data[1] ));
        setValue(person);
    }
}
