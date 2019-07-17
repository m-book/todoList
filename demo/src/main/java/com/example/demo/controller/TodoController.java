package com.example.demo.controller;

import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;

import java.io.IOException;

import com.example.demo.domain.Todo;
import com.example.demo.service.TodoService;
import com.example.demo.repository.TodoRepository;




@Controller
@RequestMapping("/")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public String index(Model model) {
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        boolean flag = (todos.size() != 0);
        model.addAttribute("flag", flag);
        return "index";
    }

    @GetMapping("search")
    public String search(Model model) {
        model.addAttribute("isSearched", false);
        return "search";
    }

    @GetMapping("search_keyword/{keyword}")
    public String searchKeyword(@PathVariable String keyword, Model model) {

        List<Todo> todos = todoRepository.findTodos(keyword);

        int todoCount = todos.size();
        boolean flag = (todoCount != 0);
        model.addAttribute("todos", todos);
        model.addAttribute("flag", flag);
        model.addAttribute("isSearched", true);
        model.addAttribute("todoCount", todoCount);
        return "search::todoList";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Todo todo = todoService.findOne(id);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @ResponseBody
    @RequestMapping(value="create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Todo todo, Model model) throws IOException {
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        todo.setCreated(sdf.format(cl.getTime()));

        todoService.save(todo);
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        boolean flag = (todos.size() != 0);
        model.addAttribute("flag", flag);
        return "index::todoList";
    }

    @RequestMapping(value="todoList", method=RequestMethod.GET)
    public String create(Model model) throws IOException {
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        boolean flag = (todos.size() != 0);
        model.addAttribute("flag", flag);
        return "index::todoList";
    }

    @ResponseBody
    @RequestMapping(value="{id}/update", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable Long id, @RequestBody Todo updateParam) throws IOException {
        Todo todo = todoService.findOne(id);
        todo.setContent(updateParam.getContent());
        todo.setDeadLine(updateParam.getDeadLine());
        todoService.save(todo);

        return "";
    }

    @PutMapping("{id}/finish")
    public String finish(@PathVariable Long id) {
        Todo todo = todoService.findOne(id);
        todo.setIsFinish(true);
        todoService.save(todo);
        return "redirect:/";
    }

    @PutMapping("{id}/unfinish")
    public String unfinish(@PathVariable Long id) {
        Todo todo = todoService.findOne(id);
        todo.setIsFinish(false);
        todoService.save(todo);
        return "redirect:/";
    }

    @DeleteMapping("{id}/delete")
    public String destroy(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("isExist/{content}")
    public boolean edit(@PathVariable String content, Model model) {
        List<Todo> todos = todoRepository.findByContent(content);
        boolean flag = (todos.size() != 0);
        return flag;
    }
}