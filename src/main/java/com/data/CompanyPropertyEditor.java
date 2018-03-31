package com.data;

import java.beans.PropertyEditorSupport;

/**
 * Created by deshan on 3/30/2018.
 */
public class CompanyPropertyEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String text) {
        Company company = new Company();
        String[] data=text.split( "-" );
        company.setName(data[0]);
        company.setRegNo(Integer.parseInt( data[1] ));
        setValue(company);
    }
}
