package in.co.itlabs.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.icon.VaadinIcon;
import in.co.itlabs.entities.Attribute;

import java.util.ArrayList;
import java.util.List;

public class MasterComponent extends Button implements DragSource<MasterComponent> {
    public enum Type {
        TextField, TextArea, Number, Password, CheckBox, Select, Radio, Button;
    }

    private Type type;

    private List<Attribute> attributes;

    public MasterComponent(Type type, VaadinIcon vaadinIcon) {
        this.type = type;
        attributes = new ArrayList<>();

        setWidth("200px");
        setIcon(vaadinIcon.create());

        addClassName("master-component");
        addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        setDraggable(true);
//        addDragEndListener(event -> {
//            Notification.show("DragEnded..." + event.getComponent().getType().name());
//        });

        switch (type) {
            case TextField -> {
                this.setText("TextField");
                populateAttributes(type);
                break;
            }
            case TextArea -> {
                this.setText("TextArea");
                break;
            }
            case Number -> {
                this.setText("Number");
                break;
            }
            case Password -> {
                this.setText("Password");
                break;
            }
            case CheckBox -> {
                this.setText("Checkbox");
                break;
            }
            case Select -> {
                this.setText("Select");
                break;
            }
            case Radio -> {
                this.setText("Radio");
                break;
            }
            case Button -> {
                this.setText("Button");
                break;
            }

            default -> {
                this.setText("");
                break;
            }
        }
    }

    private void populateAttributes(Type type) {
        if (type == Type.TextField) {
            Attribute label = new Attribute();
            attributes.add(label);

            label.setName(Attribute.Name.LABEL);
            label.setClazz(String.class);
            label.setSingleValued(true);

            Attribute placeHolder = new Attribute();
            attributes.add(placeHolder);

            placeHolder.setName(Attribute.Name.PLACEHOLDER);
            placeHolder.setClazz(String.class);
            placeHolder.setSingleValued(true);


        } else if (type == Type.TextArea) {
            Attribute label = new Attribute();
            attributes.add(label);

            label.setName(Attribute.Name.LABEL);
            label.setClazz(String.class);
            label.setSingleValued(true);

            Attribute placeHolder = new Attribute();
            attributes.add(placeHolder);

            placeHolder.setName(Attribute.Name.PLACEHOLDER);
            placeHolder.setClazz(String.class);
            placeHolder.setSingleValued(true);

        } else if (type==Type.Number){
            Attribute label = new Attribute();
            attributes.add(label);

            label.setName(Attribute.Name.LABEL);
            label.setClazz(Integer.class);
            label.setSingleValued(true);

            Attribute placeHolder = new Attribute();
            attributes.add(placeHolder);

            placeHolder.setName(Attribute.Name.PLACEHOLDER);
            placeHolder.setClazz(String.class);
            placeHolder.setSingleValued(true);

        }
    }

    public Type getType() {
        return type;
    }

}
