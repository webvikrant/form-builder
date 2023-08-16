package in.co.itlabs;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import in.co.itlabs.components.FormBuilder;
import in.co.itlabs.components.FormPreview;
import in.co.itlabs.components.MasterPalette;

@Route(value = "")
public class MainView extends VerticalLayout {

    private MasterPalette masterPalette;
    private FormBuilder formBuilder;
    private FormPreview formPreview;

    public MainView() {
        masterPalette = new MasterPalette();
        setupMasterPalette();

        formBuilder = new FormBuilder();
        setupFormBuilder();

        formPreview = new FormPreview();
        setupFormPreview();

        HorizontalLayout rootHLayout = new HorizontalLayout();
        rootHLayout.setWidthFull();
        rootHLayout.setSizeFull();

        Scroller scroller = new Scroller();
        scroller.setWidth("600px");
        scroller.setHeightFull();
        scroller.setContent(formBuilder);

        rootHLayout.add(masterPalette, scroller, formPreview);
        rootHLayout.expand(formPreview);

        add(rootHLayout);
        setSizeFull();
    }

    private void setupMasterPalette() {
        masterPalette.setWidth("300px");
    }

    private void setupFormBuilder() {
        formBuilder.setWidthFull();
        formBuilder.setMinHeight("600px");
        formBuilder.addListener(FormBuilder.FormUpdatedEvent.class, this::handleFormUpdatedEvent);
    }

    private void handleFormUpdatedEvent(FormBuilder.FormUpdatedEvent event) {
        formPreview.setFieldComponents(event.getFieldComponents());
    }

    private void setupFormPreview() {
        formPreview.setWidth("600px");
    }

}
