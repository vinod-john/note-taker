package com.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.TodoDao;
import com.entities.Todo;

@Controller
public class HomeController {

	@Autowired
	TodoDao tododao;
	
	@RequestMapping("/home")
	public String home(Model m) {
		String str="home";
		m.addAttribute("page",str);
         this.tododao.getAll();		
		m.addAttribute("todos");
		return"home";
}
	@RequestMapping("/add")
	public String addtodo(Model m) {
		
		Todo t=new Todo();
		m.addAttribute("page","add");
		m.addAttribute("todo",t);
		return"home";
	}
	@RequestMapping(value="/saveTodo",method = RequestMethod.POST)
	public String savetodo(@ModelAttribute("todo") Todo t,Model m) {
		
		t.setDate(new Date());
		///get the todo from context
		this.tododao.save(t);
		m.addAttribute("msg","SuccessFully Added");
		return"home";
	}
}
