package mybootapp.web;

import java.beans.PropertyEditorSupport;

import mybootapp.model.Party;

class EditorParty extends PropertyEditorSupport {

    @Override
    public String getAsText() {
    	
        Object o = this.getValue();
        if (o instanceof Party) {
            Party p = (Party) o;
            return p.getPartyName();
        }
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Party p = new Party(text);
            super.setValue(p);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error dans Party :(");
        }
    }

}
