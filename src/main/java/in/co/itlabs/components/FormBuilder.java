package in.co.itlabs.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.dnd.DropEffect;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;

import java.util.*;

public class FormBuilder extends VerticalLayout implements DropTarget<FormBuilder> {
    // this is where master components are dropped.
    // dropping a master component creates an instance of draggable field component.

    // a list of component UUID's is maintained here
    // a map (UUID->FieldComponent) is also maintained.

    // the order elements can be changed by drag-and-drop

    private List<FieldComponent> fieldComponents;

    public FormBuilder() {
        addClassName("form-builder");
        setPadding(true);

        setActive(true);
        setDropEffect(DropEffect.COPY);

        fieldComponents = new ArrayList<>();
//        add(new Span("This is FormBuilder"));

        addDropListener(event -> {
//            Notification.show(event.getSource().toString());
            Component droppedComponent = event.getDragSourceComponent().get();
            if (droppedComponent == null) {
                return;
            }

            addComponent(droppedComponent, -1);// add this component at the end of the list
        });
    }

    public void addComponent(Component component, int index) {
        FieldComponent fieldComponent = null;

        if (component instanceof MasterComponent) {
            MasterComponent masterComponent = (MasterComponent) component;
            fieldComponent = new FieldComponent(masterComponent);
            fieldComponent.setWidthFull();
            fieldComponent.addListener(FieldComponent.ComponentDroppedEvent.class, this::handleComponentDroppedEvent);

        } else if (component instanceof FieldComponent) {
            fieldComponent = (FieldComponent) component;
            fieldComponents.remove(fieldComponent);
        }

        if (index < 0) {
            fieldComponents.add(fieldComponent);
        } else {
            fieldComponents.add(index, fieldComponent);
        }

        refresh();
    }

    private void handleComponentDroppedEvent(FieldComponent.ComponentDroppedEvent event) {
        FieldComponent droppedOnFieldComponent = event.getSource();
        Component droppedComponent = event.getDroppedComponent();

        int droppedOnIndex = fieldComponents.indexOf(droppedOnFieldComponent);
//        Notification.show("Dropped on index: " + droppedOnIndex);

        addComponent(droppedComponent, droppedOnIndex);
    }

    private void refresh() {
        removeAll();
        for (FieldComponent fieldComponent : fieldComponents) {
            add(fieldComponent);
        }

        fireEvent(new FormUpdatedEvent(this, fieldComponents));
    }

    public static abstract class FormBuilderEvent extends ComponentEvent<FormBuilder> {

        private List<FieldComponent> fieldComponents;

        protected FormBuilderEvent(FormBuilder source, List<FieldComponent> fieldComponents) {
            super(source, false);
            this.fieldComponents = fieldComponents;
        }

        public List<FieldComponent> getFieldComponents() {
            return fieldComponents;
        }
    }

    public static class FormUpdatedEvent extends FormBuilderEvent {
        FormUpdatedEvent(FormBuilder source, List<FieldComponent> fieldComponents) {
            super(source, fieldComponents);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {

        return getEventBus().addListener(eventType, listener);
    }

}
