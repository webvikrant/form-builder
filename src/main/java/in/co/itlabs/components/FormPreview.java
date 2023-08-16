package in.co.itlabs.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class FormPreview extends VerticalLayout {
    // this acts as preview fo the designed form

    private Span titleSpan;
    private VerticalLayout contentVLayout;

    public FormPreview() {
        titleSpan = new Span("Form Preview");
        contentVLayout = new VerticalLayout();

        Scroller scroller = new Scroller();
        scroller.setSizeFull();
        scroller.setContent(contentVLayout);

        add(titleSpan, scroller);

    }

    public void setFieldComponents(List<FieldComponent> fieldComponents) {
        contentVLayout.removeAll();

        for (FieldComponent fieldComponent : fieldComponents) {
            Component component = buildComponent(fieldComponent);
            if (component != null) {
                contentVLayout.add(component);
            }
        }
    }

    private Component buildComponent(FieldComponent fieldComponent) {
        Component component = null;
        if (fieldComponent.getMasterComponent().getType() == MasterComponent.Type.TextField) {
            TextField textField = new TextField();
            textField.setWidthFull();
            hydrate(textField);
            component = textField;

        } else if (fieldComponent.getMasterComponent().getType() == MasterComponent.Type.TextArea) {
            TextArea textArea = new TextArea();
            textArea.setWidthFull();
            hydrate(textArea);
            component = textArea;

        } else if (fieldComponent.getMasterComponent().getType() == MasterComponent.Type.Number) {
            IntegerField integerField = new IntegerField();
            integerField.setWidthFull();
            hydrate(integerField);
            component = integerField;

        } else if (fieldComponent.getMasterComponent().getType() == MasterComponent.Type.CheckBox) {
            Checkbox checkbox = new Checkbox();
            hydrate(checkbox);
            component = checkbox;

        } else if (fieldComponent.getMasterComponent().getType() == MasterComponent.Type.Button) {
            Button button = new Button();
            hydrate(button);
            component = button;
        }

        return component;
    }

    private void hydrate(Button button) {
        button.setText("Button does not have a Label property");
    }

    private void hydrate(Checkbox checkbox) {
        checkbox.setLabel("Label for Checkbox");
    }

    private void hydrate(IntegerField integerField) {
        integerField.setLabel("Label for Integer Field");
    }

    private void hydrate(TextField textField) {
        textField.setLabel("This is label");
    }

    private void hydrate(TextArea textArea) {
        textArea.setLabel("Label is this.");
    }
}
