package in.co.itlabs.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropEffect;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

public class FieldComponent extends VerticalLayout implements DropTarget<FieldComponent>, DragSource<FieldComponent> {

    // this component servers as a housing for a concrete Vaadin Form components
    // clicking on this component should actually open a Properties editor popup
    // which concrete Vaadin component to house? that depends on the "Type" provided

    private MasterComponent masterComponent;
    private Component component;

    public FieldComponent(MasterComponent masterComponent) {
        this.masterComponent = masterComponent;

        addClassName("field-component");

        setDraggable(true);
//        addDragEndListener(event -> {
//            Notification.show("DragEnded..." + event.getComponent().toString());
//        });

        setActive(true);
        setDropEffect(DropEffect.COPY);

        addDropListener(event -> {
//            Notification.show(event.getSource().toString());
            Component droppedComponent = event.getDragSourceComponent().get();
            if (droppedComponent == null) {
                return;
            }

            fireEvent(new ComponentDroppedEvent(this, droppedComponent));

        });

        switch (masterComponent.getType()) {
            case TextField -> {
                TextField textField = new TextField("TextField");
                textField.setWidthFull();
                component = textField;
                break;
            }

            case TextArea -> {
                TextArea textArea = new TextArea("TextArea");
                textArea.setWidthFull();
                component = textArea;
                break;
            }

            case Number -> {
                IntegerField integerField = new IntegerField("NumberField");
                integerField.setWidthFull();
                component = integerField;
                break;
            }

            case  CheckBox -> {
                Checkbox checkbox = new Checkbox("Checkbox");
                component = checkbox;
                break;
            }

            case  Button -> {
                Button button = new Button("Button");
                component = button;
                break;
            }

            default -> {
            }
        }

        add(component);
    }

    public MasterComponent getMasterComponent() {
        return masterComponent;
    }

    public static abstract class FieldComponentEvent extends ComponentEvent<FieldComponent> {

        private Component droppedComponent;

        protected FieldComponentEvent(FieldComponent source, Component droppedComponent) {
            super(source, false);
            this.droppedComponent = droppedComponent;
        }

        public Component getDroppedComponent() {
            return droppedComponent;
        }
    }

    public static class ComponentDroppedEvent extends FieldComponentEvent {
        ComponentDroppedEvent(FieldComponent source, Component droppedComponent) {
            super(source, droppedComponent);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {

        return getEventBus().addListener(eventType, listener);
    }

}
