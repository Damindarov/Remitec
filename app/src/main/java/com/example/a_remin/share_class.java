package com.example.a_remin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class share_class {
    protected PropertyChangeSupport propertyChangeSupport;
    private String text;

    public share_class () {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    public void setText(String text) {
        String oldText = this.text;
        this.text = text;
        propertyChangeSupport.firePropertyChange("MyTextProperty",oldText, text);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public String getText() {
        return this.text;
    }

}
class MyTextListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("MyTextProperty")) {
            System.out.println(event.getNewValue().toString());
        }
    }
}
