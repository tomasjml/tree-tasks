package com.treetasks.application.views.tasks;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.treetasks.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "tasks", layout = MainView.class)
@PageTitle("Tasks")
@CssImport("./styles/views/tasks/tasks-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class TasksView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public TasksView() {
        setId("tasks-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

}
