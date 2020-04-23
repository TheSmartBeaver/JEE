package mybootapp.web;

import java.beans.PropertyEditorSupport;

import mybootapp.model.Party;
import mybootapp.model.ProductCode;

class EditorParty extends PropertyEditorSupport {

    @Override
    public String getAsText() {
    	System.err.println("PARTY get As TEXT");
        Object o = this.getValue();
        if (o instanceof ProductCode) {
            Party p = (Party) o;
            return p.getPartyName();
        }
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
        	System.err.println("PARTY set As TEXT");
            Party p = new Party(text);
            super.setValue(p);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error dans Party :(");
        }
    }

}
