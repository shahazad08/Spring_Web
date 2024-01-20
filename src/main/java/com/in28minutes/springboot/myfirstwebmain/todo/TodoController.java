package com.in28minutes.springboot.myfirstwebmain.todo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
@Controller
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
        public String listAlltodos(ModelMap model) {

        List<Todo> todos = todoService.findByUsername("in28minutes");
        model.addAttribute("todos", todos);

            return "listTodos";
        }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodoPage() {
        return "todos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, Todo todo) {
        String username = (String)model.get("name");
        todoService.addTodo(username, todo.getDescription(),
                LocalDate.now().plusYears(1), false);
        return "redirect:list-todos";
    }
    }

