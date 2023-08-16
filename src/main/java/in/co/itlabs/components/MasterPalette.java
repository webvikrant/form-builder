package in.co.itlabs.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;

public class MasterPalette extends VerticalLayout {
    // this holds a list of master components
    public MasterPalette() {
        MasterComponent textField = new MasterComponent(MasterComponent.Type.TextField, VaadinIcon.TERMINAL);
        MasterComponent textArea = new MasterComponent(MasterComponent.Type.TextArea, VaadinIcon.TEXT_INPUT);
        MasterComponent numberField = new MasterComponent(MasterComponent.Type.Number, VaadinIcon.TEXT_INPUT);
        MasterComponent checkbox = new MasterComponent(MasterComponent.Type.CheckBox, VaadinIcon.CHECK_SQUARE);
        MasterComponent button = new MasterComponent(MasterComponent.Type.Button, VaadinIcon.BUTTON);

        add(textField, textArea, numberField, checkbox, button);
    }

}
