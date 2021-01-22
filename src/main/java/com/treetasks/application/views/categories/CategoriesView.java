package com.treetasks.application.views.categories;

import java.util.Optional;

import com.treetasks.application.data.entity.Category;
import com.treetasks.application.data.service.CategoryService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;
import com.treetasks.application.views.main.MainView;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;

@Route(value = "categories", layout = MainView.class)
@PageTitle("Categories")
@CssImport("./styles/views/categories/categories-view.css")
public class CategoriesView extends Div {

    private Grid<Category> grid = new Grid<>(Category.class, false);

    private TextField name;
    private TextField description;
    private DatePicker dateOfCreation;
    private Checkbox active;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Category> binder;

    private Category category;

    public CategoriesView(@Autowired CategoryService categoryService) {
        setId("categories-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("description").setAutoWidth(true);
        grid.addColumn("dateOfCreation").setAutoWidth(true);
        TemplateRenderer<Category> activeRenderer = TemplateRenderer.<Category>of(
                "<iron-icon hidden='[[!item.active]]' icon='vaadin:check' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: var(--lumo-primary-text-color);'></iron-icon><iron-icon hidden='[[item.active]]' icon='vaadin:minus' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: var(--lumo-disabled-text-color);'></iron-icon>")
                .withProperty("active", Category::isActive);
        grid.addColumn(activeRenderer).setHeader("Active").setAutoWidth(true);

        grid.setDataProvider(new CrudServiceDataProvider<>(categoryService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Category> categoryFromBackend = categoryService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (categoryFromBackend.isPresent()) {
                    populateForm(categoryFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Category.class);

        // Bind fields. This where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.category == null) {
                    this.category = new Category();
                }
                binder.writeBean(this.category);

                categoryService.update(this.category);
                clearForm();
                refreshGrid();
                Notification.show("Category details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the category details.");
            }
        });

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        name = new TextField("Name");
        description = new TextField("Description");
        dateOfCreation = new DatePicker("Date Of Creation");
        active = new Checkbox("Active");
        active.getStyle().set("padding-top", "var(--lumo-space-m)");
        Component[] fields = new Component[]{name, description, dateOfCreation, active};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Category value) {
        this.category = value;
        binder.readBean(this.category);

    }
}
